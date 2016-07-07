/*
 * SimpleTransferable.java
 *
 * Created on February 27, 2007, 4:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.awt.datatransfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author Nachman Schachter
 */
public class SimpleTransferable implements Transferable {
  
  protected Object data;
  protected DataFlavor flavor;
  
  protected static Hashtable<Class, DataFlavor> hash = new Hashtable<Class, DataFlavor>();
  
  /** Creates a new instance of SimpleTransferable */
  public SimpleTransferable(Object data) {
    this.data = data;
    this.flavor = getDataFlavor(data.getClass());
  }
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] {flavor};
  }
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return (this.flavor == flavor);
  }  
  public Object getTransferData(DataFlavor flavor) 
       throws UnsupportedFlavorException, IOException {
    Object transfer = null;
    if (isDataFlavorSupported(flavor))
      transfer = data;
    return transfer;
  }
  
  public static DataFlavor getDataFlavor(Class dataClass) {
    DataFlavor flavor = hash.get(dataClass);
    if (flavor == null) {
      flavor = new DataFlavor(dataClass, dataClass.getName());
      hash.put(dataClass, flavor);
    }
    return flavor;
  }
}
