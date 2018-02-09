/* Neel Patel - Bharat Kumar
 * Software Methodology Spring 2018
 */
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;



public class SongLibController {
	@FXML ListView<SongMetadata> songListView;
	
	@FXML Label title, lblTest;
	@FXML Label lblA, lblB, lblC, lblD;
	@FXML Label lblDisplayName, lblDisplayAlbum, lblDisplayArtist, lblDisplayYear;
	
	@FXML TextField tfName, tfAlbum, tfArtist, tfYear;
	
	@FXML Button btnAdd, btnEdit, btnDelete, btnSave, btnCancel;
	
	
	//private ArrayList<SongMetadata> songLibArr; 
	private ObservableList<SongMetadata> obsList;        
	private String editingMode = "blank";

	String fileName = "songs.txt";
	private File file = new File(fileName);
	/**
	 * Starts the layout and sets up listview 
	 * @param mainStage 
	 */
	public void start(Stage mainStage) {

		//MainStage parameters
		mainStage.setTitle("Song Library");
		
		
		
		//Set up Array for listview
		ArrayList<SongMetadata> songLibArr = new ArrayList<SongMetadata>(0);
		obsList = FXCollections.observableList(songLibArr);
		try {
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
				// TODO Auto-generated method stub
				
				
				if (newValue != null) {
					lblDisplayName.setText(newValue.getSongName());
					lblDisplayAlbum.setText(newValue.getSongAlbum());
					lblDisplayArtist.setText(newValue.getSongArtist());
					lblDisplayYear.setText(newValue.getSongYear());
					setDetailView(newValue);
				}
			}
        });
		
        
        
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

	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();	
		if(btn == btnAdd) { // ADD BUTTON
			addButtonProcess();
		}else if (btn == btnSave) { //SAVE BUTTON
			
			//CREATE IF STATEMENT CONDITION TO PREVENT BLANK AND ALLOW IF NAME AND ARTIST EXIST
			//USE STRING TO SET MODE

			//Saves Song to ListView Array
			if (checkSongParams(tfName.getText(), tfArtist.getText(), tfAlbum.getText(), tfYear.getText())) {
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

			disableAllTextFields();
			
			tfName.setText("");
			tfArtist.setText("");
			tfAlbum.setText("");
			tfYear.setText("");
			
            btnAdd.setVisible(true);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnSave.setVisible(false);
            btnCancel.setVisible(false);
		}
		
		

		
	}
	
	
	private void addButtonProcess() {
		songListView.setDisable(true);
		lblTest.setText("Add Process started");
		editingMode = "add";
		
		enableAllTextFields();
		tfAlbum.setText(null);
		tfArtist.setText(null);
		tfName.setText(null);
		tfYear.setText(null);
		disableAllLabels();
		
		
        btnAdd.setVisible(false);
		btnEdit.setVisible(false);
		btnDelete.setVisible(false);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
        

	}
	private void editButtonProcess() {
		songListView.setDisable(true);
		lblTest.setText("Edit Process started");
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
		lblTest.setText("Delete Process started");
		songListView.setDisable(true);
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		
		if (alert.getResult() == ButtonType.YES) {
			SongMetadata currentSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex());
			SongMetadata previousSong = null;
			if (songListView.getItems().indexOf(currentSong) > 0)
				previousSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex() - 1 );
			
			if (previousSong != null) {

				songListView.getSelectionModel().select(previousSong);
				songListView.getItems().remove(currentSong);
				songListView.requestFocus();
				songListView.refresh();

				setDetailView(previousSong);
			}
			else {
				songListView.getItems().remove(currentSong);
				songListView.requestFocus();
				songListView.refresh();
				lblDisplayName.setText("");
				lblDisplayArtist.setText("");
				lblDisplayAlbum.setText("");
				lblDisplayYear.setText("");
				if (songListView.getItems().size()> 0)
					setDetailView(songListView.getSelectionModel().getSelectedItem());
			}
			
			
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
        try {
			updateFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	private void saveAddSong() {
		lblTest.setText("Saved new song");
		SongMetadata newSong = new SongMetadata(tfName.getText(), tfArtist.getText(), tfAlbum.getText(), tfYear.getText());
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

        lblTest.setText("btnSave Complete!" + obsList.size());
        songListView.setDisable(false);
        try {
			updateFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	private void saveEditSong() {
		lblTest.setText("Saved edited song");
		SongMetadata selectedSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex());
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
        try {
			updateFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	private void importFromFile(File file) throws IOException {
		obsList = FXCollections.observableArrayList();
		
		
		String line = null;
		String importName = "";
		String importArtist = "";
		String importYear = "";
		String importAlbum = "";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		 String st;
		 int lineNum = 1;
		  while ((st = br.readLine()) != null) {
			  switch(lineNum) {
			  case 1:
				  importName = st;
				  break;
			  case 2:
				  importArtist = st;
				  break;
			  case 3:
				  importAlbum = st;
				  break;
			  case 4: 
				  importYear = st;
				  break;
			  default:
				  break;
			  }
			 lineNum++;
			 if (lineNum == 5) {
				 SongMetadata importedSong = new SongMetadata(importName, importArtist, importAlbum, importYear);
				 obsList.add(importedSong);
				 lineNum = 1;
			 }
		}
		
	}
	
	@SuppressWarnings("resource")
	private void updateFile(File file) throws IOException{
		file = new File(fileName);
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		
		
		
		for (int i = 0; i < obsList.size(); i++) {
			SongMetadata song = obsList.get(i);
			
			String exportName = song.getSongName()+"\n";
			String exportArtist = song.getSongArtist()+"\n";
			String exportYear = song.getSongAlbum()+"\n";
			String exportAlbum = song.getSongYear()+"\n";
			System.out.println(song.getSongName()+"\n");
			
			printWriter.print(exportName);
			printWriter.print(exportArtist);
			printWriter.print(exportAlbum);
			printWriter.print(exportYear);			
		}
		

	    printWriter.close();
	}
	
	
	
	
	private void setDetailView(SongMetadata song) {
		lblDisplayName.setText(song.getSongName());
		lblDisplayArtist.setText(song.getSongArtist());
		lblDisplayAlbum.setText(song.getSongAlbum());
		lblDisplayYear.setText(song.getSongYear());
	}
	
	private boolean checkSongParams(String name, String artist, String album, String year) {
		if (name == null ||  artist == null) {
			Alert alert = new Alert(AlertType.ERROR, "Name or Artist missing. Please try again");
			alert.showAndWait();	
			return false;
		}
		return true;
	}
	
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
	
	/*
	private void createDummyList(){
		songLibArr.add(new SongMetadata("Name1", "Artist 1", "Album 1", "2001"));
		songLibArr.add(new SongMetadata("Name2", "Artist 2", "Album 2", "2002"));
		songLibArr.add(new SongMetadata("Name3", "Artist 3", "Album 3", "2003"));
		songLibArr.add(new SongMetadata("Name4", "Artist 4", "Album 4", "2004"));
		songLibArr.add(new SongMetadata("Name5", "Artist 5", "Album 5", "2005"));

	}*/
}
