package dsaa_jk.assignment_8.task_2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IncidenceMatrixGraph<T> extends Graph<T> {
    private final int DEFAULT_EDGE_WEIGHT = 1;

    private int[][] incidenceMatrix;
    private Map<Integer, T> vertexValueMap;

    private int vertexCount = 0;
    private int edgeCount = 0;

    public IncidenceMatrixGraph() {
        vertexValueMap = new HashMap<Integer, T>();

        incidenceMatrix = new int[][]{};
    }

    @Override
    public void addNode(T element) {
        int newNodeIndex = vertexCount;

        vertexValueMap.put(newNodeIndex, element);

        /*
        node e1	e2	e3	e4
        1	 1	 1	 1 	0
        2	 1	 0	 0 	0
        3	 0	 1	 0 	1
        4	 0	 0	 1 	1
        */

        // increment the size of adjacency matrix
        int[][] newMatrix = new int[vertexCount + 1][edgeCount];

        // copy edges
        for (int i = 0; i < vertexCount; i++) {
            newMatrix[i] = incidenceMatrix[i];
        }
        // add node to the end with no connections
        newMatrix[vertexCount] = new int[edgeCount];

        incidenceMatrix = newMatrix;

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

        int[][] newMatrix = new int[vertexCount][edgeCount + 1];

        // copy edges
        for (int i = 0; i < vertexCount; i++) {
            int[] newEdges = new int[edgeCount + 1];
            for (int j = 0; j < edgeCount; j++) {
                newEdges[j] = incidenceMatrix[i][j];
            }

            newEdges[edgeCount] = 0; // empty edge for now
            newMatrix[i] = newEdges;
        }
        newMatrix[toIndex][edgeCount] = weight;
        newMatrix[fromIndex][edgeCount] = weight;

        incidenceMatrix = newMatrix;
        edgeCount++;
    }

    @Override
    public Graph<T> getMinimumSpanningTree() {
        Graph<T> newGraph = new IncidenceMatrixGraph<>();
        for (int i = 0; i < vertexCount; i++) {
            newGraph.addNode(vertexValueMap.get(i));
        }

        int i = 0;
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        // the starting node doesn't matter
        // just choose the first one
        visited[0] = true;

        // until we connect every vertex
        while (i < vertexCount - 1) {
            int cheapestPath = Integer.MAX_VALUE;
            int optimalFrom = -1;
            int optimalTo = -1;
            int optimalEdgeIndex = -1;

            for (int nodeIndex = 0; nodeIndex < vertexCount; nodeIndex++) {
                if (visited[nodeIndex]) {
                    // find the cheapest connection to any neighbor
                    for (int neighborIndex = 0; neighborIndex < vertexCount; neighborIndex++) {
                        if(nodeIndex != neighborIndex && !visited[neighborIndex]) {
                            // check every edge
                            for(int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
                                int costFrom = incidenceMatrix[nodeIndex][edgeIndex];
                                int costTo = incidenceMatrix[neighborIndex][edgeIndex];

                                // no matter which cost we choose, they're equal
                                if(costFrom == costTo && costFrom != 0) {
                                    if (costFrom < cheapestPath) {
                                        cheapestPath = costFrom;
                                        optimalFrom = nodeIndex;
                                        optimalTo = neighborIndex;
                                        optimalEdgeIndex = edgeIndex;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // if connection exists
            if(optimalEdgeIndex != -1 && optimalFrom != -1 & optimalTo != -1) {
                try {
                    newGraph.addEdge(
                            vertexValueMap.get(optimalFrom),
                            vertexValueMap.get(optimalTo),
                            incidenceMatrix[optimalTo][optimalEdgeIndex]
                    );
                    // System.out.println(vertexValueMap.get(optimalFrom) + " - " + vertexValueMap.get(optimalTo) + ", cost " + adjacencyMatrix[optimalFrom][optimalTo]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                visited[optimalTo] = true;
                i++;
            }
        }

        return newGraph;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < incidenceMatrix.length; i++) {
            result.append(vertexValueMap.get(i)).append(": ");

            for (int j = 0; j < incidenceMatrix[i].length; j++) {
                result.append(incidenceMatrix[i][j]).append(", ");
            }

            result.append("\n");
        }
        return result.toString();
    }
}
