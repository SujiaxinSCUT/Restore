package com.fxml.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.App;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXNodesList;
import com.pojo.Channel;
import com.pojo.responsePOJO.ChannelResponse;
import com.pojo.responsePOJO.LoginResponse;
import com.pojo.responsePOJO.PageableResponseChannel;
import com.requestAPI.Impl.LoginAPIImpl;
import com.requestAPI.Impl.UserAPIImpl;
import com.utils.SceneConverter;
import com.view.LoginStage;
import com.view.RegisterStage;
import com.view.UpdateStage;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController implements Initializable{

//	底层面板
	@FXML
	private BorderPane borderpane;

	@FXML
	private HBox hb;
	@FXML
	private JFXMasonryPane Top;

//	导航栏
	@FXML
	private JFXButton home;//首页
	@FXML
	private JFXButton login;
//	@FXML
//	private JFXButton register;
//	@FXML
//	private JFXButton edit;
	
	@FXML
	private ProgressIndicator pin;

	
	@FXML
	private JFXComboBox<String> channelCb;
	
	private List<Channel> channelList;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println(App.getRes().getAccess_token());
		try {
			this.getChannel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		App.width.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				if(newValue.doubleValue()<=1280) {
					hb.setPrefWidth(1280);
					Top.setPrefWidth(newValue.doubleValue());
				}else {
					hb.setPrefWidth(newValue.doubleValue());
					Top.setPrefWidth(1280);
				}
			}
		});
	}

	
	
	
	public void handleLogin() throws Exception
	{
		SceneConverter.convertToLoginPage();
		
	}
	
//	public void handleRegister() throws IOException
//	{
//		RegisterStage stage = RegisterStage.getInstance();
//		stage.initOwner(App.stage);
//		stage.initModality(Modality.WINDOW_MODAL);
//		stage.show();
//		
//	}
//	
//	public void handleUpdate() throws IOException
//	{
//		UpdateStage stage = UpdateStage.getInstance();
//		stage.initOwner(App.stage);
//		stage.initModality(Modality.WINDOW_MODAL);
//		stage.show();
//		
//	}
	
	
    public void getChannel() throws Exception
    {    	
    	channelList=UserAPIImpl.getChannelList("Bearer "+App.getRes().getAccess_token());
        
    	for(Channel res:channelList)
    	{   
    		channelCb.getItems().add(res.getName());
    	}
    }
	public BorderPane getBorderpane() {
		return borderpane;
	}
	public void setBorderpane(BorderPane borderpane) {
		this.borderpane = borderpane;
	}
    
	public void convertToLeague() throws Exception {
		SceneConverter.convertToLeague();
	}
	
	public void convertToCa() throws Exception {
		SceneConverter.convertToCa();
	}
	
	public void convertToChainCode() throws Exception {
		SceneConverter.convertToChainCode();
	}
	
	public void convertToChannel() throws Exception {
		SceneConverter.convertToChannel();
	}
	
	public void convertToChannelPage() throws Exception {
		String channelName = channelCb.getSelectionModel().getSelectedItem();
		if(channelName != null) {
			for(Channel c:channelList) {
				if(c.getName().equals(channelName)) {
					App.selectedChannel = c;
				}
			}
		}else {
			return;
		}
		SceneConverter.convertToChannelPage();
	}
	
	public void convertToHome() throws Exception {
		SceneConverter.convertToHome();
	}
	
	public void convertToOrg() throws Exception {
		SceneConverter.convertToOrg();
	}
	
	public void convertToPeer() throws Exception {
		SceneConverter.convertToPeer();
	}
	
	public void convertToOrderer() throws Exception {
		SceneConverter.convertToOrderer();
	}
    
    
	
}



