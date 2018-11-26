package com.mycompany.client;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.MenuItemStringFunction;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

public class MainToolBar extends ToolStrip {

	private static final String skinCookieName = "skin_name_2_4";
	private static final String densityCookieName = "density_name_selected";
	private static final Map<String,String> skinDefaultDensityParams = new LinkedHashMap<String,String>() {{
    	put("Tahoe", "fontIncrease=3&sizeIncrease=10");
    	put("Stratus", "fontIncrease=3&sizeIncrease=10");
    	put("Obsidian", "fontIncrease=3&sizeIncrease=10");
    	put("default", "fontIncrease=1&sizeIncrease=2");
    }};
    private static int fontIncreaseValue;
    private static int sizeIncreaseValue;

    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    
    public static int getSizeIncrease() {
        return sizeIncreaseValue;
    }
    public static String getCurrentDensity() {
    	String currentDensity = Cookies.getCookie(densityCookieName);
        if (currentDensity == null) {
            currentDensity = "fontIncrease=" + Browser.getDefaultFontIncrease() + 
                            "&sizeIncrease=" + Browser.getDefaultSizeIncrease();
        }
    	return currentDensity;
    }
    private static String getDefaultDensityParams(String skin) {
        return skinDefaultDensityParams.get(skin);
    }

	
	
	public MainToolBar() {
		
		
		
        Label appLabel = new Label();
        appLabel.setContents("Techmind - User View");
        appLabel.setHeight100();
        appLabel.setWidth(200);
        this.addChild(appLabel);
        
		// push all buttons to the right
		this.addFill();
	/*	
		ToolStripMenuButton menuButton = getToolStripMenuButton();
		this.addMenuButton(menuButton);

		this.addSeparator();

		ToolStripButton iconButton = new ToolStripButton();
		iconButton.setIcon("silk/printer.png");
		iconButton.setTitle("Print");
		this.addButton(iconButton);

		this.addResizer();*/

		SelectItem themeItem = new SelectItem();
		themeItem.setShowTitle(false);
		themeItem.setWidth(120);

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		valueMap.put("Enterprise", Constants.enterpriseSkinName());
		valueMap.put("EnterpriseBlue", Constants.enterpriseBlueSkinName());
		valueMap.put("Graphite", Constants.graphiteSkinName());
		valueMap.put("Simplicity", Constants.simplicitySkinName());
		valueMap.put("Tahoe",Constants.tahoeSkinName());
		valueMap.put("BlackOps", Constants.blackOpsSkinName());
		valueMap.put("TreeFrog", Constants.treeFrogSkinName());
		valueMap.put("Stratus", Constants.stratusSkinName());
		valueMap.put("fleet",Constants.fleetSkinName());
		valueMap.put("Obsidian", Constants.obsidianSkinName());
		themeItem.setValueMap(valueMap);
		themeItem.setDefaultValue("Enterprise");
		
        String currentSkin = Cookies.getCookie(skinCookieName);
        if (currentSkin == null) {
            currentSkin = "Enterprise";
        }
        themeItem.setDefaultValue(currentSkin);
        themeItem.setShowTitle(false);
        themeItem.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                Cookies.setCookie(skinCookieName, (String) event.getValue());
                com.google.gwt.user.client.Window.Location.reload();
            }
        });
		
		this.addFormItem(themeItem);
		
		final LinkedHashMap<String, String> valueMapDensity = new LinkedHashMap<String, String>();
        valueMapDensity.put("fontIncrease=0&sizeIncrease=0", Constants.denseDensityName().asString());
        valueMapDensity.put("fontIncrease=1&sizeIncrease=2", Constants.compactDensityName().asString());
        valueMapDensity.put("fontIncrease=2&sizeIncrease=4", Constants.mediumDensityName().asString());
        valueMapDensity.put("fontIncrease=2&sizeIncrease=6",Constants.expandedDensityName().asString());
        valueMapDensity.put("fontIncrease=3&sizeIncrease=10",Constants.spaciousDensityName().asString());
        
        
        SelectItem selectItemDensity = new SelectItem();
        selectItemDensity.setWidth(130);
        selectItemDensity.setValueMap(valueMapDensity);
        selectItemDensity.setHoverWidth(300);
        selectItemDensity.setHoverHeight(20);
        
        String currentDensity = getCurrentDensity();
        
        selectItemDensity.setDefaultValue(currentDensity);
        selectItemDensity.setShowTitle(false);
        
        selectItemDensity.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                Cookies.setCookie("dhc", "1");
                Cookies.setCookie(densityCookieName, (String) event.getValue());
                com.google.gwt.user.client.Window.Location.reload();
            }
        });	

        this.addFormItem(selectItemDensity);
        this.addSeparator();
        

		Label userLabel =new Label();
		userLabel.setContents("Dinesh Elangovan");
		this.addMember(userLabel);
		this.addSeparator();
        
        IButton logOut = new IButton("Logout");
        
        this.addMember(logOut);
	

	}
/*
    private ToolStripMenuButton getToolStripMenuButton() {  
        Menu menu = new Menu();  
        menu.setShowShadow(true);  
        menu.setShadowDepth(3);  
  
        MenuItem newItem = new MenuItem("New", "icons/16/document_plain_new.png", "Ctrl+N");  
        MenuItem openItem = new MenuItem("Open", "icons/16/folder_out.png", "Ctrl+O");  
        MenuItem saveItem = new MenuItem("Save", "icons/16/disk_blue.png", "Ctrl+S");  
        MenuItem saveAsItem = new MenuItem("Save As", "icons/16/save_as.png");  
  
        MenuItem recentDocItem = new MenuItem("Recent Documents", "icons/16/folder_document.png");  
  
        Menu recentDocSubMenu = new Menu();  
        MenuItem dataSM = new MenuItem("data.xml");  
        dataSM.setChecked(true);  
        MenuItem componentSM = new MenuItem("Component Guide.doc");  
        MenuItem ajaxSM = new MenuItem("AJAX.doc");  
        recentDocSubMenu.setItems(dataSM, componentSM, ajaxSM);  
  
        recentDocItem.setSubmenu(recentDocSubMenu);  
  
        MenuItem exportItem = new MenuItem("Export as...", "icons/16/export1.png");  
        Menu exportSM = new Menu();  
        exportSM.setItems(  
                new MenuItem("XML"),  
                new MenuItem("CSV"),  
                new MenuItem("Plain text"));  
        exportItem.setSubmenu(exportSM);  
  
        MenuItem printItem = new MenuItem("Print", "icons/16/printer3.png", "Ctrl+P");  
        printItem.setEnabled(false);  
  
        MenuItemSeparator separator = new MenuItemSeparator();  
  
        final MenuItem activateMenu = new MenuItem("Activate");  
        activateMenu.setDynamicTitleFunction(new MenuItemStringFunction() {  
  
            public String execute(final Canvas aTarget, final Menu aMenu, final MenuItem aItem) {  
                if (Math.random() > 0.5) {  
                    return "De-Activate Blacklist";  
                } else {  
                    return "Activate Blacklist";  
                }  
            }  
        });  
  
        menu.setItems(activateMenu, newItem, openItem, separator, saveItem, saveAsItem,  
                separator, recentDocItem, separator, exportItem, separator, printItem);  
  
        ToolStripMenuButton menuButton = new ToolStripMenuButton("File", menu);  
        menuButton.setWidth(100);  
        return menuButton;  
    } 
	*/
}
