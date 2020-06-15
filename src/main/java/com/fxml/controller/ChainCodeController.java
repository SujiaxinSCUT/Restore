package com.fxml.controller;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.ChaincodeItem;
import com.fxml.bean.OrdererItem;
import com.pojo.Block;
import com.pojo.Chaincode;
import com.pojo.Orderer;
import com.pojo.Peer;
import com.pojo.responsePOJO.PageableResponseChaincode;
import com.pojo.responsePOJO.PageableResponseOrderer;
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

public class ChainCodeController implements Initializable{

	@FXML
	private TableView<ChaincodeItem> chainInfo;
	@FXML
	private TableColumn<ChaincodeItem, Integer> id;
	@FXML
	private TableColumn<ChaincodeItem, String> name;
	@FXML
	private TableColumn<ChaincodeItem, String> path;
	@FXML
	private TableColumn<ChaincodeItem, String> sourcepath;
	@FXML
	private TableColumn<ChaincodeItem, String> version;
	
    private ObservableList<ChaincodeItem> ChaincodeData = FXCollections.observableArrayList();

	
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
		container.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		setStyle();
     
		this.initTable();
       
		try {
			getInfo(0,5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setText("获取数据失败");
			mes.setVisible(true);
		}
	}

	public void initTable()
	{
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.path.setCellValueFactory(new PropertyValueFactory<>("path"));
		this.path.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.sourcepath.setCellValueFactory(new PropertyValueFactory<>("sourcePath"));
		this.sourcepath.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.version.setCellValueFactory(new PropertyValueFactory<>("version"));
		this.version.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.chainInfo.setItems(this.ChaincodeData);
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
	
	public void New()
	{
		NewPane pane=new NewPane();
		try {
			pane.genChaincodePane();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		pane.getComeBack().setOnAction(e->{
			try {
				App.setContent("fxml/chainCode");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		ScrollPane sp=new ScrollPane(pane);
		sp.setPrefHeight(700);
		container.setTop(null);
		container.setBottom(null);
		container.setCenter(sp);
		mes.setVisible(false);
				
	}
	
	
	
	public void Update() {
		if(!this.chainInfo.getSelectionModel().isEmpty()) {
			ChaincodeItem cci=this.chainInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(cci.getChaincode());
			try {
				pane.genChaincodePane();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			pane.getComeBack().setOnAction(e->{
				try {
					App.setContent("fxml/chainCode");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
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
	
	public void getInfo(int page,int size) throws Exception {
		PageableResponseChaincode chaincodePage=UserAPIImpl.getChaincodePageable("Bearer "+App.getRes().getAccess_token(),page, size);
		Set<Chaincode> contents=chaincodePage.getContents();
		for(Chaincode o:contents) {
			ChaincodeItem oi = new ChaincodeItem(o);
			this.ChaincodeData.add(oi);
		}
		this.total_pages=chaincodePage.getTotal_pages();
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
}
