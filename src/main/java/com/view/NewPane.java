package com.view;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.kordamp.bootstrapfx.scene.layout.Panel;

import com.App;
import com.pojo.Channel;
import com.pojo.League;
import com.pojo.Org;
import com.pojo.Peer;
import com.pojo.responsePOJO.ChannelResponse;
import com.requestAPI.Impl.UserAPIImpl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NewPane extends Panel{

	private GridPane gridpane;
	private Button update;
	private Button comeBack;
	private Text message;
	public NewPane() {
        super("录入");
        
		this.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		this.getStyleClass().add("panel-primary");
		
        gridpane=new GridPane();
        gridpane.setVgap(30);

        message = new Text();
        message.setText(null);
        message.setFill(Color.RED);
        
        this.setCenter(gridpane);
        this.setMargin(gridpane, new Insets(50, 50, 50, 50));
        
        update=new Button("确定");
        update.getStyleClass().add("btn-primary");
        update.setPrefHeight(40);
        update.setPrefWidth(70);
        comeBack=new Button("返回");
        comeBack.getStyleClass().add("btn-primary");
        comeBack.setPrefHeight(40);
        comeBack.setPrefWidth(70);
        
        HBox hb=new HBox();
        hb.setSpacing(30);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(comeBack,update);
        
        VBox vb = new VBox();
        vb.setSpacing(5);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(message,hb);
        vb.setMargin(hb, new Insets(20));
        
        this.setBottom(vb);
        
	}
	
	public void genLeaguePane() {
		Option o=new Option();
        o.setText("League Name");
        o.genField();
		gridpane.add(o, 0, 0);
	}
	
	public void genOrgPane() throws Exception {

		Option o=new Option();
		o.setText("League ID");
		o.genField();
		Option o2=new Option();
		o2.setText("Msp ID");
		o2.genField();
		Option o3=new Option();
		o3.setText("Name");
		o3.genField();
		Option o4 = new Option();
		o4.setText("Organization Admin");
		o4.genField();
		Option o5 = new Option();
		o5.setText("Organization Domain Name");
		o5.genField();
		Option o6 = new Option();
		o6.setText("Peer Type");
		o6.genField();
		gridpane.add(o, 0, 0);
		gridpane.add(o2, 0, 1);
		gridpane.add(o3, 0, 2);
		gridpane.add(o4, 0, 3);
		gridpane.add(o5, 0, 4);
		gridpane.add(o6, 0, 5);
	}
	
	public void genOrdererPane() throws Exception {
		Option o=new Option();
		o.setText("Domain Suffix");
        o.genField();
		Option o2=new Option();
		o2.setText("League ID");
		o2.genField();
		Option o3=new Option();
		o3.setText("Location");
		o3.genField();
		Option o4=new Option();
		o4.setText("Name");
		o4.genField();
		
		gridpane.add(o, 0, 0);
		gridpane.add(o2, 0, 1);
		gridpane.add(o3, 0, 2);
		gridpane.add(o4, 0, 3);

		
	}
	
	public void genPeerPane() throws Exception {
		Option o=new Option();
		o.setText("Eventhub Location");
		o.genField();
		Option o2=new Option();
		o2.setText("Eventhub Name");
		o2.genField();
		Option o3=new Option();
		o3.setText("Location");
		o3.genField();
		Option o4=new Option();
		o4.setText("Name");
		o4.genField();
		Option o5=new Option();
		o5.setText("Org ID");
		o5.genField();

		gridpane.add(o, 0, 0);
		gridpane.add(o2, 0, 1);
		gridpane.add(o3, 0, 2);
		gridpane.add(o4, 0, 3);
		gridpane.add(o5, 0, 4);

	}
	
	public void genCAPane() throws Exception {
		Option o=new Option();
		o.setText("节点可选列表");
		o.genCombo();
//		Set<Peer> set=UserAPIImpl.getPeersPageable("Bearer "+App.getRes().getAccess_token(),0,10).getContents();
//		for(Peer peer:set) {
//			o.getCombo().getItems().add(peer.getName());
//		}
		Option o2=new Option();
		o2.genField();
		o2.setText("CA名称");
		Option o3=new Option();
		o3.setText("CA sk文件");
		o3.genBrowseGroup("*.pem");
		Option o4=new Option();
		o4.setText("CA Certificate");
		o4.genBrowseGroup("*.pem");

		gridpane.add(o, 0, 0);
		gridpane.add(o2, 0, 1);
		gridpane.add(o3, 0, 2);
		gridpane.add(o4, 0, 3);

	}
	
	public void genChannelPane() throws Exception {
		Option o=new Option();
		o.setText("节点可选列表");
		o.genCombo();
//		Set<Peer> set;
//		set = UserAPIImpl.getPeersPageable("Bearer "+App.getRes().getAccess_token(),0,10).getContents();
//		for(Peer peer:set) {
//			o.getCombo().getItems().add(peer.getName());
//		}
		Option o2=new Option();
		o2.genField();
		o2.setText("通道名称");
		Option o3=new Option();
		o3.setText("同步区块监听");
		o3.genRadioButton();


		gridpane.add(o, 0, 0);
		gridpane.add(o2, 0, 1);
		gridpane.add(o3, 0, 2);

	}
	
	public void genChaincodePane() throws Exception {
		Option o=new Option();
		o.setText("通道可选列表");
		o.genCombo();
//		List<ChannelResponse> list=UserAPIImpl.getChannelList("Bearer "+App.getRes().getAccess_token());
//		for(ChannelResponse res:list) {
//			o.getCombo().getItems().add(res.getName());
//		}
		Option o2=new Option();
		o2.genField();
		o2.setText("链码名称");
		Option o3=new Option();
		o3.setText("链码安装路径");
		o3.genField();
		Option o4=new Option();
		o4.genField();
		o4.setText("版本");
		Option o5=new Option();
		o5.setText("提案超时时间（以毫秒为单位）");
		o5.genField();
		o5.setContent(String.valueOf(90000));
		Option o6=new Option();
		o6.genRadioButton();
		o6.setText("链码事件监听");
		gridpane.add(o, 0, 0);
		gridpane.add(o2, 0, 1);
		gridpane.add(o3, 0, 2);
		gridpane.add(o4, 0, 3);
		gridpane.add(o5, 0, 4);
		gridpane.add(o6, 0, 5);
	}

	public GridPane getGridpane() {
		return gridpane;
	}

	public void setGridpane(GridPane gridpane) {
		this.gridpane = gridpane;
	}

	public Button getUpdate() {
		return update;
	}

	public void setUpdate(Button update) {
		this.update = update;
	}

	public Button getComeBack() {
		return comeBack;
	}

	public void setComeBack(Button comeBack) {
		this.comeBack = comeBack;
	}
	
	public void setMessage(String mes) {
		message.setText(mes);
	}
}
