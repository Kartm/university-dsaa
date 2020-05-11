package dsaa.lab_9;

public class DisjointSetForest implements DisjointSetDataStructure {
    private class Element {
        int rank;
        int parent;
    }

    Element[] arr;

    public DisjointSetForest(int size) {
        //TODO
    }

    @Override
    public void makeSet(int item) {
        //TODO
        /*

               Make-Set(x)
            p[x]:=x
            rank[x]:=0
         */
    }

    @Override
    public int findSet(int item) {
        //TODO
        return -1;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        //TODO
        /*
            Union(x,y)
    Link(Find-Set(x),Find-Set(y)
         */
        return false;
    }

    @Override
    public String toString() {
        //TODO
        return null;
    }
}