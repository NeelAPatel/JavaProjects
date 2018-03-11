package controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AlbumController {
ArrayList <Node> components = new ArrayList <Node>();
	
	@FXML private Button btnAdd, btnDelete, btnOpenAlbum, btnRenameAlbum, btnUser,btnUserManagement ;
	@FXML private Label lblStatus;
	@FXML private FlowPane flow;
	@FXML private ScrollPane scrPane;
	@FXML private AnchorPane selPane, prevSelPane;
	
	private Stage currStage;
	private User currUser;
	private int isAdmin;
	
	//@FXML btnUserManagement;
	int NodeCounter = 0;
	
	public void start(Stage mainStage) {
		mainStage.setTitle("Testing program :DD");
		if (isAdmin != 1) {
			btnUserManagement.setVisible(false);
		}
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.currStage = dialogStage;
    }
	
	
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		
		if (btn == btnAdd) {
			addProcess();
			lblStatus.setText("ADD PRESSED");
			
		}
		else if (btn == btnDelete) {
			lblStatus.setText("DELETE PRESSED");
		}
		else if (btn == null) {
			
		}
	}
	
	
	public void addProcess() {
		String userAlbumName = runDialogTextInput("Add Album", "Add a new album to your library", "Album Name:");
		
		createTile(userAlbumName);
		System.out.println("Added.");
				
	}

	private String runDialogTextInput(String dialogTitle, String dialogHeader, String inputTitle) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(dialogTitle);
		dialog.setHeaderText(dialogHeader);
		dialog.setContentText(inputTitle);

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    return result.get();
		}
		
		return null;
	}

	private void createTile(String userAlbumName) {
		AnchorPane anch = new AnchorPane(); //Everything goes on top of this.
		anch.setMinWidth(150); 
		anch.setMaxWidth(150);
		anch.setMinHeight(200);
		anch.setMaxHeight(200);
		
		ImageView img = new ImageView();
		img.setX(0);
		img.setY(0);
		img.setImage(new Image(getClass().getResourceAsStream("3.png"))); // TODO: Change this to some default pic
		img.setViewport(new Rectangle2D(150,150,0,0));	
		img.setFitWidth(150);
		img.setFitHeight(150);
		
		Label albumName = new Label("AlbumName");
		albumName.setLayoutX(10);
		albumName.setLayoutY(155);
		albumName.setMinWidth(130);
		albumName.setMinHeight(20);
		albumName.setMaxWidth(130);
		albumName.setMaxHeight(20);
		
		
		Label numOfPics = new Label("##");
		numOfPics.setLayoutX(10);
		numOfPics.setLayoutY(175);
		numOfPics.setMinWidth(130);
		numOfPics.setMinHeight(17);
		numOfPics.setMaxWidth(130);
		numOfPics.setMaxHeight(17);
		
		anch.getChildren().add(numOfPics);
		anch.getChildren().add(img);
		anch.getChildren().add(albumName);
		
		anch.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		         System.out.println("Tile pressed ");
		         	
					if(selPane != anch){
		        		prevSelPane = selPane;
		        		selPane = anch;
		        	}
		        	anch.setStyle("-fx-border-color: black; -fx-alignment: top-center");
		        	anch.setEffect(new Glow(0.5));
		            System.out.println("Tile pressed ");
		            if(prevSelPane != null){
		            	prevSelPane.setStyle("-fx-alignment: top-center");
		            	prevSelPane.setEffect(new Glow(0));
		            }else{
		            	prevSelPane = selPane;
		            }
					
					
					
					
					
		         event.consume();
		     }
		});
		
		
				
		
		
		
		img.toFront();
		
	
		

		
		
		
		flow.getChildren().add(anch);
	}

	public void setCurrentUser(int isAdmin, User currUser) {
		this.currUser = currUser;
		this.isAdmin = isAdmin;
	}
}
