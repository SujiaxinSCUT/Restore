package com.fxml.controller;


import com.pojo.*;
import com.pojo.responsePOJO.PageableResponseTransaction;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.App;
import com.fxml.secondPageController.BlockController;
import com.pojo.responsePOJO.PageableResponseBlock;
import com.pojo.responsePOJO.PageableResponseOrg;
import com.requestAPI.Impl.UserAPIImpl;



public class ChannelPageController implements Initializable {

    /**
     *  各组织交易占比饼图
     */
    @FXML
    private PieChart pieChart;
    @FXML
    private Pane lineChartPane;

    /**
     *  设置点击事件，显示区块折线图
     */
    @FXML
    private Label lineChartOfBlock;
    
//    各统计数字
    @FXML
    private Text blockNum;
    @FXML
    private Text tranNum;
    @FXML
    private Text peerNum;
    @FXML
    private Text chaincodeNum;
    
    @FXML
    private Label blockNumLabel;
    @FXML
    private Label TranNumLabel;
    @FXML
    private Label PeerNumLabel;
    @FXML
    private Label chaincodeNumLabel;
    
    
    @FXML
    private HBox Top;
    
    
    
    
//    绑定区块信息表
    @FXML
    private TableView<Block> blockInfo;
    @FXML
    private TableColumn<Block, Integer> NumColumn;
    @FXML
    private TableColumn<Block, Integer> HeightColumn;
    @FXML
    private TableColumn<Block, Integer> txCountColumn;
    @FXML
    private TableColumn<Block, String> blockHash;
    @FXML
    private TableColumn<Block, String> previoushashColumn;
    @FXML
    private TableColumn<Block, String> datahash;
    
//     错误信息
    @FXML
    private Label mes;
    

//    绑定中部记录位置
    @FXML
    private HBox record;
//  登录
    @FXML
    private Label Login;
    
//    第二级导航栏
    @FXML
    private Button homeButton;
    @FXML
    private Button organization;
    @FXML
    private Button peer;
    @FXML
    private Button block;
    @FXML
    private Button chainCode;
    @FXML
    private Button transaction;

    @FXML
    private HBox hb1;
    @FXML
    private Label BPHLabel;
    @FXML
    private Label TPHLabel;
    @FXML
    private Label OPTLabel;
    @FXML
    private Label CBRLabel;
    
    /**
     *  设置点击事件，显示交易折线图
     */
    @FXML
    private Label lineChartOfTX;
    
//    底层面板
    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane container;
    @FXML
    private ScrollPane Container;
    
    private ObservableList<Block> blockData = FXCollections.observableArrayList();
    
    private ObservableList<Channel> channelData=FXCollections.observableArrayList();
    
    private int channelId;

    private String channelName;

    private int TxCount;
    

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

		channelId = App.selectedChannel.getId();
		channelName = App.selectedChannel.getName();
		mes.setVisible(false);
//		setStyle();
		App.bind(root);

		App.bind(container);
		handleEvent();
        try {
            initTableview();
			setPieChart();
			setLinechart();
            setCount();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("获取数据失败");
		}
    }

// 设置各种统计数字   
    public void setCount() throws IOException {
    	setNum();
    }

    public void setStyle()
    {
    	Top.getParent().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
    	
    	mes.getStyleClass().add("alert-danger");
    	
    	Top.getStyleClass().add("alert-success");
    	record.getStyleClass().add("lbl-info");
    	chaincodeNumLabel.getStyleClass().add("lbl-info");
    	blockNumLabel.getStyleClass().add("lbl-info");
    	TranNumLabel.getStyleClass().add("lbl-info");
    	PeerNumLabel.getStyleClass().add("lbl-info");
    	
    	hb1.getStyleClass().add("lbl-info");
    	BPHLabel.getStyleClass().add("lbl-info");
    	TPHLabel.getStyleClass().add("lbl-info");
    	OPTLabel.getStyleClass().add("lbl-info");
    	CBRLabel.getStyleClass().add("lbl-info");
    	
    	for(Node n:Top.getChildren())
    	{
    		n.getStyleClass().add("btn-success");
    	}
    }

    
    
    public int getChannelId() {
		return channelId;
	}
