package dsaa_jk.assignment_5.task_2;

public class Benchmark {
    private String stringTemplate = "[%s] %d swaps | %d iterations";

    private String name;
    private int swapCounter;
    private int iterationCounter;

    public Benchmark() {
        swapCounter = 0;
        iterationCounter = 0;
    }

    public Benchmark(String name) {
        this.name = name;
        swapCounter = 0;
        iterationCounter = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementSwap() {
        swapCounter++;
    }

    public void incrementIteration() {
        iterationCounter++;
    }

    public int getSwaps() {
        return swapCounter;
    }

    public int getIterations() {
        return iterationCounter;
    }

    public void reset() {
        swapCounter = 0;
        iterationCounter = 0;
    }

    public String toString() {
        return String.format(stringTemplate, name, swapCounter, iterationCounter);
    }
}
