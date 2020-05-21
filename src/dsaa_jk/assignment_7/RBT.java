package dsaa_jk.assignment_7;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

public class RBT<T> {

    private RBNode<T> root;

    public RBT() {
        root = null;
    }

    public RBNode<T> findNode(T toFind) {
        return findNode(root, toFind);
    }

    @SuppressWarnings("unchecked")
    private RBNode<T> findNode(RBNode<T> start, T toFind) {
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

    public void RBTLeftRotate(RBNode<T> node) {
        RBNode<T> other = node.right;
        node.right = other.left;
        if(other.left != null) {
            other.left.parent = node;
        }
        other.parent = node.parent;

        if(node.parent == null) {
            root = other;
        } else if (node == node.parent.left) {
            node.parent.left = other;
        } else {
            node.parent.right = other;
        }

        other.left = node;
        node.parent = other;
    }

    public void RBTRightRotate(RBNode<T> node) {
        RBNode<T> other = node.left;
        node.left = other.right;
        if(other.right != null) {
            other.right.parent = node;
        }
        other.parent = node.parent;

        if(node.parent == null) {
            root = other;
        } else if (node == node.parent.right) {
            node.parent.right = other;
        } else {
            node.parent.left = other;
        }

        other.right = node;
        node.parent = other;
    }

    public void RBInsert(RBNode<T> newNode) {
        if(!add(root, newNode.value)) {
            return;
        }

        newNode = findNode(root, newNode.value);
        newNode.color = Color.RED; // every inserted node has to be RED

        while(newNode != root && newNode.parent.color == Color.RED) {
            if(newNode.parent == newNode.parent.parent.left) {
                RBNode<T> otherNode = newNode.parent.parent.right;
                if(otherNode != null && otherNode.color == Color.RED) {
                    newNode.parent.color = Color.BLACK;
                    otherNode.color = Color.BLACK;
                    newNode.parent.parent.color = Color.RED;
                    newNode = newNode.parent.parent;
                } else if (newNode == newNode.parent.right) {
                    newNode = newNode.parent;
                    RBTLeftRotate(newNode);
                } else {
                    newNode.parent.color = Color.BLACK;
                    newNode.parent.parent.color = Color.RED;
                    RBTRightRotate(newNode.parent.parent);
                }
            } else {
                RBNode<T> other = newNode.parent.parent.left;
                if (other != null && other.color == Color.RED)
                {
                    newNode.parent.color = Color.BLACK;
                    other.color = Color.BLACK;
                    newNode.parent.parent.color = Color.RED;
                    newNode = newNode.parent.parent;
                }
                else if (newNode == newNode.parent.left)
                {
                    newNode = newNode.parent;
                    RBTRightRotate(newNode);
                }
                else
                {
                    newNode.parent.color = Color.BLACK;
                    newNode.parent.parent.color = Color.RED;
                    RBTLeftRotate(newNode.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    public boolean add(T elem) {
        return add(root, elem);
    }

    @SuppressWarnings("unchecked")
    private boolean add(RBNode<T> startNode, T elem) {
        RBNode<T> currNode = null;
        RBNode<T> root = startNode;
        RBNode<T> newNode = new RBNode<>(elem);

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

    public RBNode<T> determineCharacteristics(RBNode<T> currentNode) {
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

        RBNode<T> left = determineCharacteristics(currentNode.left);
        RBNode<T> right = determineCharacteristics(currentNode.right);
        // update node
        if (left == null) {
            currentNode.leftSubtreeHeight = 0;
            currentNode.leftSubtreeNodesCount = 0;
        } else {
            currentNode.leftSubtreeHeight = Math.max(left.leftSubtreeHeight, left.rightSubtreeheight) + 1;
            currentNode.leftSubtreeNodesCount = left.leftSubtreeHeight + left.rightSubtreeheight + 1;

            if (left.left == null && left.right == null) { // leaf
                currentNode.leftSubtreeLeavesCount = 1;
            } else { // not leaf
                currentNode.leftSubtreeLeavesCount = left.leftSubtreeLeavesCount + left.rightSubtreeLeavesCount;
            }
        }

        if (right == null) {
            currentNode.rightSubtreeheight = 0;
            currentNode.rightSubtreeNodesCount = 0;
        } else {
            currentNode.rightSubtreeheight = Math.max(right.leftSubtreeHeight, right.rightSubtreeheight) + 1;
            currentNode.rightSubtreeNodesCount = right.leftSubtreeHeight + right.rightSubtreeheight + 1;

            if (right.left == null && right.right == null) { // leaf
                currentNode.rightSubtreeLeavesCount = 1;
            } else { // not leaf
                currentNode.rightSubtreeLeavesCount = right.leftSubtreeLeavesCount + right.rightSubtreeLeavesCount;
            }

        }
        return currentNode;
    }

    public void printNaturalForm() {
        determineCharacteristics(root);

        int leaves = root.leftSubtreeLeavesCount + root.rightSubtreeLeavesCount;

        int width = (int) Math.pow(2.0, 2 * (Math.ceil(leaves / 2.0)));
        width = 32;
        printLevels(root, width);
    }

    private void printLevels(RBNode<T> node, int width) {
        if (node == null) {
            return;
        }

        Queue<RBNode<T>> queue = new LinkedList<>();
        queue.add(node);

        while (true) {
            int levelNodeCount = queue.size();

            if (levelNodeCount == 0) {
                break;
            }

            StringBuilder levelAsString = new StringBuilder();
            while (levelNodeCount > 0) { // until we have read this entire level
                RBNode<T> currentNode = queue.poll();

                String valToString = currentNode == null ? "  " : currentNode.value + " ";
                String valStr = StringUtils.center(valToString, width);
                levelAsString.append(valStr);

                if (currentNode != null) {
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                }

                levelNodeCount--;
            }
            width /= 2;
            System.out.printf("%s%n", levelAsString.toString());
        }
    }

    public void clear() {
        this.root = null;
    }


    public int size() {
        return this.size(this.root);
    }


    private int size(RBNode<T> start) {
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

    public RBNode<T> getRoot() {
        return this.root;
    }
}