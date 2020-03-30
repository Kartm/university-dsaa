package dsaa_jk.assignment_3b.task_3;

import java.util.Random;

public class Customer {
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    String name;
    int neededTime;

    public Customer(String name) {
        this.name = name;
        this.neededTime = getRandomNumberInRange(5, 10);
    }

    @Override
    public String toString() {
        return this.name + " (needed time: " + this.neededTime + ")";
    }
}
