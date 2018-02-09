package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Neel Patel 
 * Bharat Kumar
 * Spring 2018 - Software Methodology
 */

/**
 * 
 * @author Bharat
 * Saves and edits data in a file 
 *
 */
public class Database
{
	private File file;
	private LinkedList linkedList;
	
	/**
	 * 
	 * @param file
	 * @throws IOException
	 * Initializes a Database to read data from a file or create a file if not done so already
	 * 
	 */
	public Database(File file) throws IOException
	{
		this.file = file;
		linkedList = new LinkedList();
		
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(this.file);
		} catch(FileNotFoundException fileNotFoundError)
		{
			try 
			{
				FileWriter fileWriter = new FileWriter(this.file);
				fileWriter.close();
			} catch (IOException ioExceptionError) 
			{
				throw ioExceptionError;
			}
			
			try
			{
				scanner = new Scanner(this.file);
			} catch(FileNotFoundException fileNotFound)
			{
				throw fileNotFound;
			}
		}
		
		while (scanner.hasNextLine())
		{
			String name = scanner.nextLine();
			String artist = scanner.nextLine();
			String album = scanner.nextLine();
			int year = convertStringToInt(scanner.nextLine());
			
			if (album.equals(" "))
			{
				album = null;
			}
			
			Song song = new Song(name, artist, album, year);
			this.linkedList.addItem(song);
		}
		
		scanner.close();
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
	
	/**
	 * 
	 * @param name
	 * @param artist
	 * @param album
	 * @param year
	 * @throws IOException
	 * Adds a song with the given parameters
	 */
	public void addSong(String name, String artist, String album, int year) throws IOException
	{
		Song song = new Song(name, artist, album, year);
		this.linkedList.addItem(song);
		try
		{
			this.refresh();
		} catch(IOException ioException)
		{
			throw ioException;
		}
	}
	
	public boolean contains(String songName, String songArtist)
	{
		Node<Song> ptr = this.linkedList.getRoot();
		while(ptr != null)
		{
			if (ptr.getData().getName().equals(songName) && ptr.getData().getArtist().equals(songArtist))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addSong(Song song) throws IOException
	{
		this.linkedList.addItem(song);
		try
		{
			this.refresh();
		} catch(IOException ioException)
		{
			throw ioException;
		}
	}
	
	public int addSongWithIndex(Song song) throws IOException
	{
		int index = this.linkedList.addItemWithIndex(song) + 1;
		try
		{
			this.refresh();
		} catch(IOException ioException)
		{
			throw ioException;
		}
		return index;
	}
	
	public void removeSong(String name, String artist, String album, int year) throws IOException
	{
		Song song = new Song(name, artist, album, year);
		this.linkedList.deleteItem(song);
		try
		{
			this.refresh();
		} catch (IOException ioException)
		{
			throw ioException;
		}
	}
	
	public void removeSong(int index) throws IOException
	{
		this.linkedList.deleteIndex(index);
		try
		{
			this.refresh();
		} catch(IOException ioException)
		{
			throw ioException;
		}
	}
	
	public void editSong(int index, Song song) throws IOException
	{
		this.linkedList.deleteIndex(index);
		this.linkedList.addItem(song);
		try
		{
			this.refresh();
		} catch(IOException ioException)
		{
			throw ioException;
		}
	}
	
	public void refresh() throws IOException
	{
		FileWriter fileWriter = null;
		try
		{
			fileWriter = new FileWriter(file);
		} catch(IOException ioException)
		{
			throw ioException;
		}
		
		fileWriter.write("" + linkedList.toString());
		fileWriter.close();
	}
	
	public int getSize()
	{
		return this.linkedList.getSize();
	}
	
	public LinkedList getLinkedList()
	{
		return this.linkedList;
	}
	
	public boolean isEmpty()
	{
		return (this.linkedList.getSize() == 0);
	}
	
	public static void main(String[] args)
	{
		
	}
}