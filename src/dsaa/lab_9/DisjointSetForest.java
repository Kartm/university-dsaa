package dsaa.lab_9;

public class DisjointSetForest implements DisjointSetDataStructure {
    public class Element {
        public Element(int item) {
            parent = item;
            rank = 1;
        }

        int rank;
        int parent;

        int getParent() {
            return parent;
        }

        int getRank() {
            return rank;
        }
    }

    Element[] arr;

    public DisjointSetForest(int size) {
        arr = new Element[size];
    }

    @Override
    public void makeSet(int item) {
        arr[item] = new Element(item);
    }

    @Override
    public int findSet(int item) {
        if (arr[item] == null) {
            return -1;
        }

        int representant = item;

        while (arr[representant].parent != representant) {
            representant = arr[representant].parent;
        }

        // path compression
        arr[item].parent = representant;

        return representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        // finding representants
        itemA = findSet(itemA);
        itemB = findSet(itemB);

        if (itemA == -1 || itemB == -1 || itemA == itemB) {
            return false;
        }

        if (arr[itemA].rank <= arr[itemB].rank) {
            arr[itemA].parent = itemB;
        } else if (arr[itemA].rank > arr[itemB].rank) {
            arr[itemB].parent = itemA;
        }

        if (arr[itemA].rank == arr[itemB].rank) {
            arr[itemB].rank++;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Disjoint sets as forest:\n");
        for (int x = 0; x < arr.length; x++) {
            string.append(x).append(" -> ").append(arr[x].parent);
            if (x < arr.length - 1) string.append("\n");
        }
        return string.toString();
    }
}
