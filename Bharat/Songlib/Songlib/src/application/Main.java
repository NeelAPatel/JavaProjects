package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

//Bharat Kumar

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SongLibLayout.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Controller control = loader.getController();
			control.start(primaryStage);
			
			Scene scene = new Scene(root, 500, 300);
			
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