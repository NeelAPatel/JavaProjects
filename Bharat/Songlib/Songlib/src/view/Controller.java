package view;

import application.*;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


//Bharat Kumar and Neel Patel

public class Controller 
{
	@FXML private ListView<Song> songListView;
	
	@FXML private Label title;
	@FXML private Label lblName, lblArtist, lblAlbum, lblYear;
	@FXML private Label lblDisplayName, lblDisplayAlbum, lblDisplayArtist, lblDisplayYear;
	
	@FXML private TextField tfName, tfAlbum, tfArtist, tfYear;
	
	@FXML private Button btnAdd, btnEdit, btnDelete, btnSave, btnCancel;
	
	private ObservableList<Song> obsList;
	
	private File file;
	private Database database;
	
	private int actionType;
	private int currentIndex;
	
	private Song currentSong;
	
	public void showDetails(Song song)
	{
		hideTextFields();
		
		lblName.setVisible(true);
		lblArtist.setVisible(true);
		lblAlbum.setVisible(true);
		lblYear.setVisible(true);
		
		lblName.setText("Name: " + song.getName());
		lblArtist.setText("Artist: " + song.getArtist());
		lblAlbum.setText("Album: " + song.getAlbum());
		
		if (song.getAlbum() == null || song.getAlbum().equals(""))
		{
			lblAlbum.setText("Album is not specified");
		}
		
		lblYear.setText("Year: " + song.getYear());
		if (song.getYear() < 0)
		{
			lblYear.setText("Year is not specified");
		}
		
		btnAdd.setVisible(true);
		btnDelete.setVisible(true);
		btnEdit.setVisible(true);
		btnCancel.setVisible(false);
		btnSave.setVisible(false);
	}
	
	public void hideDetails()
	{
		lblName.setText("Name");
		lblArtist.setText("Artist");
		lblAlbum.setText("Album");
		lblYear.setText("Year");
	}
	
	public void showTitleLabels()
	{
		lblName.setVisible(true);
		lblArtist.setVisible(true);
		lblAlbum.setVisible(true);
		lblYear.setVisible(true);
	}
	
	public void hideTitleLabels()
	{
		lblName.setVisible(false);
		lblArtist.setVisible(false);
		lblAlbum.setVisible(false);
		lblYear.setVisible(false);
	}
	
	public void showTextFields()
	{
		hideDetails();
		
		tfName.setText("");
		tfAlbum.setText("");
		tfArtist.setText("");
		tfYear.setText("");
		
		tfName.setVisible(true);
		tfAlbum.setVisible(true);
		tfArtist.setVisible(true);
		tfYear.setVisible(true);
		
		btnDelete.setVisible(false);
		btnCancel.setVisible(true);
	}
	
	public void hideTextFields()
	{
		tfName.setVisible(false);
		tfAlbum.setVisible(false);
		tfArtist.setVisible(false);
		tfYear.setVisible(false);

		tfName.setText("");
		tfAlbum.setText("");
		tfArtist.setText("");
		tfYear.setText("");
		
		tfName.setPromptText("");
		tfAlbum.setPromptText("");
		tfArtist.setPromptText("");
		tfYear.setPromptText("");
		
		hideDetails();
		btnCancel.setVisible(false);
	}
	
	public void addItem()
	{
		showTitleLabels();
		showTextFields();
		btnCancel.setVisible(true);
		btnSave.setVisible(true);
		btnSave.setText("+ Add +");
		btnAdd.setVisible(false);
		btnEdit.setVisible(false);
	}
	
	public void editItem()
	{
		showTitleLabels();
		showTextFields();
		
		tfName.setText(currentSong.getName());
		tfArtist.setText(currentSong.getArtist());
		tfAlbum.setText("");
		tfYear.setText("");
		
		if (currentSong.getAlbum() != null)
		{
			tfAlbum.setText(currentSong.getAlbum());
		}
		
		if (currentSong.getYear() >= 0)
		{
			tfYear.setText("" + currentSong.getYear());
		}
		
		btnCancel.setVisible(true);
		btnSave.setVisible(true);
		btnSave.setText("' Edit '");
		btnEdit.setVisible(false);
		btnAdd.setVisible(false);
	}
	
	public void editSong()
	{
		try
		{
			database.removeSong(currentIndex);
			addSong();
		} catch(IOException ioException)
		{
			
		}
	}
	
	private static int convertCharacterToInt(String string)
	{
		if (string.substring(0, 1).equals("0"))
		{
			return 0;
		}
		else if (string.substring(0, 1).equals("1"))
		{
			return 1;
		}
		else if (string.substring(0, 1).equals("2"))
		{
			return 2;
		}
		else if (string.substring(0, 1).equals("3"))
		{
			return 3;
		}
		else if (string.substring(0, 1).equals("4"))
		{
			return 4;
		}
		else if (string.substring(0, 1).equals("5"))
		{
			return 5;
		}
		else if (string.substring(0, 1).equals("6"))
		{
			return 6;
		}
		else if (string.substring(0, 1).equals("7"))
		{
			return 7;
		}
		else if (string.substring(0, 1).equals("8"))
		{
			return 8;
		}
		else if (string.substring(0, 1).equals("9"))
		{
			return 9;
		}
		return -1;
	}
	
