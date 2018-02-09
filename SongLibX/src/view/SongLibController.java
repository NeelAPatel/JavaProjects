/* Neel Patel - Bharat Kumar
 * Software Methodology Spring 2018
 */
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
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
	private ObservableList<SongMetadata> songMetaObservableList;        
	private String editingMode = "blank";
	
	/**
	 * Starts the layout and sets up listview 
	 * @param mainStage 
	 */
	public void start(Stage mainStage) {
		
		//MainStage parameters
		mainStage.setTitle("Song Library by Neel Patel");
		
		//Set up Array for listview
		ArrayList<SongMetadata> songLibArr = new ArrayList<SongMetadata>(0);
		songMetaObservableList = FXCollections.observableList(songLibArr);
		songListView.setItems(songMetaObservableList);
		
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
				lblDisplayName.setText(newValue.getSongName());
				lblDisplayAlbum.setText(newValue.getSongAlbum());
				lblDisplayArtist.setText(newValue.getSongArtist());
				lblDisplayYear.setText(newValue.getSongYear());
			}
        });
		
        
        
        //UI Element Management
		enableAllLabels();
		disableAllTextFields();
		
		btnAdd.setVisible(true);
		if (songMetaObservableList.size()> 0) {
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
			
		}else if (btn == btnSave) { //SAVE BUTTON
			
			//CREATE IF STATEMENT CONDITION TO PREVENT BLANK AND ALLOW IF NAME AND ARTIST EXIST
			//USE STRING TO SET MODE

			//Saves Song to ListView Array
			
			if (editingMode.equals("add")) {
				SongMetadata newSong = new SongMetadata(tfName.getText(), tfArtist.getText(), tfAlbum.getText(), tfYear.getText());
				songMetaObservableList.add(newSong);
				songListView.refresh();
			}
			else if (editingMode.equals("edit")){
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
			}
			
				
			disableAllTextFields();
			enableAllLabels();
			
			btnAdd.setVisible(true);
			if (songMetaObservableList.size()> 0) {
				btnEdit.setVisible(true);
				btnDelete.setVisible(true);
			}else{
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}
            btnSave.setVisible(false);
            btnCancel.setVisible(false);


			/*
			lblDisplayName.setText();
			lblDisplayArtist.setText();
			lblDisplayAlbum.setText();
			lblDisplayYear.setText();
			*/
			
			lblTest.setText("btnSave Complete!" + songMetaObservableList.size());
			
		}else if (btn == btnEdit) { //EDIT BUTTON
			editingMode = "edit";
			enableAllTextFields();
			
			disableAllLabels();

			
			SongMetadata current = songListView.getSelectionModel().getSelectedItem();
			tfName.setText(current.getSongName());
			tfAlbum.setText(current.getSongAlbum());
			tfArtist.setText(current.getSongArtist());
			tfYear.setText(current.getSongYear());
			
			
			
			
			btnAdd.setVisible(false);
			if (songMetaObservableList.size()> 0) {
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}else
			{
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}
            btnSave.setVisible(true);
            btnCancel.setVisible(false);
			
		}else if (btn == btnDelete) {
			
			  
	        songListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SongMetadata>() {
				@Override
				public void changed(ObservableValue<? extends SongMetadata> arg0, SongMetadata oldValue, SongMetadata newValue) {
					// TODO Auto-generated method stub
					lblDisplayName.setText(newValue.getSongName());
					lblDisplayAlbum.setText(newValue.getSongAlbum());
					lblDisplayArtist.setText(newValue.getSongArtist());
					lblDisplayYear.setText(newValue.getSongYear());
				}
	        });
			
	        
	        SongMetadata selectedSong = songListView.getItems().get(songListView.getSelectionModel().getSelectedIndex());
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selectedSong.getSongName() + " by " + selectedSong.getSongArtist() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    lblTest.setText("Delete=> Clicked Yes"+ songListView.getItems().size());
			    songListView.getItems().remove(selectedSong);
			    songListView.refresh();
			}
			else {
				lblTest.setText("Delete=> Clicked No" + songListView.getItems().size());
				songListView.refresh();
			}
			
			
			
			
			
			btnAdd.setVisible(true);
			if (songMetaObservableList.size()> 0) {
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}else
			{
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}
            btnSave.setVisible(false);
            btnCancel.setVisible(false);
			
		}else if (btn == btnCancel) {


            btnAdd.setVisible(true);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnSave.setVisible(false);
            btnCancel.setVisible(false);
		}
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
