package com.mycompany.client;

import com.smartgwt.client.data.fields.DataSourceTextField;

public class RelationshipRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
		DataSourceTextField id = new DataSourceTextField("id",Constants.idLabel() );  
        DataSourceTextField primaryTable = new DataSourceTextField("primaryTable", Constants.primaryTableLabel());  
        DataSourceTextField secondaryTable	 = new DataSourceTextField("secondaryTable", Constants.secondaryTableLabel());  
        DataSourceTextField minimumMultiplicity	 = new DataSourceTextField("minimumMultiplicity", Constants.minimumMultiplicityLabel());
        DataSourceTextField maximumMultiplicity	 = new DataSourceTextField("maximumMultiplicity", Constants.maximumMultiplicityLabel());
        addField(id);
        addField(primaryTable);
        addField(secondaryTable);
        addField(minimumMultiplicity);
        addField(maximumMultiplicity);
	}
	
	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}