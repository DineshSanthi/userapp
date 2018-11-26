package com.mycompany.client;

import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class RelationLinkToolStrip extends ToolStrip {

	public RelationLinkToolStrip(final DynamicForm form, final ListGrid listGrid,final String secondaryTableName) {
		this.setWidth100();

		// push all buttons to the right
		this.addFill();
		
		listGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
	            public void onSelectionChanged(SelectionEvent event) {  
	                Record record = event.getRecord();  
	                form.editRecord(record);  
	            }  
	        });  

		ToolStripButton linkButton = new ToolStripButton("Link Record");
		linkButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final Window winModal = new Window();
				winModal.setAutoSize(true);
				winModal.setTitle("Link Window");
				winModal.setShowMinimizeButton(false);
				winModal.setIsModal(true);
				winModal.setShowModalMask(true);
				winModal.centerInPage();
				winModal.addCloseClickHandler(new CloseClickHandler() {
					public void onCloseClick(CloseClickEvent event) {
						winModal.destroy();
					}
				});
				
			       IButton button = new IButton( "Save Item");
	                button.setTop(250);
	                button.setIconOrientation("left"); 
	                button.addClickHandler(new ClickHandler() {
	                    public void onClick(ClickEvent event) {
	                    	form.setValue("secondaryTableName", secondaryTableName);
	                    	form.saveData();
	                    	winModal.destroy(); 
	                    }
	                });
	                
				
				winModal.addItem(form);
				winModal.addItem(button);
				winModal.show();
				
				//form.editNewRecord();
			}
		});
		
		ToolStripButton unlinkButton = new ToolStripButton("Unlink Record");
		unlinkButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//form.editNewRecord();
			}
		});

		this.addButton(linkButton);
		this.addButton(unlinkButton);

		this.draw();

	}
}
