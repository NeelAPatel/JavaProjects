/* Neel Patel - Bharat Kumar
 * Software Methodology Spring 2018
 */
package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.SongLibController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SongLibLayout.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			
			SongLibController songLibController = loader.getController();
			songLibController.start(primaryStage);
			
			Scene scene = new Scene(root,540,360);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
