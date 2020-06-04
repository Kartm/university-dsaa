package dsaa_jk.assignment_8.task_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdjacencyListGraph<T> extends Graph<T> {
    private static class Edge {
        private final int target;
        private final int weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public int getTarget() {
            return this.target;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other)
                return true;
            if (other == null)
                return false;
            if (getClass() != other.getClass())
                return false;

            Edge castedOther = (Edge) other;
            return this.getTarget() == castedOther.getTarget();
        }
    }

    private final int DEFAULT_EDGE_WEIGHT = 1;

    private ArrayList<Edge>[] adjacencyList;
    private final Map<Integer, T> vertexValueMap;

    private int vertexCount = 0;
    private int edgeCount = 0;

    @SuppressWarnings("unchecked")
    public AdjacencyListGraph() {
        vertexValueMap = new HashMap<Integer, T>();
        adjacencyList = (ArrayList<Edge>[]) new ArrayList[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addNode(T element) {
        int newNodeIndex = vertexCount;

        vertexValueMap.put(newNodeIndex, element);

        // increment the size of adjacency list
        ArrayList<Edge>[] newAdjacencyList = (ArrayList<Edge>[]) new ArrayList[adjacencyList.length + 1];
        int i;
        for (i = 0; i < adjacencyList.length; i++) {
            newAdjacencyList[i] = adjacencyList[i];
        }

        newAdjacencyList[i] = new ArrayList<>();

        adjacencyList = newAdjacencyList;

        vertexCount++;
    }

    int getNodeIndex(T node) {
        for (Map.Entry<Integer, T> entry : vertexValueMap.entrySet()) {
            if (entry.getValue().equals(node)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public void addEdge(T from, T to) throws Exception {
        addEdge(from, to, DEFAULT_EDGE_WEIGHT);
    }

    @Override
    public void addEdge(T from, T to, int weight) throws Exception {
        int fromIndex = getNodeIndex(from);
        int toIndex = getNodeIndex(to);

        if (fromIndex == -1) {
            throw new Exception("Source vertex not found.");
        } else if (toIndex == -1) {
            throw new Exception("Target vertex not found.");
        }

        Edge newEdge = new Edge(toIndex, weight);

        int indexOfEdge = this.adjacencyList[fromIndex].indexOf(newEdge);
        // override duplicates
        if (indexOfEdge == -1) {
            this.adjacencyList[fromIndex].add(newEdge);
            edgeCount++;
        } else {
            this.adjacencyList[fromIndex].set(indexOfEdge, newEdge);
        }
    }

    @Override
    public Graph<T> getMinimumSpanningTree() {
        Graph<T> newGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < vertexCount; i++) {
            newGraph.addNode(vertexValueMap.get(i));
        }

        int edgeCount = 0;
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        // the starting node doesn't matter
        // just choose the first one
        visited[0] = true;

        // until we connect every vertex
        while (edgeCount < vertexCount - 1) {
            int cheapestPath = Integer.MAX_VALUE;
            int optimalFrom = 0;
            Edge optimalEdge = null;

            for (int i = 0; i < adjacencyList.length; i++) {
                if (visited[i]) {
                    for (Edge neighbor : adjacencyList[i]) {
                        if (!visited[neighbor.getTarget()] && neighbor.getWeight() != 0) {
                            if (neighbor.getWeight() < cheapestPath) {
                                cheapestPath = neighbor.getWeight();
                                optimalFrom = i;
                                optimalEdge = neighbor;
                            }
                        }
                    }
                }
            }

            try {
                newGraph.addEdge(
                        vertexValueMap.get(optimalFrom),
                        vertexValueMap.get(optimalEdge.getTarget()),
                        optimalEdge.getWeight()
                );
                newGraph.addEdge(
                        vertexValueMap.get(optimalEdge.getTarget()),
                        vertexValueMap.get(optimalFrom),
                        optimalEdge.getWeight()
                );
                // System.out.println(vertexValueMap.get(optimalFrom) + " - " + vertexValueMap.get(optimalEdge.getTarget()) + ", cost " + optimalEdge.getWeight());
            } catch (Exception e) {
                e.printStackTrace();
            }

            visited[optimalEdge.getTarget()] = true;
            edgeCount++;
        }

        return newGraph;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < adjacencyList.length; i++) {
            result.append(vertexValueMap.get(i)).append(": ");

            for (Edge edge : adjacencyList[i]) {
                result.append(edge.getTarget() + "(cost=" + edge.getWeight() + ")").append(", ");
            }

            result.append("\n");
        }
        return result.toString();
    }
}
