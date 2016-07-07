/*
 * Query.java
 *
 * Created on February 18, 2008, 4:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.sql;

import java.sql.*;
import ext.java.util.VetoException;
import ext.java.sql.event.RecordSetChangeEvent;
import ext.java.sql.event.RecordSetChangeListener;
import ext.java.util.EnumerationSupport;
import ext.java.util.ReversableHashtable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


// TODO 1.15 - Closed (non-updatable) Queries - As the record set controls are being set up
// they should only be editable if the underlying query is editable

/**
 * A Query object allows easy access to database information without having
 * to use many complex java.sql features
 * The row system is 1-based, not 0-based
 * @author Menachem Schachter
 */
public class Query {

  // <editor-fold defaultstate="collapsed" desc=" Constructors ">  
  
  /** Creates a new instance of Query */
  public Query(Connection conn, String queryString) {
    setConnection(conn);
    setQueryString(queryString);
  }

  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc=" Public Interface/Key Methods ">  
  
  /** This is the method that gets everything moving. It should be called once all
   * dependencies are set up */
  public void initialize() throws SQLException {
    String query = getFullQuery();
//System.out.println(query);    
//query = "exec " + query;
//query = "{exec qryTest1}";
    if (query == null) throw new RuntimeException("Record Source cannot be null");
    if (getConnection() == null) throw new RuntimeException("Connection cannot be null");
    setResultSet(getConnection().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery());
//    setResultSet(getConnection().prepareCall(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery());
  }

  /** Change the current record (move the underlying ResultSet cursor */
  public boolean setRecordNum(int recordNum) throws SQLException {
    
    // First, update the current record, if neccessary 
    saveRecord();
    
    if (recordNum > 0 && recordNum <= getRecordCount() + 1) {

      this.recordNum = recordNum;
      
      // Reset row data cache
      setRowDataHash(new Hashtable<String, Object>());
      
      // Move result set cursor
        if (recordNum == getRecordCount() + 1) {
            getResultSet().moveToInsertRow();
        }
        else {
            getResultSet().absolute(recordNum);
        }
      // Inform all listeners that the row number has changed
      fireRecordSelected();
      
      return true;
    }
    return false;
  }

  /** Save the current record */
  public void saveRecord() throws SQLException {
    // If the data has not been changed, no update is neccessary
    if (!isDirty())
      return;
 
    // If not at the insert row, update the record. Otherwise insert it
    if (getRecordNum() != getRecordCount() + 1) {
      // If any RecordSetListeners veto the update, do not perform it
      try {
        fireRecordWillBeUpdated();
      } catch (VetoException ex) {return;}

      // Perform the update and notify any listeners
      getResultSet().updateRow();
      fireRecordUpdated();
      setDirty(false);
    }
    else {
      // If any RecordSetListeners veto the insertion, do not perform it
      try {
        fireRecordWillBeInserted();
      } catch (VetoException ex) {return;}

      // Perform the insertion and notify listeners
      getResultSet().insertRow();
      setRecordCount(getRecordCount() + 1);
      fireRecordInserted();
      setDirty(false);
      
      //Reset the row cursor or additional row actions cause a database error (at least in MSAccess)
      getResultSet().absolute(getRecordCount());
    }
  }

  /** Delete the current record */
  public void deleteRecord() throws SQLException {
    if (getRecordNum() == getRecordCount() + 1)
      throw new SQLException("Cannot delete the insert row");
    
    // If any RecordSetListeners veto the delete, do not perform it
    try {
      fireRecordWillBeDeleted();
    } catch (VetoException ex) {return;}

    // Perform the deletion and notify any listeners
    getResultSet().deleteRow();
    setDirty(false);
    setRecordCount(getRecordCount() - 1);
    fireRecordDeleted();
    setRecordNum(getRecordNum());
  }



  /** Get the value of the input field from the database
    * NOTE: Users should avoid calling getField at the insert row, but it will
    *  return null if they do
    */
  public Object getField(String fieldName) throws SQLException {
    // Check if the requested value is already known. The values are cached b\c
    // they cannot be requested twice
    // The use of dbNull is explained at the declaration
    Object val = getRowDataHash().get(fieldName);
    if (val == dbNull) return null;
    if (val != null) return val;

    // If the curser is at the insert row, there is no avaliable data
    if (getRecordNum() == getRecordCount() + 1)
      return null;
    
    // Get the requested value
    int type = getFieldTypeHash().get(fieldName);
    val = TypeConversionSupport.getFieldValue(getResultSet(), fieldName, type);

    // Add the value to the hash
    if (val == null)
      getRowDataHash().put(fieldName, dbNull);
    else
      getRowDataHash().put(fieldName, val);
    
    return val;
  }
  
