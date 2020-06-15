package com.fxml.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.App;
import com.pojo.responsePOJO.LoginResponse;
import com.requestAPI.Impl.LoginAPIImpl;
import com.utils.TopUtil;
import com.view.LoginStage;
import com.view.RegisterStage;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private BorderPane borderpane;
	@FXML
	private TextField usernamefield;
	@FXML
	private PasswordField pwfield;
	@FXML
	private Text mes;
	@FXML
	private ProgressBar bar;
	@FXML
	private HBox Top;
	@FXML
	private Button login;
	@FXML
	private Button register;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setStyle();
		mes.setVisible(false);
		bar.setVisible(false);
	}
	
	public void handleLogin() 
	{
		
			running();
			String username=usernamefield.getText();
			String pw=pwfield.getText();
			if(username.equals("")||username==null)
			{
				ending("用户名不可为空");
				return;
			}
			if(pw.equals("")||pw==null)
			{
				ending("密码不可为空");
				return;
			}
			

			LoginRequester requester=new LoginRequester(username, pw, this);
			new Thread(requester).start();
								
	}
	
	public void running() {
		bar.setVisible(true);
		mes.setVisible(false);
	}
	
	public void ending(String message) {
		bar.setVisible(false);
		mes.setVisible(true);
		mes.setText(message);
	}
	
	public void setStyle() {
		borderpane.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		Top.getStyleClass().add("alert-info");
		login.getStyleClass().add("btn-info");
		register.getStyleClass().add("btn-info");
		bar.getStyleClass().add("progress-bar-info");
		mes.getStyleClass().add("text-danger");
	}


	
	public void handleRegister() throws IOException
	{
		RegisterStage stage = RegisterStage.getInstance();
		stage.initOwner(LoginStage.getInstance());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.show();
	}


	public void clear() {
		this.bar.setVisible(false);
		this.mes.setVisible(false);
		this.usernamefield.setText(null);
		this.pwfield.setText(null);
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
					controller.ending("用户名或密码错误");

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
			e.printStackTrace();
			controller.ending("登录出错");
		} 
	}
	
}










