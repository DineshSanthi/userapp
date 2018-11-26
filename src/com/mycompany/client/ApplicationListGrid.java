package com.mycompany.client;

/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.grid.*;

public class ApplicationListGrid extends ListGrid {
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;

    public ApplicationListGrid(DataSource restDS) {


        ListGridField appName = new ListGridField("appName", Constants.appNameLabel());
        appName.setShowHover(true);
        ListGridField appDescription = new ListGridField("appDescription",Constants.descriptionLabel());
        appDescription.setShowHover(true);
        ListGridField messagingFeature = new ListGridField("messagingFeature",Constants.messageFeatureLabel());
        messagingFeature.setShowHover(true);
        ListGridField webApplication = new ListGridField("webApplication",Constants.webApplicationLabel());
        webApplication.setShowHover(true);
        ListGridField mobileApplication = new ListGridField("mobileApplication",Constants.mobileApplicationLabel());
        mobileApplication.setShowHover(true);
        ListGridField defaultTheme = new ListGridField("defaultTheme",Constants.defaultThemeLabel());
        defaultTheme.setShowHover(true);
        ListGridField descTamil = new ListGridField("descTamil",Constants.descriptonTamilLabel());
        descTamil.setShowHover(true);
        ListGridField descTelugu = new ListGridField("descTelugu",Constants.descriptionTeluguLabel());
        descTelugu.setShowHover(true);
        setUseAllDataSourceFields(true);
        setAutoFetchData(true);
       /* unitCost.setCellFormatter(new CellFormatter() {
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                if (value == null) return null;
                try {
                    NumberFormat nf = NumberFormat.getFormat("##0.00");
                    return "$" + nf.format(((Number) value).floatValue());
                } catch (Exception e) {
                    return value.toString();
                }
            }
        });

        SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.setStep(0.01);
        unitCost.setEditorType(spinnerItem);

        ListGridField sku = new ListGridField("SKU");
        sku.setCanEdit(false);

        ListGridField description = new ListGridField("description");
        description.setShowHover(true);

        ListGridField category = new ListGridField("category");
        category.setCanEdit(false);

        ListGridField inStock = new ListGridField("inStock");
        inStock.setWidth(55);
        inStock.setAlign(Alignment.CENTER);

        ListGridField nextShipment = new ListGridField("nextShipent");
        nextShipment.setShowIfCondition(new ListGridFieldIfFunction() {
            public boolean execute(ListGrid grid, ListGridField field, int fieldNum) {
                return false;
            }
        });
*/
        setFields(appName, appDescription);
        setCanEdit(true);
        setCanDragRecordsOut(true);
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
        setDataSource(restDS);
    	
    
    }
}
