package dsaa.lab_10;

import java.util.*;

public class Graph {
    private final int[][] adjacencyMatrix; // filled with zeroes by default
    private final Document[] indexToDocument;
    private final Map<String, Integer> linkToNode = new HashMap<>();

    @SuppressWarnings("unchecked")
    public Graph(SortedSet<Document> documents) {
        int size = documents.size();
        adjacencyMatrix = new int[size][size];
        indexToDocument = new Document[size];

        // setup the nodes
        int nodeIndex = 0;
        for(Document doc: documents) {
            indexToDocument[nodeIndex] = doc;
            linkToNode.put(doc.name, nodeIndex);
            nodeIndex++;
        }

        // setup the edges
        nodeIndex = 0;
        for(Document doc: documents) {
            // all the outgoing edges
            for(Link link: doc.link.values()) {
                if(linkToNode.containsKey(link.ref)) { // if target node exists
                    int targetIndex = linkToNode.get(link.ref);
                    adjacencyMatrix[nodeIndex][targetIndex] = link.weight;
                }
            }
            nodeIndex++;
        }
    }

    public String generateAdjacencyMatrix() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < adjacencyMatrix.length; i++) {
            for(int j = 0; j < adjacencyMatrix[i].length; j++) {
                result.append(adjacencyMatrix[i][j] == 0 ? "O " : adjacencyMatrix[i][j] + " ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    // todo in a case you have many way to go, analyze vertices in lexicographical order
    public String bfs(String start) {
        StringBuilder result = new StringBuilder();
        if(!checkNode(start)) {
            return null;
        }

        int startNode = linkToNode.get(start);

        boolean[] visited = new boolean[adjacencyMatrix.length];
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(startNode);
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            if(!visited[currentNode]) {
                result.append(indexToDocument[currentNode].name).append(", ");
                visited[currentNode] = true;
                // loop all adjacent nodes
                for (int i = 0; i < adjacencyMatrix[currentNode].length; i++) {
                    if (adjacencyMatrix[currentNode][i] != 0 && !visited[i]) { // if connection exists and is unvisited
                        queue.add(i);
                    }
                }
            }
        }

        return result.toString().replaceAll(", $", "");
    }

    // in a case you have many way to go, analyze vertices in lexicographical order
    public String dfs(String start) {
        StringBuilder result = new StringBuilder();

        boolean[] visited = new boolean[adjacencyMatrix.length];

        Stack<Integer> stack = new Stack<>();

        if(!checkNode(start)) {
            return null;
        }
        int startNode = linkToNode.get(start);

        stack.add(startNode);

        while(!stack.isEmpty()) {
            int currentNode = stack.pop();
            if(!visited[currentNode]) {
                visited[currentNode] = true;
                result.append(indexToDocument[currentNode].name).append(", ");

                // loop all adjacent nodes
                for (int i = adjacencyMatrix[currentNode].length - 1; i >= 0; i--) {
                    if (adjacencyMatrix[currentNode][i] != 0 && !visited[i]) { // if connection exists and is unvisited
                        stack.add(linkToNode.get(indexToDocument[i].name));
                    }
                }
            }
        }

        return result.toString().replaceAll(", $", "");
    }

    public int connectedComponents() {
        return countNumberOfDisjointSets();
    }

    public int countNumberOfDisjointSets() {
        DisjointSetForest sets = new DisjointSetForest(adjacencyMatrix.length);
        for(int nodeIndex = 0; nodeIndex < adjacencyMatrix.length; nodeIndex++) { // for each node u in V do
            sets.makeSet(nodeIndex); // MakeSet(u)
        }

        for(int nodeIndex = 0; nodeIndex < adjacencyMatrix.length; nodeIndex++) { // for each node u in V do
            for(int childIndex = 0; childIndex < adjacencyMatrix[nodeIndex].length; childIndex++) {
                if(nodeIndex != childIndex && adjacencyMatrix[nodeIndex][childIndex] != 0) {
                    //System.out.println(nodeIndex + " => " + childIndex);
                    sets.union(nodeIndex, childIndex); // Union(u,v)
                }
            }
        }

        // count the representants
        Set<Integer> representants = new HashSet<>();
        for(int nodeIndex = 0; nodeIndex < adjacencyMatrix.length; nodeIndex++) {
            representants.add(sets.findSet(nodeIndex));
        }

        return representants.size(); // todo
    }

    private boolean checkNode(String name) {
        return linkToNode.containsKey(name);
    }
}