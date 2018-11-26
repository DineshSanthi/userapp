package com.mycompany.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.widgets.grid.ListGrid;

public class ApplicationRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField appName = new DataSourceTextField("appName", Constants.appNameLabel());  
        DataSourceTextField appDesc = new DataSourceTextField("appDescription", Constants.descriptionLabel());
        DataSourceTextField descTamil = new DataSourceTextField("descTamil", Constants.descriptonTamilLabel());
        DataSourceTextField descTelugu = new DataSourceTextField("descTelugu",Constants.descriptionTeluguLabel());
       
        
        DataSourceTextField msgApplication = new DataSourceTextField("messagingFeature", Constants.messageFeatureLabel(), 100);     
        
        DataSourceTextField webApp = new DataSourceTextField("webApplication", Constants.webApplicationLabel(), 100); 
        
        DataSourceTextField mobApp = new DataSourceTextField("mobileApplication",Constants.mobileApplicationLabel(), 100);   
//        addField(appName);
//        addField(appDesc);
        setFields(appName, appDesc,descTamil,descTelugu,msgApplication,msgApplication,webApp,mobApp);
        
	}
	
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}