package view;

import java.util.Comparator;

public class SongCompare implements Comparator<SongMetadata> {
	@Override
	public int compare (SongMetadata a, SongMetadata b) {
		if (a.getSongName().compareTo(b.getSongName()) == 0) 
		{
			return a.getSongArtist().compareTo(b.getSongArtist());
		}
		return a.getSongName().compareTo(b.getSongName());
		
	}

}
