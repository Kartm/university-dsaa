package dsaa_jk.assignment_8.task_3_4;

public class DijkstraPath<T> {
    private final T target;
    private final T predecessor;
    private final int cost;

    public DijkstraPath(T target, T predecessor, int cost) {
        this.target = target;
        this.predecessor = predecessor;
        this.cost = cost;
    }

    public T getTarget() {
        return this.target;
    }

    public T getPredecessor() {
        return this.predecessor;
    }

    public int getCost() {
        return this.cost;
    }
}
