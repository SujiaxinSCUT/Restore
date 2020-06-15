package com.fxml.controller;


import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.PeerItem;
import com.pojo.Channel;
import com.pojo.Orderer;
import com.pojo.Peer;
import com.pojo.requestPOJO.OrgCreateRequest;
import com.pojo.requestPOJO.OrgUpdateRequest;
import com.pojo.requestPOJO.PeerCreateRequest;
import com.pojo.requestPOJO.PeerUpdateRequest;
import com.pojo.responsePOJO.PageableResponsePeer;
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

public class PeerController implements Initializable{

	
	@FXML
	private TableView<PeerItem> peerInfo;
	@FXML
	private TableColumn<PeerItem, Integer> id;
	@FXML
	private TableColumn<PeerItem, String> eventhubLocation;
	@FXML
	private TableColumn<PeerItem, String> LeagueName;
	@FXML
	private TableColumn<PeerItem, String> location;
	@FXML
	private TableColumn<PeerItem, String> name;
	@FXML
	private TableColumn<PeerItem, String> orgName;
	@FXML
	private TableColumn<PeerItem, Integer> ChannelNum;
	@FXML
	private TableColumn<PeerItem, Date> date;
	
	private ObservableList<PeerItem> PeerData = FXCollections.observableArrayList();
	

	
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
			mes.setText("获取数据错误");
			mes.setVisible(true);
			return;
		}
//		Peer peer=new Peer();
//		peer.setId(1);
//		PeerData.add(peer);
//		Peer peer1=new Peer();
//		peer1.setId(2);
//		PeerData.add(peer1);
		
	}

	public void initTable()
	{
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.eventhubLocation.setCellValueFactory(new PropertyValueFactory<>("eventhub_location"));
		this.eventhubLocation.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.LeagueName.setCellValueFactory(new PropertyValueFactory<>("leagueName"));
		this.LeagueName.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.location.setCellValueFactory(new PropertyValueFactory<>("location"));
		this.location.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.orgName.setCellValueFactory(new PropertyValueFactory<>("orgName"));
		this.orgName.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.ChannelNum.setCellValueFactory(new PropertyValueFactory<>("channelNum"));
		this.ChannelNum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.peerInfo.setItems(PeerData);
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
		PageableResponsePeer peerPage=UserAPIImpl.getPeersPageable("Bearer "+App.getRes().getAccess_token(),page, 10);
		Set<Peer> contents=peerPage.getContents();
		this.total_pages=peerPage.getTotal_pages();
		this.current_page=page;
		for (Peer p : contents) {
//			String[] s1 = p.getName().split("\\.");
//			p.setOrgName(s1[1]);
//			p.setChannelNum(p.getChannels().size());
////			p.setLeagueName(p.getOrg().getLeague().getName());
			PeerItem pi = new PeerItem(p);
			this.PeerData.add(pi);
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
			this.PeerData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.PeerData.clear();
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
			pane.genPeerPane();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		NewPaneController controller = new NewPaneController(pane);
		controller.peerPaneControl();
		ScrollPane sp=new ScrollPane(pane);
		sp.setPrefHeight(700);
		container.setTop(null);
		container.setBottom(null);
		container.setCenter(sp);
		mes.setVisible(false);

				
	}
	
	
	
	public void Update() {
		if(!this.peerInfo.getSelectionModel().isEmpty()) {
			PeerItem pi=this.peerInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(pi.getPeer());
			try {
				pane.genPeerPane();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            EditPaneController controller = new EditPaneController(pane, pi.getPeer());
            controller.peerPaneControl();
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



