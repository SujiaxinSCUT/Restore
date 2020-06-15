package com.fxml.controller;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.CaItem;
import com.pojo.Ca;
import com.pojo.Org;
import com.pojo.Peer;
import com.pojo.requestPOJO.LeagueManageRequest;
import com.pojo.responsePOJO.PageableResponseOrg;
import com.requestAPI.Impl.ManagerAPIImpl;

import com.requestAPI.Impl.UserAPIImpl;
import com.view.EditPane;
import com.view.NewPane;

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

public class CaController implements Initializable{

	@FXML
	private TableView<CaItem> CaInfo;
	@FXML
	private TableColumn<CaItem, Integer> id;
	@FXML
	private TableColumn<CaItem, String> name;
	@FXML
	private TableColumn<CaItem, String> admin;
	@FXML
	private TableColumn<CaItem, String> location;
	@FXML
	private TableColumn<CaItem, String> adminpw;
	
	private ObservableList<CaItem> CaData = FXCollections.observableArrayList();

	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mes.setVisible(false);
		this.setStyle();
		this.initTable();
		this.handleEvent();
		container.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		try {
			getInfo(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据失败");
		}
	}
	
	private void getInfo(int page) throws Exception{
		// TODO Auto-generated method stub
		PageableResponseOrg orgPageable = UserAPIImpl.getOrgPageable("Bearer " + App.getRes().getAccess_token(), page, 10);
		Set<Org> contents = orgPageable.getContents();
		this.total_pages=orgPageable.getTotal_pages();
		this.current_page=page;
		for (Org o : contents) {
			Set<Ca> cas = o.getCas();
			for (Ca c : cas) {
				CaItem ci = new CaItem(c);
				this.CaData.add(ci);
			}
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
	
	public void setStyle()
	{
		mes.getStyleClass().add("alert-danger");
		Top.getStyleClass().add("lbl-info");
//		UpdateButton.getStyleClass().add("btn-info");
		NewButton.getStyleClass().add("btn-info");
		DeleteButton.getStyleClass().add("btn-info");
		lastPage.getStyleClass().add("btn-primary");
		nextPage.getStyleClass().add("btn-primary");
	}
	
	public void handleEvent()
	{
		lastPage.setOnMouseClicked(e->{
			this.CaData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.CaData.clear();
			try {
				getInfo(current_page+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public void initTable()
	{
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.admin.setCellValueFactory(new PropertyValueFactory<>("admin"));
		this.admin.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.location.setCellValueFactory(new PropertyValueFactory<>("location"));
		this.location.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.adminpw.setCellValueFactory(new PropertyValueFactory<>("adminpw"));
		this.adminpw.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		this.CaInfo.setItems(CaData);
	}
	
	
	
	public void New()
	{
		NewPane pane=new NewPane();
		try {
			pane.genCAPane();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		pane.getComeBack().setOnAction(e->{
			try {
				App.setContent("fxml/CA");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		ScrollPane sp=new ScrollPane(pane);
		sp.setPrefHeight(700);
		container.setTop(null);
		container.setBottom(null);
		container.setCenter(pane);
		mes.setVisible(false);

				
	}
	
	

	
	public void Update() {
		if(!this.CaInfo.getSelectionModel().isEmpty()) {
			CaItem caItem=this.CaInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(caItem.getCa());
			try {
				pane.genCAPane();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			pane.getComeBack().setOnAction(e->{
				try {
					App.setContent("fxml/CA");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			ScrollPane sp=new ScrollPane(pane);
			sp.setPrefHeight(700);
			container.setTop(null);
			container.setBottom(null);
			container.setCenter(pane);
			mes.setVisible(false);

		}else {
			mes.setVisible(true);
			mes.setText("请选中要编辑的单元");
		}
		
		


	}

	
}