	private static int convertStringToInt(String string)
	{
		if (string.length() == 0)
		{
			return 0;
		}
		
		int number = 0;
		for (int i = 0; i < string.length(); i++)
		{
			number = 10 * number;
			number += convertCharacterToInt(string.substring(i, i + 1));
		}
		
		return number;
	}
	
	public boolean checkAddRequirements()
	{
		if (tfName.getText().equals("") || tfArtist.getText().equals(""))
		{
			return false;
		}
		
		return (!database.contains(tfName.getText(), tfArtist.getText()));
	}
	
	public void addSong()
	{
		String name = tfName.getText();
		String artist = tfArtist.getText();
		String album = tfAlbum.getText();
		String yearString = tfYear.getText();
		int year = -1;
		
		if (album == null || album.equals(""))
		{
			album = null;
		}
		
		if (!yearString.equals(""))
		{
			year = convertStringToInt(yearString);
		}
		
		Song song = new Song(name, artist, album, year);
		
		try
		{
			currentIndex = database.addSongWithIndex(song);
			insertDataIntoList();
			songListView.getSelectionModel().select(currentIndex);
			showDetails(song);
		} catch(IOException ioException)
		{
			
		}
	}
	
	public void insertDataIntoList()
	{
		LinkedList linkedList = new LinkedList();
		
		obsList = FXCollections.observableArrayList();
		
		linkedList = database.getLinkedList();
		
		if (database.isEmpty())
		{
			lblName.setVisible(false);
			lblArtist.setVisible(false);
			lblAlbum.setVisible(false);
			lblYear.setVisible(false);
			songListView.setItems(obsList);
			songListView.setEditable(true);
			currentIndex = 0;
			currentSong = null;
		}
		
		if (!database.isEmpty())
		{
			Node<Song> ptr = linkedList.getRoot();
			while (ptr != null)
			{
				obsList.add(ptr.getData());
				ptr = ptr.getNextNode();
			}
			
			songListView.setItems(obsList);
			songListView.setEditable(true);
			songListView.getSelectionModel().select(currentIndex);
			showDetails(obsList.get(0));
		}
	}
	
	public void start(Stage mainStage) 
	{
		file = new File("songs.txt");
		database = null;
		actionType = -1;
		
		
		try
		{
			database = new Database(file);
		} catch(IOException ioException)
		{
			
		}
		
		insertDataIntoList();
		
		btnDelete.setLayoutX(btnCancel.getLayoutX());
		btnDelete.setLayoutY(btnCancel.getLayoutY());
		btnDelete.setVisible(false);
		
		btnSave.setVisible(false);
		btnCancel.setVisible(false);
		btnEdit.setVisible(false);
		
		tfName.setVisible(false);
		tfAlbum.setVisible(false);
		tfArtist.setVisible(false);
		tfYear.setVisible(false);
		
		songListView.setItems(obsList);
		songListView.setEditable(true);
		
		if (!database.isEmpty())
		{
			currentIndex = 0;
			currentSong = obsList.get(0);
			showDetails(currentSong);
			songListView.getSelectionModel().select(currentIndex);
		}
		
		
		songListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() 
		{
			@Override
			public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue)
			{
				currentIndex = songListView.getSelectionModel().getSelectedIndex();
				currentSong = newValue;
				showDetails(currentSong);
			}
		});
	}
	
	@FXML
	public void btnPress(ActionEvent e) 
	{
		Button btn = (Button) e.getSource();
		
		if (btn == btnAdd) 
		{
			actionType = 0;
			addItem();
		}
		else if (btn == btnEdit) 
		{
			actionType = 1;
			editItem();
		}
		else if (btn == btnDelete) 
		{
			try
			{
				database.removeSong(currentIndex);
				insertDataIntoList();
				
				if (database.getSize() == 0)
				{
					btnDelete.setVisible(false);
					btnEdit.setVisible(false);
					hideDetails();
					lblName.setVisible(false);
					lblArtist.setVisible(false);
					lblAlbum.setVisible(false);
					lblYear.setVisible(false);
				}
				
				if (currentIndex == database.getSize())
				{
					currentIndex = currentIndex - 1;
				}
				
				currentSong = obsList.get(currentIndex);
				songListView.getSelectionModel().select(currentIndex);
				showDetails(currentSong);
			} catch(IOException ioException)
			{
				
			}
		}
		else if (btn == btnSave) 
		{
			if (actionType == 0)
			{
				addSong();
			}
			else if (actionType == 1)
			{
				editSong();
			}
		}
		else if (btn == btnCancel)
		{
			btnAdd.setVisible(true);
			
			if (!database.isEmpty())
			{
				btnEdit.setVisible(true);
				songListView.getSelectionModel().select(currentIndex);
				hideTextFields();
				showDetails(currentSong);
				actionType = -1;
			}
			else
			{
				btnEdit.setVisible(false);
				btnDelete.setVisible(false);
				btnEdit.setVisible(false);
				hideDetails();
				hideTextFields();
				lblName.setVisible(false);
				lblArtist.setVisible(false);
				lblAlbum.setVisible(false);
				lblYear.setVisible(false);
				actionType = -1;
				hideTitleLabels();
			}
			btnSave.setVisible(false);
		}
	}
}