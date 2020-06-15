package com.fxml.secondPageController;

import java.net.URL;
import java.util.ResourceBundle;

import com.fxml.bean.ChaincodeItem;
import com.pojo.Chaincode;

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

		try {
			getInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据失败");
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
		Top.getParent().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		Top.getStyleClass().add("lbl-info");
		mes.getStyleClass().add("alert-danger");
		TopLabel.getStyleClass().add("lbl-info");
		lastPage.getStyleClass().add("btn-primary");
		nextPage.getStyleClass().add("btn-primary");
	}
	
	
	
	public void getInfo() throws Exception
	{
		
	}

	
}
