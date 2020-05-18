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
		
		return arr[item] == null ? -1 : arr[item].representant;
		
	}
	
	
	@Override
	public boolean union(int itemA, int itemB)
	{
		
		itemA = findSet(itemA);
		itemB = findSet(itemB);
		if (arr[itemA].length < arr[itemB].length)
		{
			int temp = itemA;
			itemA = itemB;
			itemB = temp;
		}
		if (itemA == itemB) return false;
		arr[arr[itemA].last].next = itemB; // "Tail.next = other.head
		arr[itemA].length += arr[itemB].length;
		arr[itemA].last = arr[itemB].last;
		while (itemB != -1)
		{
			arr[itemB].representant = itemA;
			itemB = arr[itemB].next;
		}
		return true;
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "Disjoint sets as linked list:";
		for (int x = 0; x < arr.length; x++)
		{
			if (arr[x].representant != x) continue;
			string += "\n";
			while (true)
			{
				string += x;
				if (arr[x].next != NULL) string += ", ";
				else
				{
					x = arr[x].representant;
					
					break;
				}
				x = arr[x].next;
			}
		}
		return string;
		
	}
	
}
