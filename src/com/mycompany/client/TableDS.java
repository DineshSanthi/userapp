package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public class TableDS extends DataSource {

    private static TableDS instance = null;
    public TableDS()
    {
    	setDataFormat(DSDataFormat.JSON);
    //	setDataProtocol(DSProtocol.POSTMESSAGE);
    	setJsonPrefix(null);
    	setJsonSuffix(null);
    }
    
    public static TableDS getInstance() {
        if (instance == null) {
            instance = new TableDS("RestDS");
            
        }
        return instance;
    }

    public TableDS(String id) {

        setID(id);
        setClientOnly(false);

        DataSourceField propertyField = new DataSourceField("id", FieldType.TEXT);
        propertyField.setPrimaryKey(true);
        propertyField.setHidden(true);

        DataSourceField groupField = new DataSourceField("appName", FieldType.TEXT, "Application");
        groupField.setCanEdit(false);
        
        DataSourceField labelField = new DataSourceField("tableName", FieldType.TEXT, "Name");
        labelField.setCanEdit(false);
        labelField.setPrimaryKey(true);

        DataSourceField labelField1 = new DataSourceField("tableDescription", FieldType.TEXT, "Description");
        labelField1.setCanEdit(false);
        
        this.setFields(propertyField,labelField,groupField);
        
        this.setDataURL("http://127.0.0.1:8080/table/all");

    } 
    
}
