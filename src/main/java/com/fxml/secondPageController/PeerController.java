package com.fxml.secondPageController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.PeerItem;
import com.pojo.Peer;
import com.pojo.responsePOJO.PageableResponsePeer;
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

public class PeerController implements Initializable{

	@FXML
	private TableView<PeerItem> peerInfo;
	@FXML
	private TableColumn<PeerItem, String> eventhubLocation;
	@FXML
	private TableColumn<PeerItem, String> eventhubName;
	@FXML
	private TableColumn<PeerItem, String> location;
	@FXML
	private TableColumn<PeerItem, String> name;
	
	private ObservableList<PeerItem> PeerData = FXCollections.observableArrayList();

	
	
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
					mes.setText("获取数据错误");
					
				}
	}

	public void initTable()
	{
		this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.name.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.eventhubLocation.setCellValueFactory(new PropertyValueFactory<>("eventhub_location"));
		this.eventhubLocation.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.eventhubName.setCellValueFactory(new PropertyValueFactory<>("eventhub_name"));
		this.eventhubName.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.location.setCellValueFactory(new PropertyValueFactory<>("location"));
		this.location.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.peerInfo.setItems(PeerData);
	}
	
	
	
	public void setStyle()
	{
		Top.getParent().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		Top.getStyleClass().add("lbl-info");
		TopLabel.getStyleClass().add("lbl-info");
		lastPage.getStyleClass().add("btn-primary");
		nextPage.getStyleClass().add("btn-primary");
	}
	
	public void getInfo(int page) throws Exception
	{
		
		PageableResponsePeer peerPage=UserAPIImpl.getPeersPageable("Bearer "+App.getRes().getAccess_token(), page, 10);
		Set<Peer> contents=peerPage.getContents();
		this.total_pages=peerPage.getTotal_pages();
		this.current_page=page;
		for(Peer p : contents) {
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
				mes.setVisible(true);
				mes.setText("获取数据错误");
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.PeerData.clear();
			try {
				getInfo(current_page+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				mes.setVisible(true);
				mes.setText("获取数据错误");
			}
		});
	}
}
