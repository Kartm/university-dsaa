package dsaa.lab_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoWayCycledOrderedListWithSentinelTest {
    TwoWayCycledOrderedListWithSentinel<Link> list;
    Link a, b, c;
    @BeforeEach
    void setUp() {
        list = new TwoWayCycledOrderedListWithSentinel<>();
        a = new Link("aaa");
        b = new Link("a");
        c = new Link("c");
    }

    @Test
    void add() {
        list.add(a);
        list.add(c);
        assertTrue(list.contains(a));
        assertFalse(list.contains(b));
        assertTrue(list.contains(c));
    }

    @Test
    void testAddList() {
        list.add(a);

        TwoWayCycledOrderedListWithSentinel<Link> list2 = new TwoWayCycledOrderedListWithSentinel<>();
        list2.add(b);
        list2.add(c);

        list.add(list2);

        assertTrue(list.contains(a));
        assertFalse(list.contains(b));
        assertTrue(list.contains(c));
    }

    @Test
    void clear() {
    }

    @Test
    void contains() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void iterator() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void size() {
    }

    @Test
    void testAdd1() {
    }

    @Test
    void removeAll() {
    }
}