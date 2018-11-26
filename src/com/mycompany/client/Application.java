package com.mycompany.client;

import com.google.gwt.core.client.EntryPoint;

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

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FilterCriteriaFunction;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;

public class Application extends VLayout implements EntryPoint {
    private NavigatorTreeGrid navigatorTree;
    private ApplicationListGrid applicationListGrid;
    private TableListGrid tableListGrid;
    private ApplicationRestDS appRestDS;
    private TableRestDS tableRestDS;
    private Menu itemListMenu;
    private ResultsLayout resultsLayout;
    private EditLayout editLayout;
    private HLayout mainLayout;
    private MainToolBar mainMenu;
    private MainTabSet mainTabSet;
    
    public void onModuleLoad() {
    	
    	
       	mainLayout = new HLayout(); 
    	mainLayout.setWidth100();
    	mainLayout.setHeight100();
    	mainLayout.setLayoutMargin(10);

        
        mainMenu = new MainToolBar();
        

        this.setWidth100();  
        this.setHeight100();  
        this.setMembersMargin(10);  
  
        ApplicationInstance app = new ApplicationInstance();
        mainTabSet = new MainTabSet(app);
        this.addMember(mainMenu);
        this.addMember(mainTabSet);
        this.draw();   
        
    }


   
    
    
}

