package com.fxml.controller;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;


import com.App;
import com.fxml.bean.BlockItem;

import com.jfoenix.controls.JFXMasonryPane;

import com.pojo.Block;
import com.pojo.Channel;
import com.pojo.responsePOJO.*;
import com.requestAPI.Impl.UserAPIImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class HomePageController implements Initializable{

//	通道区块记录表
	@FXML
	private TableView<BlockItem> blockInfo;
	@FXML
	private TableColumn<BlockItem, Integer> HeightColumn;
	@FXML
	private TableColumn<BlockItem, Integer> TransactionsColumn;
    @FXML
    private TableColumn<BlockItem, String> BlockOfChannel;
    @FXML
    private TableColumn<BlockItem, String> dateColumn;
    
    private ObservableList<BlockItem> TotalblockData = FXCollections.observableArrayList();

    @FXML
	private TableView<BlockItem> blockInfo2;
    @FXML
	private TableColumn<BlockItem, Integer> HeightColumn2;
	@FXML
	private TableColumn<BlockItem, Integer> NumColumn;
    @FXML
    private TableColumn<BlockItem, String> PeerNameColumn;
    @FXML
    private TableColumn<BlockItem, String> ChanNameColumn;
    @FXML
    private TableColumn<BlockItem, String> BlockHashColumn;
    @FXML
    private TableColumn<BlockItem, Date> DateColumn;
    @FXML
    private TableColumn<BlockItem, String> SynColumn;
    
	// 通道区块比
	@FXML
	private PieChart PChartBlocksOfChannel;

	//通道交易比
	@FXML
	private PieChart PChartTXOfChannel;
    
	@FXML
	private LineChart<String, Number> LChartBlockOfChannel;
    @FXML
    private LineChart<String, Number> LChartOfBlock;
    @FXML
    private LineChart<String, Number> LChartOfTran;
    @FXML
    private LineChart<String, Number> LChartOfRwset;
	
    @FXML
    private HBox hb;
	@FXML
	private FlowPane container;
	@FXML
	private JFXMasonryPane mas1;
	@FXML
	private JFXMasonryPane mas2;
	@FXML
	private JFXMasonryPane mas3;
	
	
//	统计数据信息
	@FXML
	private Text peerCount;
	@FXML
	private Text channelCount;
	@FXML
	private Text chaincodeCount;
	@FXML
	private Text userCount;
	@FXML
	private Text orgCount;
	@FXML
	private Text leagueCount;
	@FXML
	private Text tranCount;
	@FXML
	private Text todayBlockNum;
	@FXML
	private Text todayTranNum;
	
	@FXML
	private Label BlockNum;
	@FXML
	private Label TranNum;
	@FXML
	private Label RwsetNum;
	
	@FXML
	private Label mes;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		setStyle();
		mes.setVisible(false);
		try {
			setLChartOfTran();
			setLChartOfBlock();
            setLChartBlockOfChannel();
            setLChartOfRwset();
			setPChartBlocksOfChannel();
			setPChartTXOfChannel();
            setCurrentDayData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		try {
			this.getBlocks();
			this.setCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
		}
		
		this.InitTable();
		App.width.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				if(newValue.doubleValue()<=1280) {
					hb.setPrefWidth(1280);
					container.setPrefWidth(newValue.doubleValue());
					mas1.setPrefWidth(newValue.doubleValue());
					mas2.setPrefWidth(newValue.doubleValue());
					mas3.setPrefWidth(newValue.doubleValue());
				}else {
					hb.setPrefWidth(newValue.doubleValue());
					container.setPrefWidth(1280);
					mas1.setPrefWidth(1280);
					mas2.setPrefWidth(1280);
					mas3.setPrefWidth(1280);
				}
			}
		});
	}
    
	public void InitTable()
	{
//		绑定表和bean
		blockInfo.setEditable(true);
    	this.HeightColumn.setCellValueFactory(new PropertyValueFactory<>("block_number"));    	
        this.TransactionsColumn.setCellValueFactory(new PropertyValueFactory<>("tx_count"));        
    	this.BlockOfChannel.setCellValueFactory(new PropertyValueFactory<>("channelName"));    	
    	this.dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    	this.blockInfo.setItems(TotalblockData);
    	
    	
    	blockInfo2.setEditable(true);   	
    	this.HeightColumn2.setCellValueFactory(new PropertyValueFactory<>("block_number"));
        this.NumColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    	this.ChanNameColumn.setCellValueFactory(new PropertyValueFactory<>("channelName"));
    	this.BlockHashColumn.setCellValueFactory(new PropertyValueFactory<>("block_hash"));
    	this.DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    	this.blockInfo2.setItems(TotalblockData);
	}

	/**
	 * 设置饼图信息
	 */
	public void setPChartBlocksOfChannel() throws Exception {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		List<Channel> channelList = UserAPIImpl.getChannelList("Bearer " + App.getRes().getAccess_token());
		for (Channel channel : channelList) {
//			pieChartData.add(new PieChart.Data(channel.getName(),channel.getBlockHeight()));
			pieChartData.add(new PieChart.Data(channel.getName(),5));
		}

		PChartBlocksOfChannel.setData(pieChartData);
		PChartBlocksOfChannel.setLabelLineLength(10);

	}

	public void setPChartTXOfChannel() throws Exception {

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		List<Channel> channelList = UserAPIImpl.getChannelList("Bearer " + App.getRes().getAccess_token());
		int TxCount;
		for (Channel channel : channelList) {
			TxCount = 0;
			PageableResponseBlock blocksByChannelIdPageable = UserAPIImpl.getBlocksByChannelIdPageable("Bearer " + App.getRes().getAccess_token(),
					channel.getId(), 0, 10);

			Set<Block> contents = blocksByChannelIdPageable.getContents();

			for (Block block : contents) {
				TxCount += block.getTxCount();
			}

//			pieChartData.add(new PieChart.Data(channel.getName(),TxCount));
			pieChartData.add(new PieChart.Data(channel.getName(),5));
		}

		PChartTXOfChannel.setData(pieChartData);
		PChartTXOfChannel.setLabelLineLength(10);

	}
	
	public void setLChartBlockOfChannel() {

        Set<String> channelsName = new HashSet<>();
        HashMap<String, ArrayList<BlockItem>> blockOfChannel = new HashMap<>();
        for(BlockItem b : this.TotalblockData) {
        	channelsName.add(b.getChannelName());
        	if(blockOfChannel.containsKey(b.getChannelName())) {
        		blockOfChannel.get(b.getChannelName()).add(b);
        	}else {
        		ArrayList<BlockItem> value = new ArrayList<>();
        		value.add(b);
        		blockOfChannel.put(b.getChannelName(), value);
        	}
        }
        for(String channelName : channelsName) {
        	XYChart.Series<String, Number> series = new XYChart.Series<>();
        	series.setName(channelName);
        	ArrayList<BlockItem> itemList = blockOfChannel.get(channelName);
        	for(int i=0;i<itemList.size();i++) {
        		BlockItem bi = itemList.get(i);       		
        		String date = bi.getDate();
        		int count = bi.getTx_count();
        		for(int j = i+1;j<itemList.size();j++) {
        			BlockItem bki = itemList.get(j);
        			if(date.equals(bki.getDate())) {
        				count+=bki.getTx_count();
        				itemList.remove(bki);
        			}else {
        				break;
        			}
        		}
        		series.getData().add(new Data<>(date,count));
        		
        	}
         

        	LChartBlockOfChannel.getData().add(series);
        }

	}

	public void setCount() throws Exception
	{
		PageableResponseLeague leaguesPageable = UserAPIImpl.getLeaguesPageable("Bearer "+App.getRes().getAccess_token(), 0, 5);
		int leaguecount=leaguesPageable.getTotal_elements();
		
		PageableResponseOrg orgPage=UserAPIImpl.getOrgPageable("Bearer "+App.getRes().getAccess_token(), 0, 5);
		int orgcount=orgPage.getTotal_elements();
		
		PageableResponsePeer peerPage=UserAPIImpl.getPeersPageable("Bearer "+App.getRes().getAccess_token(), 0, 10);
		int peercount=peerPage.getTotal_elements();
		
		PageableResponseOrderer ordererPage=UserAPIImpl.getOrdererPageable("Bearer "+App.getRes().getAccess_token(),0, 10);
		int orderercount=ordererPage.getTotal_elements();
		
		PageableResponseChannel channelPage=UserAPIImpl.getChannelPageable("Bearer "+App.getRes().getAccess_token(),0, 10);
		int channelcount=channelPage.getTotal_elements();
		
		PageableResponseTransaction descTransactionsPageable = UserAPIImpl.getDescTransactionsPageable("Bearer " + App.getRes().getAccess_token(), 0, 10);
		int TxCount = descTransactionsPageable.getTotal_elements();
		
		PageableResponseBlock blockPage=UserAPIImpl.getBlocksPageable("Bearer "+App.getRes().getAccess_token(),0, 10);
		int blockCount= blockPage.getTotal_elements();
		
		PageableResponseRWSet rwsetPage=UserAPIImpl.getDescRWSetsPageable("Bearer "+App.getRes().getAccess_token(),0, 10);
		int rwSetCount = rwsetPage.getTotal_elements();
		
		PageableResponseChaincode chaincodePage=UserAPIImpl.getChaincodePageable("Bearer "+App.getRes().getAccess_token(),0, 5);
        int chainCodeCount = chaincodePage.getTotal_elements();
				
		tranCount.setText(String.valueOf(TxCount));
		TranNum.setText(String.valueOf(TxCount));
		leagueCount.setText(String.valueOf(leaguecount));
		orgCount.setText(String.valueOf(orgcount));
		peerCount.setText(String.valueOf(peercount));
		userCount.setText(String.valueOf(orderercount));
		channelCount.setText(String.valueOf(channelcount));
		BlockNum.setText(String.valueOf(blockCount));
		RwsetNum.setText(String.valueOf(rwSetCount));
		chaincodeCount.setText(String.valueOf(chainCodeCount));
	}
	
	
	public void getBlocks() throws Exception
	{
		PageableResponseBlock blockPage=UserAPIImpl.getDescBlocksPageable("Bearer "+App.getRes().getAccess_token(), 0, 20);
		Set<Block> contents=blockPage.getContents();
		for (Block b : contents) {
			BlockItem bi = new BlockItem(b);
			this.TotalblockData.add(bi);
		}
	}
	
	public void setLChartOfBlock() throws Exception {
        Map<Date,Integer> lineChartData = new HashMap<>();
        lineChartData.putAll(UserAPIImpl.getBlocksLastFiveDays("Bearer "+App.getRes().getAccess_token()).getContents());
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		for(Date date:lineChartData.keySet()) {
			series.getData().add(new Data<>(fmt.format(date),lineChartData.get(date)));
		}
    	LChartOfBlock.getData().add(series);
	}
	
	public void setLChartOfTran() throws Exception {
        Map<Date,Integer> lineChartData = new HashMap<>();
        lineChartData.putAll(UserAPIImpl.getTransactionsLastFiveDays("Bearer "+App.getRes().getAccess_token()).getContents());
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		for(Date date:lineChartData.keySet()) {
			series.getData().add(new Data<>(fmt.format(date),lineChartData.get(date)));
		}
    	LChartOfTran.getData().add(series);
	}
	
	public void setLChartOfRwset() throws Exception {
        Map<Date,Integer> lineChartData = new HashMap<>();
        lineChartData.putAll(UserAPIImpl.getRWSetsLastFiveDays("Bearer "+App.getRes().getAccess_token()).getContents());
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		for(Date date:lineChartData.keySet()) {
			series.getData().add(new Data<>(fmt.format(date),lineChartData.get(date)));
		}
		LChartOfRwset.getData().add(series);
	}
	
	public void setCurrentDayData() throws Exception{
		List<Block> blockList =  UserAPIImpl.getCurrentDayBlocks("Bearer "+App.getRes().getAccess_token());
		todayBlockNum.setText(String.valueOf(blockList.size()));
		int count = 0;
		for(Block b:blockList) {
			count += b.getTxCount();
		}
		todayTranNum.setText(String.valueOf(count));
	}
}
