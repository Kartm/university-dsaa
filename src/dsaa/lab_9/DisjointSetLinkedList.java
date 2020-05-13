package dsaa.lab_9;

public class DisjointSetLinkedList implements DisjointSetDataStructure
{
	
	private class Element
	{
		
		private Element(int item)
		{
			
			representant = item;
			
			// next =
		}
		
		int representant;
		int next;
		int length;
		int last;
		
	}
	
	private static final int NULL = -1;
	
	Element arr[];
	int pos = 0; // Added myself
	
	public DisjointSetLinkedList(int size)
	{
		
		arr = new Element[size];
		
	}
	
	
	@Override
	public void makeSet(int item)
	{
		
		arr[item] = new Element(int item);
		
	}
	
	
	@Override
	public int findSet(int item)
	{
		
		return arr[item].representant;
		
	}
	
	
	@Override
	public boolean union(int itemA, int itemB)
	{
		
		if (arr[itemA] == null || arr[itemB] == null || itemA == itemB) return false;
		arr[arr[itemA].last].next = itemB; // "Tail.next = other.head
		arr[itemA].length += arr[itemB].length;
		arr[itemB].representant = itemA;
		for (Element elem = arr[arr[itemB].next]; elem != arr[itemB]; elem = arr[elem.next]) elem.representant = itemA;
		return true;
		
	}
	
	
	@Override
	public String toString()
	{
		
		// TODO
		return null;
		
	}
	
}
