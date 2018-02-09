package view;

import javafx.scene.control.ListCell;
/*
 * Neel Patel 
 * Bharat Kumar
 * Spring 2018 - Software Methodology
 */
public class ListViewCell extends ListCell<Song>
{
    @Override
    public void updateItem(Song songItem, boolean empty)
    {
        super.updateItem(songItem,empty);

        
        
		if (songItem == null || empty) {
			//setDisable(false);
			setText(null);
			setGraphic(null);
		}
		else
		{
			//setDisable(true);
			CellData data = new CellData();
            data.setInfo(songItem);
            setGraphic(data.getBox());
		}
    }
}