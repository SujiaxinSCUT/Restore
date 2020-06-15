package com.view;

import java.io.IOException;

import com.App;
import com.fxml.controller.LoginController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginStage extends Stage{

	private volatile static LoginStage instance = null;
	
	private static FXMLLoader fxmlLoader = null;
	
	private LoginStage() throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource("fxml/LoginPage.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		this.setScene(scene);
		this.setResizable(false);
	}
	
	public static LoginStage getInstance() throws IOException {
		if (instance == null) {
			synchronized (LoginStage.class) {
				if (instance == null) {
					instance = new LoginStage();
				}
			}
		}
		clear();
		return instance;
	}
	
	private static void clear() {
		LoginController controller = fxmlLoader.getController();
		controller.clear();
	}

}
