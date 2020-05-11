package dsaa.lab_8;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BST<T> {
    private class Node {
        T value;
        Node left = null, right = null, parent = null;

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


        @Override
        public String toString() {
            String string = "";
            string += value.toString();
            return string;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    public T getElement(T toFind) {
        return getElement(root, toFind);
    }


    @SuppressWarnings("unchecked")
    private T getElement(Node start, T toFind) {
        Comparable<T> toFindComparable = (Comparable<T>) toFind;
        if (start == null) {
            return null;
        }

        if (toFindComparable.compareTo(toFind) == 0) {
            return start.value;
        } else if (toFindComparable.compareTo(start.value) < 0) {
            // searched value is smaller - look in the left subtree
            return getElement(start.left, toFind);
        } else {
            // searched value is larger - look in the right subtree
            return getElement(start.right, toFind);
        }
    }

    @SuppressWarnings("unchecked")
    private Node findNode(Node start, T toFind) {
        Comparable<T> toFindComparable = (Comparable<T>) toFind;
        if (start == null) {
            return null;
        }

        if (toFindComparable.compareTo(start.value) == 0) {
            return start;
        } else if (toFindComparable.compareTo(start.value) < 0) {
            // searched value is smaller - look in the left subtree
            return findNode(start.left, toFind);
        } else {
            // searched value is larger - look in the right subtree
            return findNode(start.right, toFind);
        }
    }

    private T getMinimum(Node startNode) {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return getMinimumNode(startNode).value;
    }

    private Node getMinimumNode(Node startNode) {
        Node currentNode = startNode;

        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode;
    }

    private T getMaximum(Node startNode) {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return getMaximumNode(startNode).value;
    }

    private Node getMaximumNode(Node startNode) {
        Node currentNode = startNode;

        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }

        return currentNode;
    }

    public T successor(T elem) {
        Node successor = findNode(root, elem);

        if (successor.right != null) {
            return getMinimum(successor.right);
        }

        Node currNode = successor.parent;
        while (currNode != null && successor == currNode.right) {
            successor = currNode;
            currNode = currNode.parent;
        }

        return currNode.value;
    }

    public <R> void inOrderWalk(IExecutor<T, R> exec) {
        inOrderWalk(root, exec);
    }

    private <R> void inOrderWalk(Node node, IExecutor<T, R> exec) {
        if (node != null) {
            inOrderWalk(node.left, exec);
            exec.execute(node.value);
            inOrderWalk(node.right, exec);
        }
    }

    public String toStringInOrder() {
        String string = inOrder(root);

        if(string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }

        return string;
    }

    private String inOrder(Node node) {
        String string = "";

        if (node == null) {
            return string;
        }

        string += inOrder(node.left);
        string += node.toString() + ", ";
        string += inOrder(node.right);

        return string;
    }

    public <R> void preOrderWalk(IExecutor<T, R> exec) {
        preOrderWalk(root, exec);
    }

    private <R> void preOrderWalk(Node node, IExecutor<T, R> exec) {
        if (node != null) {
            exec.execute(node.value);
            preOrderWalk(node.left, exec);
            preOrderWalk(node.right, exec);
        }
    }

    public String toStringPreOrder() {
        String string = preOrder(root);

        if(string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }

        return string;
    }

    private String preOrder(Node node) {
        String string = "";

        if (node == null) {
            return string;
        }

        string += node.toString() + ", ";
        string += preOrder(node.left);
        string += preOrder(node.right);
        return string;
    }

    public <R> void postOrderWalk(IExecutor<T, R> exec) {
        postOrderWalk(root, exec);
    }

    private <R> void postOrderWalk(Node node, IExecutor<T, R> exec) {
        if (node != null) {
            postOrderWalk(node.left, exec);
            postOrderWalk(node.right, exec);
            exec.execute(node.value);
        }
    }

    public String toStringPostOrder() {
        String string = postOrder(root);

        if(string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }

        return string;
    }

    private String postOrder(Node node) {
        String string = "";

        if (node == null) {
            return string;
        }

        string += postOrder(node.left);
        string += postOrder(node.right);
        string += node.toString() + ", ";

        return string;
    }

    public boolean add(T elem) {
        return add(root, elem);
    }

    @SuppressWarnings("unchecked")
    private boolean add(Node startNode, T elem) {
        Node currNode = null;
        Node root = startNode;
        Node newNode = new Node(elem);

        while (root != null) {
            currNode = root;
            if (((Comparable<T>) newNode.value).compareTo(root.value) < 0) {
                root = root.left;
            } else if (((Comparable<T>) newNode.value).compareTo(root.value) > 0) {
                root = root.right;
            } else {
                return false; // already exists
            }
        }

        newNode.parent = currNode;
        if (currNode == null) {
            this.root = newNode;
            return true;
        } else if (((Comparable<T>) newNode.value).compareTo(currNode.value) < 0) {
            currNode.left = newNode;
            return true;
        } else if (((Comparable<T>) newNode.value).compareTo(currNode.value) > 0) {
            currNode.right = newNode;
            return true;
        } else {
            return false; // already exists
        }
    }


    public T remove(T value) {
        Node target = findNode(root, value);
        if (target == null) {
            return null;
        }

        T targetValue = target.value;

        if (size() == 1) {
            clear();
        }

        // target has no children
        else if (target.left == null && target.right == null) {
            if (target.parent.left == target) target.parent.left = null;
            else target.parent.right = null;
        }
        // target has one child
        else if (target.left != null && target.right == null) {
            if(target == root) {
                root = target.left;
            } else if (target.parent.left == target) {
                target.parent.left = target.left;
            } else {
                target.parent.right = target.left;
            }
        } else if (target.left == null) {
            if(target == root) {
                root = target.right;
            } else if (target.parent.left == target) {
                target.parent.left = target.right;
            } else {
                target.parent.right = target.right;
            }
        } else {
            // target has two children
            Node successor = findNode(root, successor(target.value));
            remove(successor.value);
            target.value = successor.value;
        }

        return targetValue;
    }


    public void clear() {
        this.root = null;
    }


    public int size() {
        return this.size(this.root);
    }


    private int size(Node start) {
        if (start == null) {
            // no root
            return 0;
        }

        int result = 1;

        // add sizes of branches
        result += size(start.left);
        result += size(start.right);

        return result;
    }
}