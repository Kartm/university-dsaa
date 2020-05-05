package dsaa.lab_7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.event.DocumentEvent;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable hashTable;

    @BeforeEach
    void beforeEach() {
        hashTable = new HashTable();
    }

    @Test
    void add() {
        hashTable.add(new Document("aaa"));
        assertEquals(hashTable.getLoadFactor(), 0.125);

        hashTable.add(new Document("bbb"));
        assertEquals(hashTable.getLoadFactor(), 0.25);

        hashTable.add(new Document("ccc"));
        assertEquals(hashTable.getLoadFactor(), 0.375);

        hashTable.add(new Document("ddd"));
        assertEquals(hashTable.getLoadFactor(), 0.5);

        hashTable.add(new Document("eee"));
        assertFalse(hashTable.add(new Document("eee")));

        // expect doubling the size
        assertNotEquals(hashTable.getLoadFactor(), 0.75);
        assertEquals(hashTable.getLoadFactor(), 0.375);

        assertEquals(hashTable.toString(), "0:\n" +
                "1:\n" +
                "2: bbb,\n" +
                "3:\n" +
                "4: ddd,\n" +
                "5:\n" +
                "6:\n" +
                "7:\n" +
                "8:\n" +
                "9: aaa,\n" +
                "10:\n" +
                "11: ccc,\n" +
                "12:\n" +
                "13: eee, eee,\n" +
                "14:\n" +
                "15:\n");
    }

    @Test
    void addAndScanner() {
        String testData = "link=a(10)\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");

        Document docA = new Document("a", testScanner);
        assertTrue(hashTable.add(docA));
        assertTrue(hashTable.add(new Document("b")));
    }

    @Test
    void addToEmpty() {
        hashTable = new HashTable(1);
        for(int i = 0; i < 100; i++) {
            String name = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 6);
            hashTable.add(new Document(name));
        }
        System.out.println(hashTable);
    }

    @Test
    void addDuplicate() {
        Document doc = new Document("eee");

        hashTable.add(doc);
        assertFalse(hashTable.add(doc));
    }

    @Test
    void getLoadFactor() {
        assertEquals(hashTable.getLoadFactor(), 0);

        hashTable.add(new Document("aaa"));
        assertEquals(hashTable.getLoadFactor(), 0.125);
    }

    @Test
    void testToString() {
        assertEquals(hashTable.toString(), "0:\n1:\n2:\n3:\n4:\n5:\n6:\n7:\n");

        hashTable.add(new Document("abc"));
        assertEquals(hashTable.toString(), "0:\n1:\n2:\n3:\n4:\n5:\n6: abc\n7:\n");
    }

    @Test
    void get() {
        Document docA = new Document("a");
        Document docAbc = new Document("abc");
        Document docCba = new Document("cba");

        hashTable.add(docA);
        hashTable.add(docAbc);
        hashTable.add(docCba);

        assertEquals(hashTable.get(docA), docA);
    }
}