package dsaa_jk.assignment_3b;

import java.util.Queue;

public class Task3 {
    Queue<Customer> customers;

    // how much time does the official have to spend with current customer
    int[] timeLeft = new int[2];

    public Task3() {
        for(int i = 0; i < 6; i++) {
            // generate names from 'A' to 'Z'
            String customerName = Character.toString(Character.forDigit(i + 65, 10));
            customers.add(new Customer(customerName));
        }

        // no customers yet, so no time left
        timeLeft[0] = 0; // official A
        timeLeft[1] = 0; // official B
        timeLeft[2] = 0; // official C
    }
}
