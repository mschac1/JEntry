/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jentry.sql;

import jentry.sql.swing.DBFieldControl;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Menachem & Shira
 * TODO This parser has not been correctly implemented
 */
public class ControlSourceParser {
  public static ArrayList<String> getFieldNames(String controlSource) {
    ArrayList<String> fieldNames = new ArrayList<String>();
    fieldNames.add(controlSource);
    return fieldNames;
  }
		
	

    public static Object evaluate(DBFieldControl control) {
        return control.getControlSource();
    }
            /*
		Query q = control.getRecordSourceControl().getQuery();
		String[] names = control.getAllFieldNames();
		int length = names.length;
		if (length == 0)
			return null;
String s = "";
try {
s = q.getField(names[0], "").toString();
for (int i = 1; i < length; i++) {
	s += " " + q.getField(names[i], "");

}
} catch (SQLException e) {}
return s;
}
*/
}
