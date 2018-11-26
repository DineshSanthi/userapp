package com.mycompany.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;

public class RepoConfiguration {

	
	protected static native Map<String,String> getFieldDetails(String entityId, String fieldId) /*-{
	try {
		var fields = eval("$wnd.repo.objectDefinition['" + entityId + "']");
		if (fields)
		{
			var columns = fields[0];
			for (var i=0; i<columns.length; i++)
			{
				if(columns[i].columnName == fieldId)
				{
					return @com.smartgwt.client.util.JSOHelper::convertToMap(Lcom/google/gwt/core/client/JavaScriptObject;)(columns[i]);
				}
			}		
		}
	} catch (error) {
		var errorText = "getFieldDetailsList failed to read value";
	}
	 return @java.util.HashMap::new()();	
	}-*/;	

	protected static native List<String> getFieldsList(String entityId) /*-{
	try {
		var list = @java.util.ArrayList::new()();
		var fields = $wnd.repo.objectDefinition[entityId];
		if (fields)
		{
			var columns = fields[0];
			for (var i=0; i<columns.length; i++)
			{
				var field = columns[i];
				list.@java.util.ArrayList::add(Ljava/lang/Object;)(field.columnName);
			}
		}
		return list;
	} catch (error) {
		var errorText = "getFieldList failed to read value";
	}
	 return @java.util.ArrayList::new()();	
	}-*/;	
	
	protected static native List<String> getRelationList(String entityId) /*-{
	try {
		var list = @java.util.ArrayList::new()();
		var fields = $wnd.repo.objectDefinition[entityId];
		if (fields)
		{
			var relations = fields[1];
			for (var i=0; i<relations.length; i++)
			{
				var field = relations[i];
				list.@java.util.ArrayList::add(Ljava/lang/Object;)(field.secondaryTable);
			}
		}
		return list;
	} catch (error) {
		var errorText = "getFieldList failed to read value";
	}
	 return @java.util.ArrayList::new()();	
	}-*/;	
	
	protected static native void setDefinition(String entityId, JavaScriptObject jsObj) /*-{
		try {
			$wnd.repo.objectDefinition[entityId] = eval(jsObj);
		} catch (error) {
			var errorText = "getFieldList failed to read value";
		}
		}-*/;	
	
	protected static native String getServiceUrl() /*-{
		if (!$wnd.repo) return "http://127.0.0.1:8080/";
		return $wnd.repo.serviceUrl;
	}-*/;

	protected static native String getAccessToken() /*-{
	if (!$wnd.keycloak.token) return "";
	return $wnd.keycloak.token;
	}-*/;

	
}
