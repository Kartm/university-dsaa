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
        arr = new int[size][size];

        nameToInt = new HashMap<>();
        HashMap<Integer, Document> documentsRaw = new HashMap<>();

        int nodeIndex = 0;
        // map document names to node numbers
        for(Entry<String, Document> entry: internet.entrySet()) {
            Document doc = entry.getValue();

            nameToInt.put(doc.name, nodeIndex);
            documentsRaw.put(nodeIndex, doc);

            nodeIndex++;
        }

        for(Entry<String, Document> entry: internet.entrySet()) {
            Set<java.util.Map.Entry<String, Link>> link = entry.getValue().link.entrySet();
            int parentIndex = nameToInt.get(entry.getValue().name);

            for(Entry<String, Link> l: link) {
                String nodeText = l.getValue().ref;

                int childIndex;

                if(nameToInt.get(nodeText) == null) {
                    childIndex = nodeIndex;
                    nodeIndex++;
                    nameToInt.put(nodeText, childIndex);
                } else {
                    childIndex = nameToInt.get(nodeText);
                }

                arr[parentIndex][childIndex] = l.getValue().weight;
                // arr[childIndex][parentIndex] = 1;
            }
        }

        documents = documentsRaw;
    }

    public String generateAdjacencyMatrix() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                result.append(arr[i][j] == 0 ? "O " : arr[i][j] + " ");
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

        queue.add(startNode);
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            result.append(documents.get(currentNode).name).append(", ");
            visited[currentNode] = true;
            // loop all adjacent nodes
            for(int i = 0; i < arr[currentNode].length; i++) {
                if(arr[currentNode][i] != 0 && !visited[i]) { // if connection exists and is unvisited
                    queue.add(i);
                }
            }
        }

        return result.toString().substring(0, result.length() - 2);
    }

    // in a case you have many way to go, analyze vertices in lexicographical order
    public String dfs(String start) {
        StringBuilder result = new StringBuilder();

        boolean[] visited = new boolean[size];

        Stack<Integer> stack = new Stack<>();
        stack.add(nameToInt.get(start));

        while(!stack.isEmpty()) {
            int currentNode = stack.pop();
            visited[currentNode] = true;
            result.append(documents.get(currentNode).name).append(", ");

            // loop all adjacent nodes
            for(int i = arr[currentNode].length - 1; i >= 0; i--) {
                if(arr[currentNode][i] != 0 && !visited[i]) { // if connection exists and is unvisited
                    stack.add(nameToInt.get(documents.get(i).name));
                }
            }
        }

        return result.toString().substring(0, result.length() - 2);
    }

    // return the number of connected components. Use DisjointSetForest class implemented previously

    /*

    ConnectedComponents(G,w)


    for each edge (u,v) in E do
        if FindSet(u)  FindSet(v) then
            Union(u,v)
     */
    // returns the number of disjoint sets


    public int connectedComponents() {
        return countNumberOfDisjointSets();
    }

    public int countNumberOfDisjointSets() {
        DisjointSetForest sets = new DisjointSetForest(size);
        for(int nodeIndex = 0; nodeIndex < size; nodeIndex++) { // for each node u in V do
            sets.makeSet(nodeIndex); // MakeSet(u)
        }

        for(int nodeIndex = 0; nodeIndex < size; nodeIndex++) { // for each node u in V do
            for(int childIndex = 0; childIndex < size; childIndex++) {
                if(nodeIndex != childIndex && arr[nodeIndex][childIndex] != 0) {
                    //System.out.println(nodeIndex + " => " + childIndex);
                    sets.union(nodeIndex, childIndex); // Union(u,v)
                }
            }
        }

        // count the representants
        Set<Integer> representants = new HashSet<>();
        for(int nodeIndex = 0; nodeIndex < size; nodeIndex++) {
            representants.add(sets.findSet(nodeIndex));
        }

        return representants.size(); // todo
    }
}