package application;
	
import java.io.IOException;
import java.util.ArrayList;

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



public class Main extends Application {
	
	private Controller controller;
	 ArrayList<Node> components = new ArrayList<Node>(); 
	@Override
	public void start(Stage primaryStage) throws Exception{
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Ph_LandingPage.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			controller = loader.getController();
			controller.start(primaryStage);
			
			
			Scene scene = new Scene(root, 700, 780);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
