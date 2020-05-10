package dsaa_jk.assignment_6;

import java.util.Random;

public class Generate {
    static Random rand = new Random();

    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static int[] randomArray(int n) {
        int[] array = new int[n];

        for(int i = 0; i < n; i++) {
            array[i] = randomInt(-10000, 10000);
        }

        return array;
    }
}
