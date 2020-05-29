package dsaa.lab_10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.*;

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

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);
        set.add(docB);
        set.add(docC);
        set.add(docD);
        set.add(docE);

        graph = new Graph(set);
    }

    @Test
    void generateAdjacencyMatrix() {
        assertEquals("O 2 1 O O \n" +
                "O O O O O \n" +
                "O O O 1 O \n" +
                "O O O O 1 \n" +
                "O O O O O \n", graph.generateAdjacencyMatrix());
    }

    @Test
    void bfs() {
        assertEquals("a, b, c, d, e", graph.bfs("a"));
    }

    @Test
    void dfs() {
        assertEquals("a, b, c, d, e", graph.dfs("a"));
    }

    @Test
    void dfsStartInMiddle() {
        assertEquals("c, d, e", graph.dfs("c"));
    }

    @Test
    void deepGraph() {
        String data = "link=b(2)\neod";
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
        Scanner sc = new Scanner(byteStream);
        sc.useDelimiter("\n");
        Document docA = new Document("a", sc);

         data = "link=c(2)\neod";
         byteStream = new ByteArrayInputStream(data.getBytes());
         sc = new Scanner(byteStream);
        Document docB = new Document("b", sc);

        data = "link=d(2)\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docC = new Document("c", sc);

        data = "link=e(2)\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docD = new Document("d", sc);

        data = "\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docE = new Document("e", sc);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);
        set.add(docB);
        set.add(docC);
        set.add(docD);
        set.add(docE);

        graph = new Graph(set);
        assertEquals("a, b, c, d, e", graph.bfs("a"));
        assertEquals("a, b, c, d, e", graph.dfs("a"));

        assertEquals("c, d, e", graph.bfs("c"));
        assertEquals("c, d, e", graph.dfs("c"));

        assertEquals("e", graph.bfs("e"));
        assertEquals("e", graph.dfs("e"));
    }

    @Test
    void pseudoGraph() {
        String data = "link=b(2)\nlink=b(1)\nlink=b(3)\neod";
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
        Scanner sc = new Scanner(byteStream);
        sc.useDelimiter("\n");
        Document docA = new Document("a", sc);

        data = "link=a(2)\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docB = new Document("b", sc);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);
        set.add(docB);

        graph = new Graph(set);
        assertEquals("a, b", graph.bfs("a"));
        assertEquals("a, b", graph.dfs("a"));

        assertEquals("b, a", graph.bfs("b"));
        assertEquals("b, a", graph.dfs("b"));
    }

    @Test
    void connectedComponentsPseudoGraph() {
        String data = "link=b(2)\nlink=b(1)\nlink=b(3)\neod";
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
        Scanner sc = new Scanner(byteStream);
        sc.useDelimiter("\n");
        Document docA = new Document("a", sc);

        data = "link=a(2)\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docB = new Document("b", sc);

        data = "\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docC = new Document("c", sc);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);
        set.add(docB);
        set.add(docC);

        graph = new Graph(set);
        assertEquals(2, graph.connectedComponents());
    }

    @Test
    void traversalNonExisting() {
        String data = "link=b(2)\nlink=b(1)\nlink=b(3)\neod";
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
        Scanner sc = new Scanner(byteStream);
        sc.useDelimiter("\n");
        Document docA = new Document("a", sc);

        data = "link=a(2)\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docB = new Document("b", sc);

        data = "\neod";
        byteStream = new ByteArrayInputStream(data.getBytes());
        sc = new Scanner(byteStream);
        Document docC = new Document("c", sc);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);
        set.add(docB);
        set.add(docC);

        graph = new Graph(set);
        assertNull(graph.dfs("d"));
    }

    @Test
    void traversalEmpty() {
        String data = "\neod";
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
        Scanner sc = new Scanner(byteStream);
        sc.useDelimiter("\n");
        Document docA = new Document("a", sc);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);

        graph = new Graph(set);
        assertEquals("a", graph.dfs("a"));
    }

    @Test
    void traversalNonExistingAdjacents() {
        String data = "\neod";
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
        Scanner sc = new Scanner(byteStream);
        sc.useDelimiter("\n");
        Document docA = new Document("a", sc);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);

        graph = new Graph(set);
        assertEquals("a", graph.dfs("a"));
    }

    private class Data {
        String nodeName;
        String data;

        Data(String nodeName, String data) {
            this.nodeName = nodeName;
            this.data = data;
        }
    }

    @Test
    void traversalAdvancedNames() {
        SortedSet<Document> set = new TreeSet<Document>();
        Data[] inputs = new Data[]{new Data("parent", "link=child1(2)\nlink=child2(2)\neod"),
                new Data("child1", "link=grandchild1(2)\neod"),
                new Data("child2", "link=grandchild2(2)\nlink=grandchild3(2)\neod"),
                new Data("grandchild1", "\neod"),
                new Data("grandchild2", "\neod"),
                new Data("grandchild3", "\neod")};

        for (Data input : inputs) {
            String data = input.data;
            ByteArrayInputStream byteStream = new ByteArrayInputStream(data.getBytes());
            Scanner sc = new Scanner(byteStream);
            sc.useDelimiter("\n");
            Document doc = new Document(input.nodeName, sc);
            set.add(doc);
        }

        graph = new Graph(set);
        assertEquals("parent, child1, grandchild1, child2, grandchild2, grandchild3", graph.dfs("parent"));
        assertEquals("parent, child1, child2, grandchild1, grandchild2, grandchild3", graph.bfs("parent"));
    }

    @Test
    void connectedComponents() {
        assertEquals(1, graph.countNumberOfDisjointSets());
    }

    @Test
    void connectedComponentsMultiple() {
        String emptyData = "\neod";
        ByteArrayInputStream inEmpty = new ByteArrayInputStream(emptyData.getBytes());
        Scanner emptyScanner = new Scanner(inEmpty);
        emptyScanner.useDelimiter("\n");

        String aData = "link=b(2)\neod";
        ByteArrayInputStream inA = new ByteArrayInputStream(aData.getBytes());
        Scanner aScanner = new Scanner(inA);
        aScanner.useDelimiter("\n");

        String dData = "link=e(1)\neod";
        ByteArrayInputStream inD = new ByteArrayInputStream(dData.getBytes());
        Scanner dScanner = new Scanner(inD);
        dScanner.useDelimiter("\n");

        Document docA = new Document("a", aScanner);
        Document docB = new Document("b", emptyScanner);
        Document docD = new Document("d", dScanner);
        Document docE = new Document("e", emptyScanner);

        SortedSet<Document> set = new TreeSet<>();
        set.add(docA);
        set.add(docB);
        set.add(docD);
        set.add(docE);

        graph = new Graph(set);

        assertEquals(2, graph.countNumberOfDisjointSets());
    }
}