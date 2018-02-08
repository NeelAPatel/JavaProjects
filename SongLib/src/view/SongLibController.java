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

	public void start(Stage mainStage) {
		// create an ObservableList 
		// from an ArrayList

		mainStage.setTitle("Song Library by Neel Patel");
		ArrayList<SongMetadata> songLibArr = new ArrayList<SongMetadata>(0);
		//createDummyList();

		songMetaObservableList = FXCollections.observableList(songLibArr);
		songListView.setItems(songMetaObservableList);
		
        songListView.setCellFactory(new Callback<ListView<SongMetadata>, javafx.scene.control.ListCell<SongMetadata>>()
        {
            @Override
            public ListCell<SongMetadata> call(ListView<SongMetadata> listView)
            {
                return new ListViewCell();
            }
        });
		

//        songListView.getSelectionModel().getSelectedItem() returns a SongMetadata item
        
        
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
		
		//songListView.setItems(obsList);
		songListView.setEditable(true);
		

			
		
		//String x = "Index: " + e.getIndex() + " Value: " + e.getNewValue() ;
		//lblTest.setText(x);
		
		lblDisplayName.setVisible(false);
		lblDisplayAlbum.setVisible(false);
		lblDisplayArtist.setVisible(false);
		lblDisplayYear.setVisible(false);
		
		
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
		
		tfName.setDisable(true);
		tfArtist.setDisable(true);
		tfAlbum.setDisable(true);
		tfYear.setDisable(true);
	}
	
	@FXML
	public void btnPress(ActionEvent e) {
		Button btn = (Button) e.getSource();
		if(btn == btnAdd) {
			lblTest.setText("ADDINGGGG");
			
			lblDisplayName.setVisible(false);
			lblDisplayAlbum.setVisible(false);
			lblDisplayArtist.setVisible(false);
			lblDisplayYear.setVisible(false);
			
			

			
			
			tfName.setVisible(true);
			tfArtist.setVisible(true);
			tfAlbum.setVisible(true);
			tfYear.setVisible(true);
			
			tfName.setDisable(false);
			tfArtist.setDisable(false);
			tfAlbum.setDisable(false);
			tfYear.setDisable(false);
			
            btnAdd.setVisible(false);
			if (songMetaObservableList.size()> 0) {
				btnEdit.setVisible(true);
				btnDelete.setVisible(true);
			}else
			{
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
			}
            btnSave.setVisible(true);
            btnCancel.setVisible(false);
			
		}else if (btn == btnSave) {
			SongMetadata newSong = new SongMetadata(tfName.getText(), tfArtist.getText(), tfAlbum.getText(), tfYear.getText());
			//songLibArr.add(newSong);
			//songListView.getItems().add(newSong);
			songMetaObservableList.add(newSong);
			songListView.refresh();
			

			lblDisplayName.setVisible(true);
			lblDisplayAlbum.setVisible(true);
			lblDisplayArtist.setVisible(true);
			lblDisplayYear.setVisible(true);
			
			
			tfName.setVisible(false);
			tfArtist.setVisible(false);
			tfAlbum.setVisible(false);
			tfYear.setVisible(false);
			
			
			tfName.setDisable(true);
			tfArtist.setDisable(true);
			tfAlbum.setDisable(true);
			tfYear.setDisable(true);


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


			/*
			lblDisplayName.setText();
			lblDisplayArtist.setText();
			lblDisplayAlbum.setText();
			lblDisplayYear.setText();
			*/
			
			lblTest.setText("btnSave Complete!" + songMetaObservableList.size());
			
		}else if (btn == btnEdit) {


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
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + lblDisplayName.getText() + " by " + lblDisplayArtist.getText() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    lblTest.setText("Delete=> Clicked Yes");
			}
			else
				lblTest.setText("Delete=> Clicked No");
			
			
			
			
			
			btnAdd.setVisible(true);
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
			
		}else if (btn == btnCancel) {


            btnAdd.setVisible(true);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnSave.setVisible(false);
            btnCancel.setVisible(false);
		}
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
