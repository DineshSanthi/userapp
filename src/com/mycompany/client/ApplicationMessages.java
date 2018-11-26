package com.mycompany.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface ApplicationMessages extends Messages{

	public static final ApplicationMessages INSTANCE = GWT.create(ApplicationMessages.class);
	
    @DefaultMessage("Enterprise")
    public String enterpriseSkinName();
    @DefaultMessage("Enterprise Blue")
    public String enterpriseBlueSkinName();
    @DefaultMessage("Graphite")
    public String graphiteSkinName();
    @DefaultMessage("Simplicity")
    public String simplicitySkinName();
    @DefaultMessage("Tahoe")
    public String tahoeSkinName();
    @DefaultMessage("Stratus")
    public String stratusSkinName();
    @DefaultMessage("Obsidian")
    public String obsidianSkinName();
    @DefaultMessage("TreeFrog")
    public String treeFrogSkinName();
    @DefaultMessage("Black Ops")
    public String blackOpsSkinName();
    @DefaultMessage("Fleet")
    public String fleetSkinName();
    
    @DefaultMessage("Density")
    public SafeHtml densityItemTitle();
    @DefaultMessage("Dense")
    public SafeHtml denseDensityName();
    @DefaultMessage("Compact")
    public SafeHtml compactDensityName();
    @DefaultMessage("Medium")
    public SafeHtml mediumDensityName();
    @DefaultMessage("Expanded")
    public SafeHtml expandedDensityName();
    @DefaultMessage("Spacious")
    public SafeHtml spaciousDensityName();  
    
    @DefaultMessage("Data Modeler - Administator View")
    public String viewName();
    @DefaultMessage("Inventory")
    public String tabName();
    @DefaultMessage("Objects")
    public String categorySectionTitle();
    @DefaultMessage("Results")
    public String resultSectionTitle();
    @DefaultMessage("Entity Details")
    public String detailSectionTitle();
    @DefaultMessage("Edit")
    public String editTabLabel();
    @DefaultMessage("Related Tables")
    public String relatedTableTabLabel();
    @DefaultMessage("Save")
    public String saveButtonLabel();
    @DefaultMessage("Delete")
    public String deleteButtonLabel();
    @DefaultMessage("Insert")
    public String insertButtonLabel();
    @DefaultMessage("Reference Data")
    public String relatedDataTabLabel();
    @DefaultMessage("Related Columns")
    public String relatedColumnTabLabel();
    
    //NavigationGrid
    @DefaultMessage("Application")
    public String applicationEntityLabel();
    @DefaultMessage("Table")
    public String tableEntityLabel();
    @DefaultMessage("Column")
    public String columnEntityLabel();
    @DefaultMessage("Reference Table")
    public String referenceTableEntityLabel();
    @DefaultMessage("Relationship")
    public String relationshipEntityLabel();
    @DefaultMessage("TableScript")
	public String tableScriptEventLabel();
    
    //Application
    @DefaultMessage("Name")
    public String appNameLabel();
    @DefaultMessage("Description")
    public String descriptionLabel();
    @DefaultMessage("Messaging Feature")
    public String messageFeatureLabel();
    @DefaultMessage("Web Application")
    public String webApplicationLabel();
    @DefaultMessage("Mobile Application")
    public String mobileApplicationLabel();
    @DefaultMessage("Default Theme")
    public String defaultThemeLabel();
    @DefaultMessage("Description(Tamil)")
    public String descriptonTamilLabel();
    @DefaultMessage("Description(Telugu)")
    public String descriptionTeluguLabel();
    
    //Column
    @DefaultMessage("Table Name")
    public String tableNameLabel();
    @DefaultMessage("Column Name")
    public String columnNameLabel();
    @DefaultMessage("Category")
    public String categoryLabel();
    @DefaultMessage("Column Type")
    public String columnTypeLabel();
    @DefaultMessage("Column Size")
    public String columnSizeLabel();
    @DefaultMessage("Required")
    public String requiredLabel();
    @DefaultMessage("Disabled")
    public String disabledLabel();
    @DefaultMessage("Hidden")
    public String hiddenLabel();
    @DefaultMessage("ReadOnly")
    public String readOnlyLabel();
    
    //Reference Data
    @DefaultMessage("Reference ID")
    public String referenceIdLabel();
    @DefaultMessage("Code")
    public String codeLabel();
    @DefaultMessage("Display Value")
    public String displayValueLabel();
    @DefaultMessage("Display Value(Tamil)")
    public String displayValueTamilLabel();
    @DefaultMessage("Display Value(Telugu)")
    public String displayValueTeluguLabel();
    
    //Relation
    @DefaultMessage("ID")
    public String idLabel();
    @DefaultMessage("Primary Table")
    public String primaryTableLabel();
    @DefaultMessage("Secondary Table")
    public String secondaryTableLabel();
    @DefaultMessage("Minimum Multiplicity")
    public String minimumMultiplicityLabel();
    @DefaultMessage("Maximum Multiplicity")
    public String maximumMultiplicityLabel();
    
    
    //Table
    @DefaultMessage("Set Expire(minutes)")
    public String ttlLabel();
    
    @DefaultMessage("Script")
    public String scriptTabLabel();
   	@DefaultMessage("PreSave Event")
   	public String preSaveEventLabel();
   	@DefaultMessage("PreUpdate Event")
   	public String preUpdateLabel();
   	@DefaultMessage("PostUpdate Event")
   	public String postUpdateLabel();
   	
   	//Scripts
   	@DefaultMessage("Before Save")
   	public String beforeSaveLabel();
   	@DefaultMessage("After Save")
	public String afterSaveLabel();
	@DefaultMessage("Before Fetch")
	public String beforeFetchLabel();
	@DefaultMessage("After Fetch")
	public String afterFetchLabel();
	@DefaultMessage("Before Delete")
	public String beforeDeleteLabel();
	@DefaultMessage("After Delete")
	public String afterDeleteLabel();
	@DefaultMessage("Before Insert")
	public String beforeInsertLabel();
	@DefaultMessage("After Insert")
	public String afterInsertLabel();
	@DefaultMessage("Required Event")
	public String requiredEventLabel();
	@DefaultMessage("Hidden Event")
	public String hiddenEventLabel();
	@DefaultMessage("Disabled Event")
	public String disabledEventLabel();
	@DefaultMessage("ReadOnly Event")
	public String readOnlyEventLabel();
	
   
    
    
    
    
    
    
	
}
