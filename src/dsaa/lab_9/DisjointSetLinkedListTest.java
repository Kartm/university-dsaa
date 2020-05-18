package dsaa.lab_9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjointSetLinkedListTest {

    DisjointSetLinkedList list = new DisjointSetLinkedList(5);

    @BeforeEach
    void beforeEach() {
        list = new DisjointSetLinkedList(5);
        for (int x = 0; x < 5; x++) {
            list.makeSet(x);
        }
    }

    @Test
    void makeSet() {
        assertDoesNotThrow(() -> {
            list = new DisjointSetLinkedList(5);
            for (int x = 0; x < 5; x++) {
                list.makeSet(x);
            }
        });
    }

    @Test
    void findSet() {
        assertEquals( 0, list.findSet(0));
        assertEquals(4, list.findSet(4));

        list = new DisjointSetLinkedList(20);
        for (int x = 5; x < 20; x++) {
            list.makeSet(x);
        }

        assertEquals(-1, list.findSet(2));
    }

    @Test
    void union() {
        assertEquals( 0, list.arr[0].getRepresentant());
        assertEquals( 4, list.arr[4].getRepresentant());
        assertEquals(1, list.arr[list.findSet(1)].getRepresentant());

        int itemA = list.findSet(0);
        int itemB = list.findSet(1);

        assertFalse(list.union(itemA, itemA));
        assertFalse(list.union(itemB, itemB));

        assertTrue(list.union(itemA, itemB));
        // now b is the parent of a

        // they should have the same roots then
        assertEquals(list.findSet(itemA), list.findSet(itemB));

        // parent of a (which is b) has now rank 2
        assertEquals(2, list.arr[list.findSet(itemA)].getLength());
    }

    @Test
    void link() {
    }

    @Test
    void testToString() {
        assertEquals("Disjoint sets as linked list:\n" +
                "0\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "4\n", list.toString());

        int itemA = list.findSet(0);
        int itemB = list.findSet(1);

        list.union(itemA, itemB);

        assertEquals("Disjoint sets as linked list:\n" +
                "1 0\n" +
                "2\n" +
                "3\n" +
                "4\n", list.toString());

        list.union(list.findSet(1), list.findSet(2));
        list.union(list.findSet(1), list.findSet(3));
        list.union(list.findSet(1), list.findSet(4));

        assertEquals("0 -> 1\n" +
                "1 -> 1\n" +
                "2 -> 1\n" +
                "3 -> 1\n" +
                "4 -> 1", list.toString());
    }
}