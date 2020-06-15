package com;

import java.io.IOException;

import com.pojo.Channel;
import com.pojo.responsePOJO.LoginResponse;
import com.view.LoginStage;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static BorderPane root;

    public static DoubleProperty width;
    public static Stage stage;
    public static Channel selectedChannel;
    private static LoginResponse response;
    

    @SuppressWarnings("static-access")
	@Override
    public void start(Stage stage) throws Exception {
    	

    	width=new SimpleDoubleProperty();
    	this.stage=stage;

//        initialize();
//        LoginStage.getInstance().show();
    	loginPage();
        
    }
    
    public static void initialize() throws IOException
    {
    	scene=new Scene(loadFXML("fxml/homePage/Home"),1280,800);
    	root=(BorderPane) scene.getRoot();
    	root.setCenter(loadFXML("fxml/homePage/HomePage"));
    	root.getStylesheets().add(App.class.getResource("css/tableview.css").toExternalForm());

    	width.bind(stage.widthProperty());
    	
    	bind(root);
                        
        stage.setScene(scene);
        stage.setMinWidth(320);
        stage.show();
    }
    
    public static void loginPage() throws IOException {
    	scene=new Scene(loadFXML("fxml/loginPage/LoginPage"),1280,800);
    	stage.setScene(scene);
    	stage.setMinWidth(320);
    	stage.show();
    }
    

    
    public static void setContent(String fxml) throws Exception
    {
    	root.setCenter(loadFXML(fxml));    	
    }
    
    

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));      
        return fxmlLoader.load();
    }
    
    public static void bind(Pane pane)
    {
    	pane.prefWidthProperty().bind(width);
    }
    
    public static  void setResponse(LoginResponse res)
    {
    	response=res;
    }
    
    public static LoginResponse getRes()
    {
    	return response;
    }
   
    public static void main(String[] args) {
        launch();
    }

}