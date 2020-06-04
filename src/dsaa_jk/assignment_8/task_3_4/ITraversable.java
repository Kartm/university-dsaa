package dsaa_jk.assignment_8.task_3_4;

public interface ITraversable<T> {
    public Iterable<T> bfs(T start);
    public Iterable<T> dfs(T start);
    public Iterable<DijkstraPath<T>> dijkstra(T start);
}
