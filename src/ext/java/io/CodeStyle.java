/*
 * CodeStyle.java
 *
 * Created on January 8, 2007, 8:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ext.java.io;

import java.beans.PropertyEditorManager;
import ext.java.beans.EnumPersistanceDelegate;
import ext.java.lang.EnumEditor;

/**
 *
 * @author Menachem & Shira
 */
public class CodeStyle implements Cloneable {
  public enum BraceStyle {SAME_LINE, NEXT_LINE}
  
  public enum Newline {
    SYSTEM(System.getProperty("line.separator")),
    WINDOWS("\n"), 
    LINUX("\r\n");
    
    private String newlineString;
    Newline(String newlineString) {this.newlineString = newlineString;}
    public String getNewlineString() {return newlineString;}
  }
  
  protected BraceStyle openBraceStyle;
  protected boolean tabsToSpaces;
  protected int tabLength;
  protected Newline newline;
  protected int lineWidth;
  protected int continueIndent;
  
  /**
   * Creates a new instance of CodeStyle
   */
  public CodeStyle() {
    openBraceStyle = BraceStyle.SAME_LINE;
    tabsToSpaces = true;
    tabLength = 3;
    newline = Newline.SYSTEM;
    lineWidth = 80;
    continueIndent = 1;
  }
  public CodeStyle clone() {
    CodeStyle style = new CodeStyle();
    style.continueIndent = continueIndent;
    style.lineWidth = lineWidth;
    style.newline = newline;
    style.openBraceStyle = openBraceStyle;
    style.tabLength = tabLength;
    style.tabsToSpaces = tabsToSpaces;
    return style;
  }
  
  public void setOpenBraceStyle(BraceStyle openBraceStyle) {this.openBraceStyle = openBraceStyle;}
  public BraceStyle getOpenBraceStyle() {return openBraceStyle;}
  public void setTabsToSpaces(boolean tabsToSpaces) {this.tabsToSpaces = tabsToSpaces;}
  public boolean getTabsToSpaces() {return tabsToSpaces;}
  public void setTabLength(int tabLength) {this.tabLength = tabLength;}
  public int getTabLength() {return tabLength;}
  public void setNewline(Newline newline) {this.newline = newline;}
  public Newline getNewline() {return newline;}
  public int getLineWidth() {return lineWidth;}
  public void setLineWidth(int lineWidth) {this.lineWidth = lineWidth;}
  public int getContinueIndent() {return continueIndent;}
  public void setContinueIndent(int continueIndent) {this.continueIndent = continueIndent;}
  
  static {
    // Load Property Editors
    PropertyEditorManager.registerEditor(BraceStyle.class, EnumEditor.class);
    PropertyEditorManager.registerEditor(Newline.class, EnumEditor.class);
    EnumPersistanceDelegate.installFor(Newline.values());
    EnumPersistanceDelegate.installFor(BraceStyle.values());
  }  
}
