package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.grid.ListGrid;

public class RelatedColumnListGrid extends ListGrid{

    public RelatedColumnListGrid(String entityId, DataSource dataDS) {
        setAutoFetchData(true); 
        setHoverWidth(200);
        setHoverHeight(20);
		setShowAllRecords(true);
		setDataSource(dataDS);
    }
    
    public void refreshData()
    {
    	super.refreshData();
    }
    
}
