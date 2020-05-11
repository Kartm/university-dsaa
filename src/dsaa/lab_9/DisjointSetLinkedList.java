package dsaa.lab_9;

public class DisjointSetLinkedList implements DisjointSetDataStructure {
    private class Element {
        int representant;
        int next;
        int length;
        int last;
    }

    private static final int NULL = -1;

    Element arr[];

    public DisjointSetLinkedList(int size) {
        //TODO
    }

    @Override
    public void makeSet(int item) {
        //TODO
    }

    @Override
    public int findSet(int item) {
        //TODO
        return -1;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        //TODO
        return null;
    }
}
