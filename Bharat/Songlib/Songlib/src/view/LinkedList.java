package view;

/*
 * Neel Patel 
 * Bharat Kumar
 * Spring 2018 - Software Methodology
 */

public class LinkedList
{
	private Node<Song> root;
	private int size;
	
	public LinkedList()
	{
		this.root = null;
		this.size = 0;
	}
	
	public int addItemWithIndex(Song song)
	{
		if (this.size == 0)
		{
			this.root = new Node<Song>(song);
			this.size++;
			return 0;
		}
		
		Node<Song> ptr = this.root;
		Node<Song> tmp = null;
		int i = 0;
		do
		{
			if (ptr.getData().compare(song) == 0)
			{
				return -1;
			}
			
			if (song.compare(ptr.getData()) < 0)
			{
				if (tmp == null)
				{
					tmp = new Node<Song>(this.root.getData(), this.root.getNextNode());
					this.root = new Node<Song>(song, tmp);
					this.size++;
					return 0;
				}
				
				tmp.setNextNode(new Node<Song>(song, ptr));
				this.size++;
				return i;
			}
			
			tmp = ptr;
			ptr = ptr.getNextNode();
			i++;
		} while(ptr != null);
		
		tmp.setNextNode(new Node<Song>(song));
		this.size++;
		return i;
	}
	
	public void addItem(Song song)
	{
		if (this.size == 0)
		{
			this.root = new Node<Song>(song);
			this.size++;
			return;
		}
		
		Node<Song> ptr = this.root;
		Node<Song> tmp = null;
		do
		{
			if (ptr.getData().compare(song) == 0)
			{
				return;
			}
			
			if (song.compare(ptr.getData()) < 0)
			{
				if (tmp == null)
				{
					tmp = new Node<Song>(this.root.getData(), this.root.getNextNode());
					this.root = new Node<Song>(song, tmp);
					this.size++;
					return;
				}
				
				tmp.setNextNode(new Node<Song>(song, ptr));
				this.size++;
				return;
			}
			
			tmp = ptr;
			ptr = ptr.getNextNode();
		} while(ptr != null);
		
		tmp.setNextNode(new Node<Song>(song));
		this.size++;
		return;
	}
	
	public void deleteItem(Song song)
	{
		if (this.size == 0)
		{
			return;
		}
		
		Node<Song> ptr = this.root;
		Node<Song> tmp = null;
		do
		{
			if (ptr.getData().equals(song))
			{
				if (tmp == null)
				{
					this.root = null;
					this.size--;
					return;
				}
				
				tmp.setNextNode(ptr.getNextNode());
				this.size--;
				return;
			}
			
			tmp = ptr;
			ptr = ptr.getNextNode();
		} while (ptr != null);
	}
	
	public void deleteIndex(int index)
	{
		if (this.size == 0 || index < 0 || index >= this.size)
		{
			return;
		}
		
		if (index == 0)
		{
			this.root = this.root.getNextNode();
			this.size--;
			return;
		}
		
		Node<Song> ptr = this.root;
		Node<Song> tmp = null;
		
		for (int i = 0; i < this.size; i++)
		{
			if (i == index)
			{
				if (tmp == null)
				{
					this.root = null;
					this.size--;
					return;
				}
				
				tmp.setNextNode(ptr.getNextNode());
				this.size--;
				return;
			}
			
			tmp = ptr;
			ptr = ptr.getNextNode();
		}
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public int getIndex(Song song)
	{
		Node<Song> ptr = this.root;
		for (int i = 0; i < this.size; i++)
		{
			if (ptr.getData().equals(song))
			{
				return i;
			}
			
			ptr = ptr.getNextNode();
		}
		
		return -1;
	}
	
	public Song getSong(int index)
	{
		if (index < 0 || index >= this.size)
		{
			return null;
		}
		
		Node<Song> ptr = this.root;
		for (int i = 0; i < this.size; i++)
		{
			if (i == index)
			{
				return ptr.getData();
			}
			
			ptr = ptr.getNextNode();
		}
		
		return ptr.getData();
	}
	
	public void printList()
	{
		if (this.size == 0)
		{
			return;
		}
		
		Node<Song> ptr = this.root;
		for (int i = 0; i < this.size; i++)
		{
			System.out.println(ptr);
			ptr = ptr.getNextNode();
		}
	}
	
	public String toString()
	{
		String string = "";
		
		if (this.size == 0)
		{
			return string;
		}
		
		if (this.size == 1)
		{
			Song song = this.root.getData();
			string += song.getName() + "\n" + song.getArtist() + "\n" + song.getAlbum() + "\n" + song.getYear() + "\n";
			return string;
		}
		
		Node<Song> ptr = this.root;
		String s = "";
		
		for (int i = 0; i < this.size - 1; i++)
		{
			Song song = ptr.getData();
			
			if (song.getAlbum() == null)
			{
				s = " ";
			}
			else
			{
				s = song.getAlbum();
			}
			
			string += song.getName() + "\n" + song.getArtist() + "\n" + s + "\n" + song.getYear() + "\n";
			ptr = ptr.getNextNode();
		}
		
		if (ptr == null)
		{
			return string;
		}
		
		Song song = ptr.getData();
		
		if (song.getAlbum() == null)
		{
			s = " ";
		}
		else
		{
			s = song.getAlbum();
		}
		
		string += song.getName() + "\n" + song.getArtist() + "\n" + s + "\n" + song.getYear();
		return string;
	}
	
	public Node<Song> getRoot()
	{
		return this.root;
	}
	
	public static void main(String[] args)
	{
		
	}
}
