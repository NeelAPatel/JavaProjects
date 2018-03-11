package controller;



import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
	@FXML TextField tfUsername;
	@FXML PasswordField passField;
	@FXML Button btnLogin;
	@FXML Label lblErrorMsg;
	
	Stage currStage;
	private ArrayList<User> userList;
	
	/**
	 * initializes and sets up the login page
	 * @param mainStage 
	 */
	public void start(Stage mainStage) {
		currStage = mainStage;
		mainStage.setTitle("Photos - Login");
		lblErrorMsg.setVisible(false);
		
		//open file for holding
		
	}
	
	/**
	 * Handles button press events according to its source, and runs it's process
	 * @param e ActionEvent
	 */
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		
		if (btn == btnLogin) {
			loginProcess();
		}
	}
	
	/**
	 * Checks if the user is valid. 
	 */
	private void loginProcess() {
		// Check if login is valid
		
		String myUsername = tfUsername.getText();
		String myPassword = passField.getText();
		
		if (myUsername.equals("admin") && myPassword.equals("admin")) {
			lblErrorMsg.setVisible(false);
			openAlbumPane(1,new User(100, "admin", "admin"));
	        
		}
		else
		{
			lblErrorMsg.setVisible(true);
		}
				
	}
	
	private void openAlbumPane(int isAdmin, User currUser) {
		try {
			
			FXMLLoader albumLoader = new FXMLLoader();
			albumLoader.setLocation(getClass().getResource("/fxml/Ph_LandingPage.fxml"));
    		AnchorPane root = (AnchorPane) albumLoader.load();
    		
    		Stage albumStage = new Stage();
    		albumStage.setTitle("Photos - Albums");
    		albumStage.initModality(Modality.WINDOW_MODAL);
    		albumStage.initOwner(currStage);
    		Scene scene = new Scene(root);
    		albumStage.setScene(scene);
    		
    		AlbumController albumController = albumLoader.getController();
    		albumController.setDialogStage(albumStage);
    		albumController.setCurrentUser(isAdmin, currUser);
    		albumStage.showAndWait();
    		
    		
    		
    		
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
