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
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;



public class TableItemDetailTabPane extends AbstractTabPane {

	private ListGrid editGrid;
	private Label editorLabel;
	private ResultsLayout resultsLayout;
	private static ListGrid relatedColumnGrid;
	private static ListGrid relatedTableScriptGrid;
	private TabSet topTabSet = new TabSet();  
	private Tab scriptTab;
	private DynamicForm tableScriptForm = new DynamicForm(); 
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

	public TableItemDetailTabPane(final DataSource appRestDS, final DataSource tableDS, 
			DataSource columnDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
		this.resultsLayout = resultsLayout;
	}

	
}
