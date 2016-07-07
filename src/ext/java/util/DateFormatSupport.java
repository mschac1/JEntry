/*
 * DateFormatSupport.java
 *
 * Created on February 29, 2008, 10:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nachman Schachter
 */
public class DateFormatSupport {
  
  /** Creates a new instance of DateFormatSupport */
  private DateFormatSupport() {
  }
  
  public static final DateFormat STD_24HR_TIME = new SimpleDateFormat("HH:mm");
  public static final DateFormat STD_SHORT_TIME = new SimpleDateFormat("h:mm");
  public static final DateFormat STD_MEDIUM_TIME = new SimpleDateFormat("h:mm a");
  public static final DateFormat STD_LONG_TIME = new SimpleDateFormat("h:mm:ss a");
  public static final DateFormat STD_MILLI_TIME = new SimpleDateFormat("HH:mm:ss.SSS");
  public static final DateFormat STD_SHORT_NUMERIC_DATE = new SimpleDateFormat("M/d/yy");
  public static final DateFormat STD_MEDIUM_NUMERIC_DATE = new SimpleDateFormat("M/dd/yyyy");
  public static final DateFormat STD_LONG_NUMERIC_DATE = new SimpleDateFormat("MM/dd/yyyy");
  public static final DateFormat STD_SHORT_DATE = new SimpleDateFormat("MMM. dd, yyyy");
  public static final DateFormat STD_MEDIUM_DATE = new SimpleDateFormat("MMMM dd, yyyy");
  public static final DateFormat STD_LONG_DATE = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
  public static final DateFormat STD_SHORT_DATE_TIME = new SimpleDateFormat("M/d/yy HH:mm:ss");
  public static final DateFormat STD_MEDIUM_DATE_TIME = new SimpleDateFormat("M/dd/yyyy HH:mm:ss");
  public static final DateFormat STD_LONG_DATE_TIME = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
  public static final DateFormat STD_MILLI_DATE_TIME = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
}
