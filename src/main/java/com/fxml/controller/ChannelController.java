 package com.fxml.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.ChannelItem;
import com.pojo.Channel;
import com.pojo.responsePOJO.ChannelResponse;
import com.pojo.responsePOJO.PageableResponseChannel;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

public class ChannelController implements Initializable{

	@FXML
	private TableView<ChannelItem> channelInfo;
	@FXML
	private TableColumn<ChannelItem, Long> blockHeight;
	@FXML
	private TableColumn<ChannelItem, Integer> chaincodecount;
	@FXML
	private TableColumn<ChannelItem, Integer> peerCount;
	@FXML
	private TableColumn<ChannelItem, String> name;
	@FXML
	private TableColumn<ChannelItem, String> objectHex;
	@FXML
	private TableColumn<ChannelItem, String> leagueId;
	
	private ObservableList<ChannelItem> ChannelData = FXCollections.observableArrayList();
	
	
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
		this.setStyle();
		this.initTable();
		this.handleEvent();

		try {
			this.getInfo(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据错误");
			return;
		}
	}
	
	public void initTable()
	{
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.blockHeight.setCellValueFactory(new PropertyValueFactory<>("block_height"));
		this.blockHeight.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
		
		this.chaincodecount.setCellValueFactory(new PropertyValueFactory<>("chaincodecount"));
		this.chaincodecount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.peerCount.setCellValueFactory(new PropertyValueFactory<>("peerCount"));
		this.peerCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.objectHex.setCellValueFactory(new PropertyValueFactory<>("objectHex"));
		this.objectHex.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.leagueId.setCellValueFactory(new PropertyValueFactory<>("leagueName"));
		this.leagueId.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.channelInfo.setItems(ChannelData);
		
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
		PageableResponseChannel channelPage = UserAPIImpl.getChannelPageable("Bearer " + App.getRes().getAccess_token()
				,page,10);
		Set<Channel> contents = channelPage.getContents();
		
		this.total_pages=channelPage.getTotal_pages();
		this.current_page=page;
		
		for(Channel c:contents)
		{
//			Channel newchannel = new Channel();
//			newchannel.setBlock_height(c.getBlockHeight());
//			newchannel.setName(c.getName());
//			newchannel.setId(c.getId());
//			newchannel.setLeague(c.getLeague());
//			newchannel.setBlocks(c.getBlocks());
//			newchannel.setObjectHex(c.getObjectHex());
//			newchannel.setPeers(c.getPeers());
			System.out.println(c.getLeague());
			ChannelItem cli = new ChannelItem(c);
			this.ChannelData.add(cli);
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
			this.ChannelData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.ChannelData.clear();
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
			pane.genChannelPane();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		pane.getComeBack().setOnAction(e->{
			try {
				App.setContent("fxml/channel");
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
		if(!this.channelInfo.getSelectionModel().isEmpty()) {
			ChannelItem cli=this.channelInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(cli.getChannel());
			try {
				pane.genChannelPane();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			pane.getComeBack().setOnAction(e->{
				try {
					App.setContent("fxml/channel");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

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
