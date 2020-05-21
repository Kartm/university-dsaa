package dsaa_jk.assignment_7;

public class RBNode<T> {

    public T value;
    RBNode<T> left = null, right = null, parent = null;

    public Color color = Color.RED;

    public int leftSubtreeHeight = 0;
    public int rightSubtreeheight = 0;
    public int leftSubtreeNodesCount = 0;
    public int rightSubtreeNodesCount = 0;
    public int leftSubtreeLeavesCount = 0;
    public int rightSubtreeLeavesCount = 0;

    public RBNode(T v) {
        value = v;
    }

    public RBNode(T value, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
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
