package view;

import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<SongMetadata>
{
    @Override
    public void updateItem(SongMetadata songItem, boolean empty)
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
			Data data = new Data();
            data.setInfo(songItem);
            setGraphic(data.getBox());
		}
    }
}