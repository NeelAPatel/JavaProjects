package application;

public class Node<T>
{
	private T data;
	private Node<T> nextNode;
	
	public Node()
	{
		this.data = null;
		this.nextNode = null;
	}
	
	public Node(T data)
	{
		this.data = data;
		this.nextNode = null;
	}
	
	public Node(T data, Node<T> nextNode)
	{
		this.data = data;
		this.nextNode = nextNode;
	}
	
	public T getData()
	{
		return this.data;
	}
	
	public Node<T> getNextNode()
	{
		return this.nextNode;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	public void setNextNode(Node<T> nextNode)
	{
		this.nextNode = nextNode;
	}
	
	public String toString()
	{
		return this.data.toString();
	}
	
	public boolean equals(Node<T> node)
	{
		if (node == null)
		{
			return false;
		}
		
		if (this.data == null && node.getData() == null)
		{
			return true;
		}
		
		return (this.data.equals(node.getData()) && this.nextNode.equals(node.getNextNode()));
	}
}
