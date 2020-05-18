package dsaa.lab_9;

public class DisjointSetLinkedList implements DisjointSetDataStructure {
    public class Element {

        public Element(int item) {

            representant = item;
            last = item;

        }

        int representant;
        int next = -1;
        int length = 1;
        int last;

        public int getRepresentant() {
            return representant;
        }

        public int getLength() {
            return length;
        }
    }

    Element[] arr;

    public DisjointSetLinkedList(int size) {

        arr = new Element[size];

    }


    @Override
    public void makeSet(int item) {

        arr[item] = new Element(item);

    }


    @Override
    public int findSet(int item) {
        if(arr[item] == null) {
            return -1;
        }

        return  arr[item].representant;
    }


    @Override
    public boolean union(int itemA, int itemB) {
        // finding representants
        itemA = findSet(itemA);
        itemB = findSet(itemB);

        if (itemA == itemB) {
            return false;
        }

        if (arr[itemA].length < arr[itemB].length) {
            // swap the items
            int temp = itemA;
            itemA = itemB;
            itemB = temp;
        }

        // attach as next
        arr[arr[itemA].last].next = itemB;
        arr[itemA].length += arr[itemB].length;
        arr[itemA].last = arr[itemB].last;

        while (itemB != -1) {
            arr[itemB].representant = itemA;
            itemB = arr[itemB].next;
        }

        return true;
    }


    @Override
    public String toString() {

        StringBuilder string = new StringBuilder("Disjoint sets as linked list:");
        for (int x = 0; x < arr.length; x++) {
            if (arr[x].representant != x) {
                continue;
            }

            string.append("\n");

            while (true) {
                string.append(x);
                if (arr[x].next != -1) {
                    string.append(", ");
                } else {
                    x = arr[x].representant;
                    break;
                }
                x = arr[x].next;
            }
        }
        return string.toString();
    }
}
