package dsaa.lab_8;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BST<T> {
    private class Node {
        T value;
        Node left, right, parent;

        public Node(T v) {
            value = v;
        }

        public Node(T value, Node left, Node right, Node parent) {
            super();
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private Comparator<T> comparator = null;
    private Node root;

    public BST() {
        root = null;
    }

    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    public T getElement(T toFind) {
        return getElement(root, toFind);
    }

    @SuppressWarnings("unchecked")
    private T getElement(Node start, T toFind) {
        // TODO
        Comparable toFindComparable = (Comparable) toFind;
        if(start == null) {
            return null;
        }

        if(toFindComparable.compareTo(toFind) == 0) {
            return start.value;
        } else if(toFindComparable.compareTo(start.value) < 0) {
            // searched value is smaller - look in the left subtree
            return getElement(start.left, toFind);
        } else {
            // searched value is larger - look in the right subtree
            return getElement(start.right, toFind);
        }
    }

    @SuppressWarnings("unchecked")
    private Node findNode(Node start, T toFind) {
        // TODO
        Comparable toFindComparable = (Comparable) toFind;
        if(start == null) {
            return null;
        }

        if(toFindComparable.compareTo(toFind) == 0) {
            return start;
        } else if(toFindComparable.compareTo(start.value) < 0) {
            // searched value is smaller - look in the left subtree
            return findNode(start, toFind);
        } else {
            // searched value is larger - look in the right subtree
            return findNode(start, toFind);
        }
    }

    private T getMinimum(Node startNode) {
        if(root == null) {
            throw new NoSuchElementException();
        }

        return getMinimumNode(startNode).value;
    }

    private Node getMinimumNode(Node startNode) {
        Node currentNode = startNode;

        while(currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode;
    }

    private T getMaximum(Node startNode) {
        if(root == null) {
            throw new NoSuchElementException();
        }

        return getMaximumNode(startNode).value;
    }

    private Node getMaximumNode(Node startNode) {
        Node currentNode = startNode;

        while(currentNode.right != null) {
            currentNode = currentNode.right;
        }

        return currentNode;
    }

    public T successor(T elem) {
        Node successor = findNode(root, elem);

        if(successor.right != null) {
            return getMinimum(successor.right);
        }

        Node currNode = successor.parent;
        while(currNode != null && successor == currNode.right) {
            successor = currNode;
            currNode = currNode.parent;
        }

        return currNode.value;
    }

    public <R> void inOrderWalk(IExecutor<T,R> exec){
        inOrderWalk(root,exec);}

    private <R> void inOrderWalk(Node node, IExecutor<T,R> exec) {
        if(node!=null){
            inOrderWalk(node.left, exec);
            exec.execute(node.value);
            inOrderWalk(node.right, exec);
        }
    }

    public String toStringInOrder() {
        // TODO
        return null;
    }

    public <R> void preOrderWalk(IExecutor<T,R> exec){
        preOrderWalk(root,exec);}

    private <R> void preOrderWalk(Node node, IExecutor<T,R> exec) {
        if(node!=null){
            exec.execute(node.value);
            preOrderWalk(node.left, exec);
            preOrderWalk(node.right, exec);
        }
    }

    public String toStringPreOrder() {
        // TODO
        return null;
    }

    public <R> void postOrderWalk(IExecutor<T,R> exec){
        postOrderWalk(root,exec);}

    private <R> void postOrderWalk(Node node, IExecutor<T,R> exec) {
        if(node!=null){
            postOrderWalk(node.left, exec);
            postOrderWalk(node.right, exec);
            exec.execute(node.value);
        }
    }

    public String toStringPostOrder() {
        // TODO
        return null;
    }


    public boolean add(T elem) {
        return add(root, elem);
    }

    @SuppressWarnings("unchecked")
    private boolean add(Node startNode, T elem) {
        // todo return false if already exists

        Node currNode = null;
        Node root = startNode;
        Node newNode = new Node(elem);

        while(root != null) {
            currNode = root;
            if(((Comparable)newNode.value).compareTo(root.value) < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        newNode.parent = currNode;
        if(currNode == null) {
            this.root = newNode;
            return true;
        } else if (((Comparable)newNode.value).compareTo(currNode.value) < 0) {
            // todo make sure it's correct
            currNode.left = newNode;
            return true;
        } else {
            currNode.right = newNode;
            return true;
        }
    }


    public T remove(T value) {
        // TODO
        return null;
    }

    public void clear() {
        // TODO
    }

    public int size() {
        // TODO
        return 0;
    }

}