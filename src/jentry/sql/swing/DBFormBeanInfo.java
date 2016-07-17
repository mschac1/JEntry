/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jentry.sql.swing;

import java.beans.*;

/**
 *
 * @author Menachem & Shira
 */
public class DBFormBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( jentry.sql.swing.DBForm.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor

    beanDescriptor.setValue("containerDelegate", "getContentPane");



        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_accessibleContext = 0;
    private static final int PROPERTY_actionMap = 1;
    private static final int PROPERTY_alignmentX = 2;
    private static final int PROPERTY_alignmentY = 3;
    private static final int PROPERTY_ancestorListeners = 4;
    private static final int PROPERTY_autoscrolls = 5;
    private static final int PROPERTY_background = 6;
    private static final int PROPERTY_backgroundSet = 7;
    private static final int PROPERTY_baselineResizeBehavior = 8;
    private static final int PROPERTY_border = 9;
    private static final int PROPERTY_bounds = 10;
    private static final int PROPERTY_colorModel = 11;
    private static final int PROPERTY_component = 12;
    private static final int PROPERTY_componentCount = 13;
    private static final int PROPERTY_componentListeners = 14;
    private static final int PROPERTY_componentOrientation = 15;
    private static final int PROPERTY_componentPopupMenu = 16;
    private static final int PROPERTY_components = 17;
    private static final int PROPERTY_connection = 18;
    private static final int PROPERTY_containerListeners = 19;
    private static final int PROPERTY_contentPane = 20;
    private static final int PROPERTY_cursor = 21;
    private static final int PROPERTY_cursorSet = 22;
    private static final int PROPERTY_debugGraphicsOptions = 23;
    private static final int PROPERTY_deletesAllowed = 24;
    private static final int PROPERTY_dirty = 25;
    private static final int PROPERTY_displayable = 26;
    private static final int PROPERTY_doubleBuffered = 27;
    private static final int PROPERTY_dropTarget = 28;
    private static final int PROPERTY_enabled = 29;
    private static final int PROPERTY_filter = 30;
    private static final int PROPERTY_focusable = 31;
    private static final int PROPERTY_focusCycleRoot = 32;
    private static final int PROPERTY_focusCycleRootAncestor = 33;
    private static final int PROPERTY_focusListeners = 34;
    private static final int PROPERTY_focusOwner = 35;
    private static final int PROPERTY_focusTraversable = 36;
    private static final int PROPERTY_focusTraversalKeys = 37;
    private static final int PROPERTY_focusTraversalKeysEnabled = 38;
    private static final int PROPERTY_focusTraversalPolicy = 39;
    private static final int PROPERTY_focusTraversalPolicyProvider = 40;
    private static final int PROPERTY_focusTraversalPolicySet = 41;
    private static final int PROPERTY_font = 42;
    private static final int PROPERTY_fontSet = 43;
    private static final int PROPERTY_foreground = 44;
    private static final int PROPERTY_foregroundSet = 45;
    private static final int PROPERTY_formDirty = 46;
    private static final int PROPERTY_graphics = 47;
    private static final int PROPERTY_graphicsConfiguration = 48;
    private static final int PROPERTY_height = 49;
    private static final int PROPERTY_hierarchyBoundsListeners = 50;
    private static final int PROPERTY_hierarchyListeners = 51;
    private static final int PROPERTY_ignoreRepaint = 52;
    private static final int PROPERTY_inheritsPopupMenu = 53;
    private static final int PROPERTY_inputContext = 54;
    private static final int PROPERTY_inputMap = 55;
    private static final int PROPERTY_inputMethodListeners = 56;
    private static final int PROPERTY_inputMethodRequests = 57;
    private static final int PROPERTY_inputVerifier = 58;
    private static final int PROPERTY_insertsAllowed = 59;
    private static final int PROPERTY_insets = 60;
    private static final int PROPERTY_keyListeners = 61;
    private static final int PROPERTY_layout = 62;
    private static final int PROPERTY_lightweight = 63;
    private static final int PROPERTY_locale = 64;
    private static final int PROPERTY_location = 65;
    private static final int PROPERTY_locationOnScreen = 66;
    private static final int PROPERTY_managingFocus = 67;
    private static final int PROPERTY_maximumSize = 68;
    private static final int PROPERTY_maximumSizeSet = 69;
    private static final int PROPERTY_minimumSize = 70;
    private static final int PROPERTY_minimumSizeSet = 71;
    private static final int PROPERTY_mouseListeners = 72;
    private static final int PROPERTY_mouseMotionListeners = 73;
    private static final int PROPERTY_mousePosition = 74;
    private static final int PROPERTY_mouseWheelListeners = 75;
    private static final int PROPERTY_name = 76;
    private static final int PROPERTY_navigationPane = 77;
    private static final int PROPERTY_navigationPaneVisible = 78;
    private static final int PROPERTY_nextFocusableComponent = 79;
    private static final int PROPERTY_opaque = 80;
    private static final int PROPERTY_optimizedDrawingEnabled = 81;
    private static final int PROPERTY_paintingForPrint = 82;
    private static final int PROPERTY_paintingTile = 83;
    private static final int PROPERTY_parent = 84;
    private static final int PROPERTY_parentChildLink = 85;
    private static final int PROPERTY_peer = 86;
    private static final int PROPERTY_preferredSize = 87;
    private static final int PROPERTY_preferredSizeSet = 88;
    private static final int PROPERTY_propertyChangeListeners = 89;
    private static final int PROPERTY_recordCount = 90;
    private static final int PROPERTY_recordNum = 91;
    private static final int PROPERTY_recordSource = 92;
    private static final int PROPERTY_registeredKeyStrokes = 93;
    private static final int PROPERTY_requestFocusEnabled = 94;
    private static final int PROPERTY_rootPane = 95;
    private static final int PROPERTY_showing = 96;
    private static final int PROPERTY_size = 97;
    private static final int PROPERTY_sort = 98;
    private static final int PROPERTY_source = 99;
    private static final int PROPERTY_toolkit = 100;
    private static final int PROPERTY_toolTipText = 101;
    private static final int PROPERTY_topLevelAncestor = 102;
    private static final int PROPERTY_transferHandler = 103;
    private static final int PROPERTY_treeLock = 104;
    private static final int PROPERTY_UI = 105;
    private static final int PROPERTY_UIClassID = 106;
    private static final int PROPERTY_valid = 107;
    private static final int PROPERTY_validateRoot = 108;
    private static final int PROPERTY_verifyInputWhenFocusTarget = 109;
    private static final int PROPERTY_vetoableChangeListeners = 110;
    private static final int PROPERTY_visible = 111;
    private static final int PROPERTY_visibleRect = 112;
    private static final int PROPERTY_width = 113;
    private static final int PROPERTY_x = 114;
    private static final int PROPERTY_y = 115;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[116];
    
        try {
            properties[PROPERTY_accessibleContext] = new PropertyDescriptor ( "accessibleContext", jentry.sql.swing.DBForm.class, "getAccessibleContext", null ); // NOI18N
            properties[PROPERTY_actionMap] = new PropertyDescriptor ( "actionMap", jentry.sql.swing.DBForm.class, "getActionMap", "setActionMap" ); // NOI18N
            properties[PROPERTY_alignmentX] = new PropertyDescriptor ( "alignmentX", jentry.sql.swing.DBForm.class, "getAlignmentX", "setAlignmentX" ); // NOI18N
            properties[PROPERTY_alignmentY] = new PropertyDescriptor ( "alignmentY", jentry.sql.swing.DBForm.class, "getAlignmentY", "setAlignmentY" ); // NOI18N
            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor ( "ancestorListeners", jentry.sql.swing.DBForm.class, "getAncestorListeners", null ); // NOI18N
            properties[PROPERTY_autoscrolls] = new PropertyDescriptor ( "autoscrolls", jentry.sql.swing.DBForm.class, "getAutoscrolls", "setAutoscrolls" ); // NOI18N
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", jentry.sql.swing.DBForm.class, "getBackground", "setBackground" ); // NOI18N
            properties[PROPERTY_backgroundSet] = new PropertyDescriptor ( "backgroundSet", jentry.sql.swing.DBForm.class, "isBackgroundSet", null ); // NOI18N
            properties[PROPERTY_baselineResizeBehavior] = new PropertyDescriptor ( "baselineResizeBehavior", jentry.sql.swing.DBForm.class, "getBaselineResizeBehavior", null ); // NOI18N
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", jentry.sql.swing.DBForm.class, "getBorder", "setBorder" ); // NOI18N
            properties[PROPERTY_bounds] = new PropertyDescriptor ( "bounds", jentry.sql.swing.DBForm.class, "getBounds", "setBounds" ); // NOI18N
            properties[PROPERTY_colorModel] = new PropertyDescriptor ( "colorModel", jentry.sql.swing.DBForm.class, "getColorModel", null ); // NOI18N
            properties[PROPERTY_component] = new IndexedPropertyDescriptor ( "component", jentry.sql.swing.DBForm.class, null, null, "getComponent", null ); // NOI18N
            properties[PROPERTY_componentCount] = new PropertyDescriptor ( "componentCount", jentry.sql.swing.DBForm.class, "getComponentCount", null ); // NOI18N
            properties[PROPERTY_componentListeners] = new PropertyDescriptor ( "componentListeners", jentry.sql.swing.DBForm.class, "getComponentListeners", null ); // NOI18N
            properties[PROPERTY_componentOrientation] = new PropertyDescriptor ( "componentOrientation", jentry.sql.swing.DBForm.class, "getComponentOrientation", "setComponentOrientation" ); // NOI18N
            properties[PROPERTY_componentPopupMenu] = new PropertyDescriptor ( "componentPopupMenu", jentry.sql.swing.DBForm.class, "getComponentPopupMenu", "setComponentPopupMenu" ); // NOI18N
            properties[PROPERTY_components] = new PropertyDescriptor ( "components", jentry.sql.swing.DBForm.class, "getComponents", null ); // NOI18N
            properties[PROPERTY_connection] = new PropertyDescriptor ( "connection", jentry.sql.swing.DBForm.class, "getConnection", "setConnection" ); // NOI18N
            properties[PROPERTY_containerListeners] = new PropertyDescriptor ( "containerListeners", jentry.sql.swing.DBForm.class, "getContainerListeners", null ); // NOI18N
            properties[PROPERTY_contentPane] = new PropertyDescriptor ( "contentPane", jentry.sql.swing.DBForm.class, "getContentPane", "setContentPane" ); // NOI18N
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", jentry.sql.swing.DBForm.class, "getCursor", "setCursor" ); // NOI18N
            properties[PROPERTY_cursorSet] = new PropertyDescriptor ( "cursorSet", jentry.sql.swing.DBForm.class, "isCursorSet", null ); // NOI18N
            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor ( "debugGraphicsOptions", jentry.sql.swing.DBForm.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions" ); // NOI18N
            properties[PROPERTY_deletesAllowed] = new PropertyDescriptor ( "deletesAllowed", jentry.sql.swing.DBForm.class, "getDeletesAllowed", "setDeletesAllowed" ); // NOI18N
            properties[PROPERTY_dirty] = new PropertyDescriptor ( "dirty", jentry.sql.swing.DBForm.class, "isDirty", null ); // NOI18N
            properties[PROPERTY_displayable] = new PropertyDescriptor ( "displayable", jentry.sql.swing.DBForm.class, "isDisplayable", null ); // NOI18N
            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor ( "doubleBuffered", jentry.sql.swing.DBForm.class, "isDoubleBuffered", "setDoubleBuffered" ); // NOI18N
            properties[PROPERTY_dropTarget] = new PropertyDescriptor ( "dropTarget", jentry.sql.swing.DBForm.class, "getDropTarget", "setDropTarget" ); // NOI18N
            properties[PROPERTY_enabled] = new PropertyDescriptor ( "enabled", jentry.sql.swing.DBForm.class, "isEnabled", "setEnabled" ); // NOI18N
            properties[PROPERTY_filter] = new PropertyDescriptor ( "filter", jentry.sql.swing.DBForm.class, "getFilter", "setFilter" ); // NOI18N
            properties[PROPERTY_focusable] = new PropertyDescriptor ( "focusable", jentry.sql.swing.DBForm.class, "isFocusable", "setFocusable" ); // NOI18N
            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor ( "focusCycleRoot", jentry.sql.swing.DBForm.class, "isFocusCycleRoot", "setFocusCycleRoot" ); // NOI18N
            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor ( "focusCycleRootAncestor", jentry.sql.swing.DBForm.class, "getFocusCycleRootAncestor", null ); // NOI18N
            properties[PROPERTY_focusListeners] = new PropertyDescriptor ( "focusListeners", jentry.sql.swing.DBForm.class, "getFocusListeners", null ); // NOI18N
            properties[PROPERTY_focusOwner] = new PropertyDescriptor ( "focusOwner", jentry.sql.swing.DBForm.class, "isFocusOwner", null ); // NOI18N
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", jentry.sql.swing.DBForm.class, "isFocusTraversable", null ); // NOI18N
            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor ( "focusTraversalKeys", jentry.sql.swing.DBForm.class, null, null, null, "setFocusTraversalKeys" ); // NOI18N
            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor ( "focusTraversalKeysEnabled", jentry.sql.swing.DBForm.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor ( "focusTraversalPolicy", jentry.sql.swing.DBForm.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicyProvider] = new PropertyDescriptor ( "focusTraversalPolicyProvider", jentry.sql.swing.DBForm.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor ( "focusTraversalPolicySet", jentry.sql.swing.DBForm.class, "isFocusTraversalPolicySet", null ); // NOI18N
            properties[PROPERTY_font] = new PropertyDescriptor ( "font", jentry.sql.swing.DBForm.class, "getFont", "setFont" ); // NOI18N
            properties[PROPERTY_fontSet] = new PropertyDescriptor ( "fontSet", jentry.sql.swing.DBForm.class, "isFontSet", null ); // NOI18N
            properties[PROPERTY_foreground] = new PropertyDescriptor ( "foreground", jentry.sql.swing.DBForm.class, "getForeground", "setForeground" ); // NOI18N
            properties[PROPERTY_foregroundSet] = new PropertyDescriptor ( "foregroundSet", jentry.sql.swing.DBForm.class, "isForegroundSet", null ); // NOI18N
            properties[PROPERTY_formDirty] = new PropertyDescriptor ( "formDirty", jentry.sql.swing.DBForm.class, "isFormDirty", null ); // NOI18N
            properties[PROPERTY_graphics] = new PropertyDescriptor ( "graphics", jentry.sql.swing.DBForm.class, "getGraphics", null ); // NOI18N
            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor ( "graphicsConfiguration", jentry.sql.swing.DBForm.class, "getGraphicsConfiguration", null ); // NOI18N
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", jentry.sql.swing.DBForm.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor ( "hierarchyBoundsListeners", jentry.sql.swing.DBForm.class, "getHierarchyBoundsListeners", null ); // NOI18N
            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor ( "hierarchyListeners", jentry.sql.swing.DBForm.class, "getHierarchyListeners", null ); // NOI18N
            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor ( "ignoreRepaint", jentry.sql.swing.DBForm.class, "getIgnoreRepaint", "setIgnoreRepaint" ); // NOI18N
            properties[PROPERTY_inheritsPopupMenu] = new PropertyDescriptor ( "inheritsPopupMenu", jentry.sql.swing.DBForm.class, "getInheritsPopupMenu", "setInheritsPopupMenu" ); // NOI18N
            properties[PROPERTY_inputContext] = new PropertyDescriptor ( "inputContext", jentry.sql.swing.DBForm.class, "getInputContext", null ); // NOI18N
            properties[PROPERTY_inputMap] = new PropertyDescriptor ( "inputMap", jentry.sql.swing.DBForm.class, "getInputMap", null ); // NOI18N
            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor ( "inputMethodListeners", jentry.sql.swing.DBForm.class, "getInputMethodListeners", null ); // NOI18N
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", jentry.sql.swing.DBForm.class, "getInputMethodRequests", null ); // NOI18N
            properties[PROPERTY_inputVerifier] = new PropertyDescriptor ( "inputVerifier", jentry.sql.swing.DBForm.class, "getInputVerifier", "setInputVerifier" ); // NOI18N
            properties[PROPERTY_insertsAllowed] = new PropertyDescriptor ( "insertsAllowed", jentry.sql.swing.DBForm.class, "getInsertsAllowed", "setInsertsAllowed" ); // NOI18N
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", jentry.sql.swing.DBForm.class, "getInsets", null ); // NOI18N
            properties[PROPERTY_keyListeners] = new PropertyDescriptor ( "keyListeners", jentry.sql.swing.DBForm.class, "getKeyListeners", null ); // NOI18N
            properties[PROPERTY_layout] = new PropertyDescriptor ( "layout", jentry.sql.swing.DBForm.class, "getLayout", "setLayout" ); // NOI18N
            properties[PROPERTY_lightweight] = new PropertyDescriptor ( "lightweight", jentry.sql.swing.DBForm.class, "isLightweight", null ); // NOI18N
            properties[PROPERTY_locale] = new PropertyDescriptor ( "locale", jentry.sql.swing.DBForm.class, "getLocale", "setLocale" ); // NOI18N
            properties[PROPERTY_location] = new PropertyDescriptor ( "location", jentry.sql.swing.DBForm.class, "getLocation", "setLocation" ); // NOI18N
            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor ( "locationOnScreen", jentry.sql.swing.DBForm.class, "getLocationOnScreen", null ); // NOI18N
            properties[PROPERTY_managingFocus] = new PropertyDescriptor ( "managingFocus", jentry.sql.swing.DBForm.class, "isManagingFocus", null ); // NOI18N
            properties[PROPERTY_maximumSize] = new PropertyDescriptor ( "maximumSize", jentry.sql.swing.DBForm.class, "getMaximumSize", "setMaximumSize" ); // NOI18N
            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor ( "maximumSizeSet", jentry.sql.swing.DBForm.class, "isMaximumSizeSet", null ); // NOI18N
            properties[PROPERTY_minimumSize] = new PropertyDescriptor ( "minimumSize", jentry.sql.swing.DBForm.class, "getMinimumSize", "setMinimumSize" ); // NOI18N
            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor ( "minimumSizeSet", jentry.sql.swing.DBForm.class, "isMinimumSizeSet", null ); // NOI18N
            properties[PROPERTY_mouseListeners] = new PropertyDescriptor ( "mouseListeners", jentry.sql.swing.DBForm.class, "getMouseListeners", null ); // NOI18N
            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor ( "mouseMotionListeners", jentry.sql.swing.DBForm.class, "getMouseMotionListeners", null ); // NOI18N
            properties[PROPERTY_mousePosition] = new PropertyDescriptor ( "mousePosition", jentry.sql.swing.DBForm.class, "getMousePosition", null ); // NOI18N
            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor ( "mouseWheelListeners", jentry.sql.swing.DBForm.class, "getMouseWheelListeners", null ); // NOI18N
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", jentry.sql.swing.DBForm.class, "getName", "setName" ); // NOI18N
            properties[PROPERTY_navigationPane] = new PropertyDescriptor ( "navigationPane", jentry.sql.swing.DBForm.class, "getNavigationPane", "setNavigationPane" ); // NOI18N
            properties[PROPERTY_navigationPaneVisible] = new PropertyDescriptor ( "navigationPaneVisible", jentry.sql.swing.DBForm.class, "isNavigationPaneVisible", "setNavigationPaneVisible" ); // NOI18N
            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor ( "nextFocusableComponent", jentry.sql.swing.DBForm.class, "getNextFocusableComponent", "setNextFocusableComponent" ); // NOI18N
            properties[PROPERTY_opaque] = new PropertyDescriptor ( "opaque", jentry.sql.swing.DBForm.class, "isOpaque", "setOpaque" ); // NOI18N
            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor ( "optimizedDrawingEnabled", jentry.sql.swing.DBForm.class, "isOptimizedDrawingEnabled", null ); // NOI18N
            properties[PROPERTY_paintingForPrint] = new PropertyDescriptor ( "paintingForPrint", jentry.sql.swing.DBForm.class, "isPaintingForPrint", null ); // NOI18N
            properties[PROPERTY_paintingTile] = new PropertyDescriptor ( "paintingTile", jentry.sql.swing.DBForm.class, "isPaintingTile", null ); // NOI18N
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", jentry.sql.swing.DBForm.class, "getParent", null ); // NOI18N
            properties[PROPERTY_parentChildLink] = new PropertyDescriptor ( "parentChildLink", jentry.sql.swing.DBForm.class, "getParentChildLink", "setParentChildLink" ); // NOI18N
            properties[PROPERTY_peer] = new PropertyDescriptor ( "peer", jentry.sql.swing.DBForm.class, "getPeer", null ); // NOI18N
            properties[PROPERTY_preferredSize] = new PropertyDescriptor ( "preferredSize", jentry.sql.swing.DBForm.class, "getPreferredSize", "setPreferredSize" ); // NOI18N
            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor ( "preferredSizeSet", jentry.sql.swing.DBForm.class, "isPreferredSizeSet", null ); // NOI18N
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", jentry.sql.swing.DBForm.class, "getPropertyChangeListeners", null ); // NOI18N
            properties[PROPERTY_recordCount] = new PropertyDescriptor ( "recordCount", jentry.sql.swing.DBForm.class, "getRecordCount", null ); // NOI18N
            properties[PROPERTY_recordNum] = new PropertyDescriptor ( "recordNum", jentry.sql.swing.DBForm.class, "getRecordNum", null ); // NOI18N
            properties[PROPERTY_recordSource] = new PropertyDescriptor ( "recordSource", jentry.sql.swing.DBForm.class, "getRecordSource", "setRecordSource" ); // NOI18N
            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor ( "registeredKeyStrokes", jentry.sql.swing.DBForm.class, "getRegisteredKeyStrokes", null ); // NOI18N
            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor ( "requestFocusEnabled", jentry.sql.swing.DBForm.class, "isRequestFocusEnabled", "setRequestFocusEnabled" ); // NOI18N
            properties[PROPERTY_rootPane] = new PropertyDescriptor ( "rootPane", jentry.sql.swing.DBForm.class, "getRootPane", null ); // NOI18N
            properties[PROPERTY_showing] = new PropertyDescriptor ( "showing", jentry.sql.swing.DBForm.class, "isShowing", null ); // NOI18N
            properties[PROPERTY_size] = new PropertyDescriptor ( "size", jentry.sql.swing.DBForm.class, "getSize", "setSize" ); // NOI18N
            properties[PROPERTY_sort] = new PropertyDescriptor ( "sort", jentry.sql.swing.DBForm.class, "getSort", "setSort" ); // NOI18N
            properties[PROPERTY_source] = new PropertyDescriptor ( "source", jentry.sql.swing.DBForm.class, "getSource", null ); // NOI18N
            properties[PROPERTY_toolkit] = new PropertyDescriptor ( "toolkit", jentry.sql.swing.DBForm.class, "getToolkit", null ); // NOI18N
            properties[PROPERTY_toolTipText] = new PropertyDescriptor ( "toolTipText", jentry.sql.swing.DBForm.class, "getToolTipText", "setToolTipText" ); // NOI18N
            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor ( "topLevelAncestor", jentry.sql.swing.DBForm.class, "getTopLevelAncestor", null ); // NOI18N
            properties[PROPERTY_transferHandler] = new PropertyDescriptor ( "transferHandler", jentry.sql.swing.DBForm.class, "getTransferHandler", "setTransferHandler" ); // NOI18N
            properties[PROPERTY_treeLock] = new PropertyDescriptor ( "treeLock", jentry.sql.swing.DBForm.class, "getTreeLock", null ); // NOI18N
            properties[PROPERTY_UI] = new PropertyDescriptor ( "UI", jentry.sql.swing.DBForm.class, "getUI", "setUI" ); // NOI18N
            properties[PROPERTY_UIClassID] = new PropertyDescriptor ( "UIClassID", jentry.sql.swing.DBForm.class, "getUIClassID", null ); // NOI18N
            properties[PROPERTY_valid] = new PropertyDescriptor ( "valid", jentry.sql.swing.DBForm.class, "isValid", null ); // NOI18N
            properties[PROPERTY_validateRoot] = new PropertyDescriptor ( "validateRoot", jentry.sql.swing.DBForm.class, "isValidateRoot", null ); // NOI18N
            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor ( "verifyInputWhenFocusTarget", jentry.sql.swing.DBForm.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget" ); // NOI18N
            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor ( "vetoableChangeListeners", jentry.sql.swing.DBForm.class, "getVetoableChangeListeners", null ); // NOI18N
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", jentry.sql.swing.DBForm.class, "isVisible", "setVisible" ); // NOI18N
            properties[PROPERTY_visibleRect] = new PropertyDescriptor ( "visibleRect", jentry.sql.swing.DBForm.class, "getVisibleRect", null ); // NOI18N
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", jentry.sql.swing.DBForm.class, "getWidth", null ); // NOI18N
            properties[PROPERTY_x] = new PropertyDescriptor ( "x", jentry.sql.swing.DBForm.class, "getX", null ); // NOI18N
            properties[PROPERTY_y] = new PropertyDescriptor ( "y", jentry.sql.swing.DBForm.class, "getY", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_ancestorListener = 0;
    private static final int EVENT_componentListener = 1;
    private static final int EVENT_containerListener = 2;
    private static final int EVENT_focusListener = 3;
    private static final int EVENT_hierarchyBoundsListener = 4;
    private static final int EVENT_hierarchyListener = 5;
    private static final int EVENT_inputMethodListener = 6;
    private static final int EVENT_keyListener = 7;
    private static final int EVENT_mouseListener = 8;
    private static final int EVENT_mouseMotionListener = 9;
    private static final int EVENT_mouseWheelListener = 10;
    private static final int EVENT_propertyChangeListener = 11;
    private static final int EVENT_vetoableChangeListener = 12;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[13];
    
        try {
            eventSets[EVENT_ancestorListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[] {"ancestorAdded", "ancestorRemoved", "ancestorMoved"}, "addAncestorListener", "removeAncestorListener" ); // NOI18N
            eventSets[EVENT_componentListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "componentListener", java.awt.event.ComponentListener.class, new String[] {"componentResized", "componentMoved", "componentShown", "componentHidden"}, "addComponentListener", "removeComponentListener" ); // NOI18N
            eventSets[EVENT_containerListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "containerListener", java.awt.event.ContainerListener.class, new String[] {"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener" ); // NOI18N
            eventSets[EVENT_focusListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "focusListener", java.awt.event.FocusListener.class, new String[] {"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener" ); // NOI18N
            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[] {"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener" ); // NOI18N
            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[] {"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener" ); // NOI18N
            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[] {"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener" ); // NOI18N
            eventSets[EVENT_keyListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "keyListener", java.awt.event.KeyListener.class, new String[] {"keyTyped", "keyPressed", "keyReleased"}, "addKeyListener", "removeKeyListener" ); // NOI18N
            eventSets[EVENT_mouseListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "mouseListener", java.awt.event.MouseListener.class, new String[] {"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"}, "addMouseListener", "removeMouseListener" ); // NOI18N
            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[] {"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener" ); // NOI18N
            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[] {"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener" ); // NOI18N
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( jentry.sql.swing.DBForm.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_action0 = 0;
    private static final int METHOD_add1 = 1;
    private static final int METHOD_add2 = 2;
    private static final int METHOD_add3 = 3;
    private static final int METHOD_add4 = 4;
    private static final int METHOD_add5 = 5;
    private static final int METHOD_add6 = 6;
    private static final int METHOD_addImpl7 = 7;
    private static final int METHOD_addNotify8 = 8;
    private static final int METHOD_addPropertyChangeListener9 = 9;
    private static final int METHOD_ancestorAdded10 = 10;
    private static final int METHOD_ancestorMoved11 = 11;
    private static final int METHOD_ancestorRemoved12 = 12;
    private static final int METHOD_applyComponentOrientation13 = 13;
    private static final int METHOD_areFocusTraversalKeysSet14 = 14;
    private static final int METHOD_bounds15 = 15;
    private static final int METHOD_checkImage16 = 16;
    private static final int METHOD_checkImage17 = 17;
    private static final int METHOD_computeVisibleRect18 = 18;
    private static final int METHOD_contains19 = 19;
    private static final int METHOD_contains20 = 20;
    private static final int METHOD_countComponents21 = 21;
    private static final int METHOD_createImage22 = 22;
    private static final int METHOD_createImage23 = 23;
    private static final int METHOD_createToolTip24 = 24;
    private static final int METHOD_createVolatileImage25 = 25;
    private static final int METHOD_createVolatileImage26 = 26;
    private static final int METHOD_dbFieldUpdated27 = 27;
    private static final int METHOD_deleteRecord28 = 28;
    private static final int METHOD_deliverEvent29 = 29;
    private static final int METHOD_disable30 = 30;
    private static final int METHOD_dispatchEvent31 = 31;
    private static final int METHOD_doLayout32 = 32;
    private static final int METHOD_enable33 = 33;
    private static final int METHOD_enable34 = 34;
    private static final int METHOD_enableInputMethods35 = 35;
    private static final int METHOD_findComponentAt36 = 36;
    private static final int METHOD_findComponentAt37 = 37;
    private static final int METHOD_firePropertyChange38 = 38;
    private static final int METHOD_firePropertyChange39 = 39;
    private static final int METHOD_firePropertyChange40 = 40;
    private static final int METHOD_firePropertyChange41 = 41;
    private static final int METHOD_firePropertyChange42 = 42;
    private static final int METHOD_firePropertyChange43 = 43;
    private static final int METHOD_firePropertyChange44 = 44;
    private static final int METHOD_firePropertyChange45 = 45;
    private static final int METHOD_firstRecord46 = 46;
    private static final int METHOD_getActionForKeyStroke47 = 47;
    private static final int METHOD_getBaseline48 = 48;
    private static final int METHOD_getBounds49 = 49;
    private static final int METHOD_getClientProperty50 = 50;
    private static final int METHOD_getComponentAt51 = 51;
    private static final int METHOD_getComponentAt52 = 52;
    private static final int METHOD_getComponentZOrder53 = 53;
    private static final int METHOD_getConditionForKeyStroke54 = 54;
    private static final int METHOD_getDefaultLocale55 = 55;
    private static final int METHOD_getField56 = 56;
    private static final int METHOD_getField57 = 57;
    private static final int METHOD_getFieldType58 = 58;
    private static final int METHOD_getFocusTraversalKeys59 = 59;
    private static final int METHOD_getFontMetrics60 = 60;
    private static final int METHOD_getInsets61 = 61;
    private static final int METHOD_getListeners62 = 62;
    private static final int METHOD_getLocation63 = 63;
    private static final int METHOD_getMousePosition64 = 64;
    private static final int METHOD_getPopupLocation65 = 65;
    private static final int METHOD_getPropertyChangeListeners66 = 66;
    private static final int METHOD_getSize67 = 67;
    private static final int METHOD_getToolTipLocation68 = 68;
    private static final int METHOD_getToolTipText69 = 69;
    private static final int METHOD_gotFocus70 = 70;
    private static final int METHOD_grabFocus71 = 71;
    private static final int METHOD_handleEvent72 = 72;
    private static final int METHOD_hasFocus73 = 73;
    private static final int METHOD_hide74 = 74;
    private static final int METHOD_imageUpdate75 = 75;
    private static final int METHOD_insets76 = 76;
    private static final int METHOD_inside77 = 77;
    private static final int METHOD_invalidate78 = 78;
    private static final int METHOD_isAncestorOf79 = 79;
    private static final int METHOD_isFocusCycleRoot80 = 80;
    private static final int METHOD_isLightweightComponent81 = 81;
    private static final int METHOD_keyDown82 = 82;
    private static final int METHOD_keyUp83 = 83;
    private static final int METHOD_lastRecord84 = 84;
    private static final int METHOD_layout85 = 85;
    private static final int METHOD_list86 = 86;
    private static final int METHOD_list87 = 87;
    private static final int METHOD_list88 = 88;
    private static final int METHOD_list89 = 89;
    private static final int METHOD_list90 = 90;
    private static final int METHOD_locate91 = 91;
    private static final int METHOD_location92 = 92;
    private static final int METHOD_lostFocus93 = 93;
    private static final int METHOD_minimumSize94 = 94;
    private static final int METHOD_mouseDown95 = 95;
    private static final int METHOD_mouseDrag96 = 96;
    private static final int METHOD_mouseEnter97 = 97;
    private static final int METHOD_mouseExit98 = 98;
    private static final int METHOD_mouseMove99 = 99;
    private static final int METHOD_mouseUp100 = 100;
    private static final int METHOD_move101 = 101;
    private static final int METHOD_newRecord102 = 102;
    private static final int METHOD_nextFocus103 = 103;
    private static final int METHOD_nextRecord104 = 104;
    private static final int METHOD_paint105 = 105;
    private static final int METHOD_paintAll106 = 106;
    private static final int METHOD_paintComponents107 = 107;
    private static final int METHOD_paintImmediately108 = 108;
    private static final int METHOD_paintImmediately109 = 109;
    private static final int METHOD_postEvent110 = 110;
    private static final int METHOD_preferredSize111 = 111;
    private static final int METHOD_prepareImage112 = 112;
    private static final int METHOD_prepareImage113 = 113;
    private static final int METHOD_previousRecord114 = 114;
    private static final int METHOD_print115 = 115;
    private static final int METHOD_printAll116 = 116;
    private static final int METHOD_printComponents117 = 117;
    private static final int METHOD_putClientProperty118 = 118;
    private static final int METHOD_recordDeleted119 = 119;
    private static final int METHOD_recordDirtied120 = 120;
    private static final int METHOD_recordInserted121 = 121;
    private static final int METHOD_recordSelected122 = 122;
    private static final int METHOD_recordSetChanged123 = 123;
    private static final int METHOD_recordUpdated124 = 124;
    private static final int METHOD_recordWillBeDeleted125 = 125;
    private static final int METHOD_recordWillBeInserted126 = 126;
    private static final int METHOD_recordWillBeUpdated127 = 127;
    private static final int METHOD_registerKeyboardAction128 = 128;
    private static final int METHOD_registerKeyboardAction129 = 129;
    private static final int METHOD_remove130 = 130;
    private static final int METHOD_remove131 = 131;
    private static final int METHOD_remove132 = 132;
    private static final int METHOD_removeAll133 = 133;
    private static final int METHOD_removeNotify134 = 134;
    private static final int METHOD_removePropertyChangeListener135 = 135;
    private static final int METHOD_repaint136 = 136;
    private static final int METHOD_repaint137 = 137;
    private static final int METHOD_repaint138 = 138;
    private static final int METHOD_repaint139 = 139;
    private static final int METHOD_repaint140 = 140;
    private static final int METHOD_requery141 = 141;
    private static final int METHOD_requestDefaultFocus142 = 142;
    private static final int METHOD_requestFocus143 = 143;
    private static final int METHOD_requestFocus144 = 144;
    private static final int METHOD_requestFocusInWindow145 = 145;
    private static final int METHOD_resetKeyboardActions146 = 146;
    private static final int METHOD_reshape147 = 147;
    private static final int METHOD_resize148 = 148;
    private static final int METHOD_resize149 = 149;
    private static final int METHOD_revalidate150 = 150;
    private static final int METHOD_saveRecord151 = 151;
    private static final int METHOD_scrollRectToVisible152 = 152;
    private static final int METHOD_setBounds153 = 153;
    private static final int METHOD_setComponentZOrder154 = 154;
    private static final int METHOD_setDefaultLocale155 = 155;
    private static final int METHOD_setRecordNum156 = 156;
    private static final int METHOD_show157 = 157;
    private static final int METHOD_show158 = 158;
    private static final int METHOD_showDatabaseError159 = 159;
    private static final int METHOD_size160 = 160;
    private static final int METHOD_toString161 = 161;
    private static final int METHOD_transferFocus162 = 162;
    private static final int METHOD_transferFocusBackward163 = 163;
    private static final int METHOD_transferFocusDownCycle164 = 164;
    private static final int METHOD_transferFocusUpCycle165 = 165;
    private static final int METHOD_unregisterKeyboardAction166 = 166;
    private static final int METHOD_update167 = 167;
    private static final int METHOD_updateField168 = 168;
    private static final int METHOD_updateUI169 = 169;
    private static final int METHOD_validate170 = 170;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[171];
    
        try {
            methods[METHOD_action0] = new MethodDescriptor(java.awt.Component.class.getMethod("action", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_action0].setDisplayName ( "" );
            methods[METHOD_add1] = new MethodDescriptor(java.awt.Component.class.getMethod("add", new Class[] {java.awt.PopupMenu.class})); // NOI18N
            methods[METHOD_add1].setDisplayName ( "" );
            methods[METHOD_add2] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_add2].setDisplayName ( "" );
            methods[METHOD_add3] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.lang.String.class, java.awt.Component.class})); // NOI18N
            methods[METHOD_add3].setDisplayName ( "" );
            methods[METHOD_add4] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_add4].setDisplayName ( "" );
            methods[METHOD_add5] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_add5].setDisplayName ( "" );
            methods[METHOD_add6] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class, int.class})); // NOI18N
            methods[METHOD_add6].setDisplayName ( "" );
            methods[METHOD_addImpl7] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("addImpl", new Class[] {java.awt.Component.class, java.lang.Object.class, int.class})); // NOI18N
            methods[METHOD_addImpl7].setDisplayName ( "" );
            methods[METHOD_addNotify8] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("addNotify", new Class[] {})); // NOI18N
            methods[METHOD_addNotify8].setDisplayName ( "" );
            methods[METHOD_addPropertyChangeListener9] = new MethodDescriptor(java.awt.Container.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_addPropertyChangeListener9].setDisplayName ( "" );
            methods[METHOD_ancestorAdded10] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("ancestorAdded", new Class[] {javax.swing.event.AncestorEvent.class})); // NOI18N
            methods[METHOD_ancestorAdded10].setDisplayName ( "" );
            methods[METHOD_ancestorMoved11] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("ancestorMoved", new Class[] {javax.swing.event.AncestorEvent.class})); // NOI18N
            methods[METHOD_ancestorMoved11].setDisplayName ( "" );
            methods[METHOD_ancestorRemoved12] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("ancestorRemoved", new Class[] {javax.swing.event.AncestorEvent.class})); // NOI18N
            methods[METHOD_ancestorRemoved12].setDisplayName ( "" );
            methods[METHOD_applyComponentOrientation13] = new MethodDescriptor(java.awt.Container.class.getMethod("applyComponentOrientation", new Class[] {java.awt.ComponentOrientation.class})); // NOI18N
            methods[METHOD_applyComponentOrientation13].setDisplayName ( "" );
            methods[METHOD_areFocusTraversalKeysSet14] = new MethodDescriptor(java.awt.Container.class.getMethod("areFocusTraversalKeysSet", new Class[] {int.class})); // NOI18N
            methods[METHOD_areFocusTraversalKeysSet14].setDisplayName ( "" );
            methods[METHOD_bounds15] = new MethodDescriptor(java.awt.Component.class.getMethod("bounds", new Class[] {})); // NOI18N
            methods[METHOD_bounds15].setDisplayName ( "" );
            methods[METHOD_checkImage16] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage16].setDisplayName ( "" );
            methods[METHOD_checkImage17] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage17].setDisplayName ( "" );
            methods[METHOD_computeVisibleRect18] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("computeVisibleRect", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_computeVisibleRect18].setDisplayName ( "" );
            methods[METHOD_contains19] = new MethodDescriptor(java.awt.Component.class.getMethod("contains", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_contains19].setDisplayName ( "" );
            methods[METHOD_contains20] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("contains", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_contains20].setDisplayName ( "" );
            methods[METHOD_countComponents21] = new MethodDescriptor(java.awt.Container.class.getMethod("countComponents", new Class[] {})); // NOI18N
            methods[METHOD_countComponents21].setDisplayName ( "" );
            methods[METHOD_createImage22] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {java.awt.image.ImageProducer.class})); // NOI18N
            methods[METHOD_createImage22].setDisplayName ( "" );
            methods[METHOD_createImage23] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createImage23].setDisplayName ( "" );
            methods[METHOD_createToolTip24] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("createToolTip", new Class[] {})); // NOI18N
            methods[METHOD_createToolTip24].setDisplayName ( "" );
            methods[METHOD_createVolatileImage25] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createVolatileImage25].setDisplayName ( "" );
            methods[METHOD_createVolatileImage26] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class, java.awt.ImageCapabilities.class})); // NOI18N
            methods[METHOD_createVolatileImage26].setDisplayName ( "" );
            methods[METHOD_dbFieldUpdated27] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("dbFieldUpdated", new Class[] {jentry.sql.events.DBFieldUpdateEvent.class})); // NOI18N
            methods[METHOD_dbFieldUpdated27].setDisplayName ( "" );
            methods[METHOD_deleteRecord28] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("deleteRecord", new Class[] {})); // NOI18N
            methods[METHOD_deleteRecord28].setDisplayName ( "" );
            methods[METHOD_deliverEvent29] = new MethodDescriptor(java.awt.Container.class.getMethod("deliverEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_deliverEvent29].setDisplayName ( "" );
            methods[METHOD_disable30] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("disable", new Class[] {})); // NOI18N
            methods[METHOD_disable30].setDisplayName ( "" );
            methods[METHOD_dispatchEvent31] = new MethodDescriptor(java.awt.Component.class.getMethod("dispatchEvent", new Class[] {java.awt.AWTEvent.class})); // NOI18N
            methods[METHOD_dispatchEvent31].setDisplayName ( "" );
            methods[METHOD_doLayout32] = new MethodDescriptor(java.awt.Container.class.getMethod("doLayout", new Class[] {})); // NOI18N
            methods[METHOD_doLayout32].setDisplayName ( "" );
            methods[METHOD_enable33] = new MethodDescriptor(java.awt.Component.class.getMethod("enable", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enable33].setDisplayName ( "" );
            methods[METHOD_enable34] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("enable", new Class[] {})); // NOI18N
            methods[METHOD_enable34].setDisplayName ( "" );
            methods[METHOD_enableInputMethods35] = new MethodDescriptor(java.awt.Component.class.getMethod("enableInputMethods", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enableInputMethods35].setDisplayName ( "" );
            methods[METHOD_findComponentAt36] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_findComponentAt36].setDisplayName ( "" );
            methods[METHOD_findComponentAt37] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_findComponentAt37].setDisplayName ( "" );
            methods[METHOD_firePropertyChange38] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, byte.class, byte.class})); // NOI18N
            methods[METHOD_firePropertyChange38].setDisplayName ( "" );
            methods[METHOD_firePropertyChange39] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, short.class, short.class})); // NOI18N
            methods[METHOD_firePropertyChange39].setDisplayName ( "" );
            methods[METHOD_firePropertyChange40] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, long.class, long.class})); // NOI18N
            methods[METHOD_firePropertyChange40].setDisplayName ( "" );
            methods[METHOD_firePropertyChange41] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, float.class, float.class})); // NOI18N
            methods[METHOD_firePropertyChange41].setDisplayName ( "" );
            methods[METHOD_firePropertyChange42] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, double.class, double.class})); // NOI18N
            methods[METHOD_firePropertyChange42].setDisplayName ( "" );
            methods[METHOD_firePropertyChange43] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, boolean.class, boolean.class})); // NOI18N
            methods[METHOD_firePropertyChange43].setDisplayName ( "" );
            methods[METHOD_firePropertyChange44] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, int.class, int.class})); // NOI18N
            methods[METHOD_firePropertyChange44].setDisplayName ( "" );
            methods[METHOD_firePropertyChange45] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, char.class, char.class})); // NOI18N
            methods[METHOD_firePropertyChange45].setDisplayName ( "" );
            methods[METHOD_firstRecord46] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("firstRecord", new Class[] {})); // NOI18N
            methods[METHOD_firstRecord46].setDisplayName ( "" );
            methods[METHOD_getActionForKeyStroke47] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getActionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getActionForKeyStroke47].setDisplayName ( "" );
            methods[METHOD_getBaseline48] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBaseline", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getBaseline48].setDisplayName ( "" );
            methods[METHOD_getBounds49] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBounds", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_getBounds49].setDisplayName ( "" );
            methods[METHOD_getClientProperty50] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getClientProperty", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_getClientProperty50].setDisplayName ( "" );
            methods[METHOD_getComponentAt51] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getComponentAt51].setDisplayName ( "" );
            methods[METHOD_getComponentAt52] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getComponentAt52].setDisplayName ( "" );
            methods[METHOD_getComponentZOrder53] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentZOrder", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_getComponentZOrder53].setDisplayName ( "" );
            methods[METHOD_getConditionForKeyStroke54] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getConditionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getConditionForKeyStroke54].setDisplayName ( "" );
            methods[METHOD_getDefaultLocale55] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getDefaultLocale", new Class[] {})); // NOI18N
            methods[METHOD_getDefaultLocale55].setDisplayName ( "" );
            methods[METHOD_getField56] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("getField", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getField56].setDisplayName ( "" );
            methods[METHOD_getField57] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("getField", new Class[] {java.lang.String.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_getField57].setDisplayName ( "" );
            methods[METHOD_getFieldType58] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("getFieldType", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getFieldType58].setDisplayName ( "" );
            methods[METHOD_getFocusTraversalKeys59] = new MethodDescriptor(java.awt.Container.class.getMethod("getFocusTraversalKeys", new Class[] {int.class})); // NOI18N
            methods[METHOD_getFocusTraversalKeys59].setDisplayName ( "" );
            methods[METHOD_getFontMetrics60] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getFontMetrics", new Class[] {java.awt.Font.class})); // NOI18N
            methods[METHOD_getFontMetrics60].setDisplayName ( "" );
            methods[METHOD_getInsets61] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getInsets", new Class[] {java.awt.Insets.class})); // NOI18N
            methods[METHOD_getInsets61].setDisplayName ( "" );
            methods[METHOD_getListeners62] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getListeners", new Class[] {java.lang.Class.class})); // NOI18N
            methods[METHOD_getListeners62].setDisplayName ( "" );
            methods[METHOD_getLocation63] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getLocation", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getLocation63].setDisplayName ( "" );
            methods[METHOD_getMousePosition64] = new MethodDescriptor(java.awt.Container.class.getMethod("getMousePosition", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_getMousePosition64].setDisplayName ( "" );
            methods[METHOD_getPopupLocation65] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getPopupLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getPopupLocation65].setDisplayName ( "" );
            methods[METHOD_getPropertyChangeListeners66] = new MethodDescriptor(java.awt.Component.class.getMethod("getPropertyChangeListeners", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getPropertyChangeListeners66].setDisplayName ( "" );
            methods[METHOD_getSize67] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getSize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_getSize67].setDisplayName ( "" );
            methods[METHOD_getToolTipLocation68] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipLocation68].setDisplayName ( "" );
            methods[METHOD_getToolTipText69] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipText", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipText69].setDisplayName ( "" );
            methods[METHOD_gotFocus70] = new MethodDescriptor(java.awt.Component.class.getMethod("gotFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_gotFocus70].setDisplayName ( "" );
            methods[METHOD_grabFocus71] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("grabFocus", new Class[] {})); // NOI18N
            methods[METHOD_grabFocus71].setDisplayName ( "" );
            methods[METHOD_handleEvent72] = new MethodDescriptor(java.awt.Component.class.getMethod("handleEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_handleEvent72].setDisplayName ( "" );
            methods[METHOD_hasFocus73] = new MethodDescriptor(java.awt.Component.class.getMethod("hasFocus", new Class[] {})); // NOI18N
            methods[METHOD_hasFocus73].setDisplayName ( "" );
            methods[METHOD_hide74] = new MethodDescriptor(java.awt.Component.class.getMethod("hide", new Class[] {})); // NOI18N
            methods[METHOD_hide74].setDisplayName ( "" );
            methods[METHOD_imageUpdate75] = new MethodDescriptor(java.awt.Component.class.getMethod("imageUpdate", new Class[] {java.awt.Image.class, int.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_imageUpdate75].setDisplayName ( "" );
            methods[METHOD_insets76] = new MethodDescriptor(java.awt.Container.class.getMethod("insets", new Class[] {})); // NOI18N
            methods[METHOD_insets76].setDisplayName ( "" );
            methods[METHOD_inside77] = new MethodDescriptor(java.awt.Component.class.getMethod("inside", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_inside77].setDisplayName ( "" );
            methods[METHOD_invalidate78] = new MethodDescriptor(java.awt.Container.class.getMethod("invalidate", new Class[] {})); // NOI18N
            methods[METHOD_invalidate78].setDisplayName ( "" );
            methods[METHOD_isAncestorOf79] = new MethodDescriptor(java.awt.Container.class.getMethod("isAncestorOf", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isAncestorOf79].setDisplayName ( "" );
            methods[METHOD_isFocusCycleRoot80] = new MethodDescriptor(java.awt.Container.class.getMethod("isFocusCycleRoot", new Class[] {java.awt.Container.class})); // NOI18N
            methods[METHOD_isFocusCycleRoot80].setDisplayName ( "" );
            methods[METHOD_isLightweightComponent81] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("isLightweightComponent", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isLightweightComponent81].setDisplayName ( "" );
            methods[METHOD_keyDown82] = new MethodDescriptor(java.awt.Component.class.getMethod("keyDown", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyDown82].setDisplayName ( "" );
            methods[METHOD_keyUp83] = new MethodDescriptor(java.awt.Component.class.getMethod("keyUp", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyUp83].setDisplayName ( "" );
            methods[METHOD_lastRecord84] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("lastRecord", new Class[] {})); // NOI18N
            methods[METHOD_lastRecord84].setDisplayName ( "" );
            methods[METHOD_layout85] = new MethodDescriptor(java.awt.Container.class.getMethod("layout", new Class[] {})); // NOI18N
            methods[METHOD_layout85].setDisplayName ( "" );
            methods[METHOD_list86] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {})); // NOI18N
            methods[METHOD_list86].setDisplayName ( "" );
            methods[METHOD_list87] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintStream.class})); // NOI18N
            methods[METHOD_list87].setDisplayName ( "" );
            methods[METHOD_list88] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintWriter.class})); // NOI18N
            methods[METHOD_list88].setDisplayName ( "" );
            methods[METHOD_list89] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintStream.class, int.class})); // NOI18N
            methods[METHOD_list89].setDisplayName ( "" );
            methods[METHOD_list90] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintWriter.class, int.class})); // NOI18N
            methods[METHOD_list90].setDisplayName ( "" );
            methods[METHOD_locate91] = new MethodDescriptor(java.awt.Container.class.getMethod("locate", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_locate91].setDisplayName ( "" );
            methods[METHOD_location92] = new MethodDescriptor(java.awt.Component.class.getMethod("location", new Class[] {})); // NOI18N
            methods[METHOD_location92].setDisplayName ( "" );
            methods[METHOD_lostFocus93] = new MethodDescriptor(java.awt.Component.class.getMethod("lostFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_lostFocus93].setDisplayName ( "" );
            methods[METHOD_minimumSize94] = new MethodDescriptor(java.awt.Container.class.getMethod("minimumSize", new Class[] {})); // NOI18N
            methods[METHOD_minimumSize94].setDisplayName ( "" );
            methods[METHOD_mouseDown95] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDown", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDown95].setDisplayName ( "" );
            methods[METHOD_mouseDrag96] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDrag", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDrag96].setDisplayName ( "" );
            methods[METHOD_mouseEnter97] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseEnter", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseEnter97].setDisplayName ( "" );
            methods[METHOD_mouseExit98] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseExit", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseExit98].setDisplayName ( "" );
            methods[METHOD_mouseMove99] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseMove", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseMove99].setDisplayName ( "" );
            methods[METHOD_mouseUp100] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseUp", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseUp100].setDisplayName ( "" );
            methods[METHOD_move101] = new MethodDescriptor(java.awt.Component.class.getMethod("move", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_move101].setDisplayName ( "" );
            methods[METHOD_newRecord102] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("newRecord", new Class[] {})); // NOI18N
            methods[METHOD_newRecord102].setDisplayName ( "" );
            methods[METHOD_nextFocus103] = new MethodDescriptor(java.awt.Component.class.getMethod("nextFocus", new Class[] {})); // NOI18N
            methods[METHOD_nextFocus103].setDisplayName ( "" );
            methods[METHOD_nextRecord104] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("nextRecord", new Class[] {})); // NOI18N
            methods[METHOD_nextRecord104].setDisplayName ( "" );
            methods[METHOD_paint105] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paint", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paint105].setDisplayName ( "" );
            methods[METHOD_paintAll106] = new MethodDescriptor(java.awt.Component.class.getMethod("paintAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintAll106].setDisplayName ( "" );
            methods[METHOD_paintComponents107] = new MethodDescriptor(java.awt.Container.class.getMethod("paintComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintComponents107].setDisplayName ( "" );
            methods[METHOD_paintImmediately108] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_paintImmediately108].setDisplayName ( "" );
            methods[METHOD_paintImmediately109] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_paintImmediately109].setDisplayName ( "" );
            methods[METHOD_postEvent110] = new MethodDescriptor(java.awt.Component.class.getMethod("postEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_postEvent110].setDisplayName ( "" );
            methods[METHOD_preferredSize111] = new MethodDescriptor(java.awt.Container.class.getMethod("preferredSize", new Class[] {})); // NOI18N
            methods[METHOD_preferredSize111].setDisplayName ( "" );
            methods[METHOD_prepareImage112] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage112].setDisplayName ( "" );
            methods[METHOD_prepareImage113] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage113].setDisplayName ( "" );
            methods[METHOD_previousRecord114] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("previousRecord", new Class[] {})); // NOI18N
            methods[METHOD_previousRecord114].setDisplayName ( "" );
            methods[METHOD_print115] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("print", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_print115].setDisplayName ( "" );
            methods[METHOD_printAll116] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("printAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printAll116].setDisplayName ( "" );
            methods[METHOD_printComponents117] = new MethodDescriptor(java.awt.Container.class.getMethod("printComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printComponents117].setDisplayName ( "" );
            methods[METHOD_putClientProperty118] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("putClientProperty", new Class[] {java.lang.Object.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_putClientProperty118].setDisplayName ( "" );
            methods[METHOD_recordDeleted119] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordDeleted", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordDeleted119].setDisplayName ( "" );
            methods[METHOD_recordDirtied120] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordDirtied", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordDirtied120].setDisplayName ( "" );
            methods[METHOD_recordInserted121] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordInserted", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordInserted121].setDisplayName ( "" );
            methods[METHOD_recordSelected122] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordSelected", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordSelected122].setDisplayName ( "" );
            methods[METHOD_recordSetChanged123] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordSetChanged", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordSetChanged123].setDisplayName ( "" );
            methods[METHOD_recordUpdated124] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordUpdated", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordUpdated124].setDisplayName ( "" );
            methods[METHOD_recordWillBeDeleted125] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordWillBeDeleted", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordWillBeDeleted125].setDisplayName ( "" );
            methods[METHOD_recordWillBeInserted126] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordWillBeInserted", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordWillBeInserted126].setDisplayName ( "" );
            methods[METHOD_recordWillBeUpdated127] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("recordWillBeUpdated", new Class[] {jentry.sql.events.RecordSetChangeEvent.class})); // NOI18N
            methods[METHOD_recordWillBeUpdated127].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction128] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction128].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction129] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction129].setDisplayName ( "" );
            methods[METHOD_remove130] = new MethodDescriptor(java.awt.Component.class.getMethod("remove", new Class[] {java.awt.MenuComponent.class})); // NOI18N
            methods[METHOD_remove130].setDisplayName ( "" );
            methods[METHOD_remove131] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {int.class})); // NOI18N
            methods[METHOD_remove131].setDisplayName ( "" );
            methods[METHOD_remove132] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_remove132].setDisplayName ( "" );
            methods[METHOD_removeAll133] = new MethodDescriptor(java.awt.Container.class.getMethod("removeAll", new Class[] {})); // NOI18N
            methods[METHOD_removeAll133].setDisplayName ( "" );
            methods[METHOD_removeNotify134] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("removeNotify", new Class[] {})); // NOI18N
            methods[METHOD_removeNotify134].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener135] = new MethodDescriptor(java.awt.Component.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_removePropertyChangeListener135].setDisplayName ( "" );
            methods[METHOD_repaint136] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {})); // NOI18N
            methods[METHOD_repaint136].setDisplayName ( "" );
            methods[METHOD_repaint137] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {long.class})); // NOI18N
            methods[METHOD_repaint137].setDisplayName ( "" );
            methods[METHOD_repaint138] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint138].setDisplayName ( "" );
            methods[METHOD_repaint139] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {long.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint139].setDisplayName ( "" );
            methods[METHOD_repaint140] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_repaint140].setDisplayName ( "" );
            methods[METHOD_requery141] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("requery", new Class[] {})); // NOI18N
            methods[METHOD_requery141].setDisplayName ( "" );
            methods[METHOD_requestDefaultFocus142] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestDefaultFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestDefaultFocus142].setDisplayName ( "" );
            methods[METHOD_requestFocus143] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestFocus143].setDisplayName ( "" );
            methods[METHOD_requestFocus144] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_requestFocus144].setDisplayName ( "" );
            methods[METHOD_requestFocusInWindow145] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocusInWindow", new Class[] {})); // NOI18N
            methods[METHOD_requestFocusInWindow145].setDisplayName ( "" );
            methods[METHOD_resetKeyboardActions146] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("resetKeyboardActions", new Class[] {})); // NOI18N
            methods[METHOD_resetKeyboardActions146].setDisplayName ( "" );
            methods[METHOD_reshape147] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("reshape", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_reshape147].setDisplayName ( "" );
            methods[METHOD_resize148] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_resize148].setDisplayName ( "" );
            methods[METHOD_resize149] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_resize149].setDisplayName ( "" );
            methods[METHOD_revalidate150] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("revalidate", new Class[] {})); // NOI18N
            methods[METHOD_revalidate150].setDisplayName ( "" );
            methods[METHOD_saveRecord151] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("saveRecord", new Class[] {})); // NOI18N
            methods[METHOD_saveRecord151].setDisplayName ( "" );
            methods[METHOD_scrollRectToVisible152] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("scrollRectToVisible", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_scrollRectToVisible152].setDisplayName ( "" );
            methods[METHOD_setBounds153] = new MethodDescriptor(java.awt.Component.class.getMethod("setBounds", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_setBounds153].setDisplayName ( "" );
            methods[METHOD_setComponentZOrder154] = new MethodDescriptor(java.awt.Container.class.getMethod("setComponentZOrder", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_setComponentZOrder154].setDisplayName ( "" );
            methods[METHOD_setDefaultLocale155] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("setDefaultLocale", new Class[] {java.util.Locale.class})); // NOI18N
            methods[METHOD_setDefaultLocale155].setDisplayName ( "" );
            methods[METHOD_setRecordNum156] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("setRecordNum", new Class[] {int.class})); // NOI18N
            methods[METHOD_setRecordNum156].setDisplayName ( "" );
            methods[METHOD_show157] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {})); // NOI18N
            methods[METHOD_show157].setDisplayName ( "" );
            methods[METHOD_show158] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_show158].setDisplayName ( "" );
            methods[METHOD_showDatabaseError159] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("showDatabaseError", new Class[] {java.lang.Exception.class})); // NOI18N
            methods[METHOD_showDatabaseError159].setDisplayName ( "" );
            methods[METHOD_size160] = new MethodDescriptor(java.awt.Component.class.getMethod("size", new Class[] {})); // NOI18N
            methods[METHOD_size160].setDisplayName ( "" );
            methods[METHOD_toString161] = new MethodDescriptor(java.awt.Component.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString161].setDisplayName ( "" );
            methods[METHOD_transferFocus162] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocus", new Class[] {})); // NOI18N
            methods[METHOD_transferFocus162].setDisplayName ( "" );
            methods[METHOD_transferFocusBackward163] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusBackward", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusBackward163].setDisplayName ( "" );
            methods[METHOD_transferFocusDownCycle164] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusDownCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusDownCycle164].setDisplayName ( "" );
            methods[METHOD_transferFocusUpCycle165] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocusUpCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusUpCycle165].setDisplayName ( "" );
            methods[METHOD_unregisterKeyboardAction166] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("unregisterKeyboardAction", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_unregisterKeyboardAction166].setDisplayName ( "" );
            methods[METHOD_update167] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("update", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_update167].setDisplayName ( "" );
            methods[METHOD_updateField168] = new MethodDescriptor(jentry.sql.swing.DBForm.class.getMethod("updateField", new Class[] {java.lang.String.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_updateField168].setDisplayName ( "" );
            methods[METHOD_updateUI169] = new MethodDescriptor(javax.swing.JPanel.class.getMethod("updateUI", new Class[] {})); // NOI18N
            methods[METHOD_updateUI169].setDisplayName ( "" );
            methods[METHOD_validate170] = new MethodDescriptor(java.awt.Container.class.getMethod("validate", new Class[] {})); // NOI18N
            methods[METHOD_validate170].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.
    
        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx

    
//GEN-FIRST:Superclass

    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
	
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     * 
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
	return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     * 
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
	return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     * 
     * @return  An array of EventSetDescriptors describing the kinds of 
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
	return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     * 
     * @return  An array of MethodDescriptors describing the methods 
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
	return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are 
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean. 
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * will typically be GIFs, but may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from
     * this method.
     * <p>
     * There are four possible flavors of icons (16x16 color,
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * support a single icon we recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background
     * so they can be rendered onto an existing background.
     *
     * @param  iconKind  The kind of icon requested.  This should be
     *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32, 
     *    ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return  An image object representing the requested icon.  May
     *    return null if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch ( iconKind ) {
        case ICON_COLOR_16x16:
            if ( iconNameC16 == null )
                return null;
            else {
                if( iconColor16 == null )
                    iconColor16 = loadImage( iconNameC16 );
                return iconColor16;
            }
        case ICON_COLOR_32x32:
            if ( iconNameC32 == null )
                return null;
            else {
                if( iconColor32 == null )
                    iconColor32 = loadImage( iconNameC32 );
                return iconColor32;
            }
        case ICON_MONO_16x16:
            if ( iconNameM16 == null )
                return null;
            else {
                if( iconMono16 == null )
                    iconMono16 = loadImage( iconNameM16 );
                return iconMono16;
            }
        case ICON_MONO_32x32:
            if ( iconNameM32 == null )
                return null;
            else {
                if( iconMono32 == null )
                    iconMono32 = loadImage( iconNameM32 );
                return iconMono32;
            }
	default: return null;
        }
    }

}

