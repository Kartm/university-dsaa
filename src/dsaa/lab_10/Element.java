package dsaa.lab_10;

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
