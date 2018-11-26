package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class DefaultDataDS extends DataSource {
	
	private static DefaultDataDS instance = null;  
    
    public static DefaultDataDS getInstance() {  
        if (instance == null) {  
            instance = new DefaultDataDS("defaultDataDS");  
        }  
        return instance;  
    }  
    
    public DefaultDataDS(String id) {  
        setID(id);  
          
        DataSourceTextField data = new DataSourceTextField("data", "Data");  
        setFields(data);  
          
        setTestData(DefaultData.getNewRecords());  
        setClientOnly(true);  
    }  
}  
    


