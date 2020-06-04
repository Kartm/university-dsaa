package dsaa_jk.assignment_8.task_2;

public abstract class Graph<T> {
    public abstract void addNode(T name);
    public abstract void addEdge(T from, T to) throws Exception;
    public abstract void addEdge(T from, T to, int weight) throws Exception;
    public abstract Graph<T> getMinimumSpanningTree();
}
