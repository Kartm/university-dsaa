package dsaa.lab_9;

public class DisjointSetForest implements DisjointSetDataStructure
{
	
	// public static void main(String[] args)
	// {
	//
	// DisjointSetForest list = new DisjointSetForest(5);
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
			
			parent = item;
			rank = 1;
			
		}
		
		int rank;
		int parent;
		
	}
	
	Element[] arr;
	
	public DisjointSetForest(int size)
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
		
		if (arr[item] == null) return -1;
		while (arr[item].parent != item) item = arr[item].parent;
		return item;
		
	}
	
	
	@Override
	public boolean union(int itemA, int itemB)
	{
		
		itemA = findSet(itemA);
		itemB = findSet(itemB);
		if (itemA == -1 || itemB == -1 || itemA == itemB) return false;
		if (arr[itemA].rank < arr[itemB].rank)
		{
			int temp = itemA;
			itemA = itemB;
			itemB = temp;
		}
		else if (arr[itemA].rank == arr[itemB].rank && arr[itemA].rank == 1) arr[itemA].rank = 2;
		arr[itemB].parent = itemA;
		return true;
		// TODO
		/*
		 * Union(x,y) Link(Find-Set(x),Find-Set(y)
		 */
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		for (int x = 0; x < arr.length; x++)
		{
			string += x + " -> " + arr[x].parent;
			if (x < arr.length - 1) string += "\n";
		}
		return string;
		
	}
	
}
