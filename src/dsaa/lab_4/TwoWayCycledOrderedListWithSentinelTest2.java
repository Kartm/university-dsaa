package dsaa.lab_4;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoWayCycledOrderedListWithSentinelTest2 {

    private static TwoWayCycledOrderedListWithSentinel<Link> links = new TwoWayCycledOrderedListWithSentinel<Link>();
    private static final Link a = new Link("a", 1);
    private static final Link b = new Link("b", 2);
    private static final Link c = new Link("c", 3);
    private static final Link[] all = {a, c, b};
    private static final String abc = "\na(1)\nb(2)\nc(3)";

    @BeforeEach
    void beforeEach() {

        links = new TwoWayCycledOrderedListWithSentinel<Link>();

    }


    private void addAll() {

        links.add(a);
        links.add(c);
        links.add(b);

    }


    @Test
    void testAddE() {

        assertTrue(links.add(a));
        assertTrue(links.add(c));
        assertTrue(links.add(b));
        assertEquals(links.toString(), abc);
        assertTrue(links.add(c));

    }


    @Test
    void testClear() {

        addAll();
        links.clear();
        assertEquals("", links.toString());
        // Does clearing empty cause an exception?
        links.clear();

    }


    @Test
    void testContains() {

        for (Link link : all) {
            links.add(link);
            assertTrue(links.contains(link));
        }
        for (Link link : all) assertTrue(links.contains(link));
        links.clear();
        assertFalse(links.contains(a));
        links.add(a);
        links.add(b);
        assertFalse(links.contains(c));

    }


    @Test
    void testGet() {

        addAll();
        assertEquals(links.get(0), a);
        assertEquals(links.get(1), b);
        assertEquals(links.get(2), c);

        assertThrows(NoSuchElementException.class, () -> {
            links.get(-1);
        });
        assertThrows(NoSuchElementException.class, () -> {
            links.get(4);
        });
    }


    @Test
    void testIndexOf() {

        addAll();
        assertEquals(links.indexOf(a), 0);
        assertEquals(links.indexOf(b), 1);
        assertEquals(links.indexOf(c), 2);
        links.clear();
        assertEquals(links.indexOf(a), -1);

    }


    @Test
    void testRemoveInt() {

        addAll();
        assertEquals(links.remove(0), a);
        assertEquals(links.remove(1), c);
        assertEquals(links.remove(0), b);
        addAll();
        assertTrue(links.remove(a));
        assertFalse(links.remove(a));

    }


    @Test
    void testSize() {

        assertEquals(links.size(), 0);
        links.add(a);
        assertEquals(links.size(), 1);
        links.add(b);
        assertEquals(links.size(), 2);
        links.add(c);
        assertEquals(links.size(), 3);

    }


    @Test
    void testAddTwoWayCycledOrderedListWithSentinelOfE() {

        // normal situation test
        TwoWayCycledOrderedListWithSentinel<Link> links2 = new TwoWayCycledOrderedListWithSentinel<Link>();
        addAll();
        links2.add(b);
        links2.add(c);
        links.add(links2);
        assertEquals(links.toString(), "\na(1)\nb(2)\nb(2)\nc(3)\nc(3)");
        assertTrue(links2.isEmpty());
        // the same object test
        links.clear();
        links2.clear();
        for (Link link : all) {
            links.add(link);
            links2.add(link);
        }
        links.add(links2);
        assertEquals(abc, links.toString());
        assertEquals(links2.toString(), abc);
        // adding empty object test
        links2.clear();
        links.add(links2);
        assertEquals(links.toString(), abc);
        // adding to an an empty object test
        links2.add(links);
        assertEquals(links2.toString(), abc);

    }


    @Test
    void testRemoveAll() {

        addAll();
        addAll();
        addAll();
        links.removeAll(a);
        assertFalse(links.contains(a));
        assertTrue(links.contains(b));
        // Does throw error when there is no elem
        links.removeAll(a);
        // Does throw error when empty
        links.clear();
        links.removeAll(a);

    }

}