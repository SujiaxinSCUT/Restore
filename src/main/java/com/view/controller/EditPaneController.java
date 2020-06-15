package com.view.controller;

import java.util.List;

import com.App;
import com.pojo.League;
import com.pojo.Orderer;
import com.pojo.Org;
import com.pojo.Peer;
import com.pojo.requestPOJO.LeagueManageRequest;

import com.pojo.requestPOJO.OrdererUpdateRequest;

import com.pojo.requestPOJO.OrgUpdateRequest;

import com.pojo.requestPOJO.PeerUpdateRequest;
import com.requestAPI.Impl.ManagerAPIImpl;
import com.utils.SceneConverter;
import com.view.EditPane;

import com.view.Option;


import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class EditPaneController {

	private EditPane pane;
	
    private GridPane gridPane;
	
	private Button update;
	
	private Button comeBack;
	
	private String authorization;
	
	private List<Node> options;
	
	private Object model;
	
	public EditPaneController(EditPane pane,Object model) {
		this.pane = pane;
		this.gridPane = pane.getGridpane();
		this.update = pane.getUpdate();
		this.comeBack = pane.getComeBack();
		this.options = gridPane.getChildren();
		this.model = model;
		if(App.getRes()==null) {
			authorization = null;
		}else {
			authorization = "Bearer "+App.getRes().getAccess_token();
		}
	}

	public EditPane getPane() {
		return pane;
	}

	public void setPane(EditPane pane) {
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
		
		League league = (League)this.model;
		
		Option o = (Option) options.get(0);
		
		o.setContent(league.getName());
		
        comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToLeague();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        update.setOnAction(e->{
        	LeagueManageRequest request = new LeagueManageRequest();
        	request.setName(o.getContent());
        	String result = null;
        	try {
				result = ManagerAPIImpl.updateLeague(authorization,league.getId(), request);
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
		Org org = (Org)model;
		
		Option o = (Option) options.get(0);
		Option o2 = (Option) options.get(1);
		Option o3 = (Option) options.get(2);
		Option o4 = (Option) options.get(3);
		Option o5 = (Option) options.get(4);
        
		o.setContent(org.getMsp_id());
		o2.setContent(org.getName());
		o3.setContent(org.getOrg_admin());
		o4.setContent(org.getOrg_domain_name());
		o5.setContent(org.getPeer_type());
		
		comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToOrg();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		update.setOnAction(e->{
            OrgUpdateRequest request = new OrgUpdateRequest();
            request.setId(org.getId());
        	request.setMsp_id(o.getContent());
        	request.setName(o2.getContent());
        	request.setOrg_admin(o3.getContent());
        	request.setOrg_domain_name(o4.getContent());
        	request.setPeer_type(o5.getContent());
        	String result = null;
        	
        	try {
				result = ManagerAPIImpl.updateOrg(authorization, request);
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
		Orderer orderer = (Orderer)model;
		
		Option o = (Option) options.get(0);
		Option o2 = (Option) options.get(1);
		Option o3 = (Option) options.get(2);
		
		o.setContent(orderer.getDomain_suffix());
		o2.setContent(orderer.getLocation());
		o3.setContent(orderer.getName());
		
		comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToOrderer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		update.setOnAction(e->{
            OrdererUpdateRequest request = new OrdererUpdateRequest();
            request.setDomain_suffix(o.getContent());
            request.setId(orderer.getId());
            request.setLocation(o2.getContent());
            request.setName(o3.getContent());
            String result = null;
            
            try {
				result = ManagerAPIImpl.updateOrderer(authorization, request);
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
		Peer peer = (Peer)model;
		
		Option o = (Option) options.get(0);
		Option o2 = (Option) options.get(1);
		Option o3 = (Option) options.get(2);
		Option o4 = (Option) options.get(3);

		o.setContent(peer.getEventhub_location());
		o2.setContent(peer.getEventhub_name());
		o3.setContent(peer.getLocation());
		o4.setContent(peer.getName());
		
		comeBack.setOnAction(e->{
        	try {
				SceneConverter.convertToPeer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		
		update.setOnAction(e->{
            PeerUpdateRequest request = new PeerUpdateRequest();
            request.setEventhub_location(o.getContent());
            request.setEventhub_name(o2.getContent());
            request.setLocation(o3.getContent());
            request.setName(o4.getContent());
            request.setId(peer.getId());
            String result = null;
            
            try {
				result = ManagerAPIImpl.updatePeer(authorization, request);
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
