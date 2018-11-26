package com.mycompany.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceLinkField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceSequenceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;

public class RelationDataRestDS extends RestDataSource {

	private String entityId = null;
	
	private DSRequest fetchProps;

	public DSRequest getFetchProps() {
		return fetchProps;
	}

	public void setFetchProps(DSRequest fetchProps) {
		this.fetchProps = fetchProps;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public RelationDataRestDS(String entityId) {
		this.entityId = entityId  + "_RelatedData" ;

		setID(SC.generateID("TableRestDS"));
		setDataFormat(DSDataFormat.JSON);
		setDataProtocol(DSProtocol.POSTMESSAGE);
		setJsonPrefix(null);
		setJsonSuffix(null);
		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		fetch.setDataProtocol(DSProtocol.POSTMESSAGE);
		fetchProps = new DSRequest();
		fetchProps.setHttpMethod("PUT");
		fetchProps.setContentType("application/json");
		/*
		 * Map<String, String> httpHeaders = new HashMap<String, String>();
		 * httpHeaders.put("Accept", "application/json");
		 * httpHeaders.put("Authorization", "Bearer "+ getAccessToken());
		 * fetchProps.setHttpHeaders(httpHeaders);
		 */
		
		fetch.setRequestProperties(fetchProps);

		OperationBinding add = new OperationBinding();
		add.setOperationType(DSOperationType.ADD);
		add.setDataProtocol(DSProtocol.POSTMESSAGE);
		DSRequest addProps = new DSRequest();
		addProps.setHttpMethod("POST");
		addProps.setContentType("application/json");
		add.setRequestProperties(addProps);

		OperationBinding update = new OperationBinding();
		update.setOperationType(DSOperationType.UPDATE);
		update.setDataProtocol(DSProtocol.POSTMESSAGE);

		DSRequest updateProps = new DSRequest();
		updateProps.setHttpMethod("PUT");
		updateProps.setContentType("application/json");
		update.setRequestProperties(updateProps);

		OperationBinding remove = new OperationBinding();
		remove.setOperationType(DSOperationType.REMOVE);
		DSRequest removeProps = new DSRequest();
		removeProps.setHttpMethod("DELETE");
		removeProps.setContentType("application/json");
		remove.setRequestProperties(removeProps);
		setOperationBindings(fetch, add, update, remove);

		setEntityFields();

		/*
		 * DataSourceTextField appId = new
		 * DataSourceTextField(getPrimaryKeyFieldId(), "ID");
		 * appId.setPrimaryKey(true); appId.setHidden(true);
		 * appId.setCanEdit(false); addField(appId);
		 */

		setFetchDataURL(getCustomFetchDataURL());
		setAddDataURL(getCustomAddDataURL());
		setUpdateDataURL(getCustomUpdateDataURL());
		setRemoveDataURL(getRemoveDataURL());

	}

	public String getCustomFetchDataURL() {
		return getServiceRoot() + "/all";
	}

	public String getCustomAddDataURL() {
		return getServiceRoot() + "/insert";
	}

	public String getCustomUpdateDataURL() {
		return getServiceRoot() + "/update";
	}

	public String getRemoveDataURL() {
		return getServiceRoot() + "/delete";
	}

	public String getServiceRoot() {
		return RepoConfiguration.getServiceUrl() + getServiceName();
	}

	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase() + "/"
				+ (getEntityId() != null ? getEntityId() : "");
	}

	protected void setEntityFields() {
		
		 DataSourceTextField id = new DataSourceTextField("id", "ID");
		 id.setHidden(true);
		 DataSourceTextField primaryTableId = new DataSourceTextField("primaryTableRecordId", "PrimaryTable Name");
		 DataSourceTextField secondaryTableName = new DataSourceTextField("secondaryTableName", "SecondaryTable Id");
		 DataSourceTextField secondaryTableId = new DataSourceTextField("secondaryTableRecordId", "SecondaryTable Name");
		 
		 addField(id);
		 addField(primaryTableId);
		 addField(secondaryTableName);
		 addField(secondaryTableId);
		
		 
		
	}

	protected String getPrimaryKeyFieldId() {
		return "id";
	}

	@Override
	protected Object transformRequest(DSRequest dsRequest) {

		/*
		 * Map<String, String> httpHeaders = new HashMap<String, String>();
		 * httpHeaders.put("Accept", "application/json");
		 * httpHeaders.put("Authorization", "Bearer "+ getAccessToken());
		 * dsRequest.setHttpHeaders(httpHeaders);
		 */

		if (dsRequest.getOperationType() == DSOperationType.FETCH) {
			dsRequest.setActionURL(getCustomFetchDataURL());
			
		} else if (dsRequest.getOperationType() == DSOperationType.UPDATE) {
			final JavaScriptObject basicJSObject = dsRequest.getOldValues().getJsObj();
			final JavaScriptObject latestChanges = dsRequest.getData();
			JSOHelper.addProperties(basicJSObject, latestChanges);
			String primaryKey = dsRequest.getOldValues().getAttribute(getPrimaryKeyFieldId());
			dsRequest.setActionURL(getCustomUpdateDataURL() + "/" + primaryKey);
			final String resultString = JSON.encode(basicJSObject);
			return resultString;
		} else if (dsRequest.getOperationType() == DSOperationType.ADD) {
			final JavaScriptObject latestChanges = dsRequest.getData();
			final String resultString = JSON.encode(latestChanges);
			return resultString;
		} else if (dsRequest.getOperationType() == DSOperationType.REMOVE) {
			String primaryKey = dsRequest.getOldValues().getAttribute(getPrimaryKeyFieldId());
			dsRequest.setActionURL(getRemoveDataURL() + "/" + primaryKey);
		}
		return super.transformRequest(dsRequest);
	}

	@Override
	protected void transformResponse(DSResponse response, DSRequest request, Object data) {
		super.transformResponse(response, request, data);
	}

}