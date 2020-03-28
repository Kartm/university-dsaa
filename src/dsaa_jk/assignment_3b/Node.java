package dsaa_jk.assignment_3b;

public class Node<E> {
    E object;
    Node<E> next;

    public Node(E object) {
        this.object = object;
        next = null;
    }
}