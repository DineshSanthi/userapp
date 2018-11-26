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

public class DataRestDS extends RestDataSource {

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

	public DataRestDS(String entityId) {
		this.entityId = entityId;

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
		List<String> list = RepoConfiguration.getFieldsList(entityId);
		if (list.size() > 0) {
			DataSourceField[] dataSourceFields = new DataSourceField[list.size() + 1];
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> columnMap = RepoConfiguration.getFieldDetails(entityId, list.get(i));

				if (columnMap.get("columnType").equalsIgnoreCase("string")) {
					DataSourceTextField dataSourceField = new DataSourceTextField(list.get(i).toString(),
							columnMap.get("columnDesc"), 50, false);
					if(columnMap.get("required").equalsIgnoreCase("yes"))
					{
					dataSourceField.setRequired(true);
					}
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("date")) {
					DataSourceDateField dataSourceField = new DataSourceDateField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("double")) {
					DataSourceTextField dataSourceField = new DataSourceTextField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					if(columnMap.get("readOnly").equalsIgnoreCase("yes"))
					{
					dataSourceField.setCanEdit(false);
					}
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("array")) {
					DataSourceTextField dataSourceField = new DataSourceTextField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("int")) {
					DataSourceIntegerField dataSourceField = new DataSourceIntegerField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("object")) {
					DataSourceTextField dataSourceField = new DataSourceTextField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				}  else if (columnMap.get("columnType").equalsIgnoreCase("sequence")) {
					DataSourceSequenceField dataSourceField = new DataSourceSequenceField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("datetime")) {
					DataSourceDateTimeField dataSourceField = new DataSourceDateTimeField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("boolean")) {
					DataSourceBooleanField dataSourceField = new DataSourceBooleanField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceField.setTitle(list.get(i).toString());
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("float")) {
					DataSourceFloatField dataSourceField = new DataSourceFloatField(list.get(i).toString(),
							columnMap.get("columnDesc"),5);
					FloatRangeValidator rangeValidator = new FloatRangeValidator();
					rangeValidator.setMin(0);
					rangeValidator.setErrorMessage("Please enter a valid (positive) cost");

					FloatPrecisionValidator precisionValidator = new FloatPrecisionValidator();
					precisionValidator.setPrecision(2);
					precisionValidator.setErrorMessage("The maximum allowed precision is 2");

					dataSourceField.setValidators(rangeValidator, precisionValidator);
					dataSourceFields[i] = dataSourceField;

				} else if (columnMap.get("columnType").equalsIgnoreCase("password")) {
					DataSourcePasswordField dataSourceField = new DataSourcePasswordField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("link")) {
					DataSourceLinkField dataSourceField = new DataSourceLinkField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					dataSourceField.setValueXPath("URL");
					dataSourceField.setAttribute("target", "_blank");
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("image")) {
					DataSourceImageField dataSourceField = new DataSourceImageField(list.get(i).toString(),
							columnMap.get("columnDesc"));
					if(columnMap.get("hidden").equalsIgnoreCase("yes"))
					{
					dataSourceField.setHidden(true);
					}
					dataSourceFields[i] = dataSourceField;
				} else if (columnMap.get("columnType").equalsIgnoreCase("states")) {
					DataSourceEnumField dataSourceField = new DataSourceEnumField(list.get(i).toString(),
							columnMap.get("columnDesc"), 3);
					dataSourceField.setValueMap("Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
							"Goa", "Gujarat", "Tamil Nadu", "Karnataka", "Kerala", "Telangana");
					dataSourceFields[i] = dataSourceField;
				}
			}
			DataSourceTextField dataSourceField = new DataSourceTextField("id", "ID");
			dataSourceField.setHidden(true);
			dataSourceField.setPrimaryKey(true);
			dataSourceFields[list.size()] = dataSourceField;
			setFields(dataSourceFields);

		}
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
		/*	if(dsRequest.getData() == null)
			dsRequest.setData("");*/
			
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