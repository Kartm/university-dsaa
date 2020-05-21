package dsaa_jk.assignment_7;

public class Main {
    // benchmark
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(7);
        bst.add(5);
        bst.add(9);
        bst.add(1);
        bst.add(12);
        bst.add(8);
        bst.add(3);

        System.out.println(bst.printCharacteristics());
    }
}
