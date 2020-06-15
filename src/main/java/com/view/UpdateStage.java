package com.view;

import java.io.IOException;

import com.App;
import com.fxml.controller.LoginController;
import com.fxml.controller.UpdateController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpdateStage extends Stage{

	private volatile static UpdateStage instance = null;
	
	private static FXMLLoader fxmlLoader = null;
	
	private UpdateStage() throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource("fxml/UpdatePage.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		this.setScene(scene);
		this.setResizable(false);
	}
	
	public static UpdateStage getInstance() throws IOException {
		if (instance == null) {
			synchronized (UpdateStage.class) {
				if (instance == null) {
					instance = new UpdateStage();
				}
			}
		}
		clear();
		return instance;
	}
	
	private static void clear() {
		UpdateController controller = fxmlLoader.getController();
		controller.clear();
	}

}
