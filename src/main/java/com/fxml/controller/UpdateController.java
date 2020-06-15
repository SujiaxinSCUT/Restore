package com.fxml.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.App;
import com.pojo.responsePOJO.LoginResponse;
import com.requestAPI.Impl.LoginAPIImpl;
import com.requestAPI.Impl.UserAPIImpl;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateController implements Initializable{

	@FXML
	private TextField usernamefield;
	@FXML
	private PasswordField pwfield;
	@FXML
	private HBox Top;
	@FXML
	private ProgressBar bar;
	@FXML
	private Button handleUpdate;
	@FXML
	private Text mes;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setStyle();
		mes.setVisible(false);
		bar.setVisible(false);
	}
	
	public void setStyle()
	{
		Top.getParent().getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		Top.getStyleClass().add("alert-info");
        handleUpdate.getStyleClass().add("btn-primary");
        bar.getStyleClass().add("progress-bar-info");
		mes.getStyleClass().add("text-danger");
	}
	
	public void running() {
		bar.setVisible(true);
		mes.setVisible(false);
	}
	
	public void ending(String message) {
		bar.setVisible(false);
		mes.setVisible(true);
		mes.setText(message);
		this.usernamefield.setText("");
		this.pwfield.setText("");
	}
	
	public void handleUpdate()
	{
		
			running();
			String username=usernamefield.getText();
			String pw=pwfield.getText();
			if(username==null||username.equals(""))
			{
				ending("用户名不可为空");
				return;
			}
			if(pw==null||pw.equals(""))
			{
				ending("密码不可为空");
				return;
			}		
			UpdateRequester ur=new UpdateRequester(username, pw, this);
			new Thread(ur).start();
			
			
	}
	
	public void clear() {
		this.bar.setVisible(false);
		this.mes.setVisible(false);
		this.usernamefield.setText(null);
		this.pwfield.setText(null);
	}
	
	class UpdateRequester implements Runnable{

		private String username;
		private String pw;
		private UpdateController controller;
		public UpdateRequester(String username,String pw,UpdateController controller)
		{
			this.username=username;
			this.pw=pw;
			this.controller=controller;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
				Platform.runLater(() -> {
					String message=null;
					try {
						message =UserAPIImpl.updateAccountInfo("Bearer "+App.getRes().getAccess_token()
								, username, pw);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						controller.ending("修改失败");
					}
					controller.ending(message);
				} );
				
		
		}
		
	}


	
}
