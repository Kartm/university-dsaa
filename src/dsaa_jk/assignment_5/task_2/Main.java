package dsaa_jk.assignment_5.task_2;

public class Main {
    public static int[] randomArr(int min, int max, int n) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * max) + min;
        }
        return arr;
    }

    public static void main(String[] args) {
        Sorting sorting = new Sorting(randomArr(0, 100, 20));
        sorting.printArr();
        System.out.println("------------------");
        Sorting.printArr(sorting.shakerSort());
        System.out.println("");
        Sorting.printArr(sorting.shakerSortOptimised());
    }
}
