package dsaa_jk.assignment_3b.task_2;

public class Main {
    public static void main(String[] args) {
        CyclicBuffer cyclicBuffer = new CyclicBuffer(3);
        System.out.println("Cyclic buffer is a FIFO structure.");
        System.out.println("Adding elements \"A\", \"B\", \"C\", \"D\" to a buffer with a size of 3.");
        cyclicBuffer.add("A");
        cyclicBuffer.add("B");
        cyclicBuffer.add("C");
        cyclicBuffer.add("D");

        System.out.println("The contents:");
        while(!cyclicBuffer.isEmpty()) {
            System.out.println(cyclicBuffer.get());
        }

        System.out.println("Element \"D\" wasn't added since the buffer was already full.");
        System.out.println("Now the buffer is empty. Next attempt to get a value will return null:");
        System.out.println(cyclicBuffer.get());

        System.out.println("Adding a new element \"E\" will make it available in the buffer:");
        cyclicBuffer.add("E");
        System.out.println(cyclicBuffer.get());

        System.out.println("Now the buffer is empty again. Next attempt will return null:");
        System.out.println(cyclicBuffer.get());
    }
}
