package dsaa.lab_2;

import java.util.Arrays;
import java.util.List;

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
    void size() {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        assertEquals(list.size(), 0);
        list.add(1);
        assertEquals(list.size(), 1);
    }
}