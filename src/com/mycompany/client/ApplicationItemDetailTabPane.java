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
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class ApplicationItemDetailTabPane extends AbstractTabPane {

	private ListGrid editGrid;
	private Label editorLabel;
	private ResultsLayout resultsLayout;
	private static ListGrid relatedTableGrid;
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public ApplicationItemDetailTabPane(final DataSource appRestDS, final DataSource tableDS, DataSource defaultDataDS,
			ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
		this.resultsLayout = resultsLayout;

		editorLabel = new Label();
		editorLabel.setWidth100();
		editorLabel.setHeight100();
		editorLabel.setAlign(Alignment.CENTER);
		editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

		final DynamicForm orderForm = new DynamicForm();
		orderForm.setDataSource(appRestDS);
		orderForm.setWidth100();

		TextItem appId = new TextItem("id");
		appId.setTitle("ID");
		appId.setDisabled(true);
		appId.setHidden(true);
		appId.setWidth("*");

		TextItem appName = new TextItem("appName");
		appName.setTitle(Constants.appNameLabel());
		appName.setRequired(true);

		TextItem appDesc = new TextItem("appDescription");
		appDesc.setTitle(Constants.descriptionLabel());
		appDesc.setRequired(true);

		TextItem descTamil = new TextItem("descTamil");
		descTamil.setTitle(Constants.descriptonTamilLabel());
		descTamil.setRequired(true);

		TextItem descTelugu = new TextItem("descTelugu");
		descTelugu.setTitle(Constants.descriptionTeluguLabel());
		descTelugu.setRequired(true);

		SelectItem messagingFeature = new SelectItem("messagingFeature");
		messagingFeature.setTitle(Constants.messageFeatureLabel());
		messagingFeature.setWidth(240);
		messagingFeature.setOptionDataSource(defaultDataDS);
		messagingFeature.setValueField("data");
		messagingFeature.setDisplayField("data");
		messagingFeature.setPickListWidth(450);
		messagingFeature.setRequired(true);

		SelectItem webApplication = new SelectItem("webApplication");
		webApplication.setTitle(Constants.webApplicationLabel());
		webApplication.setWidth(240);
		webApplication.setOptionDataSource(defaultDataDS);
		webApplication.setValueField("data");
		webApplication.setDisplayField("data");
		webApplication.setPickListWidth(450);
		webApplication.setRequired(true);

		SelectItem mobileApplication = new SelectItem("mobileApplication");
		mobileApplication.setTitle(Constants.mobileApplicationLabel());
		mobileApplication.setWidth(240);
		mobileApplication.setOptionDataSource(defaultDataDS);
		mobileApplication.setValueField("data");
		mobileApplication.setDisplayField("data");
		mobileApplication.setPickListWidth(450);
		mobileApplication.setRequired(true);

		orderForm.setFields(appId, appName, appDesc, descTamil, descTelugu, messagingFeature, webApplication,
				mobileApplication);

		resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			public void onSelectionChanged(SelectionEvent event) {
				Record record = event.getRecord();
				orderForm.editRecord(record);
			}
		});

		editGrid = new ListGrid();
		editGrid.setWidth(620);
		editGrid.setHeight(224);
		editGrid.setShowAllRecords(true);
		editGrid.setCellHeight(42);
		editGrid.setWrapCells(true);
		editGrid.setDataSource(appRestDS);

		editGrid.setAutoFetchData(true);
		editGrid.setCanEdit(true);
		editGrid.setEditEvent(ListGridEditEvent.CLICK);

		EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);

		relatedTableGrid = new ListGrid();
		relatedTableGrid.setWidth100();
		relatedTableGrid.setHeight100();
		relatedTableGrid.setShowAllRecords(true);
		relatedTableGrid.setCellHeight(42);
		relatedTableGrid.setWrapCells(true);
		relatedTableGrid.setDataSource(tableDS);

		ListGridField groupField = new ListGridField("appName", Constants.appNameLabel());
		groupField.setCanEdit(false);

		ListGridField labelField = new ListGridField("tableName", Constants.tableNameLabel());
		labelField.setCanEdit(false);

		ListGridField labelField1 = new ListGridField("tableDescription", Constants.descriptionLabel());
		labelField1.setCanEdit(false);

		relatedTableGrid.setFields(groupField, labelField, labelField1);

		relatedTableGrid.setAutoFetchData(true);

		Tab editTab = new Tab(Constants.editTabLabel());
		editTab.setIcon("demoApp/icon_edit.png");
		editTab.setWidth(70);

		VLayout layout = new VLayout();
		layout.addMember(toolStrip);
		layout.addMember(orderForm);
		editTab.setPane(layout);

		Tab tableTab = new Tab(Constants.relatedTableTabLabel());
		tableTab.setWidth(70);
		tableTab.setPane(relatedTableGrid);

		setTabs(editTab, tableTab);
	}

	public static ListGrid getRelatedTableGrid() {
		return relatedTableGrid;
	}

	public void setRelatedTableGrid(ListGrid relatedTableGrid) {
		this.relatedTableGrid = relatedTableGrid;
	}

}
