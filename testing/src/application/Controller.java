package application;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	
	ArrayList <Node> components = new ArrayList <Node>();
	
	@FXML Button btnAdd, btnDelete;
	@FXML Label lblSelectedName;
	@FXML FlowPane flow;
	@FXML ScrollPane scrPane;
	
	@FXML TilePane tilePane;
	int NodeCounter = 0;
	
	public void start(Stage mainStage) {
		mainStage.setTitle("Testing program :DD");
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
		
		AnchorPane anch = new AnchorPane();
		ImageView img = new ImageView();
		Label albumName = new Label();
		
		flow.getChildren().add(new Text("Hello."));
		
		
		
		
		
		System.out.println("Added.");
				
	}
	
	
	
}
