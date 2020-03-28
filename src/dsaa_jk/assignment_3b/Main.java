package dsaa_jk.assignment_3b;

import java.util.Random;

public class Main {


    public static void main(String[] args) {
        // Task 1
//        OneWayLinkedListWithoutSentinel<String> linkedList = new OneWayLinkedListWithoutSentinel<>();
//        System.out.println("Adding three elements [First, Second, Third] to the linked list: ");
//        linkedList.add("First");
//        linkedList.add("Second");
//        linkedList.add("Third");
//        System.out.println(linkedList);

        // Task 2
        CyclicBuffer cyclicBuffer = new CyclicBuffer(3);
        cyclicBuffer.add("A");
        cyclicBuffer.add("B");
        cyclicBuffer.add("C");
        cyclicBuffer.add("D");
        System.out.println(cyclicBuffer.get());
        System.out.println(cyclicBuffer.get());
        System.out.println(cyclicBuffer.get());
        // now we have emptied the buffer
        System.out.println(cyclicBuffer.get());
        System.out.println(cyclicBuffer.get());
        cyclicBuffer.add("E");
        System.out.println(cyclicBuffer.get());
        System.out.println(cyclicBuffer.get());
        System.out.println(cyclicBuffer.get());
    }
}
