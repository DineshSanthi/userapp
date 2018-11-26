package com.mycompany.client;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FilterBuilder;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ResultsView extends VLayout {

	private String collectionName;

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	private ResultsLayout resultsLayout;

	private ListGrid listGrid;

	private ResultsPanelToolStrip toolStrip;

	private DataRestDS datasource;

	private DataDetailTabPane editView;

	public DataDetailTabPane getEditView() {
		return editView;
	}

	public void setEditView(DataDetailTabPane editView) {
		this.editView = editView;
	}

	public DataRestDS getDatasource() {
		return datasource;
	}

	public void setDatasource(DataRestDS datasource) {
		this.datasource = datasource;
	}

	public void init(String collectionName) {
		this.collectionName = collectionName;
		this.datasource = new DataRestDS(collectionName);
		DataSource relatedDataDS = new RelationDataRestDS(collectionName);
		
		listGrid = new RelatedColumnListGrid(collectionName, datasource);
		toolStrip = new ResultsPanelToolStrip(listGrid,collectionName, datasource);
		editView = new DataDetailTabPane(collectionName, listGrid, datasource,relatedDataDS);

		final DynamicForm form = new DynamicForm();
		form.setIsGroup(true);
		form.setGroupTitle(collectionName + " Filter");
		form.setNumCols(2);
		form.setDataSource(datasource);

		ToolStripButton filterButton = new ToolStripButton("Filter");
		filterButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final Window winModal = new Window();
				winModal.setAutoSize(true);
				winModal.setTitle("Search Record");
				winModal.setShowMinimizeButton(false);
				winModal.setIsModal(true);
				winModal.setShowModalMask(true);
				winModal.centerInPage();
				winModal.addCloseClickHandler(new CloseClickHandler() {
					public void onCloseClick(CloseClickEvent event) {
						winModal.destroy();
					}
				});
				IButton okButton = new IButton("OK");
				okButton.setAlign(Alignment.CENTER);
				okButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {

						Criteria criteria = new Criteria();
						form.getFields();
						for (FormItem field : form.getFields()) {

							criteria.addCriteria(field.getName(), field.getValue());
						}

						getListGrid().invalidateCache();
						getListGrid().fetchData(criteria);
						winModal.hide();
					}
				});

				VLayout layout1 = new VLayout();
				layout1.addMember(form);
				layout1.addMember(okButton);
				winModal.addItem(layout1);
				winModal.show();
			}
		});
		toolStrip.addButton(filterButton);
		addMember(toolStrip);
		addMember(listGrid);
	}

	public ResultsLayout getResultsLayout() {
		return resultsLayout;
	}

	public void setResultsLayout(ResultsLayout resultsLayout) {
		this.resultsLayout = resultsLayout;
	}

	public ListGrid getListGrid() {
		return listGrid;
	}

	public void setListGrid(ListGrid listGrid) {
		this.listGrid = listGrid;
	}

	public ResultsPanelToolStrip getToolStrip() {
		return toolStrip;
	}

	public void setToolStrip(ResultsPanelToolStrip toolStrip) {
		this.toolStrip = toolStrip;
	}

}
