package dsaa_jk.assignment_8.task_3_4;

import dsaa_jk.assignment_8.task_2.Graph;

import java.util.*;

public class AdjacencyListGraph<T> extends Graph<T> implements ITraversable<T> {
    private final int DEFAULT_EDGE_WEIGHT = 1;
    private final Map<Integer, T> vertexValueMap;
    private ArrayList<Edge>[] adjacencyList;
    private int vertexCount = 0;
    private int edgeCount = 0;

    @SuppressWarnings("unchecked")
    public AdjacencyListGraph() {
        vertexValueMap = new HashMap<Integer, T>();
        adjacencyList = (ArrayList<Edge>[]) new ArrayList[0];
    }

    @Override
    public Iterable<T> bfs(T start) {
        ArrayList<T> traversal = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount]; // every node unvisited by default

        int startNodeIndex = getNodeIndex(start);
        visited[startNodeIndex] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNodeIndex);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            traversal.add(vertexValueMap.get(currentNode));

            for (Edge neighbor : adjacencyList[currentNode]) {
                int targetNode = neighbor.getTarget();
                if (!visited[targetNode]) {
                    visited[targetNode] = true;
                    queue.add(targetNode);
                }
            }
        }

        return traversal;
    }

    @Override
    public Iterable<T> dfs(T start) {
        ArrayList<T> traversal = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount]; // every node unvisited by default

        int startNodeIndex = getNodeIndex(start);

        Stack<Integer> stack = new Stack<>();
        stack.add(startNodeIndex);

        while (!stack.isEmpty()) {
            int currentNode = stack.pop();

            if (!visited[currentNode]) {
                traversal.add(vertexValueMap.get(currentNode));
                visited[currentNode] = true;

                for (Edge neighbor : adjacencyList[currentNode]) {
                    stack.add(neighbor.getTarget());
                }
            }
        }
        return traversal;
    }


    @Override
    public Iterable<DijkstraPath<T>> dijkstra(T start) {
        ArrayList<DijkstraPath<T>> traversal = new ArrayList<>();

        int[] distance = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE); // consider this infinite distance

        int[] predecessor = new int[vertexCount];
        Arrays.fill(predecessor, -1); // -1 = no predecessor

        int startNodeIndex = getNodeIndex(start);
        distance[startNodeIndex] = 0; // no cost for no travel

        // sort nodes by total path weights
        Comparator<Integer> nodeComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer nodeIndexA, Integer nodeIndexB) {
                return distance[nodeIndexA] - distance[nodeIndexB];
            }
        };

        PriorityQueue<Integer> sortedNodes = new PriorityQueue<>(nodeComparator);

        for (int nodeIndex = 0; nodeIndex < vertexCount; nodeIndex++) {
            sortedNodes.add(nodeIndex);
        }

        while (!sortedNodes.isEmpty()) {
            // minimal path node
            int currentNode = sortedNodes.poll();

            // for all neighbors
            for (Edge neighborEdge : adjacencyList[currentNode]) {

                if (neighborEdge.weight > 0 && currentNode != neighborEdge.getTarget()) {
                    int newPath = distance[currentNode] + neighborEdge.weight;

                    // a shorter path is found
                    // ignore infinite distance
                    int targetNode = neighborEdge.getTarget();
                    if (newPath < distance[targetNode]) {
                        distance[targetNode] = newPath;
                        predecessor[targetNode] = currentNode;

                        // to update neighborIndex node in the priority queue
                        // we have to reinsert it
                        if (!sortedNodes.isEmpty()) {
                            sortedNodes.remove(targetNode);
                            sortedNodes.add(targetNode);
                        }
                    }
                }
            }
        }

        for (int nodeIndex = 0; nodeIndex < vertexCount; nodeIndex++) {
            // in case no path to nodeIndex
            if (distance[nodeIndex] == Integer.MAX_VALUE) {
                traversal.add(new DijkstraPath<T>(
                        vertexValueMap.get(nodeIndex),
                        null,
                        -1));
            } else {
                traversal.add(new DijkstraPath<T>(
                        vertexValueMap.get(nodeIndex),
                        vertexValueMap.get(predecessor[nodeIndex]),
                        distance[nodeIndex]));
            }
        }
        return traversal;
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
}