//	public void setChannelId(int channelId, String channelName) {
//		this.channelId = channelId;
//		this.channelName = channelName;
//        try {
//			
//			setPieChart();
////			setLinechart();
//            setCount();
//			this.initTableview();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			mes.setVisible(true);
//			mes.setText("获取数据失败");
//		}
//		
//	}
	/**
     * 设置饼图信息
     * @throws Exception 
     */
    public void setPieChart() throws Exception {

    	PageableResponseOrg orgPage=UserAPIImpl.getOrgPageable("Bearer "+App.getRes().getAccess_token()
    			, 0, 10);
    	Set<Org> contents=orgPage.getContents();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();

        Map<String , Integer> TxOfOrg = new HashMap<>();

        PageableResponseTransaction descTransactionsPageable = UserAPIImpl.getDescTransactionsPageable("Bearer " + App.getRes().getAccess_token(), 0, 10);
        int total = descTransactionsPageable.getTotal_elements();
        PageableResponseTransaction TXpageable = UserAPIImpl.getDescTransactionsPageable("Bearer " + App.getRes().getAccess_token(), 0, total);
        Set<Transaction> TXs = TXpageable.getContents();

        int TXnum = 0;
        for (Transaction t : TXs) {
            if (t.getChannel_id().equals(this.channelName)) {
                if (TxOfOrg.containsKey(t.getCreate_mspid())) {
                    int num = TxOfOrg.get(t.getCreate_mspid());
                    TxOfOrg.put(t.getCreate_mspid(), num + 1);
                } else {
                    TxOfOrg.put(t.getCreate_mspid(), 1);
                }
                ++TXnum;
            }
        }

        this.TxCount = TXnum;

        for (Org org : contents) {
            pieChartData.add(new PieChart.Data(org.getName(),TxOfOrg.get(org.getMsp_id())));
        }

        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(10);

    }

    /**
     * 设置折线图
     */
    public void setLinechart() {

        lineChartPane.getChildren().clear();

        NumberAxis xAxis = new NumberAxis(1,24,6);
        NumberAxis yAxis = new NumberAxis(1,50,10);
        xAxis.setLabel("Hours");
        yAxis.setLabel("Blocks");

        LineChart<Number,Number> lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Blocks/Hour");

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));

        lineChart.getData().add(series);
        lineChart.setMaxWidth(300);
        lineChart.setMaxHeight(310);

        lineChartPane.getChildren().add(lineChart);
    }
    
    
    
    
    public void setNum() throws IOException {
        PageableResponseBlock blocksByChannelIdPageable = UserAPIImpl.getBlocksByChannelIdPageable("Bearer " + App.getRes().getAccess_token(), this.channelId, 0, 5);
        Integer blockCount = blocksByChannelIdPageable.getTotal_elements();
        blockData.setAll(blocksByChannelIdPageable.getContents());
        blockNum.setText(String.valueOf(blockCount));

        tranNum.setText(String.valueOf(TxCount));
    }

//    绑定区块信息表和Bean
    public void initTableview() throws Exception
    {
		
		blockInfo.setEditable(true);
//    	区块信息表
    	this.HeightColumn.setCellValueFactory(new PropertyValueFactory<>("block_number"));
    	
    	this.txCountColumn.setCellValueFactory(new PropertyValueFactory<>("tx_count"));
    	
    	this.blockHash.setCellValueFactory(new PropertyValueFactory<>("block_hash"));
    	
    	this.previoushashColumn.setCellValueFactory(new PropertyValueFactory<>("previous_hash"));
    	
    	this.datahash.setCellValueFactory(new PropertyValueFactory<>("data_hash"));
    	
    
    	this.blockInfo.setItems(blockData);
    	
    }
    



	public void setCenter(String fxml)
    {
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/secondPage/"+fxml + ".fxml")); 
    	try {
			root.setCenter(fxmlLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			mes.setVisible(true);
			mes.setText("未知错误");
		}
    }
    
    public void handleEvent()
    {
    	homeButton.setOnMouseClicked(e->{
    		root.setCenter(Container);
    		App.bind(container);
    	});
    	
    	
    	organization.setOnMouseClicked(e->{
    		this.setCenter("fxml/organization");
    		App.bind((Pane) root.getCenter());
    	});
    	
    	peer.setOnMouseClicked(e->{
    		this.setCenter("fxml/peer");
    		
    		App.bind((Pane) root.getCenter());
    	});
    	
    	block.setOnMouseClicked(e->{
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/secondPage/block.fxml")); 
    		try {
				this.root.setCenter(fxmlLoader.load());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				mes.setVisible(true);
				mes.setText("未知错误");
			}
    		BlockController  controller=fxmlLoader.getController();
    		controller.setChannelId(getChannelId());
    		App.bind((Pane) root.getCenter());
    	});
    	
    	chainCode.setOnMouseClicked(e->{
    		this.setCenter("fxml/chainCode");
    		App.bind((Pane) root.getCenter());
    	});
    	
    	transaction.setOnMouseClicked(e->{
    		this.setCenter("fxml/transaction");
    		App.bind((Pane) root.getCenter());
    	});
    	
    }
    
   
}

