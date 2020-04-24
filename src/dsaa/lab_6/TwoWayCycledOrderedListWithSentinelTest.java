package dsaa.lab_6;

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
    void addOrder() {
        list.add(b); // a
        list.add(a); // aaa

        assertEquals("\na(1)\naaa(1)", list.toString());

        list.add(c); // c

        assertEquals("\na(1)\naaa(1)\nc(1)", list.toString());

        assertEquals(0, list.indexOf(b));
        assertEquals(1, list.indexOf(a));
        assertEquals(2, list.indexOf(c));

        list.clear();
        list.add(new Link("aa"));
        list.add(new Link("aaa"));

        assertEquals(0, list.indexOf(new Link("aa")));
        assertEquals(1, list.indexOf(new Link("aaa")));

        list.add(new Link("a"));
        assertEquals(0, list.indexOf(new Link("a")));
        list.add(new Link("zzz"));
        assertEquals(0, list.indexOf(new Link("a")));
        assertEquals(3, list.indexOf(new Link("zzz")));
        list.add(new Link("b"));
        assertEquals(3, list.indexOf(new Link("b")));
    }

    @Test
    void testAddList() {
        list.add(a);

        TwoWayCycledOrderedListWithSentinel<Link> list2 = new TwoWayCycledOrderedListWithSentinel<>();
        list2.add(c);

        list.add(list2);

        assertTrue(list.contains(a));
        assertFalse(list.contains(b));
        assertTrue(list.contains(c));
    }

    @Test
    void testAddOrderWeights() {
        Link link1 = new Link("aaa"); // weight 1
        Link link2 = new Link("a"); // weight 1
        list.add(link1); // weight 1
        list.add(link2); // weight 1

        TwoWayCycledOrderedListWithSentinel<Link> secondList = new TwoWayCycledOrderedListWithSentinel<>();
        Link link3 = new Link("a", 5);
        secondList.add(link3);

        list.add(secondList);

        assertEquals(link1, list.get(2));
        assertEquals(link2, list.get(0));
        assertEquals(link3, list.get(1));
    }


    @Test
    void clear() {
        list.add(a);
        list.add(b);
        list.add(c);

        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void contains() {
        list.add(a);
        list.add(c);
        assertTrue(list.contains(a));
        assertFalse(list.contains(b));
        assertTrue(list.contains(c));
    }

    @Test
    void get() {
        list.add(a);
        list.add(b);
        list.add(c);

        assertEquals(b, list.get(0));
        assertEquals(a, list.get(1));
        assertEquals(c, list.get(2));
    }

    @Test
    void indexOf() {
        list.add(c);
        list.add(a);
        list.add(b);

        assertEquals(0, list.indexOf(b));
        assertEquals(1, list.indexOf(a));
        assertEquals(2, list.indexOf(c));
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());

        list.add(a);
        list.add(b);
        list.add(c);

        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
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
    void removeAll() {
    }
}