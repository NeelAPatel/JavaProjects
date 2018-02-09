package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.IOException;
/*
 * Neel Patel 
 * Bharat Kumar
 * Spring 2018 - Software Methodology
 */
public class CellData
{
    @FXML
    private VBox vBox;
    @FXML
    private Label lblSongItemName;
    @FXML
    private Label lblSongItemArtist;

    public CellData()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewCellLayout.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Song songItem)
    {
    	lblSongItemName.setText(songItem.getName());
    	lblSongItemArtist.setText(songItem.getArtist());
    }
    
    
    public VBox getBox()
    {
        return vBox;
    }
}