package dsaa.lab_10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph graph;

    @BeforeEach
    void beforeEach() {
        String emptyData = "\neod";
        ByteArrayInputStream inEmpty = new ByteArrayInputStream(emptyData.getBytes());
        Scanner emptyScanner = new Scanner(inEmpty);
        emptyScanner.useDelimiter("\n");

        String aData = "link=b(2)\nlink=c(1)\neod";
        ByteArrayInputStream inA = new ByteArrayInputStream(aData.getBytes());
        Scanner aScanner = new Scanner(inA);
        aScanner.useDelimiter("\n");

        String cData = "link=d(1)\neod";
        ByteArrayInputStream inC = new ByteArrayInputStream(cData.getBytes());
        Scanner cScanner = new Scanner(inC);
        cScanner.useDelimiter("\n");

        String dData = "link=e(1)\neod";
        ByteArrayInputStream inD = new ByteArrayInputStream(dData.getBytes());
        Scanner dScanner = new Scanner(inD);
        dScanner.useDelimiter("\n");

        Document docA = new Document("a", aScanner);
        Document docB = new Document("b", emptyScanner);
        Document docC = new Document("c", cScanner);
        Document docD = new Document("d", dScanner);
        Document docE = new Document("e", emptyScanner);

        SortedMap<String, Document> sortedMap = new TreeMap<String, Document>();
        sortedMap.put(docA.name, docA);
        sortedMap.put(docB.name, docB);
        sortedMap.put(docC.name, docC);
        sortedMap.put(docD.name, docD);
        sortedMap.put(docE.name, docE);

        graph = new Graph(sortedMap);
    }
    @Test
    void generateAdjacencyMatrix() {
        assertEquals("O X X O O \n" +
                "O O O O O \n" +
                "O O O X O \n" +
                "O O O O X \n" +
                "O O O O O \n", graph.generateAdjacencyMatrix());
    }

    @Test
    void bfsRaw() {
        assertEquals("0, 1, 2, 3, 4", graph.bfs("a"));
    }

    @Test
    void dfsRaw() {
        assertEquals("0, 1, 2, 3, 4", graph.dfs("a"));
    }

    @Test
    void connectedComponents() {
    }
}