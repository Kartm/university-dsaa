package dsaa.lab_10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;

public class Graph {
    int arr[][];
    //TODO? Collection to map Document to index of vertex
    // You can change it
    HashMap<String,Integer> name2Int;
    @SuppressWarnings("unchecked")
    //TODO? Collection to map index of vertex to Document
            // You can change it
            Entry<String, Document>[] arrDoc=(Map.Entry<String, Document>[])new Map.Entry[0];

    // The argument type depend on a selected collection in the Main class
    public Graph(SortedMap<String,Document> internet){
        int size=internet.size();
        arr=new int[size][size];
        // TODO
    }

    public String bfs(String start) {
        // TODO
        return null;
    }

    public String dfs(String start) {
        // TODO
        return null;
    }

    public int connectedComponents() {
        // TODO
        return -1;
    }
}