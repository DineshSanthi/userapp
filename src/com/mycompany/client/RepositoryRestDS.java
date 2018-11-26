package com.mycompany.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.Constants;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class RepositoryRestDS extends DataSource {
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    private static RepositoryRestDS instance = null;
    public RepositoryRestDS()
    {
    	setDataFormat(DSDataFormat.JSON);
    //	setDataProtocol(DSProtocol.POSTMESSAGE);
    	setJsonPrefix(null);
    	setJsonSuffix(null);
    }
    
    public static RepositoryRestDS getInstance(String appName) {
        return new RepositoryRestDS(appName);
    }
    public RepositoryRestDS(String id) {

        setID(id);
        setClientOnly(true);
        
        DataSourceField typeField = new DataSourceField("objectType", FieldType.TEXT, "Object Type");
        typeField.setPrimaryKey(true);
        typeField.setHidden(true);
        
        DataSourceField nameField = new DataSourceField("objectName", FieldType.TEXT, "Object Name");
        nameField.setPrimaryKey(true);
        
        this.setFields(typeField,nameField);
        
        

        if(id == null)
        {
        this.addData(createRecord("application", Constants.applicationEntityLabel()));
        }
        else
        {
        this.addData(createRecord("table", Constants.tableEntityLabel()));
       }
    } 
    
    
    public ListGridRecord createRecord(String objectType, String objectName) {  
        ListGridRecord record = new ListGridRecord();  
        record.setAttribute("objectType", objectType);  
        record.setAttribute("objectName", objectName);  
        return record;  
    } 

}
