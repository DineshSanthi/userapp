package com.mycompany.client;

import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.TabTitleEditEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class MainTabSet extends TabSet{

	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	//final TabSet topTabSet = new TabSet(); 
	public Tab gettTab1() {
		return tTab1;
	}

	public void settTab1(Tab tTab1) {
		this.tTab1 = tTab1;
	}

	public Tab gettTab2() {
		return tTab2;
	}

	public void settTab2(Tab tTab2) {
		this.tTab2 = tTab2;
	}

	public TabSet getTopTabSet() {
		return this;
	}

	Tab tTab1 = new Tab(Constants.tabName());  
	Tab tTab2 = new Tab("");
	 
	public MainTabSet(HLayout layout)
	{
        
        this.setTabBarPosition(Side.TOP);  
        this.setTabBarAlign(Side.LEFT);  
        this.setWidth100();;  
        this.setHeight100();  
        this.setTitleEditEvent(TabTitleEditEvent.DOUBLECLICK);
        
        tTab1.setPane(layout);
        tTab1.setCanEditTitle(true);

        this.addTab(tTab1);
        
        tTab2.addTabSelectedHandler(new TabSelectedHandler() {
			
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				
				Tab newTab = new Tab(Constants.tabName());
				newTab.setCanClose(true);
				newTab.setCanEditTitle(true);		        
				newTab.setPane(new ApplicationInstance());
				addTab(newTab,getTabs().length - 1);
				selectTab(getTabs().length - 2);
			}
		});
        
        this.addTab(tTab2);
        
	}
	
}
