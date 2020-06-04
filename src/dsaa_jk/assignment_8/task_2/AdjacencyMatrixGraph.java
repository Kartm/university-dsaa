package dsaa_jk.assignment_8.task_2;

import java.util.*;

public class AdjacencyMatrixGraph<T> extends Graph<T> {
    private final int DEFAULT_EDGE_WEIGHT = 1;

    private int[][] adjacencyMatrix;
    private Map<Integer, T> vertexValueMap;

    private int vertexCount = 0;
    private int edgeCount = 0;

    public AdjacencyMatrixGraph() {
        vertexValueMap = new HashMap<Integer, T>();

        adjacencyMatrix = new int[][]{};
    }

    @Override
    public void addNode(T element) {
        int newNodeIndex = vertexCount;

        vertexValueMap.put(newNodeIndex, element);

        // increment the size of adjacency matrix
        int[][] newAdjacencyMatrix = new int[vertexCount + 1][vertexCount + 1];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            newAdjacencyMatrix[i] = Arrays.copyOf(adjacencyMatrix[i], vertexCount + 1); // the last element is zero by default
        }
        newAdjacencyMatrix[vertexCount] = new int[vertexCount + 1];

        adjacencyMatrix = newAdjacencyMatrix;

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

        // if edge is unset
        if (this.adjacencyMatrix[fromIndex][toIndex] == 0) {
            edgeCount++;
        }
        this.adjacencyMatrix[fromIndex][toIndex] = weight;
    }

    @Override
    public Graph<T> getMinimumSpanningTree() {
        Graph<T> newGraph = new AdjacencyMatrixGraph<>();
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
            int optimalTo = 0;

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (visited[i]) {
                    for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                        if (!visited[j] && adjacencyMatrix[i][j] != 0) {
                            if (adjacencyMatrix[i][j] < cheapestPath) {
                                cheapestPath = adjacencyMatrix[i][j];
                                optimalFrom = i;
                                optimalTo = j;
                            }
                        }
                    }
                }
            }

            try {
                newGraph.addEdge(
                        vertexValueMap.get(optimalFrom),
                        vertexValueMap.get(optimalTo),
                        adjacencyMatrix[optimalFrom][optimalTo]
                );
                newGraph.addEdge(
                        vertexValueMap.get(optimalTo),
                        vertexValueMap.get(optimalFrom),
                        adjacencyMatrix[optimalFrom][optimalTo]
                );
                // System.out.println(vertexValueMap.get(optimalFrom) + " - " + vertexValueMap.get(optimalTo) + ", cost " + adjacencyMatrix[optimalFrom][optimalTo]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            visited[optimalTo] = true;
            edgeCount++;
        }

        return newGraph;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            result.append(vertexValueMap.get(i)).append(": ");

            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                result.append(adjacencyMatrix[i][j]).append(", ");
            }

            result.append("\n");
        }
        return result.toString();
    }
}
