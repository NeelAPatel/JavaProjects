package application;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
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
		anch.setMinWidth(150);
		anch.setMaxWidth(150);
		anch.setMinHeight(200);
		anch.setMaxHeight(200);
		
		ImageView img = new ImageView();
		img.setX(0);
		img.setY(0);
		//Rec2D (xy for size of image, xy for position it starts at
		img.setImage(new Image(getClass().getResourceAsStream("3.png")));
		img.setViewport(new Rectangle2D(150,150,0,0));	
		anch.getChildren().add(img);
		
	
		
		Label albumName = new Label("AlbumName");
		albumName.setLayoutX(10);
		albumName.setLayoutY(155);
		albumName.setMinWidth(130);
		albumName.setMinHeight(20);
		albumName.setMaxWidth(130);
		albumName.setMaxHeight(20);
		anch.getChildren().add(albumName);
		
		Label numOfPics = new Label("##");
		numOfPics.setLayoutX(10);
		numOfPics.setLayoutY(175);
		numOfPics.setMinWidth(130);
		numOfPics.setMinHeight(17);
		numOfPics.setMaxWidth(130);
		numOfPics.setMaxHeight(17);
		anch.getChildren().add(numOfPics);
		
		
		
		flow.getChildren().add(anch);
		
		
		
		
		
		System.out.println("Added.");
				
	}
	
	
	
}
