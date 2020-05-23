package dsaa.lab_10;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    @Test
    void createLink() {
        String token = "link=abc";
        Link link = Document.createLink(token);
        assertEquals(link, new Link("abc"));

        token = "link=__$";
        link = Document.createLink(token);
        assertNull(link);

        token = "link=abc(20)";
        link = Document.createLink(token);
        assertEquals(20, link.weight);
        assertEquals(link, new Link("abc", 20));
    }

    @Test
    void testToString() {
        String testData = "link=abc";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);

        Document doc1 = new Document("doc1", testScanner);
        assertEquals("Document: doc1\n" +
                "abc(1)", doc1.toString());

        testScanner.close();
    }

    @Test
    void toStringReverse() {
    }

    @Test
    void testIsCorrectId() {
        String correct = "zero";
        String incorrect = "3eddff";
        assertTrue(Document.isCorrectId(correct));
        assertFalse(Document.isCorrectId(incorrect));
    }
}