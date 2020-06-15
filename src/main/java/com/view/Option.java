package com.view;


import java.io.File;
import java.util.List;
import java.util.Set;

import com.App;
import com.pojo.League;
import com.pojo.responsePOJO.ChannelResponse;
import com.pojo.responsePOJO.PageableResponseLeague;
import com.requestAPI.Impl.UserAPIImpl;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Option extends VBox{

     private Label label;
     private TextField field;
     private ComboBox<String> combo;
     private RadioButton rb;
     private Button browse;
     private FileChooser chooser;
     
     public Option() {
    	 super();
    	 this.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
    	 label=new Label();
    	 label.setPrefHeight(40);
    	 label.setPrefWidth(900);
    	 label.getStyleClass().add("alert-success");
    	 label.setFont(new Font(24));
    	 this.getChildren().add(label);
    	 this.setSpacing(10);
     }
     
     public void genField() {
    	 
    	 field=new TextField();   	 
    	 field.setPrefHeight(40);
    	 field.setPrefWidth(900);
    	 field.setFont(new Font(24));
    	 this.getChildren().addAll(field);
     }
     
     public void genCombo() {
    	 
    	 combo=new ComboBox<String>();
    	 combo.setPrefHeight(40);
    	 combo.setPrefWidth(900);
    	 combo.getStyleClass().add("btn-info");
     	 this.getChildren().add(combo);
    }
     
    public void genRadioButton() {
    	ColorAdjust adjust=new ColorAdjust();
    	adjust.setHue(1.9);
    	rb=new RadioButton();
    	rb.setEffect(adjust);
    	rb.setPrefHeight(40);
    	rb.setPrefWidth(100);
    	rb.setText("启用");
//    	rb.getStylesheets().add(App.class.getResource("stylesheet2.css").toString());
    	this.getChildren().add(rb);
    }
    
    public void genBrowseGroup(String postFix) {
    	field=new TextField();   	 
     	field.setPrefHeight(40);
   	    field.setPrefWidth(820);
   	    field.setFont(new Font(24));
   	    browse=new Button("Browse");
   	    browse.setPrefHeight(40);
   	    browse.setPrefWidth(80);
   	    browse.getStyleClass().add("btn-primary");
   	    browse.setOnAction(e->{
   	    	chooser=new FileChooser();
   	    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("crtFile", "postFix");
   	    	chooser.getExtensionFilters().add(extFilter);
   	    	File file=chooser.showOpenDialog(new Stage());
   	    	if(file!=null) {
   	    	field.setText(file.getAbsolutePath());
   	    	}
   	    });
   	    HBox hb=new HBox(field,browse);
   	    this.getChildren().add(hb);
    }
     
     
     
     public void setText(String mes) {
    	 label.setText(mes);
     }
     
     public void setContent(String content) {
    	 field.setText(content);
     }
     
     public String getContent() {
    	 return field.getText();
     }

	public ComboBox<String> getCombo() {
		return combo;
	}

	public void setCombo(ComboBox<String> combo) {
		this.combo = combo;
	}

	public String getText() {
		return label.getText();
	}
     
    
}
