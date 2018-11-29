package com.mycompany.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.MenuItemStringFunction;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

public class ResultsPanelToolStrip extends ToolStrip {

	
	private Window window;
	 
	private FormPanel uploadPanel = new FormPanel();
	
	private VerticalPanel panel = new VerticalPanel();
	
    private FileUpload upload;
	
    private String file;
    
    private TextBox docTypeTxtBox = new TextBox();
    
    private TextBox idTxtBox  = new TextBox();
    
	public ResultsPanelToolStrip(final ListGrid resultsListGrid,String collectionName,DataRestDS datasource ) {
		
	this.setWidth100();  
	  
    //push all buttons to the right  
    this.addFill();  
        
    
    upload = new FileUpload();
    upload.setVisible( false );
    upload.setName( "image" );
    upload.addChangeHandler( new ChangeHandler()
    {
    	 @Override
         public void onChange( ChangeEvent event )
         {
    		 file= upload.getFilename().replace( "C:\\fakepath\\", "" );
     		 docTypeTxtBox.setValue(file.substring(file.indexOf(".") + 1));
    		 uploadPanel.submit();
         }
    } );
   
    docTypeTxtBox.setName("docType");
    docTypeTxtBox.setVisible(false);
    
    idTxtBox.setName("id");
    idTxtBox.setVisible(false);
    
    uploadPanel.setEncoding( FormPanel.ENCODING_MULTIPART );
    uploadPanel.setMethod( FormPanel.METHOD_POST );
    uploadPanel.setVisible( false );
    uploadPanel.setAction( RepoConfiguration.getServiceUrl() + "tablethumbnail/insert" );
   
    
    
    panel.add(upload);
    panel.add(docTypeTxtBox);
    panel.add(idTxtBox);
    
    uploadPanel.add(panel);
    
    uploadPanel.addSubmitHandler( new FormPanel.SubmitHandler()
    {
        public void onSubmit( SubmitEvent event )
        {
        }
    } );
    

	final DynamicForm form = new DynamicForm();
	form.setIsGroup(true);
	form.setGroupTitle(collectionName + " Filter");
	form.setNumCols(2);
	form.setDataSource(datasource);
	
    ToolStripButton uploadButton = new ToolStripButton();  
    uploadButton.setIcon("icons/16/find.png"); 
    uploadButton.addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {
    		triggerUploadClick(upload.getElement());
    		String id = resultsListGrid.getSelectedRecord().getAttribute("id");
    		idTxtBox.setValue(id);
        }   
    });
    
    
	this.addSeparator();
	
	ToolStripButton insertButton = new ToolStripButton();
	insertButton.setIcon("icons/16/icon_add_files.png");
	insertButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			final Window winModal = new Window();
			winModal.setAutoSize(true);
			winModal.setTitle("Insert Record");
			winModal.setShowMinimizeButton(false);
			winModal.setIsModal(true);
			winModal.setShowModalMask(true);
			winModal.centerInPage();
			winModal.addCloseClickHandler(new CloseClickHandler() {
				public void onCloseClick(CloseClickEvent event) {
					winModal.destroy();
				}
			});
			IButton saveButton = new IButton("Save");
			saveButton.setAlign(Alignment.CENTER);
			saveButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					form.saveData();
					winModal.hide();
				}
			});

			VLayout layout1 = new VLayout();
			layout1.addMember(form);
			layout1.addMember(saveButton);
			winModal.addItem(layout1);
			winModal.show();
		}
		
	});

    ToolStripButton deleteButton = new ToolStripButton();  
    deleteButton.setIcon("icons/16/close.png"); 
    deleteButton.addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {  
    		resultsListGrid.removeSelectedData();
        }   
    });
    
    ToolStripButton refreshButton = new ToolStripButton();  
    refreshButton.setIcon("icons/menu/refresh.png"); 
    refreshButton.addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {  
    
    		resultsListGrid.refreshFields();
        }   
    });
  /*  
    ToolStripButton exportButton = new ToolStripButton();  
    exportButton.setIcon("icons/16/export1.png"); 
    exportButton.addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {  
    		
        }   
    });*/
    
    this.addMember(uploadPanel);
    this.addMember(insertButton);
    this.addButton(uploadButton);
//    this.addButton(exportButton);
    this.addButton(refreshButton);
    this.addButton(deleteButton);
    
    
	}   
	
    public native void triggerUploadClick( Element element ) /*-{
		element.click();
    }-*/;
	
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
}


  