  /** This is the same us the getField(String fieldName) method, but it allows
    * the user to specify a value to returned if the value in the db is null
    */
  public Object getField(String fieldName, Object ifNull) throws SQLException {
    Object val = getField(fieldName);
    if (val == null)
      val = ifNull;
    return val;
  }

  public Object getField(int columnIndex) throws SQLException {
    return getField(getColumnHash().getKey(columnIndex));
  }

  public Object getField(int columnIndex, Object ifNull) throws SQLException {
    return getField(getColumnHash().getKey(columnIndex), ifNull);
  }
  /** Update a database field value.
    * NOTE the changes are not actually made to the database until the
    * saveRecord method is called
    */
  public void updateField(String fieldName, Object value) throws SQLException {
    int type = getFieldTypeHash().get(fieldName);
    TypeConversionSupport.updateFieldValue(getResultSet(), fieldName, type, value);

    // Update the value in the hash
    getRowDataHash().put(fieldName, (value == null)? dbNull : value);
    
    setDirty(true);
  }
  
  /**
   * This method is called when the record Number changes b/c the new record is
   * not dirty and whenever updateField is called b/c the record is dirtied.
   * If dirty is true but it was previously false then a recordDirty event will be fired
   */
  public void setDirty(boolean dirty) {
    if (!isDirty() && dirty == true)
      fireRecordDirtied();
    this.dirty = dirty;
  }

  /** Requeries the database. The record number is set to 1 */
  public void requery() throws SQLException {
    saveRecord();
    initialize();
  }

  /** Returns all of the database data as a 2-dimensional Object array */

  public Object[][] getData() throws SQLException {
    int columns = EnumerationSupport.getCount(getFieldNames());
    int rows = this.getRecordCount();
    Object[][] data = new Object[rows][columns];
    for (int i = 0; i < rows; i++) {
      setRecordNum(i+1);
      for (int j = 0; j < columns; j++) {
       data[i][j] = getField(j);
      }
    }
    return data;
  }

  public String[] getColumnNames() {
    String[] columnNames = new String[getFieldTypeHash().size()];
    for (int i = 0; i < columnNames.length; i++) {
      columnNames[i] = getColumnHash().getKey(i);

    }
    return columnNames;
  }

  public void close() {
    if (resultSet != null) {
      try {resultSet.close();} catch(SQLException e) {}
    }
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Helper Methods ">  

  /** returns an enumeration of the names of all the Queries fields*/
  public Enumeration<String> getFieldNames() {return getFieldTypeHash().keys();}
  
  /** Returns the java class associated with the given fieldName*/
  public Class getFieldType(String fieldName) {
    int sqlType =  getFieldTypeHash().get(fieldName);
    return TypeConversionSupport.getJavaClass(sqlType);
  }

  public String getFullQuery() {
    String fullQuery = null;
    
    String fromClause = formalizeQuery(getQueryString());
    
    String whereClause = getFilter();
    if (whereClause == null || whereClause.equals(""))
      whereClause = "";
    else
      whereClause = " WHERE " + whereClause;
    
    String sortClause = getSort();
    if (sortClause == null || sortClause.equals(""))
      sortClause = "";
    else
      sortClause = " ORDER BY " + sortClause;
    
    if (whereClause.equals("") && sortClause.equals(""))
      fullQuery = fromClause;
    else {
      // Remove colon
      fromClause = fromClause.substring(0, fromClause.length() - 1);
      fullQuery = "SELECT * FROM (" + fromClause + ")" + whereClause +
        sortClause;
    }
    
    return fullQuery;
  }
  
  /** If the record source is a table name, construct a select query
      If the table name has any spaces in it, add brackets ([]) */
  public static String formalizeQuery(String query) {
    int index = query.indexOf(" ");
    if (index == -1 || !query.substring(0, index).equalsIgnoreCase("select")) {
      if (query.charAt(0) == '[' || index == -1)
        query = "SELECT * FROM " + query + "";
      else
        query = "SELECT * FROM [" + query + "]";
    }
    return query;
  }

  protected void setResultSet(ResultSet resultSet) throws SQLException {
    // Close previous result set
    close();
    
    this.resultSet = resultSet;
    
    // Hash the field types and store them
    initializeFieldTypeHash();

    // Find the number of records by moving to the last record
    resultSet.last();
    setRecordCount(resultSet.getRow());

    // Check whether the recordSet is editable
    try {
      resultSet.moveToInsertRow();
      setEditable(true);
    } catch (SQLException ex) {
      setEditable(false);
    }
    
    resultSet.first();
    fireRecordSetChanged();

    // Position the ResultSet at the first record
    setRecordNum(1);
  }
  
  // Hash the field types and store them  
  protected void initializeFieldTypeHash() throws SQLException {
    ResultSetMetaData rsmd = getResultSet().getMetaData();
    int count = rsmd.getColumnCount();
    Hashtable <String, Integer> hash = new Hashtable<String, Integer>(count);
    ReversableHashtable<String, Integer> rHash = new ReversableHashtable<String, Integer>(count);
    
    for (int i = 0; i < count; i++) {
      hash.put(rsmd.getColumnName(i + 1), rsmd.getColumnType(i + 1));
      rHash.put(rsmd.getColumnName(i + 1), i);
    }
    setFieldTypeHash(hash);
    setColumnHash(rHash);
  }

	@Override
  protected void finalize() {
    close();
  }
  
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc=" Event Registration/Firing Methods ">  
  
  public void addRecordSetChangeListener(RecordSetChangeListener listener) {
    recordSetChangeListenerList.add (listener);
  }
  public void removeRecordSetChangeListener(RecordSetChangeListener listener) {
    recordSetChangeListenerList.remove (listener);
  }

  public void fireRecordSelected() {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordSelected(evt);
    }
  }
  public void fireRecordSetChanged() {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordSetChanged(evt);
    }
  }
  public void fireRecordWillBeInserted() throws VetoException {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordWillBeInserted(evt);
    }
  }
  public void fireRecordInserted() {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordInserted(evt);
    }
  }

  private void fireRecordDirtied() {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordDirtied(evt);
    }
  }

  private void fireRecordWillBeUpdated() throws VetoException {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordWillBeUpdated(evt);
    }
  }

  private void fireRecordUpdated() {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordUpdated(evt);
    }
  }
  private void fireRecordWillBeDeleted() throws VetoException {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordWillBeDeleted(evt);
    }
  }

  private void fireRecordDeleted() {
    RecordSetChangeEvent evt = new RecordSetChangeEvent(this);
    for (RecordSetChangeListener listener : recordSetChangeListenerList) {
      listener.recordDeleted(evt);
    }
  }
