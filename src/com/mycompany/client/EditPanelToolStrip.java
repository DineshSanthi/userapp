package com.mycompany.client;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class EditPanelToolStrip extends ToolStrip {

	public EditPanelToolStrip(final DynamicForm form) {
		this.setWidth100();

		// push all buttons to the right
		this.addFill();

		ToolStripButton saveButton = new ToolStripButton();
		saveButton.setIcon("icons/16/save_as.png");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// editGrid.saveAllEdits();
				form.saveData();
			}
		});

	/*	ToolStripButton insertButton = new ToolStripButton();
		insertButton.setIcon("icons/16/icon_add_files.png");
		insertButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// editGrid.saveAllEdits();
				form.editNewRecord();
			}
		});*/

		//this.addButton(insertButton);
		this.addButton(saveButton);

		this.draw();

	}
}
