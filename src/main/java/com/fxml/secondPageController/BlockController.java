package com.fxml.secondPageController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.App;
import com.fxml.bean.BlockItem;
import com.pojo.Block;
import com.pojo.Channel;
import com.pojo.responsePOJO.PageableResponseBlock;
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

public class BlockController implements Initializable{

	@FXML
	private TableView<BlockItem> blockInfo;
	@FXML
	private TableColumn<BlockItem, Integer> blockNumber;
	@FXML
	private TableColumn<BlockItem, Integer> txCount;
	@FXML
	private TableColumn<BlockItem,String> blockHash;
	@FXML
	private TableColumn<BlockItem,String> previousHash;
	@FXML
	private TableColumn<BlockItem,String> dataHash;
	@FXML
	private TableColumn<BlockItem,String> channelId;
	
	private ObservableList<BlockItem> BlockData = FXCollections.observableArrayList();

	
	
	
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
	
    private int ChannelId;
	
//	总页数
	private int total_pages;
//	当前页
	private int current_page;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mes.setVisible(false);
//		Block block=new Block();
//        block.setBlock_hash("6b415e600accc57f9db6c96f218f95480ebe6008e39ef4506ab94017153e7db0");
//        block.setBlock_number(92);
//        block.setData_hash("6b415e600accc57f9db6c96f218f95480ebe6008e39ef4506ab94017153e7db0");
//        block.setId(1);
//        block.setPrevious_hash("6b415e600accc57f9db6c96f218f95480ebe6008e39ef4506ab94017153e7db0");
//        block.setTx_count(10);
//        BlockData.add(block);
        this.setStyle();
        this.initTable();
        this.handleEvent();


        
	}
	
	public void setChannelId(int channelId)
	{
		this.ChannelId=channelId;
		try {
			getInfo(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据失败");
			
		}
	}
	
	public void initTable()
	{
		this.blockNumber.setCellValueFactory(new PropertyValueFactory<>("block_number"));
		this.blockNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.txCount.setCellValueFactory(new PropertyValueFactory<>("tx_count"));
		this.txCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		this.blockHash.setCellValueFactory(new PropertyValueFactory<>("block_hash"));
		this.blockHash.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.previousHash.setCellValueFactory(new PropertyValueFactory<>("previous_hash"));
		this.previousHash.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.dataHash.setCellValueFactory(new PropertyValueFactory<>("data_hash"));
		this.dataHash.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.channelId.setCellValueFactory(new PropertyValueFactory<>("channelName"));
		this.channelId.setCellFactory(TextFieldTableCell.forTableColumn());
		
		this.blockInfo.setItems(BlockData);
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
		PageableResponseBlock blockPage=UserAPIImpl.getDescBlocksByChannelIdPageable("Bearer "+App.getRes().getAccess_token(), ChannelId, page, 20);
		Set<Block> contents=blockPage.getContents();
		this.total_pages=blockPage.getTotal_pages();
		this.current_page=page;
		for (Block b : contents) {
			BlockItem bi = new BlockItem(b);
			this.BlockData.add(bi);
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
			this.BlockData.clear();
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
			this.BlockData.clear();
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
