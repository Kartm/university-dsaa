package dsaa_jk.assignment_3b.task_3;

import java.util.*;

public class Main {

    private static ArrayDeque<Customer> generateCustomers(int number) {
        List<Customer> list = new ArrayList<>();
        for(int i = 0; i < number; i++) {
            // generate names from 'A' to 'Z'
            String customerName = Utilities.getLetter(i);
            list.add(new Customer("customer" + customerName));
        }
        Collections.shuffle(list);

        return new ArrayDeque<>(list);
    }

    private static int readNumberOfCustomers(Scanner sc) {
        System.out.println("Enter the number of customers to handle: ");
        while(sc.hasNext()) {
            try {
                return Integer.parseInt(sc.next());
            } catch(NumberFormatException ex) {
                System.out.println("Invalid number format. Enter the correct number of customers to handle: ");
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // I'm using add and remove to make ArrayDeque behave as a queue (FIFO)
        ArrayDeque<Customer> customers = generateCustomers(readNumberOfCustomers(sc));

        Officials officials = new Officials(3);

        while(!customers.isEmpty()) {
            Official freeOfficial = officials.getFreeOfficial();
            if(freeOfficial == null) {
                System.out.println("[TICK] Wait...");
            } else {
                Customer customer = customers.remove();
                freeOfficial.assignCustomer(customer);
                System.out.println("[TICK] Assigned " + customer + " to official " + freeOfficial);
            }

            officials.tick();
        }

        sc.close();
    }
}
