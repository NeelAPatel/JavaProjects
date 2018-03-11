package application;
	
import java.io.IOException;
import java.util.ArrayList;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;



public class Photos62 extends Application {
	
	private LoginController loginController;
	 ArrayList<Node> components = new ArrayList<Node>(); 
	@Override
	public void start(Stage primaryStage) throws Exception{
			
			FXMLLoader loginLoader = new FXMLLoader();
			loginLoader.setLocation(getClass().getResource("/fxml/Ph_LoginPage.fxml"));
			
			AnchorPane root = (AnchorPane) loginLoader.load();
			loginController = loginLoader.getController();
			loginController.start(primaryStage);
			
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
