package dsaa_jk.assignment_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RBTTest {

    @Test
    void RB_Insert() {
    }

    @Test
    void RBTLeftRotate() {
        RBT<Integer> rbTree = new RBT<Integer>();
        rbTree.add(1);
        rbTree.add(2);

        rbTree.printNaturalForm();
        System.out.println("\n");

        rbTree.RBTLeftRotate(rbTree.findNode(1));
        rbTree.printNaturalForm();
        System.out.println("\n");

        rbTree.RBTRightRotate(rbTree.findNode(2));
        rbTree.printNaturalForm();
        System.out.println("\n");
    }
}