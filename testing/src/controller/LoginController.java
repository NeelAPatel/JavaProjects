package controller;



import java.io.IOException;

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
	public void start(Stage mainStage) {
		currStage = mainStage;
		mainStage.setTitle("Photos - Login");
		lblErrorMsg.setVisible(false);
	}
	
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		
		if (btn == btnLogin) {
			loginProcess();
		}
	}
	
	private void loginProcess() {
		// Check if login is valid
		
		String myUsername = tfUsername.getText();
		String myPassword = passField.getText();
		
		if (myUsername.equals("admin") && myPassword.equals("admin")) {
			lblErrorMsg.setVisible(false);
			openAlbumPane();
	        
		}
		else
		{
			lblErrorMsg.setVisible(true);
		}
				
	}
	
	private void openAlbumPane() {
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
    		albumStage.showAndWait();
    		
    		
    		
    		
		} catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
