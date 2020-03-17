package dsaa.lab_2;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void load() {
        String data = "link=a";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);

            Document doc = new Document("Name", scanner);
            assertEquals("Document: Name\n" +
                    "a", doc.toString());
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void testToString() {
    }
}