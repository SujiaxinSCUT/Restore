package com.fxml.secondPageController;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.TransactionItem;
import com.pojo.Chaincode;
import com.pojo.Transaction;
import com.pojo.responsePOJO.PageableResponseTransaction;
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
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class TransactionController implements Initializable{

	@FXML
	private TableView<TransactionItem>  TranInfo;
	@FXML
	private TableColumn<TransactionItem, String> channel_id;
	@FXML
	private TableColumn<TransactionItem, String> transaction_id;
	@FXML
	private TableColumn<TransactionItem, String> type;
	@FXML
	private TableColumn<TransactionItem, Boolean> valid;
	@FXML
	private TableColumn<TransactionItem, Date> date;
	
	private ObservableList<TransactionItem> TransactionData = FXCollections.observableArrayList();

	
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
		this.InitTable();
		this.handleEvent();

		try {
			getInfo(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据失败");
		}
	}
	
	public void InitTable()
	{
		TranInfo.setEditable(true);
		this.channel_id.setCellValueFactory(new PropertyValueFactory<>("channel_id"));
		this.channel_id.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.transaction_id.setCellValueFactory(new PropertyValueFactory<>("transaction_id"));
		this.transaction_id.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.type.setCellValueFactory(new PropertyValueFactory<>("type"));
		this.type.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.valid.setCellValueFactory(new PropertyValueFactory<>("valid"));
		this.valid.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
		
		this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.date.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
		
		this.TranInfo.setItems(this.TransactionData);
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
		PageableResponseTransaction tranPage=UserAPIImpl.getDescTransactionsPageable("Bearer "+App.getRes().getAccess_token()
				, page, 20);
		Set<Transaction> contents=tranPage.getContents();
        this.total_pages=tranPage.getTotal_pages();
        this.current_page=page;
		for(Transaction t : contents) {
			TransactionItem ti = new TransactionItem(t);
			this.TransactionData.add(ti);
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
			this.TransactionData.clear();
			try {
				getInfo(current_page-1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				mes.setVisible(true);
				mes.setText("获取数据失败");
			}
		});
		nextPage.setOnMouseClicked(e->{
			this.TransactionData.clear();
			try {
				getInfo(current_page+1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				mes.setVisible(true);
				mes.setText("获取数据失败");
			}
		});
	}

	
}
