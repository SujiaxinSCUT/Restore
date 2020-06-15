package com.view;

import java.io.IOException;

import com.App;
import com.fxml.controller.LoginController;
import com.fxml.controller.RegisterController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterStage extends Stage{

	private volatile static RegisterStage instance = null;
	
	private static FXMLLoader fxmlLoader = null;
	
	private RegisterStage() throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource("fxml/RegisterPage.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		this.setScene(scene);
		this.setResizable(false);
	}
	
	public static RegisterStage getInstance() throws IOException {
		if (instance == null) {
			synchronized (RegisterStage.class) {
				if (instance == null) {
					instance = new RegisterStage();
				}
			}
		}
		clear();
		return instance;
	}
	
	private static void clear() {
		RegisterController controller = fxmlLoader.getController();
		controller.clear();
	}

}
