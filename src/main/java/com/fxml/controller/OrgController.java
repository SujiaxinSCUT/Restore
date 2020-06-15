package com.fxml.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.OrgItem;
import com.pojo.League;
import com.pojo.Org;
import com.pojo.Peer;
import com.pojo.requestPOJO.OrgCreateRequest;
import com.pojo.requestPOJO.OrgUpdateRequest;
import com.pojo.responsePOJO.PageableResponseOrg;
import com.requestAPI.Impl.ManagerAPIImpl;
import com.requestAPI.Impl.UserAPIImpl;
import com.view.EditPane;
import com.view.NewPane;
import com.view.controller.EditPaneController;
import com.view.controller.NewPaneController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

public class OrgController implements Initializable{

	@FXML
	private TableView<OrgItem> orgInfo;
	@FXML
	private TableColumn<OrgItem, Integer> id;
	@FXML
	private TableColumn<OrgItem, Integer> peerCount;
	@FXML
	private TableColumn<OrgItem, String> name;
	@FXML
	private TableColumn<OrgItem, String> mspId;
	@FXML
	private TableColumn<OrgItem, String> orgAdmin;
	@FXML
	private TableColumn<OrgItem, String> orgDomainName;
	@FXML
	private TableColumn<OrgItem, String> peerType;
	@FXML
	private TableColumn<OrgItem, String> leagueId;

	private ObservableList<OrgItem> OrgData = FXCollections.observableArrayList();

	@FXML
	private BorderPane container;
	@FXML
	private Label mes;

	@FXML
	private HBox Top;

	@FXML
	private Button UpdateButton;
	@FXML
	private Button NewButton;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button lastPage;
	@FXML
	private Button nextPage;	
	
//	总页数
	private int total_pages;
//	当前页
	private int current_page;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mes.setVisible(false);
		container.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
	    this.setStyle();
		this.initTable();
		this.handleEvent();

		try {
			this.getInfo(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据出错");

		}

//		Org org=new Org();
//		org.setId(1);
//
//		this.OrgData.add(org);

	}

	public void initTable()
	{
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.peerCount.setCellValueFactory(new PropertyValueFactory<>("peerCount"));
		this.peerCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.mspId.setCellValueFactory(new PropertyValueFactory<>("msp_id"));
		this.mspId.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.orgAdmin.setCellValueFactory(new PropertyValueFactory<>("org_admin"));
		this.orgAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.orgDomainName.setCellValueFactory(new PropertyValueFactory<>("org_domain_name"));
		this.orgDomainName.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.peerType.setCellValueFactory(new PropertyValueFactory<>("peer_type"));
		this.peerType.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.leagueId.setCellValueFactory(new PropertyValueFactory<>("leagueName"));
		this.leagueId.setCellFactory(TextFieldTableCell.forTableColumn());
		this.orgInfo.setItems(OrgData);
	}

	
	
	public void setStyle()
	{
		mes.getStyleClass().add("alert-danger");
		Top.getStyleClass().add("lbl-info");
		UpdateButton.getStyleClass().add("btn-info");
		NewButton.getStyleClass().add("btn-info");
		DeleteButton.getStyleClass().add("btn-info");
		lastPage.getStyleClass().add("btn-primary");
		nextPage.getStyleClass().add("btn-primary");
	}

	public void getInfo(int page) throws Exception
	{
		PageableResponseOrg orgPage=UserAPIImpl.getOrgPageable("Bearer "+App.getRes().getAccess_token(), page, 5);
		Set<Org> contents=orgPage.getContents();
		this.total_pages=orgPage.getTotal_pages();
		this.current_page=page;
		for (Org o : contents) {
			OrgItem oi = new OrgItem(o);
			this.OrgData.add(oi);
		}
		if(current_page==0)
		{
			lastPage.setDisable(true);
		}else
		{
			lastPage.setDisable(false);
		}
		if(current_page==total_pages-1)
		{
			nextPage.setDisable(true);
		}else
		{
			nextPage.setDisable(false);
		}
	}
	
	public void handleEvent()
	{
		lastPage.setOnMouseClicked(e->{
			this.OrgData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.OrgData.clear();
			try {
				getInfo(current_page+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public void New()
	{
		NewPane pane=new NewPane();
		try {
			pane.genOrgPane();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        NewPaneController controller = new NewPaneController(pane);
        controller.orgPaneControl();
        
		ScrollPane sp=new ScrollPane(pane);
		sp.setPrefHeight(700);
		container.setTop(null);
		container.setBottom(null);
		container.setCenter(sp);
		mes.setVisible(false);

	}

	

	public void Update() {
		if(!this.orgInfo.getSelectionModel().isEmpty()) {
			OrgItem oi=this.orgInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(oi.getOrg());
			try {
				pane.genOrgPane();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			EditPaneController controller = new EditPaneController(pane, oi.getOrg());
			controller.orgPaneControl();

			ScrollPane sp=new ScrollPane(pane);
			sp.setPrefHeight(700);
			container.setTop(null);
			container.setBottom(null);
			container.setCenter(sp);
			mes.setVisible(false);
		}else {
			mes.setVisible(true);
			mes.setText("请选中要编辑的单元");
		}

		

	}


}