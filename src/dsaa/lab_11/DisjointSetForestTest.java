package dsaa.lab_11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjointSetForestTest {

    DisjointSetForest forest = new DisjointSetForest(5);

    @BeforeEach
    void beforeEach() {
        forest = new DisjointSetForest(5);
        for (int x = 0; x < 5; x++) {
            forest.makeSet(x);
        }
    }

    @Test
    void makeSet() {
        assertDoesNotThrow(() -> {
            forest = new DisjointSetForest(5);
            for (int x = 0; x < 5; x++) {
                forest.makeSet(x);
            }
        });
    }

    @Test
    void findSet() {
        assertEquals( 0, forest.findSet(0));
        assertEquals(4, forest.findSet(4));

        forest = new DisjointSetForest(20);
        for (int x = 5; x < 20; x++) {
            forest.makeSet(x);
        }
        assertNotEquals(2, forest.findSet(2));
        assertEquals(-1, forest.findSet(2));
    }

    @Test
    void union() {
        assertEquals( 0, forest.arr[0].getParent());
        assertEquals( 4, forest.arr[4].getParent());
        assertEquals(1, forest.arr[forest.findSet(1)].getParent());

        int itemA = forest.findSet(0);
        int itemB = forest.findSet(1);

        assertFalse(forest.union(itemA, itemA));
        assertFalse(forest.union(itemB, itemB));

        assertTrue(forest.union(itemA, itemB));
        // now b is the parent of a

        // they should have the same roots then
        assertEquals(forest.findSet(itemA), forest.findSet(itemB));

        // parent of a (which is b) has now rank 2
        assertEquals(2, forest.arr[forest.findSet(itemA)].rank);
    }

    @Test
    void link() {
    }

    @Test
    void testToString() {
        assertEquals("0 -> 0\n" +
                "1 -> 1\n" +
                "2 -> 2\n" +
                "3 -> 3\n" +
                "4 -> 4", forest.toString());

        int itemA = forest.findSet(0);
        int itemB = forest.findSet(1);

        forest.union(itemA, itemB);

        assertEquals("0 -> 1\n" +
                "1 -> 1\n" +
                "2 -> 2\n" +
                "3 -> 3\n" +
                "4 -> 4", forest.toString());

        forest.union(forest.findSet(1), forest.findSet(2));
        forest.union(forest.findSet(1), forest.findSet(3));
        forest.union(forest.findSet(1), forest.findSet(4));

        assertEquals("0 -> 1\n" +
                "1 -> 1\n" +
                "2 -> 1\n" +
                "3 -> 1\n" +
                "4 -> 1", forest.toString());
    }
}