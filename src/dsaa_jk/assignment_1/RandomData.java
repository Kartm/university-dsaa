package dsaa_jk.assignment_1;

import java.util.concurrent.ThreadLocalRandom;

public class RandomData {
    public static double grade() {
        double min = 2;
        double max = 5;
        double randomDouble = ThreadLocalRandom.current().nextDouble(min, max);
        return (int)randomDouble;
    }
}
