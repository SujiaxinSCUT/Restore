package com.view.controller;

import java.util.ArrayList;
import java.util.List;

import com.App;
import com.pojo.requestPOJO.LeagueManageRequest;
import com.pojo.requestPOJO.OrdererCreateRequest;
import com.pojo.requestPOJO.OrgCreateRequest;
import com.pojo.requestPOJO.PeerCreateRequest;
import com.requestAPI.Impl.ManagerAPIImpl;
import com.utils.SceneConverter;
import com.utils.TextFieldValidator;
import com.view.NewPane;
import com.view.Option;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class NewPaneController {

	private NewPane pane;
	
	private GridPane gridPane;
	
	private Button update;
	
	private Button comeBack;
	
	private String authorization;
	
	private List<Node> options;
	
	public NewPaneController(NewPane pane) {
		this.pane = pane;
		this.gridPane = pane.getGridpane();
		this.update = pane.getUpdate();
		this.comeBack = pane.getComeBack();
		this.options = gridPane.getChildren();
		if(App.getRes()==null) {
			authorization = null;
		}else {
			authorization = "Bearer "+App.getRes().getAccess_token();
		}
	}

	public NewPane getPane() {
		return pane;
	}

	public void setPane(NewPane pane) {
		this.pane = pane;
	}
	
	public void showDialog(String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(null);
    	alert.setHeaderText(null);
    	alert.setContentText(info);
    	alert.showAndWait();
	}
	
	public void leaguePaneControl() {		
		Option o = (Option) options.get(0);
		
        comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToLeague();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        update.setOnAction(e->{
        	if(!TextFieldValidator.judgeNull(options).isValid()){
        		pane.setMessage(TextFieldValidator.judgeNull(options).getDescription());
        		return;
        	}
        	System.out.println("succeed");
        	LeagueManageRequest request = new LeagueManageRequest();
        	request.setName(o.getContent());
        	String result = null;
        	try {
				result = ManagerAPIImpl.createLeague(authorization, request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	showDialog(result);
        	try {
				SceneConverter.convertToLeague();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
	}
	
	public void orgPaneControl() {
		Option o = (Option) options.get(0);
		Option o2 = (Option) options.get(1);
		Option o3 = (Option) options.get(2);
		Option o4 = (Option) options.get(3);
		Option o5 = (Option) options.get(4);
		Option o6 = (Option) options.get(5);
		
		comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToOrg();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		update.setOnAction(e->{
			
        	if(!TextFieldValidator.judgeNull(options).isValid()){
        		pane.setMessage(TextFieldValidator.judgeNull(options).getDescription());
        		return;
        	}
        	
            OrgCreateRequest request = new OrgCreateRequest();
            request.setLeague_id(Integer.parseInt(o.getContent()));
        	request.setMsp_id(o2.getContent());
        	request.setName(o3.getContent());
        	request.setOrg_admin(o4.getContent());
        	request.setOrg_domain_name(o5.getContent());
        	request.setPeer_type(o6.getContent());
        	String result = null;
        	
        	try {
				result = ManagerAPIImpl.addOrg(authorization, request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	showDialog(result);
        	try {
        		SceneConverter.convertToOrg();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
	}
	
	public void ordererPaneControl() {
		Option o = (Option) options.get(0);
		Option o2 = (Option) options.get(1);
		Option o3 = (Option) options.get(2);
		Option o4 = (Option) options.get(3);
		
		comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToOrderer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		update.setOnAction(e->{
			
        	if(!TextFieldValidator.judgeNull(options).isValid()){
        		pane.setMessage(TextFieldValidator.judgeNull(options).getDescription());
        		return;
        	}
        	
            OrdererCreateRequest request = new OrdererCreateRequest();
            request.setDomain_suffix(o.getContent());
            request.setLeague_id(Integer.parseInt(o2.getContent()));
            request.setLocation(o3.getContent());
            request.setName(o4.getContent());
            String result = null;
            
            try {
				result = ManagerAPIImpl.addOrderer(authorization, request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            showDialog(result);
        	try {
        		SceneConverter.convertToOrderer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
	}
	
	public void peerPaneControl() {
		Option o = (Option) options.get(0);
		Option o2 = (Option) options.get(1);
		Option o3 = (Option) options.get(2);
		Option o4 = (Option) options.get(3);
		Option o5 = (Option) options.get(4);
		
		comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToPeer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		update.setOnAction(e->{
			
        	if(!TextFieldValidator.judgeNull(options).isValid()){
        		pane.setMessage(TextFieldValidator.judgeNull(options).getDescription());
        		return;
        	}
        	
            PeerCreateRequest request = new PeerCreateRequest();
            request.setEventhub_location(o.getContent());
            request.setEventhub_name(o2.getContent());
            request.setLocation(o3.getContent());
            request.setName(o4.getContent());
            request.setOrg_id(Integer.parseInt(o5.getContent()));
            String result = null;
            
            try {
				result = ManagerAPIImpl.addPeer(authorization, request);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            showDialog(result);
        	try {
        		SceneConverter.convertToPeer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
	}
}
