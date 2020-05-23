package dsaa.lab_10;

import java.util.*;
import java.util.Map.Entry;

public class Graph {
    private final int[][] arr;
    private int size;

    HashMap<String, Integer> nameToInt; // map of document names into node numbers
    HashMap<Integer, Document> documents; // map node numbers to Documents

    @SuppressWarnings("unchecked")
    public Graph(SortedMap<String, Document> internet) {
        size = internet.size();
        // todo use sth different than size.
        // take into account all the children
        arr = new int[size + 1][size + 1];

        nameToInt = new HashMap<>();
        HashMap<Integer, Document> documentsRaw = new HashMap<>();

        int nodeIndex = 0;
        // map document names to node numbers
        for(var entry: internet.entrySet()) {
            Document doc = entry.getValue();

            nameToInt.put(doc.name, nodeIndex);
            documentsRaw.put(nodeIndex, doc);

            nodeIndex++;
        }

        for(var entry: internet.entrySet()) {
            var link = entry.getValue().link.entrySet();
            int parentIndex = nameToInt.get(entry.getValue().name);

            for(var l: link) {
                String nodeText = l.getValue().ref;

                var childIndex = nameToInt.get(nodeText);
                if(childIndex == null) {
                    childIndex = nodeIndex + 1;
                    nodeIndex++;
                }

                nameToInt.put(nodeText, childIndex);

                arr[parentIndex][childIndex] = 1;
                //arr[childIndex][parentIndex] = 1; // is the arrow in both directions?

                nameToInt.put(nodeText, childIndex);
            }
        }

        documents = documentsRaw;
    }

    public String generateAdjacencyMatrix() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                result.append(arr[i][j] == 0 ? "O " : "X ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    // todo in a case you have many way to go, analyze vertices in lexicographical order
    public String bfs(String start) {
        StringBuilder result = new StringBuilder();
        int startNode = nameToInt.get(start);

        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<Integer>();

        visited[startNode] = true;
        queue.add(startNode);
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            result.append(currentNode).append(", ");
            //System.out.print(currentNode + ": ");
            // loop all adjacent nodes
            for(int i = 0; i < arr[currentNode].length; i++) {
                int adjacentNode = arr[currentNode][i];
                if(adjacentNode == 1 && !visited[i]) { // if connection exists and is unvisited
                    //System.out.print(i + ", ");
                    queue.add(i);
                }
            }
        }

        return result.toString().substring(0, result.length() - 2);
    }

    // in a case you have many way to go, analyze vertices in lexicographical order
    public String dfs(String start) {
        StringBuilder result = new StringBuilder();
        int startNode = nameToInt.get(start);

        boolean[] visited = new boolean[size];
        Stack<Integer> stack = new Stack<>();

        visited[startNode] = true;
        stack.add(startNode);
        while(!stack.isEmpty()) {
            int currentNode = stack.pop();
            result.append(currentNode).append(", ");
            //System.out.print(currentNode + ": ");
            // loop all adjacent nodes
            for(int i = 0; i < arr[currentNode].length; i++) {
                int adjacentNode = arr[currentNode][i];
                if(adjacentNode == 1 && !visited[i]) { // if connection exists and is unvisited
                    //System.out.print(i + ", ");
                    stack.add(i);
                }
            }
        }

        return result.toString().substring(0, result.length() - 2);
    }

    // return the number of connected components. Use DisjointSetForest class implemented previously
    // you can add function to count number of disjoint sets
    /*

    ConnectedComponents(G,w)
    for each vertex uV[G] do
        MakeSet(u)
    for each edge (u,v)E do
        if FindSet(u)  FindSet(v) then
            Union(u,v)
     */
    public int connectedComponents() {
        // TODO
        return -1;
    }
}