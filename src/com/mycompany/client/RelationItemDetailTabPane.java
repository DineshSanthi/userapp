package com.mycompany.client;

/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class RelationItemDetailTabPane extends AbstractTabPane {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public RelationItemDetailTabPane(final DataSource objectDS,final DataSource tableRestDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
        this.resultsLayout = resultsLayout;

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        final DynamicForm orderForm = new DynamicForm();  
        orderForm.setDataSource(objectDS);  
  
        TextItem appId = new TextItem("id");  
        appId.setTitle(Constants.idLabel());  
        appId.setDisabled(true);
        appId.setHidden(true);
  
        SelectItem primaryTable = new SelectItem("primaryTable");  
        primaryTable.setTitle(Constants.primaryTableLabel());  
        primaryTable.setWidth(240);  
        primaryTable.setOptionDataSource(tableRestDS);  
        primaryTable.setValueField("tableName");  
        primaryTable.setDisplayField("tableName");  
        primaryTable.setPickListWidth(450);
        primaryTable.setRequired(true);
        
        SelectItem secondaryTable = new SelectItem("secondaryTable");  
        secondaryTable.setTitle(Constants.secondaryTableLabel());  
        secondaryTable.setWidth(240);  
        secondaryTable.setOptionDataSource(tableRestDS);  
        secondaryTable.setValueField("tableName");  
        secondaryTable.setDisplayField("tableName");  
        secondaryTable.setPickListWidth(450); 
        secondaryTable.setRequired(true);
      
        TextItem minimumMultiplicity = new TextItem("minimumMultiplicity");  
        minimumMultiplicity.setTitle(Constants.minimumMultiplicityLabel()); 
        minimumMultiplicity.setRequired(true);
        
        TextItem maximumMultiplicity = new TextItem("maximumMultiplicity");  
        maximumMultiplicity.setTitle(Constants.maximumMultiplicityLabel());  
        
        
  
        orderForm.setFields(appId,primaryTable, secondaryTable,minimumMultiplicity,maximumMultiplicity); 
        
        
        resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
                Record record = event.getRecord();  
                orderForm.editRecord(record);  
            }  
        });  
        
        
       
        
       /* IButton button1 = new IButton("Delete");  
        button1.setTop(250);
        button1.setIconOrientation("right"); 
        button1.addClickHandler(new ClickHandler() {  
        	public void onClick(ClickEvent event) {  
                ListGridRecord record = new ListGridRecord();  
                record.setAttribute("id", editGrid.getSelectedRecord().getAttribute("id"));  
                editGrid.removeData(record);  
                editGrid.fetchData();
        		//editGrid.removeSelectedData();
        		resultsListGrid.removeSelectedData();
            }   
        }); 
        
        IButton button = new IButton( "Save Item");
        button.setTop(250);
        button.setIconOrientation("left"); 
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//editGrid.saveAllEdits();
            	orderForm.saveData();
            }
        });
        
        IButton button3 = new IButton("Insert");  
        button3.setTop(250);
        button3.setShowDown(true);
       // button1.setIconOrientation("right");
        button3.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//resultsListGrid.startEditingNew();
            	final Window winModal = new Window();  
                winModal.setAutoSize(true);  
                winModal.setTitle("New Record");  
                winModal.setShowMinimizeButton(false);  
                winModal.setIsModal(true);  
                winModal.setShowModalMask(true);  
                winModal.centerInPage();  
                winModal.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClickEvent event) {  
                        winModal.destroy();  
                    }
                  });
               final DynamicForm form = new DynamicForm();  
                form.setHeight100();  
                form.setWidth100();  
                form.setPadding(5); 
                form.setDataSource(objectDS);
                form.setLayoutAlign(VerticalAlignment.BOTTOM);  
          
                SelectItem primaryTable = new SelectItem("tableName");  
                primaryTable.setTitle("Primary Table");  
                primaryTable.setWidth(240);  
                primaryTable.setOptionDataSource(tableRestDS);  
                primaryTable.setValueField("tableName");  
                primaryTable.setDisplayField("tableName");  
                primaryTable.setPickListWidth(450);
                
                SelectItem secondaryTable = new SelectItem("tableName");  
                secondaryTable.setTitle("Secondary Table");  
                secondaryTable.setWidth(240);  
                secondaryTable.setOptionDataSource(tableRestDS);  
                secondaryTable.setValueField("tableName");  
                secondaryTable.setDisplayField("tableName");  
                secondaryTable.setPickListWidth(450); 
              
                TextItem minimumMultiplicity = new TextItem("minimumMultiplicity");  
                minimumMultiplicity.setTitle("Minimum Multiplicity ");  
                
                TextItem maximumMultiplicity = new TextItem("maximumMultiplicity");  
                maximumMultiplicity.setTitle("Maximum Multiplicity");  
                
               
                IButton button = new IButton( "Save Item");
                button.setTop(250);
                button.setIconOrientation("left"); 
                button.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                    	//editGrid.saveAllEdits();
                    	form.saveData();
                    	winModal.destroy(); 
                    }
                });
                
                form.setFields(primaryTable, secondaryTable,minimumMultiplicity,maximumMultiplicity);  
                winModal.addItem(form);
                winModal.addItem(button);
                winModal.show();
               
            }  
        });      
         
        */
        
        EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);

        Tab editTab = new Tab(Constants.editTabLabel());
        editTab.setIcon("demoApp/icon_edit.png");
        editTab.setWidth(70);
      //  editTab.setPane(editGrid);
        
        VLayout layout = new VLayout();
        //layout.addMember(editGrid);
        layout.addMember(toolStrip);
        layout.addMember(orderForm);
        /*layout.addMember(button);
        layout.addMember(button1);
        layout.addMember(button3);*/
        editTab.setPane(layout);
        
               
        setTabs(editTab);

       /* addTabSelectedHandler(new TabSelectedHandler() {
            public void onTabSelected(TabSelectedEvent event) {
                updateDetails();
            }
        });*/
    }

	

/*
    public void clearDetails(Record selectedCategoryRecord) {
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData((Record[]) null);
        } else {
            // edit tab : show new record editor, or empty message
            if (selectedCategoryRecord != null) {
                updateTab(1, editorForm);
                Map initialValues = new HashMap();
                initialValues.put("category", selectedCategoryRecord.getAttribute("categoryName"));
                editorForm.editNewRecord(initialValues);
            } else {
                updateTab(1, editorLabel);
            }
        }
    }*/
    

   /* public void updateDetails() {
        Record selectedRecord  = vLayout.getResultGrid().getSelectedRecord();
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData(new Record[]{selectedRecord});
        } else {
            // edit tab : show record editor
            editorForm.editRecord(selectedRecord);
        }
    }*/
}

