package application;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	
	ArrayList <Node> components = new ArrayList <Node>();
	
	@FXML Button btnAdd, btnDelete;
	@FXML Label lblSelectedName;
	@FXML FlowPane flow;
	@FXML ScrollPane scrPane;
	int NodeCounter = 0;
	
	public void start(Stage mainStage) {
		
		mainStage.setTitle("Testing program :DD");
		scrPane = new ScrollPane();
		flow = new FlowPane();
		flow.setPadding(new Insets(5,0,5,0));
		flow.setVgap(4);
		flow.setHgap(4);
		flow.setPrefWrapLength(170);
	    for (int x = 0; x < 20; x++) {
	    	  HBox hb = new HBox();
	            // set hb attributes

	            Button b = new Button("Button" + x);
	            // set b attributes

	            // then add action listener
	            b.setOnAction(e->{ 
	                flow.getChildren().remove(hb); // remove by Object reference
	            });

	            Label l = new Label("Label" + x);
	            // set l attributes

	            hb.getChildren().addAll(b,l);
	            components.add(hb);
	    }
	    
	    flow.getChildren().addAll(components);
	    
	}
	
	
	
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		
		if (btn == btnAdd) {
			lblSelectedName.setText("ADD PRESSED");
			addProcess();
			
		}
		else if (btn == btnDelete) {
			lblSelectedName.setText("DELETE PRESSED");
		}
	}
	
	
	public void addProcess() {
		//FXMLLoader itemLoader = new FXMLLoader();
		//itemLoader.setLocation(getClass().getResource("Ph_AlbumThumb1.fxml"));

		AnchorPane anch = new AnchorPane();
		Label lblTest = new Label();
		lblTest.setText("HELLO, WORLD");
		lblTest.setPrefWidth(150);
		lblTest.setPrefHeight(27);
		
		anch.getChildren().add(lblTest);
		
		
		
		components.add(anch);
		flow.getChildren().add(new Text("Hello."));
		System.out.println("Added.");
		
		
		
		
	}
	
	
	
}
