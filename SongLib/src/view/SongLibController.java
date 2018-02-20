/* Neel Patel - Bharat Kumar
 * Software Methodology Spring 2018
 */
package view;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SongLibController {
	@FXML ListView<SongMetadata> songListView;
	
	@FXML Label title;
	@FXML Label lblA, lblB, lblC, lblD;
	@FXML Label lblDisplayName, lblDisplayAlbum, lblDisplayArtist, lblDisplayYear;
	
	@FXML TextField tfName, tfAlbum, tfArtist, tfYear;
	
	@FXML Button btnAdd, btnEdit, btnDelete, btnSave, btnCancel;
	
	
	//private ArrayList<SongMetadata> songLibArr; 
	private ObservableList<SongMetadata> obsList;        
	private String editingMode = "blank";

	String fileName = "songs.txt";
	private File file = new File(fileName);

	
	// Start Method
	public void start(Stage mainStage) {

		//MainStage parameters
		mainStage.setTitle("Song Library");
		
		
		
		//Set up Array for listview
		ArrayList<SongMetadata> songLibArr = new ArrayList<SongMetadata>(0);
		obsList = FXCollections.observableList(songLibArr);
		try {
			file = new File(fileName);
			importFromFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		songListView.setItems(obsList);
		
		//Sets listview's custom cell format
        songListView.setCellFactory(new Callback<ListView<SongMetadata>, javafx.scene.control.ListCell<SongMetadata>>() {
            @Override
            public ListCell<SongMetadata> call(ListView<SongMetadata> listView)
            {
                return new ListViewCell();
            }
        });

        
        //Populates Listview
        songListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SongMetadata>() {
			@Override
			public void changed(ObservableValue<? extends SongMetadata> arg0, SongMetadata oldValue, SongMetadata newValue) {
				if (newValue != null) {
					lblDisplayName.setText(newValue.getSongName());
					lblDisplayAlbum.setText(newValue.getSongAlbum());
					lblDisplayArtist.setText(newValue.getSongArtist());
					lblDisplayYear.setText(newValue.getSongYear());
					setDetailView(newValue);
				}
			}
        });
		
        
        if (!songListView.getItems().isEmpty()) {
        	obsList.sort(new SongCompare());
        	songListView.refresh();
        	songListView.requestFocus();
        	songListView.getSelectionModel().select(0);
        }
        
        //UI Element Management
		enableAllLabels();
		disableAllTextFields();
		
		btnAdd.setVisible(true);
		if (obsList.size()> 0) {
			btnEdit.setVisible(true);
			btnDelete.setVisible(true);
		}else
		{
			btnEdit.setVisible(false);
			btnDelete.setVisible(false);
		}
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
		
	}

	// Button press action
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		
		if(btn == btnAdd) { 
			addButtonProcess(); // ADD BUTTON
		}
		else if (btn == btnSave) { //SAVE BUTTON
			if (checkSongParams(tfName.getText().trim(), tfArtist.getText().trim(), tfAlbum.getText().trim(), tfYear.getText().trim())) {
				if (editingMode.equals("add")) 
					saveAddSong();
				else if (editingMode.equals("edit"))
					saveEditSong();
			}
		}else if (btn == btnEdit) { //EDIT BUTTON
			editButtonProcess();
		}else if (btn == btnDelete) {
			deleteButtonProcess();
		}else if (btn == btnCancel) {
			cancelButtonProcess();
		}
	}
	
	// Button processes
	private void addButtonProcess() {
		songListView.setDisable(true);
		editingMode = "add";
		
		enableAllTextFields();
		tfAlbum.setText("");
		tfArtist.setText("");
		tfName.setText("");
		tfYear.setText("");
		disableAllLabels();
		
		
        btnAdd.setVisible(false);
		btnEdit.setVisible(false);
		btnDelete.setVisible(false);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        

	}
	private void editButtonProcess() {
		songListView.setDisable(true);
		editingMode = "edit";
		enableAllTextFields();
		
		disableAllLabels();

		
		SongMetadata current = songListView.getSelectionModel().getSelectedItem();
		tfName.setText(current.getSongName());
		tfAlbum.setText(current.getSongAlbum());
		tfArtist.setText(current.getSongArtist());
		tfYear.setText(current.getSongYear());
		
		
		
		
		btnAdd.setVisible(false);
		if (obsList.size()> 0) {
			btnEdit.setVisible(false);
			btnDelete.setVisible(false);
		}else
		{
			btnEdit.setVisible(false);
			btnDelete.setVisible(false);
		}
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        

	}
	private void deleteButtonProcess() {
		songListView.setDisable(true);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		
		if (alert.getResult() == ButtonType.YES) {
			SongMetadata currentSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex());
			SongMetadata nextSong = null;
			//if selected song is the last one
			if (songListView.getItems().indexOf(currentSong) == (songListView.getItems().size()-1)) {
				
				nextSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex());
				setDetailView(nextSong);
			}
			else if (songListView.getItems().indexOf(currentSong) == 0)
			{
				nextSong = null;
			}
			else {
				nextSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex()+1);
				setDetailView(nextSong);
			}
			
			if (nextSong != null) {

				songListView.getSelectionModel().select(nextSong);
				songListView.getItems().remove(currentSong);
				songListView.requestFocus();
				songListView.refresh();

				setDetailView(nextSong);
			}
			else {
				songListView.getItems().remove(currentSong);
				songListView.requestFocus();
				songListView.refresh();
				lblDisplayName.setText("");
				lblDisplayArtist.setText("");
				lblDisplayAlbum.setText("");
				lblDisplayYear.setText("");
				
			}
			
			
			
			
			if (songListView.getItems().size()> 0)
				setDetailView(songListView.getSelectionModel().getSelectedItem());
			else
				setDetailView(new SongMetadata("","","",""));
			
			btnAdd.setVisible(true);
			if (obsList.size()> 0) {
				btnEdit.setVisible(true);
				btnDelete.setVisible(true);
			}else
			{
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}
	        btnSave.setVisible(false);
	        btnCancel.setVisible(false);
			
		}
		else {
			// do nothing
		}

		songListView.setDisable(false);
        songListView.requestFocus();
		

	}
	private void saveAddSong() {
		
		String x = tfYear.getText();
		//System.out.println("[" + x + "]");
		
		if ((isInteger(tfYear.getText()) &&  (Integer.parseInt(tfYear.getText()) >= 0)) || x.equals("")) {

			
			SongMetadata newSong = new SongMetadata(tfName.getText().trim(), tfArtist.getText().trim(), tfAlbum.getText().trim(), tfYear.getText().trim());
			
			if (!songListView.getItems().contains(newSong)) { 
				
				
				obsList.add(newSong);
				songListView.requestFocus();
				songListView.refresh();
				songListView.getSelectionModel().select(newSong);
				
								
				disableAllTextFields();
				enableAllLabels();
				
				btnAdd.setVisible(true);
				if (obsList.size()> 0) {
					btnEdit.setVisible(true);
					btnDelete.setVisible(true);
				}else{
					btnEdit.setVisible(false);
					btnDelete.setVisible(false);
				}
		        btnSave.setVisible(false);
		        btnCancel.setVisible(false);
		        
		        songListView.getItems().sort(new SongCompare());
		        songListView.setDisable(false);
		        songListView.requestFocus();
			} 
			else
			{
				//Popup for duplicate.
				Alert alert = new Alert(AlertType.ERROR, "The song you are trying to add already exists.");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "Year is invalid. Please try again.");
			alert.showAndWait();
		}
			

		
		


	}
	private void saveEditSong() {

		String x = tfYear.getText();
		//System.out.println("[" + x + "]");
		
		if ((isInteger(tfYear.getText()) &&  (Integer.parseInt(tfYear.getText()) >= 0)) || x.equals("")) {

			SongMetadata editedSong = new SongMetadata(tfName.getText(), tfArtist.getText(), tfAlbum.getText(), tfYear.getText());
			
			
			SongMetadata selectedSong = songListView.getSelectionModel().getSelectedItem();
			int selectedIndex = songListView.getSelectionModel().getSelectedIndex();
			
			
			if ((selectedSong.equals(editedSong)) || (!songListView.getItems().contains(editedSong)))
				{
				//SongMetadata selectedSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex());
				selectedSong.setSongAlbum(tfAlbum.getText());
				selectedSong.setSongArtist(tfArtist.getText());
				selectedSong.setSongName(tfName.getText());
				selectedSong.setSongYear(tfYear.getText());
				songListView.refresh();
				lblDisplayName.setText(selectedSong.getSongName());
				lblDisplayArtist.setText(selectedSong.getSongArtist());
				lblDisplayAlbum.setText(selectedSong.getSongAlbum());
				lblDisplayYear.setText(selectedSong.getSongYear());
				
				
				
				
				disableAllTextFields();
				enableAllLabels();
				
				btnAdd.setVisible(true);
				if (obsList.size()> 0) {
					btnEdit.setVisible(true);
					btnDelete.setVisible(true);
				}else{
					btnEdit.setVisible(false);
					btnDelete.setVisible(false);
				}
				btnSave.setVisible(false);
				btnCancel.setVisible(false);
				songListView.setDisable(false);
		        songListView.requestFocus();
			}
			else
			{
				//Popup for duplicate.
				Alert alert = new Alert(AlertType.ERROR, "The song you are trying to edit already exists.");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "Year is invalid. Please try again.");
			alert.showAndWait();
		}
		
	}
	private void cancelButtonProcess() {

		disableAllTextFields();
		enableAllLabels();
		tfName.setText("");
		tfArtist.setText("");
		tfAlbum.setText("");
		tfYear.setText("");
		
        btnAdd.setVisible(true);
        if (obsList.size()> 0) {
			btnEdit.setVisible(true);
			btnDelete.setVisible(true);
		}else{
			btnEdit.setVisible(false);
			btnDelete.setVisible(false);
		}
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        songListView.setDisable(false);
        if (!songListView.getItems().isEmpty()) {
        	setDetailView(songListView.getSelectionModel().getSelectedItem());
        }
        songListView.refresh();
        songListView.requestFocus();
	}
	
	// FILE I/O handling
	@SuppressWarnings("resource")
	private void importFromFile(File file) throws IOException {
		obsList = FXCollections.observableArrayList();
	
		Scanner scan;
		try {
			scan = new Scanner(new File(fileName));
		} catch (Exception e) {
			return;
		}
		
		int lineCount = 0;
		while (scan.hasNextLine()){
			
			String[] songLine = scan.nextLine().split(",");
			if (songLine.length != 4) {
				
				Alert alert = new Alert(AlertType.ERROR, "[Line " + lineCount + "]: Song does not have 4 items. Cannot import list of songs. "
						+ "Please open songs.txt in Eclipse to fix the data.", ButtonType.CLOSE);
				alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
				alert.showAndWait();
				System.exit(0);
			}
			else { // song has 4 items. 
			 
				if (songLine[0].equals("")) {
					Alert alert = new Alert(AlertType.ERROR, "[Line " + lineCount + "]: Missing song Name. Cannot import list of songs. "
							+ "Please open songs.txt in Eclipse to fix the data.", ButtonType.CLOSE);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					System.exit(0);
				}
				
				if (songLine[1].equals("")) {
					Alert alert = new Alert(AlertType.ERROR, "[Line " + lineCount + "]: Missing song Album. Cannot import list of songs. "
							+ "Please open songs.txt in Eclipse to fix the data.", ButtonType.CLOSE);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					System.exit(0);
				}
				
				
				
				
				String a = songLine[2];
				String b = songLine[3];
				
				if (songLine[2].equals("null")) {
					a = "";
				}
				if (songLine[3].equals("null")) {
					b= "";
				}
				try {
					Integer.parseInt(b);
				}
				catch ( NumberFormatException e){
					System.out.println("ERROR [Line " + lineCount + "]: NumberFormatException - Year is not properly formatted.");
					System.exit(0);
				}
				SongMetadata importedSong = new SongMetadata(songLine[0], songLine[1], a, b);
				
				if (obsList.contains(importedSong)) {
					Alert alert = new Alert(AlertType.ERROR, "[Line " + lineCount + "]: Duplicate Songs detected. Cannot import list of songs. "
							+ "Please open songs.txt in Eclipse to fix the data.", ButtonType.CLOSE);
					alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
					alert.showAndWait();
					System.exit(0);
				}
				else{
					obsList.add(importedSong);
				}
				
				
				 
			 }
			 
		}
		
	}
	public void saveToFile() throws IOException{
		//File file = new File(fileName);
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		
		
		
		for (int i = 0; i < obsList.size(); i++) {
			SongMetadata song = obsList.get(i);
			
			String exportName = song.getSongName();
			String exportArtist = song.getSongArtist();
			String exportAlbum = song.getSongAlbum();
			String exportYear = song.getSongYear();
			
			if (exportAlbum.equals("")) {
				exportAlbum = "null";
			}
			
		//	System.out.println("eName " + exportName + " eArtist " + exportArtist +" eAlbum " +exportAlbum + " eYear" + exportYear);
			if (exportYear.equals("")) {
				exportYear = "null";
			}
			
			printWriter.println(exportName + "," + exportArtist + "," + exportAlbum + "," +exportYear);		
		}
		

	    printWriter.close();
	}
	
	// Helper to check if string is an integer
	public boolean isInteger( String input )
	{

	   try
	   {
	      Integer.parseInt( input );
	      return true;
	   }
	   catch( Exception e )
	   {
	      return false;
	   }
	}	
	private void setDetailView(SongMetadata song) {
		lblDisplayName.setText(song.getSongName());
		lblDisplayArtist.setText(song.getSongArtist());
		lblDisplayAlbum.setText(song.getSongAlbum());
		lblDisplayYear.setText(song.getSongYear());
	}
	private boolean checkSongParams(String name, String artist, String album, String year) {
		if (name.equals("") ||  artist.equals("")) {
			Alert alert = new Alert(AlertType.ERROR, "Name or Artist missing. Please try again.");
			alert.showAndWait();	
			return false;
		}
		return true;
	}
	
	// Visibility Controls for Textfields and labels
	private void disableAllTextFields() {
		tfName.setVisible(false);
		tfArtist.setVisible(false);
		tfAlbum.setVisible(false);
		tfYear.setVisible(false);
	}
	private void enableAllTextFields() {
		tfName.setVisible(true);
		tfArtist.setVisible(true);
		tfAlbum.setVisible(true);
		tfYear.setVisible(true);
	}
	private void disableAllLabels() {
		lblDisplayName.setVisible(false);
		lblDisplayAlbum.setVisible(false);
		lblDisplayArtist.setVisible(false);
		lblDisplayYear.setVisible(false);
	}
	private void enableAllLabels() {
		lblDisplayName.setVisible(true);
		lblDisplayAlbum.setVisible(true);
		lblDisplayArtist.setVisible(true);
		lblDisplayYear.setVisible(true);
	}
}
