package com.fxml.loginController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.App;
import com.jfoenix.controls.JFXSpinner;
import com.pojo.responsePOJO.LoginResponse;
import com.requestAPI.Impl.LoginAPIImpl;
import com.utils.TopUtil;
import com.view.LoginStage;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LoginController implements Initializable{

	@FXML
	private TextField usernamefield;
	@FXML
	private PasswordField pwfield;

	@FXML
	private ImageView loginIcon;

	@FXML
	private HBox topContainer;

	@FXML
	private Button login;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginIcon.setImage(new Image(App.class.getResource("icon/login.png").toString()));
	}
	
	public void handleLogin() 
	{
		    TopUtil.loadSpinner(topContainer);
			String username=usernamefield.getText();
			String pw=pwfield.getText();
			if(username.equals("")||username==null)
			{
				TopUtil.loadMassageLabel(topContainer, "用户名不能为空");
				return;
			}
			if(pw.equals("")||pw==null)
			{
				TopUtil.loadMassageLabel(topContainer, "密码不能为空");
				return;
			}
			LoginRequester requester=new LoginRequester(username, pw, this);
			new Thread(requester).start();
								
	}
	
	public HBox getTopContainer() {
		return topContainer;
	}
	
	
	
	
	
}



class LoginRequester implements Runnable{

	private String username;
	private String pw;
	private LoginController controller;
	public LoginRequester(String username,String pw,LoginController controller)
	{
		this.username=username;
		this.pw=pw;
		this.controller=controller;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			LoginResponse res=LoginAPIImpl.login(username, pw);
			Platform.runLater(() -> {
				if(res==null)
				{
					TopUtil.loadMassageLabel(controller.getTopContainer(), "用户名或密码错误");
					return;
				}
				try {
					App.setResponse(res);					
					LoginStage.getInstance().close();		
					App.initialize();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    });
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			TopUtil.loadMassageLabel(controller.getTopContainer(), "登录出错");
			e.printStackTrace();
		} 
	}
	
}







