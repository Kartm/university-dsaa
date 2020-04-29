package dsaa_jk.assignment_5.task_2;

import java.beans.beancontext.BeanContextChild;
import java.util.Arrays;

public class Sorting {
    private Benchmark benchmark = null;
    int[] array;

    public Sorting(int[] array) {
        this.array = array;
        benchmark = new Benchmark();
    }

    private static void swap(int[] arr, int posA, int posB) {
        int temp = arr[posA];
        arr[posA] = arr[posB];
        arr[posB] = temp;
    }

    public void printArr() {
        printArr(array);
    }

    public static void printArr(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.print("\n");
    }

    public int[] shakerSort() {
        int[] temp = Arrays.copyOf(array, array.length);

        benchmark.reset();
        benchmark.setName("Unoptimised shakersort");

        if(temp.length > 1) {
            boolean swapped = true;

            int start = 0;
            int end = temp.length - 1;

            while(swapped) {
                swapped = false;

                // left to right
                for(int i = start; i < end; i++) {
                    if(temp[i] > temp[i+1]) {
                        swap(temp, i, i+1);
                        swapped = true;
                        benchmark.incrementSwap();
                    }
                    benchmark.incrementIteration();
                }

                if(!swapped) {
                    // nothing moved, so the array
                    // must be sorted
                    break;
                }

                swapped = false;

                // right to left
                for(int i = end - 1; i >= start; i--) {
                    if(temp[i] > temp[i+1]) {
                        swap(temp, i, i+1);
                        swapped = true;
                        benchmark.incrementSwap();
                    }
                    benchmark.incrementIteration();
                }
            }
        }

        System.out.println(benchmark.toString());
        return temp;
    }

    public int[] shakerSortOptimised() {
        int[] temp = Arrays.copyOf(array, array.length);

        benchmark.reset();
        benchmark.setName("Optimised shakersort");

        if(temp.length > 1) {
            boolean swapped = true;

            int start = 0;
            int end = temp.length - 1;

            int lastSwapIndex = end;

            while(swapped) {
                swapped = false;
                // left to right

                for(int i = start; i < end; i++) {
                    if(temp[i] > temp[i+1]) {
                        swap(temp, i, i+1);
                        swapped = true;
                        lastSwapIndex = i;
                        benchmark.incrementSwap();
                    }
                    benchmark.incrementIteration();
                }

                end = lastSwapIndex;

                // nothing moved, so the array
                // must be sorted
                if(!swapped)
                    break;

                swapped = false;

                // right to left
                for(int i = end - 1; i >= start; i--) {
                    if(temp[i] > temp[i+1]) {
                        swap(temp, i, i+1);
                        swapped = true;
                        lastSwapIndex = i;
                        benchmark.incrementSwap();
                    }
                    benchmark.incrementIteration();
                }

                start = lastSwapIndex + 1;
            }
        }

        System.out.println(benchmark);
        return temp;
    }
}
