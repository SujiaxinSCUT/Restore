package com.fxml.secondPageController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.OrgItem;
import com.pojo.Org;
import com.pojo.Peer;
import com.pojo.responsePOJO.PageableResponseOrg;
import com.requestAPI.Impl.UserAPIImpl;

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
	private TableColumn<OrgItem, Integer> userCount;
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
	private TableColumn<OrgItem, Integer> leagueId;
	
	private ObservableList<OrgItem> OrgData = FXCollections.observableArrayList();

	
	@FXML
	private HBox Top;
	@FXML
	private Label TopLabel;
	@FXML
	private Label mes;
	
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

	try {
		this.getInfo(0);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		mes.setVisible(true);
		mes.setText("获取数据出错");
		
	}
	}
	
	public void initTable()
	{
		orgInfo.setEditable(true);
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
		
		this.orgInfo.setItems(OrgData);
	}
	

	
	public void setStyle()
	{
		Top.getParent().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		Top.getStyleClass().add("lbl-info");
		mes.getStyleClass().add("alert-danger");
		TopLabel.getStyleClass().add("lbl-info");
		lastPage.getStyleClass().add("btn-primary");
		nextPage.getStyleClass().add("btn-primary");
	}
	
	public void getInfo(int page) throws Exception
	{
		
		PageableResponseOrg orgPage=UserAPIImpl.getOrgPageable("Bearer "+App.getRes().getAccess_token(), page, 10);
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
				mes.setVisible(true);
				mes.setText("获取数据出错");
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.OrgData.clear();
			try {
				getInfo(current_page+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				mes.setVisible(true);
				mes.setText("获取数据出错");
			}
		});
	}
}
