package com.fxml.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.LeagueItem;
import com.pojo.Channel;
import com.pojo.League;
import com.pojo.Org;
import com.pojo.requestPOJO.LeagueManageRequest;
import com.pojo.requestPOJO.PeerCreateRequest;
import com.pojo.requestPOJO.PeerUpdateRequest;
import com.pojo.responsePOJO.PageableResponseLeague;
import com.pojo.responsePOJO.PageableResponseOrderer;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class LeagueController implements Initializable{

	@FXML
	private TableView<LeagueItem> leagueInfo;
	@FXML
	private TableColumn<LeagueItem, Integer> id;
	@FXML
	private TableColumn<LeagueItem, Integer> ordererCount;
	@FXML
	private TableColumn<LeagueItem, Integer> orgCount;
	@FXML
	private TableColumn<LeagueItem, String> name;
	@FXML
	private TableColumn<LeagueItem, Date> date;
	
	private ObservableList<LeagueItem> LeagueData = FXCollections.observableArrayList();

	
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
	@FXML
	private Label mes;
	
	@FXML
	private BorderPane container;
	
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
				getInfo(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mes.setVisible(true);
				mes.setText("获取数据失败");
				return;
			
			}
		
	}
	
	public void getInfo(int page) throws Exception
	{		
			PageableResponseLeague leaguesPageable = UserAPIImpl.getLeaguesPageable("Bearer "+App.getRes().getAccess_token(), page, 5);
			Set<League> contents = leaguesPageable.getContents();
			this.total_pages=leaguesPageable.getTotal_pages();
			this.current_page=page;
			for (League l : contents) {

//				PageableResponseOrg orgByLeagueIdPageable = UserAPIImpl.getOrgByLeagueIdPageable("Bearer " + App.getRes().getAccess_token(),
//						l.getId(), 0, 10);
//				int orgs = orgByLeagueIdPageable.getTotal_elements();
//
//				PageableResponseOrderer ordererByLeagueIdPageable = UserAPIImpl.getOrdererByLeagueIdPageable("Bearer " + App.getRes().getAccess_token(),
//						l.getId(), 0, 10);
//				int orderers = ordererByLeagueIdPageable.getTotal_elements();
                LeagueItem li = new LeagueItem(l);
                this.LeagueData.add(li);
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
	
	public void initTable()
	{
		leagueInfo.setEditable(true);
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.ordererCount.setCellValueFactory(new PropertyValueFactory<>("ordererCount"));
		this.ordererCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.orgCount.setCellValueFactory(new PropertyValueFactory<>("orgCount"));
		this.orgCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.date.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
		
		this.leagueInfo.setItems(LeagueData);
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
	
	public void New() {
		NewPane pane=new NewPane();
		pane.genLeaguePane();
        NewPaneController controller = new NewPaneController(pane);
        controller.leaguePaneControl();
		container.setTop(null);
		container.setBottom(null);
		container.setCenter(pane);
		mes.setVisible(false);
	}
	
	
	public void handleEvent()
	{
		lastPage.setOnMouseClicked(e->{
			this.LeagueData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.LeagueData.clear();
			try {
				getInfo(current_page+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	

	

	
	public void Update() throws Exception {
		
		
		if(!this.leagueInfo.getSelectionModel().isEmpty()) {
			LeagueItem li=this.leagueInfo.getSelectionModel().getSelectedItem();
			EditPane pane=new EditPane();
			pane.setModel(li.getLeague());
			pane.genLeaguePane();
			EditPaneController controller = new EditPaneController(pane,li.getLeague());
			controller.leaguePaneControl();

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
