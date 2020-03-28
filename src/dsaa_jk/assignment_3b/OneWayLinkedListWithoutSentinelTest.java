package dsaa_jk.assignment_3b;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneWayLinkedListWithoutSentinelTest {
    OneWayLinkedListWithoutSentinel<String> list;
    String a, b, c;
    @BeforeEach
    void beforeEach() {
        list = new OneWayLinkedListWithoutSentinel<>();
        a = "a";
        b = "b";
        c = "c";
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.add(a);
        assertFalse(list.isEmpty());
    }

    @Test
    void add() {
        assertEquals("LIST IS EMPTY\n", list.toString());
        list.add(a);
        assertEquals("a\n", list.toString());
        list.add(b);
        assertEquals("a\nb\n", list.toString());
        list.add(c);
        assertEquals("a\nb\nc\n", list.toString());
    }

    @Test
    void toStringTest() {
        assertEquals("LIST IS EMPTY\n", list.toString());
        list.add(a);
        list.add(b);
        list.add(c);
        assertEquals("a\nb\nc\n", list.toString());
    }
}