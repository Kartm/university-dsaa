package dsaa.lab_9;

public class DisjointSetLinkedList implements DisjointSetDataStructure
{
	
	// public static void main(String[] args)
	// {
	//
	// DisjointSetLinkedList list = new DisjointSetLinkedList(5);
	// for (int x = 0; x < 5; x++)
	// {
	// list.makeSet(x);
	// }
	// list.union(2, 4);
	// list.union(2, 1);
	// System.out.println(list.toString());
	//
	// }
	
	private class Element
	{
		
		private Element(int item)
		{
			
			representant = item;
			last = item;
			
		}
		
		int representant;
		int next = -1;
		int length = 1;
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
		
		arr[item] = new Element(item);
		
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
		arr[itemA].last = itemB;
		return true;
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		for (int x = 0; x < arr.length; x++)
		{
			if (arr[x].representant != x) continue;
			while (true)
			{
				string += x;
				if (arr[x].next != NULL) string += ", ";
				else
				{
					x = arr[x].representant;
					string += "\n";
					break;
				}
				x = arr[x].next;
			}
		}
		return string;
		
	}
	
}
