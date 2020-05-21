package dsaa_jk.assignment_7;

public class Main {
    public static void main(String[] args) {
        RBT<Integer> rbTree = new RBT<Integer>();
        rbTree.RBInsert(new RBNode<>(1));
        rbTree.RBInsert(new RBNode<>(2));

        rbTree.printNaturalForm();
        System.out.println("\n");

        rbTree.RBTLeftRotate(rbTree.findNode(1));
        rbTree.printNaturalForm();
        System.out.println("\n");

        rbTree.RBTRightRotate(rbTree.findNode(2));
        rbTree.printNaturalForm();
        System.out.println("\n");

        rbTree.RBInsert(new RBNode<>(3));
        rbTree.printNaturalForm();

        System.out.println("\n");

        rbTree.RBInsert(new RBNode<>(4));
        rbTree.printNaturalForm();
    }
}
