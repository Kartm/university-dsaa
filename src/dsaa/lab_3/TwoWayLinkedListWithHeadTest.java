package dsaa.lab_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TwoWayLinkedListWithHeadTest {
    TwoWayLinkedListWithHead<Link> list;
    Link a, b, c;

    @BeforeEach
    void setUp() {
        list = new TwoWayLinkedListWithHead<>();
        a = new Link("a");
        b = new Link("b");
        c = new Link("c");
    }

    @Test
    void add() {
        assertTrue(list.add(a));
        assertTrue(list.add(b));
        assertTrue(list.add(c));
    }

    @Test
    void testAddOnIndex() {
        list.add(a);
        list.add(0, b);
        assertEquals(b, list.get(0));
        assertEquals(a, list.get(1));
        assertThrows(NoSuchElementException.class, () -> list.add(200, c));
    }

    @Test
    void clear() {
        list.add(a);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        list.add(a);
        list.add(c);
        assertTrue(list.contains(a));
        assertFalse(list.contains(b));
    }

    @Test
    void get() {
        list.add(a);
        list.add(b);
        list.add(c);
        assertEquals(a, list.get(0));
        assertEquals(b, list.get(1));
        assertEquals(c, list.get(2));
        assertThrows(NoSuchElementException.class, () -> list.get(55));
    }

    @Test
    void set() {
        list.add(a);
        list.add(b);
        assertEquals(b, list.set(1, c));
        assertEquals(c, list.set(1, a));
    }

    @Test
    void indexOf() {
        list.add(a);
        list.add(b);
        assertEquals(0, list.indexOf(a));
        assertEquals(1, list.indexOf(b));
        assertEquals(-1, list.indexOf(c));
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.add(a);
        assertFalse(list.isEmpty());
    }

    @Test
    void iterator() {
        list.add(a);
        Iterator<Link> it = list.iterator();
        assertTrue(it.hasNext());
        while(it.hasNext()) {
            assertEquals(a, it.next());
        }

        list.clear();
        assertFalse(it.hasNext());
    }

    @Test
    void remove() {
        list.add(a);
        assertTrue(list.contains(a));
        list.remove(a);
        assertFalse(list.contains(a));
        assertTrue(list.isEmpty());
        assertFalse(list.remove(b));
    }

    @Test
    void removeAtIndex() {
        assertThrows(NoSuchElementException.class, () -> list.remove(-10));
        assertThrows(NoSuchElementException.class, () -> list.remove(0));
        list.add(a);
        list.add(b);
        assertEquals(a, list.remove(0));
        assertEquals(b, list.remove(0));
        list.add(a);
        list.add(b);
        assertEquals(b, list.remove(1));
    }

    @Test
    void size() {
        assertEquals(0, list.size());
        list.add(a);
        list.add(b);
        assertEquals(2, list.size());
        list.add(c);
        assertEquals(3, list.size());
    }

    @Test
    void toStringReverse() {
        list.add(a);
        list.add(b);
        list.add(c);
        assertEquals("c\nb\na", list.toStringReverse());
    }

    @Test
    void testAddOtherList() {
        TwoWayLinkedListWithHead<Link> list2 = new TwoWayLinkedListWithHead<>();

        list.add(a);
        list2.add(b);
        list2.add(c);

        list.add(list2);

        assertEquals("c\nb\na", list.toStringReverse());
    }
}