package dsaa_jk.assignment_7;

public class Node<T> {
    public T value;
    Node<T> left = null, right = null, parent = null;
    
    public int leftSubtreeHeight = 0;
    public int rightSubtreeheight = 0;
    public int leftSubtreeNodesCount = 0;
    public int rightSubtreeNodesCount = 0;
    public int leftSubtreeLeavesCount = 0;
    public int rightSubtreeLeavesCount = 0;

    public Node(T v) {
        value = v;
    }

    public Node(T value, Node<T> left, Node<T> right, Node<T> parent) {
        super();
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        String string = "";
        string += value.toString();
        return string;
    }
}
