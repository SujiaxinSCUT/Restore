package com.utils;

import com.App;
import com.jfoenix.controls.JFXSpinner;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class TopUtil {

	public static void loadSpinner(HBox hb) {
		hb.setPrefHeight(40);
		hb.getChildren().clear();
		JFXSpinner spinner = new JFXSpinner(-1F);
		hb.getChildren().add(spinner);
	}
	
	public static void loadMassageLabel(HBox hb,String message) {
		hb.setPrefHeight(40);
		hb.getChildren().clear();
		MessageLabel ml = new MessageLabel(message);
		hb.getChildren().add(ml);
		ml.getClose().setOnMouseClicked(e->{
			hb.getChildren().clear();
		});
	}
	
	
}

class MessageLabel extends AnchorPane{
	private String message;
	private ImageView close;
	
	public MessageLabel(String message) {
		this.message = message;
		close = new ImageView(new Image(App.class.getResource("icon/close.png").toString()));
		close.setCursor(Cursor.HAND);
		close.setFitWidth(20);
		close.setFitHeight(20);
		Label label = new Label(message);
		label.setFont(Font.font(16));
		this.setPrefHeight(40);
		this.setPrefWidth(600);
		
		this.getChildren().add(label);
		this.getChildren().add(close);
		
		this.setTopAnchor(label, 5.0);
		this.setTopAnchor(close, 5.0);
		this.setLeftAnchor(label, 10.0);
		this.setRightAnchor(close, 10.0);
		
		this.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		this.getStylesheets().add(App.class.getResource("css/general.css").toExternalForm());
		this.getStyleClass().add("alert-danger");
		this.getStyleClass().add("controls_border");
	}
	
	public ImageView getClose() {
		return close;
	}
}