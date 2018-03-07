package application;


import java.io.FileNotFoundException;
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
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
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
	//@FXML btnUserManagement;
	int NodeCounter = 0;
	
	public void start(Stage mainStage) {
		mainStage.setTitle("Testing program :DD");
	}
	
	
	
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		
		if (btn == btnAdd) {
			addProcess();
			lblSelectedName.setText("ADD PRESSED");
			
		}
		else if (btn == btnDelete) {
			lblSelectedName.setText("DELETE PRESSED");
		}
		else if (btn == null) {
			
		}
	}
	
	
	public void addProcess() {
		
		tileVersion2();
		System.out.println("Added.");
				
	}



	private void tileVersion1() {
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
		img.setFitWidth(150);
		img.setFitHeight(150);
		anch.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         System.out.println("Tile pressed ");
		         	Glow glow = new Glow(); 
					
					//setting level of the glow effect 
					glow.setLevel(0.9); 
					
					//Applying bloom effect to text 
					img.setEffect(glow);
		         event.consume();
		     }
		});
		
		
		
		
		
		anch.getChildren().add(img);
		img.toFront();
		
	
		
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
	}
	
	private void tileVersion2() {
		AnchorPane anch = new AnchorPane();
		anch.setMinWidth(150);
		anch.setMaxWidth(150);
		anch.setMinHeight(150);
		anch.setMaxHeight(150);
		
		ImageView img = new ImageView();
		img.setX(0);
		img.setY(0);
		//Rec2D (xy for size of image, xy for position it starts at
		img.setImage(new Image(getClass().getResourceAsStream("3.png")));
		img.setViewport(new Rectangle2D(150,150,0,0));	
		img.setFitWidth(150);
		img.setFitHeight(150);
		anch.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		         System.out.println("Tile pressed ");
		         	Glow glow = new Glow();  
					glow.setLevel(0.9); 
					img.setEffect(glow);
		         event.consume();
		     }
		});
		img.toFront();
		anch.getChildren().add(img);
		
		
	
		
		Label albumName = new Label("AlbumName");
		albumName.setLayoutX(0);
		albumName.setLayoutY(123);
		albumName.setMinWidth(150);
		albumName.setMinHeight(27);
		albumName.setMaxWidth(150);
		albumName.setMaxHeight(27);
		
		albumName.setTextFill(Color.WHITE);
		anch.getChildren().add(albumName);
		
		Label numOfPics = new Label("##");
		numOfPics.setLayoutX(0);
		numOfPics.setLayoutY(105);
		anch.getChildren().add(numOfPics);
		
		
		
		flow.getChildren().add(anch);
	}
	
}