// </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Trivial Accessors/Mutators ">  
  public int getRecordNum() {return recordNum;}
  
  protected ResultSet getResultSet() {return resultSet;}
  
  public Connection getConnection() {return conn;}
  public void setConnection(Connection conn) {this.conn = conn;}
  
  public String getQueryString() {return queryString;}
  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }
  
  public int getRecordCount() {return recordCount;}
  protected void setRecordCount(int i) {recordCount = i;}

  public Hashtable<String, Integer> getFieldTypeHash() {return fieldTypeHash;}
  protected void setFieldTypeHash(Hashtable<String, Integer> fieldTypeHash) {this.fieldTypeHash = fieldTypeHash;}

  public ReversableHashtable<String, Integer> getColumnHash() {return columnHash;}
  protected void setColumnHash(ReversableHashtable<String, Integer> columnHash) {this.columnHash = columnHash;}

  public Hashtable<String, Object> getRowDataHash() {return rowDataHash;}
  protected void setRowDataHash(Hashtable<String, Object> rowDataHash) {this.rowDataHash = rowDataHash;}

  public boolean isEditable() {return editable;}
  public void setEditable(boolean editable) {this.editable = editable;}

  public boolean isDirty() {return dirty;}
 
  public String getFilter() {return filter;}
  public void setFilter(String filter) throws SQLException {
    this.filter = filter;
    requery();
  }
  
  public String getSort() {return sort;}
  public void setSort(String sort) throws SQLException {
    this.sort = sort;
    requery();
  }
  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc=" Variable Declaration ">  
  private ResultSet resultSet;
  
  // The current position of the result set cursor
  private int recordNum = 0;
  
  // The total number of records in the set
  private int recordCount = 0;
  
  private boolean dirty = false;
  
  private Connection conn;

  private String queryString;
  
  private String filter = null;
  
  private String sort = null;

  private boolean editable = false;

  private transient ArrayList<RecordSetChangeListener> recordSetChangeListenerList = new ArrayList<RecordSetChangeListener>();
  
  // stores the sql types associated with each field
  private Hashtable <String, Integer> fieldTypeHash;

  // Store the association between the column positions and the column names
  private ReversableHashtable <String, Integer> columnHash;
  
  // caches the values of each field
  private Hashtable<String, Object> rowDataHash = new Hashtable<String, Object>();
  
  // This constant is added to the rowDataHash whenever the value is null
  // becuase Hashtables do not allow null values 
  protected final static Object dbNull = new Object();


  
  // </editor-fold>
}