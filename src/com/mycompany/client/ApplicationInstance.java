package com.mycompany.client;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class ApplicationInstance extends HLayout {
	private NavigatorTreeGrid navigatorTree;
	private ResultsLayout resultsLayout;
	private EditLayout editLayout;
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	private ResultsView resultsView = new ResultsView();
	private Map<String,ResultsView> resultViewProvider = new HashMap<String,ResultsView>();
			
	public ApplicationInstance() {

		this.setWidth100();
		this.setHeight100();
		this.setLayoutMargin(10);

		DataSource tableDS = new TableRestDS();

		navigatorTree = new NavigatorTreeGrid(tableDS);
		navigatorTree.setAutoFetchData(true);

		navigatorTree.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				String id = event.getRecord().getAttribute("tableName");
				findObjects(id);
			}
		});

		RPCManager.setAllowCrossDomainCalls(true);


		resultsLayout = new ResultsLayout();
		editLayout = new EditLayout();

		SectionStack leftSideLayout = new SectionStack();
		leftSideLayout.setWidth(280);
		leftSideLayout.setShowResizeBar(true);
		leftSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		leftSideLayout.setAnimateSections(true);

		SectionStackSection suppliesCategorySection = new SectionStackSection(Constants.categorySectionTitle());
		suppliesCategorySection.setExpanded(true);
		suppliesCategorySection.setItems(navigatorTree);

		leftSideLayout.setSections(suppliesCategorySection);

		SectionStack rightSideLayout = new SectionStack();
		rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		rightSideLayout.setAnimateSections(true);

		SectionStackSection resultsSection = new SectionStackSection(Constants.resultSectionTitle());
		resultsSection.addItem(resultsLayout);
		resultsSection.setExpanded(true);

		SectionStackSection itemDetailsSection = new SectionStackSection(Constants.detailSectionTitle());


		itemDetailsSection.setItems(editLayout);
		itemDetailsSection.setExpanded(true);

		rightSideLayout.setSections(resultsSection, itemDetailsSection);

		this.addMember(leftSideLayout);
		this.addMember(rightSideLayout);

	}


	public void findObjects(final String collectionName) {
		if(resultsLayout.getMembersLength() > 0)
		resultsLayout.removeMember(resultsLayout.getMember(0));
		if(editLayout.getMembersLength() > 0)
        editLayout.removeMember(editLayout.getMember(0));
        if (resultViewProvider.containsKey(collectionName))
        {
        	resultsView = resultViewProvider.get(collectionName);
			resultsLayout.addMember(resultsView);
			editLayout.addMember(resultsView.getEditView());
        }
        else
        {
 		   RPCRequest request = new RPCRequest();
 		   request.setHttpMethod("GET");
 		   request.setContentType("application/json");
 		   request.setActionURL(RepoConfiguration.getServiceUrl() + "column/definition/" + collectionName);

 		   RPCManager.sendRequest(request, 
 		       new RPCCallback () {
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							RepoConfiguration.setDefinition(collectionName, response.getDataAsObject());
							resultsView = new ResultsView();
							resultsView.init(collectionName);
							resultsLayout.addMember(resultsView);
							editLayout.addMember(resultsView.getEditView());
							resultViewProvider.put(collectionName, resultsView);
							
						}
 		       }
 		   );
        }
	}

}
