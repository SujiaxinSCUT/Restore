package com.fxml.controller;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.OrdererItem;
import com.pojo.Chaincode;
import com.pojo.League;
import com.pojo.Orderer;
import com.pojo.requestPOJO.OrdererCreateRequest;
import com.pojo.requestPOJO.OrdererUpdateRequest;
import com.pojo.requestPOJO.OrgCreateRequest;
import com.pojo.requestPOJO.OrgUpdateRequest;
import com.pojo.responsePOJO.PageableResponseOrderer;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

public class OrdererController implements Initializable{

	@FXML
	private TableView<OrdererItem> ordererInfo;
	@FXML
	private TableColumn<OrdererItem, Integer> id;
	@FXML
	private TableColumn<OrdererItem, String> name;
	@FXML
	private TableColumn<OrdererItem, String> domain_suffix;
	@FXML
	private TableColumn<OrdererItem, String> location;
	@FXML
	private TableColumn<OrdererItem, String> leagueName;
	

    
	@FXML
	private HBox Top;
	@FXML
	private Label mes;
	@FXML
	private BorderPane container;

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
	
	private ObservableList<OrdererItem> OrdererData = FXCollections.observableArrayList();
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mes.setVisible(false);
		container.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		this.setStyle();
		this.InitTable();
		this.handleEvent();
		try {
			getInfo(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据出错");
			return;
		}
	}
	
	public void InitTable()
	{
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.domain_suffix.setCellValueFactory(new PropertyValueFactory<>("domain_suffix"));
		this.domain_suffix.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.location.setCellValueFactory(new PropertyValueFactory<>("location"));
		this.location.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.leagueName.setCellValueFactory(new PropertyValueFactory<>("leagueName"));
		this.leagueName.setCellFactory(TextFieldTableCell.forTableColumn());
		this.ordererInfo.setItems(this.OrdererData);
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
		PageableResponseOrderer ordererPage=UserAPIImpl.getOrdererPageable("Bearer "+App.getRes().getAccess_token(),page, 10);
		Set<Orderer> contents=ordererPage.getContents();
		for(Orderer o:contents) {
			OrdererItem oi = new OrdererItem(o);
			this.OrdererData.add(oi);
		}
		this.total_pages=ordererPage.getTotal_pages();
		this.current_page=page;
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
			this.OrdererData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.OrdererData.clear();
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
			pane.genOrdererPane();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        NewPaneController controller = new NewPaneController(pane);
        controller.ordererPaneControl();
		ScrollPane sp=new ScrollPane(pane);
		sp.setPrefHeight(700);
		container.setTop(null);
		container.setBottom(null);
		container.setCenter(sp);
		mes.setVisible(false);


				
	}
	
	
	public void Update() {
		if(!this.ordererInfo.getSelectionModel().isEmpty()) {
			OrdererItem oi=this.ordererInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(oi.getOrderer());
			try {
				pane.genOrdererPane();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			EditPaneController controller = new EditPaneController(pane, oi.getOrderer());
			controller.ordererPaneControl();

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
