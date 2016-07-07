/*
 * MenuSupport.java
 *
 * Created on January 3, 2007, 3:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.javax.swing;

import java.awt.Event;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Nachman Schachter
 */
public class MenuSupport {
  public static JMenuItem newMenuItem(String label, String command, char mnemonic, int acceloratorKey, ActionListener listener) {
    JMenuItem item = new JMenuItem(label);
    item.addActionListener(listener);
    item.setActionCommand(command);
    if (mnemonic != 0) item.setMnemonic(mnemonic);
    if (acceloratorKey != 0)
      item.setAccelerator(KeyStroke.getKeyStroke(acceloratorKey, Event.CTRL_MASK));
    return item;
  }
}
