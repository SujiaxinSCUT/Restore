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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RegisterController implements Initializable{

	@FXML
	private TextField usernamefield;
	@FXML
	private PasswordField pwfield;
	@FXML
	private ProgressBar bar;
	@FXML
	private PasswordField confirm;
	@FXML
	private Text mes;
	@FXML
	private HBox Top;
	@FXML
	private Button register;
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
        register.getStyleClass().add("btn-primary");
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
		this.confirm.setText("");
	}
	
    public void handleRegister()
    {
    	running();
    	String username=this.usernamefield.getText();
    	String pw=this.pwfield.getText();
    	String con=this.confirm.getText();
    	if(username.equals(""))
    	{
    		ending("用户名不能为空");
    		return;
    	}
    	if(pw.equals(""))
    	{
    		ending("密码不能为空");
    		return;
    	}
    	if(con.equals(""))
    	{
    		ending("请确认密码");
    		return;
    	}
    	if(!pw.equals(con))
    	{
    		ending("密码不一致");
    		return;
    	}
    	RegisterRequester rr=new RegisterRequester(username, pw, this);
    	new Thread(rr).start();
    }
    
	public void clear() {
		this.bar.setVisible(false);
		this.mes.setVisible(false);
		this.usernamefield.setText(null);
		this.pwfield.setText(null);
	}

}

class RegisterRequester implements Runnable{

	private String username;
	private String pw;
	private RegisterController controller;
	public RegisterRequester(String username,String pw,RegisterController controller)
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
					message = UserAPIImpl.signUp(username, pw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					controller.ending("注册出错");
				}
				controller.ending(message);
			} );
			
	
	}
	
}
