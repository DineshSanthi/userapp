package com.mycompany.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class DataDetailTabPane extends AbstractTabPane {

	private ListGrid editGrid;
	private Label editorLabel;
	private static ListGrid relatedDataGrid;
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	private static String COLUMN_DESC = "columnDesc";
	private static String COLUMN_TYPE = "columnType";
	private ResultsPanelToolStrip toolStrip;
	private ResultsView resultsview;

	public DataDetailTabPane(String collectionName, final ListGrid listGrid, DataSource dataSource,
			DataSource relatedDataDS) {

		editorLabel = new Label();
		editorLabel.setWidth100();
		editorLabel.setHeight100();
		editorLabel.setAlign(Alignment.CENTER);
		editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

		final DynamicForm form = new DynamicForm();
		form.setIsGroup(true);
		form.setGroupTitle(collectionName + " Details");
		form.setNumCols(4);
		form.setDataSource(dataSource);

		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				form.reset();
				form.editSelectedData(listGrid);
			}
		});
		final EditPanelToolStrip toolstrip = new EditPanelToolStrip(form);

		Tab editTab = new Tab(Constants.editTabLabel());
		editTab.setIcon("demoApp/icon_edit.png");
		editTab.setWidth(70);

		VLayout layout = new VLayout();
		layout.addMember(toolstrip);
		layout.addMember(form);
		editTab.setPane(layout);

		List<String> relationList = RepoConfiguration.getRelationList(collectionName);

		Tab[] tabArr = new Tab[relationList.size() + 1];
		tabArr[0] = editTab;

		for (int j = 0; j < relationList.size(); j++) {
			Tab tab = new Tab(relationList.get(j));
			tab.setIcon("demoApp/icon_edit.png");
			tab.setWidth(70);
			DataRestDS relationDS = new DataRestDS(relationList.get(j));
			final RelatedColumnListGrid relatedTableGrid = new RelatedColumnListGrid(relationList.get(j), relationDS);
			final DynamicForm insertForm = new DynamicForm();
			insertForm.setIsGroup(true);
			insertForm.setGroupTitle(relationList.get(j) + " Details");
			insertForm.setNumCols(4);
			insertForm.setDataSource(dataSource);

			TextItem id = new TextItem("id");
			id.setTitle("ID");
			id.setDisabled(true);
			id.setHidden(true);
			id.setWidth("*");

			TextItem primaryTableRecordId = new TextItem("primaryTableRecordId");
			primaryTableRecordId.setHidden(true);

			TextItem secondaryTableName = new TextItem("secondaryTableName");
			secondaryTableName.setHidden(true);

			SelectItem relatedData = new SelectItem("SecondaryTableRecordId");
			relatedData.setTitle("Teachers");
			relatedData.setWidth(240);
			relatedData.setOptionDataSource(relationDS);
			relatedData.setValueField("id");
			relatedData.setDisplayField("TeachersName");
			relatedData.setPickListWidth(450);
			relatedData.setRequired(true);
			
			insertForm.setFields(relatedData, id, primaryTableRecordId, secondaryTableName);
			
			String relatedTableName = relationList.get(j);

			final RelationLinkToolStrip relationToolStrip = new RelationLinkToolStrip(insertForm,listGrid,relatedTableName);
			
			tabArr[j + 1] = tab;
			final Criteria criteria = new Criteria();
			VLayout layout2 = new VLayout();
			layout2.addMember(relationToolStrip);
			listGrid.addRecordClickHandler(new RecordClickHandler() {
				public void onRecordClick(RecordClickEvent event) {
					criteria.addCriteria("SecondaryTableRecordId", listGrid.getAttribute("SecondaryTableRecordId"));
				}
			});
			relatedTableGrid.invalidateCache();
			relatedTableGrid.fetchData(criteria);
			
			layout2.addMember(relatedTableGrid);
			tabArr[j + 1].setPane(layout2);
		}
		setTabs(tabArr);

	}
}
