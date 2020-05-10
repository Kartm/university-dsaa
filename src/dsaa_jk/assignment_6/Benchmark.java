package dsaa_jk.assignment_6;

public class Benchmark {
    int swapsCounter;
    int comparisonsCounter;
    long timeCounter;

    public Benchmark() {
        reset();
    }

    public void incrementSwaps() {
        swapsCounter++;
    }
    
    public void incrementComparisons() {
        comparisonsCounter++;
    }
    
    public void timeStart() {
        timeCounter = System.nanoTime();
    }

    public void timeEnd() {
        timeCounter = System.nanoTime() - timeCounter;
    }

    public void reset() {
        this.swapsCounter = 0;
        this.comparisonsCounter = 0;
        this.timeCounter = 0;
    }

    public int getSwapsCounter() {
        return swapsCounter;
    }

    public int getComparisonsCounter() {
        return comparisonsCounter;
    }

    public long getTimeCounter() {
        return timeCounter;
    }
}
