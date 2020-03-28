package dsaa.lab_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {
    Link link, link2;
    @BeforeEach
    void beforeEach() {
        link = new Link("test");
        link2 = new Link("test", 500);
    }
    @Test
    void testEquals() {
        assertEquals(link, link2);
        assertNotEquals(link, new Link("test3"));
    }

    @Test
    void testToString() {
        assertEquals("test(1)", link.toString());
        assertEquals("test(500)", link2.toString());
    }

    @Test
    void compareTo() {
        assertEquals(0, link.compareTo(link2));
        assertTrue(link.compareTo(new Link("aaaaaaaaaaaaaaa")) > 0);
        assertTrue(link.compareTo(new Link("zzzz")) < 0);
    }
}