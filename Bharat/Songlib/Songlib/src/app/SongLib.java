package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.SongLibController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/*
 * Neel Patel 
 * Bharat Kumar
 * Spring 2018 - Software Methodology
 */

public class SongLib extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SongLibLayout.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			
			SongLibController control = loader.getController();
			control.start(primaryStage);
			
			Scene scene = new Scene(root, 465, 530);
			
			primaryStage.setResizable(false);
			primaryStage.setTitle("Songlib by Bharat Kumar and Neel Patel");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}