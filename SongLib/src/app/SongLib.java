/* Neel Patel - Bharat Kumar
 * Software Methodology Spring 2018
 */
package app;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.SongLibController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class SongLib extends Application {
	private SongLibController songLibController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SongLibLayout.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			
			songLibController = loader.getController();
			songLibController.start(primaryStage);
			
			Scene scene = new Scene(root,465, 530);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws IOException{
		songLibController.saveToFile();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
