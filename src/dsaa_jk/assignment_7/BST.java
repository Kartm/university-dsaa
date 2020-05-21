package dsaa_jk.assignment_7;

import java.util.NoSuchElementException;

public class BST<T> {

    private Node<T> root;

    public BST() {
        root = null;
    }

    public T getElement(T toFind) {
        return getElement(root, toFind);
    }

    @SuppressWarnings("unchecked")
    private T getElement(Node<T> start, T toFind) {
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
    private Node<T> findNode(Node<T> start, T toFind) {
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

    public T getMinimumRecursive(Node<T> startNode) {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return (T) getMinimumNode(startNode).value;
    }

    private Node<T> getMinimumNode(Node<T> startNode) {
        Node<T> currentNode = startNode;

        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode;
    }

    private T getMaximumRecursive(Node<T> startNode) {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return (T) getMaximumNode(startNode).value;
    }

    private Node<T> getMaximumNode(Node<T> startNode) {
        Node<T> currentNode = startNode;

        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }

        return currentNode;
    }

    public T successor(T elem) {
        Node<T> successor = findNode(root, elem);

        if (successor.right != null) {
            return getMinimumRecursive(successor.right);
        }

        Node<T> currNode = successor.parent;
        while (currNode != null && successor == currNode.right) {
            successor = currNode;
            currNode = currNode.parent;
        }

        if (currNode == null) {
            return null;
        }

        return currNode.value;
    }

    public String toStringInOrder() {
        String string = inOrder(root);

        if (string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }

        return string;
    }

    private String inOrder(Node<T> node) {
        String string = "";

        if (node == null) {
            return string;
        }

        string += inOrder(node.left);
        string += node.toString() + ", ";
        string += inOrder(node.right);

        return string;
    }

    public String toStringPreOrder() {
        String string = preOrder(root);

        if (string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }

        return string;
    }

    private String preOrder(Node<T> node) {
        String string = "";

        if (node == null) {
            return string;
        }

        string += node.toString() + ", ";
        string += preOrder(node.left);
        string += preOrder(node.right);
        return string;
    }

    public String toStringPostOrder() {
        String string = postOrder(root);

        if (string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }

        return string;
    }

    private String postOrder(Node<T> node) {
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
    private boolean add(Node<T> startNode, T elem) {
        Node<T> currNode = null;
        Node<T> root = startNode;
        Node<T> newNode = new Node<>(elem);

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
        Node<T> target = findNode(root, value);
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
            if (target == root) {
                root = target.left;
            } else if (target.parent.left == target) {
                target.parent.left = target.left;
            } else {
                target.parent.right = target.left;
            }
        } else if (target.left == null) {
            if (target == root) {
                root = target.right;
            } else if (target.parent.left == target) {
                target.parent.left = target.right;
            } else {
                target.parent.right = target.right;
            }
        } else {
            // target has two children
            Node<T> successor = findNode(root, successor(target.value));
            remove(successor.value);
            target.value = successor.value;
        }

        return targetValue;
    }

    public Node<T> determineCharacteristics(Node<T> currentNode) {
        if (currentNode == null) {
            return null; // no node has no height
        }
        // reset characteristics in case we are running the method multiple times
        currentNode.leftSubtreeHeight = 0;
        currentNode.rightSubtreeheight = 0;
        currentNode.leftSubtreeNodesCount = 0;
        currentNode.rightSubtreeNodesCount = 0;
        currentNode.leftSubtreeLeavesCount = 0;
        currentNode.rightSubtreeLeavesCount = 0;

        Node<T> left = determineCharacteristics(currentNode.left);
        Node<T> right = determineCharacteristics(currentNode.right);
        // update node
        if(left == null) {
            currentNode.leftSubtreeHeight = 0;
            currentNode.leftSubtreeNodesCount = 0;
        } else {
            currentNode.leftSubtreeHeight = Math.max(left.leftSubtreeHeight, left.rightSubtreeheight) + 1;
            currentNode.leftSubtreeNodesCount = left.leftSubtreeHeight + left.rightSubtreeheight + 1;

            if(left.left == null && left.right == null) { // leaf
                currentNode.leftSubtreeLeavesCount = 1;
            } else { // not leaf
                currentNode.leftSubtreeLeavesCount = left.leftSubtreeLeavesCount + left.rightSubtreeLeavesCount;
            }
        }

        if(right == null) {
            currentNode.rightSubtreeheight = 0;
            currentNode.rightSubtreeNodesCount = 0;
        } else {
            currentNode.rightSubtreeheight = Math.max(right.leftSubtreeHeight, right.rightSubtreeheight) + 1;
            currentNode.rightSubtreeNodesCount = right.leftSubtreeHeight + right.rightSubtreeheight + 1;

            if(right.left == null && right.right == null) { // leaf
                currentNode.rightSubtreeLeavesCount = 1;
            } else { // not leaf
                currentNode.rightSubtreeLeavesCount = right.leftSubtreeLeavesCount + right.rightSubtreeLeavesCount;
            }

        }
        return currentNode;
    }

    private String collectCharacteristics(Node<T> node) {
        String string = "";

        if (node == null) {
            return string;
        }

        string += collectCharacteristics(node.left);
        string += node.value
                + ": Lheight=" + node.leftSubtreeHeight
                + ", Rheight=" + node.rightSubtreeheight
                + ", Lnodecount=" + node.leftSubtreeNodesCount
                + ", Rnodecount=" + node.rightSubtreeNodesCount
                + ", Lleafcount=" + node.leftSubtreeLeavesCount
                + ", Rleafcount=" + node.rightSubtreeLeavesCount
                + "\n";
        string += collectCharacteristics(node.right);

        return string;
    }

    public String printCharacteristics() {
        determineCharacteristics(root);
        return collectCharacteristics(root);
    }

    public void clear() {
        this.root = null;
    }


    public int size() {
        return this.size(this.root);
    }


    private int size(Node<T> start) {
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

    public Node<T> getRoot() {
        return this.root;
    }
}