package dsaa.lab_9;

public interface DisjointSetDataStructure {
    void makeSet(int item);

    int findSet(int item);

    boolean union(int itemA, int itemB);
}