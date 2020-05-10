package dsaa_jk.assignment_6;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Benchmark benchmark = new Benchmark();
        int n = 50;
        long[] results = new long[n];

        for(int i = 0; i < n; i++) {
            int[] test = Generate.randomArray(100);
            benchmark.reset();

            benchmark.timeStart();
            Arrays.sort(test);
            //Arrays.parallelSort(test);
            benchmark.timeEnd();

            results[i] = benchmark.getTimeCounter();
        }

        for(int i = 0; i < n; i++) {
            System.out.println(results[i]);
        }
    }
}
