package dsaa.lab_2;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class OneWayLinkedListTest {

    @org.junit.jupiter.api.Test
    void iterator() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        int sum = 0;
        for(Integer integer:list) {
            sum += integer;
        }
        assertEquals(0, sum);

        sum = 0;
        list.add(1);
        list.add(1);
        list.add(1);
        for(Integer integer:list) {
            sum += integer;
        }
        assertEquals(3, sum);
    }

    @org.junit.jupiter.api.Test
    void add() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        list.add(1);
        assertEquals(1, list.get(0));

        list.add(0, 123123213);
        assertEquals(123123213, list.get(0));
    }

    @org.junit.jupiter.api.Test
    void addMultiple() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(5);

        Integer[] numbers = new Integer[]{1, 2, 5};
        List<Integer> expected = Arrays.asList(numbers);

        int i = 0;
        for(Integer integer:list) {
            assertEquals(expected.get(i), integer);
            i++;
        }
    }

    @org.junit.jupiter.api.Test
    void clear() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        list.add(1);
        list.add(5123);
        list.add(0, 123123213);
        assertFalse(list.isEmpty());

        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        assertFalse(list.contains(1));
        list.add(100);
        assertTrue(list.contains(100));
    }

    @org.junit.jupiter.api.Test
    void get() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        Integer a = 100;
        list.add(a);
        assertEquals(a, list.get(0));

        Integer b = 10;
        list.add(0, b);
        assertEquals(b, list.get(0));
    }

    @org.junit.jupiter.api.Test
    void set() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();

        assertThrows(NoSuchElementException.class, () -> list.set(0, new Integer(10)));
        list.add(new Integer(10));
        list.set(0, new Integer(123));
        assertTrue(list.contains(new Integer(123)));
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();

        Integer a = 100;
        list.add(a);
        assertEquals(0, list.indexOf(a));

        Integer b = 10;
        list.add(b);
        assertEquals(1, list.indexOf(b));

        Integer c = 1;
        assertEquals(-1, list.indexOf(c));

        list.remove(a);
        assertEquals(0, list.indexOf(b));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        Integer a = 100;
        Integer b = 10;
        list.add(a);
        list.add(b);
        assertTrue(list.remove(b));
    }

    @org.junit.jupiter.api.Test
    void removeObject() {
        OneWayLinkedList<Link> linkList = new OneWayLinkedList<>();
        linkList.add(new Link("a"));
        linkList.add(new Link("b"));
        assertFalse(linkList.remove(new Link("c")));
        assertTrue(linkList.remove(new Link("a")));
    }

    @org.junit.jupiter.api.Test
    void removeIndexObject() {
        OneWayLinkedList<Link> linkList = new OneWayLinkedList<>();
        linkList.add(new Link("a"));
        linkList.add(new Link("b"));
        linkList.add(new Link("c"));
        assertTrue(linkList.remove(new Link("a")));
        assertEquals(new Link("c"), linkList.remove(1));
    }

    @org.junit.jupiter.api.Test
    void size() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        assertEquals(list.size(), 0);
        list.add(1);
        assertEquals(list.size(), 1);
    }

    @org.junit.jupiter.api.Test
    void nextIfEmpty() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThrows(NullPointerException.class, it::next);
    }

    @org.junit.jupiter.api.Test
    void removeIfEmpty() {
        OneWayLinkedList<Link> list = new OneWayLinkedList<>();
        assertFalse(list.remove(new Link("10")));
        assertThrows(NoSuchElementException.class, () -> list.remove(40));
    }
}