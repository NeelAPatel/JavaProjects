package application;

//Bharat Kumar

public class Song 
{
	private String name, artist, album;
	private int year;
	
	public Song(String name, String artist)
	{
		this.name = name;
		this.artist = artist;
		this.album = null;
		this.year = -1;
	}
	
	public Song(String name, String artist, String album)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = -1;
	}
	
	public Song(String name, String artist, int year)
	{
		this.name = name;
		this.artist = artist;
		this.album = null;
		this.year = year;
	}
	
	public Song(String name, String artist, String album, int year)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getArtist()
	{
		return this.artist;
	}
	
	public String getAlbum()
	{
		return this.album;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public void set(String name, String artist, String album, int year)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public void setAlbum(String album)
	{
		this.album = album;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public boolean equals(Song song)
	{
		if (this.compare(song) != 0)
		{
			return false;
		}
		
		if (!this.getAlbum().equals(song.getAlbum()))
		{
			return false;
		}
		
		if (this.getYear() != song.getYear())
		{
			return false;
		}
		
		return true;
	}
	
	public int compare(Song song)
	{
		int result = this.getName().compareTo(song.getName());
		
		if (result == 0)
		{
			result = this.getArtist().compareTo(song.getArtist());
			return result;
		}
		
		return result;
	}
	
	public String toString()
	{
		return (this.getName() + "\n" + this.getArtist());
	}
}
