package dsaa.lab_3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TwoWayLinkedListWithHeadTest2
{

    private final Link a = new Link("a");
    private final Link b = new Link("b");
    private final Link c = new Link("c");
    private TwoWayLinkedListWithHead<Link> links = new TwoWayLinkedListWithHead<Link>();

    @AfterEach
    void beforeEach()
    {

        links.clear();

    }


    @Test
    void testAddE()
    {

        assertTrue(links.add(a));
        assertTrue(links.add(b));
        assertTrue(links.add(c));
        assertEquals("a\nb\nc", links.toString());

    }


    @Test
    void testAddIntE()
    {

        assertThrows(NoSuchElementException.class, () -> links.add(2, a));
        assertThrows(NoSuchElementException.class, () -> links.add(-1, a));
        links.add(0, a);
        assertEquals("a", links.toString());
        assertThrows(NoSuchElementException.class, () -> links.add(2, a));
        assertThrows(NoSuchElementException.class, () -> links.add(-1, a));
        links.add(1, b);
        assertEquals("a\nb", links.toString());
        links.add(1, c);
        assertEquals("a\nc\nb", links.toString());
        assertEquals("b\nc\na", links.toStringReverse());
        assertEquals(c, links.remove(1));
        assertEquals("b\na", links.toStringReverse());

    }


    @Test
    void testContains()
    {

        assertFalse(links.contains(a));
        assertFalse(links.contains(null));
        links.add(a);
        assertTrue(links.contains(a));
        assertFalse(links.contains(b));

    }


    @Test
    void testGet()
    {

        assertThrows(NoSuchElementException.class, () -> links.get(0));
        assertThrows(NoSuchElementException.class, () -> links.get(1));
        assertThrows(NoSuchElementException.class, () -> links.get(-1));
        links.add(a);
        links.add(b);
        links.add(c);
        assertEquals(b, links.get(1));
        assertThrows(NoSuchElementException.class, () -> links.get(10));
        assertThrows(NoSuchElementException.class, () -> links.get(3));
        assertThrows(NoSuchElementException.class, () -> links.get(-1));

    }


    @Test
    void testSet()
    {

        assertThrows(NoSuchElementException.class, () -> links.set(0, a));
        assertThrows(NoSuchElementException.class, () -> links.set(1, a));
        assertThrows(NoSuchElementException.class, () -> links.set(-1, a));
        links.add(a);
        links.add(a);
        links.add(c);
        assertEquals(a, links.set(1, b));
        assertEquals("a\nb\nc", links.toString());
        assertThrows(NoSuchElementException.class, () -> links.set(10, a));
        assertThrows(NoSuchElementException.class, () -> links.set(3, a));
        assertThrows(NoSuchElementException.class, () -> links.set(-1, a));

    }


    @Test
    void testIndexOf()
    {

        assertEquals(-1, links.indexOf(a));
        links.add(a);
        links.add(b);
        links.add(c);
        assertEquals(0, links.indexOf(a));
        assertEquals(1, links.indexOf(b));
        assertEquals(2, links.indexOf(c));

    }


    @Test
    void testIsEmpty()
    {

        assertTrue(links.isEmpty());
        links.add(a);
        assertFalse(links.isEmpty());

    }


    @Test
    void testRemoveInt()
    {

        assertThrows(NoSuchElementException.class, () -> links.remove(0));
        assertThrows(NoSuchElementException.class, () -> links.remove(-1));
        assertThrows(NoSuchElementException.class, () -> links.remove(1));
        assertThrows(NoSuchElementException.class, () -> links.remove(5));
        links.add(a);
        links.add(b);
        links.add(c);
        assertEquals(a, links.remove(0));
        try
        {
            links.remove(3);
            fail("should throw exception");
        }
        catch (NoSuchElementException e)
        {
        }
        assertEquals(c, links.remove(1));
        assertEquals(b, links.remove(0));
        assertTrue(links.isEmpty());
        //
        links.clear();
        links.add(0, a);
        links.remove(0);
        assertTrue(links.isEmpty());

    }


    @Test
    void testRemoveE()
    {

        assertFalse(links.remove(a));
        links.add(a);
        assertFalse(links.remove(b));
        assertTrue(links.remove(a));
        assertTrue(links.isEmpty());

    }


    @Test
    void testSize()
    {

        assertEquals(0, links.size());
        links.add(a);
        assertEquals(1, links.size());
        links.clear();
        assertEquals(0, links.size());

    }


    @Test
    void testToStringReverse()
    {

        assertEquals("", links.toStringReverse());
        links.add(a);
        assertEquals("a", links.toStringReverse());
        links.add(b);
        links.add(c);
        assertEquals("c\nb\na", links.toStringReverse());

    }


    @Test
    void testAddTwoWayLinkedListWithHeadOfE()
    {

        links.add(a);
        links.add(b);
        // if the same nothing happens
        TwoWayLinkedListWithHead<Link> links2 = links;
        links.add(links);
        assertEquals(links, links2);
        links2 = new TwoWayLinkedListWithHead<>();
        links2.add(c);
        links2.add(b);
        links.add(links2);
        // adding empty second list
        assertTrue(links2.isEmpty());
        assertEquals("a\nb\nc\nb", links.toString());
        // adding an empty list
        links2.clear();
        links.add(links2);
        assertEquals("a\nb\nc\nb", links.toString());
        // adding to an empty list
        links2.add(links);
        assertEquals("a\nb\nc\nb", links2.toString());
        // adding a completely new list
        links = new TwoWayLinkedListWithHead<>();
        links2.add(links);
        assertEquals("a\nb\nc\nb", links2.toString());
        // adding null
        links2 = null;
        links.add(a);
        links.add(b);
        try
        {
            links.add(links2);
            fail("no exception thrown");
        }
        catch (NullPointerException e)
        {
        }

    }


    @Test
    void testIterator()
    {

        Iterator<Link> it = links.iterator();
        assertFalse(it.hasNext());
        links.add(a);
        links.add(b);
        links.add(c);
        it = links.iterator();
        for (Link link : links) assertEquals(link, it.next());
        links.clear();
        try
        {
            Object obj = it.next();
            fail("no exception thrown " + obj);
        }
        catch (NoSuchElementException e)
        {
        }

    }


    @Test
    void testRandom()
    {

        links.add(0, b);
        links.add(0, a);
        links.add(c);
        links.remove(1);
        links.add(1, b);
        links.set(0, links.set(1, a));
        assertTrue(links.contains(a));
        assertTrue(links.contains(b));
        assertTrue(links.contains(c));
        links.add(0, links.remove(2));
        assertEquals("a\nb\nc", links.toStringReverse());

    }

}