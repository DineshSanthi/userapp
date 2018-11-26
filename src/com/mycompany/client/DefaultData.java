package com.mycompany.client;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DefaultData {

	private static ListGridRecord[] records;    
    
    public static ListGridRecord[] getRecords() {  
        if (records == null) {  
            records = getNewRecords();    
        }    
        return records;    
    }    
    
    public static ListGridRecord createRecord(String data){
    	
    	ListGridRecord record = new ListGridRecord();  
        record.setAttribute("data", data);  
        return record;
    }
    
    public static ListGridRecord[] getNewRecords() {  
        return new ListGridRecord[]{ 		
        		createRecord("Yes"),
        		createRecord("No")
        };
    }
    
}
