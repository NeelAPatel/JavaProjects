package view;

public class SongMetadata {
	

	private String songName;
	private String songArtist;
	private String songAlbum;
	private String songYear;
	
	

	public SongMetadata(String songName, String songArtist, String songAlbum, String songYear) {
		super();
		this.songName = songName;
		this.songArtist = songArtist;
		this.songAlbum = songAlbum;
		this.songYear = songYear;
	}
	
	public String getSongName() {
		return songName;
	}
	public String getSongArtist() {
		return songArtist;
	}
	public String getSongAlbum() {
		return songAlbum;
	}
	public String getSongYear() {
		return songYear;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}
	public void setSongAlbum(String songAlbum) {
		this.songAlbum = songAlbum;
	}
	public void setSongYear(String songYear) {
		this.songYear = songYear;
	}
	
	@Override
	public boolean equals(Object item) {
		if ( ! (item instanceof SongMetadata)) {
			return false; // not a song. 
		}
		SongMetadata x = (SongMetadata) item;
		
		if (this.songName.equalsIgnoreCase(x.songName) &&  this.songArtist.equalsIgnoreCase(x.songArtist)) {
			return true;
		}
		return false;
	}
	
}
