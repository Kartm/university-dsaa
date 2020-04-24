package dsaa.lab_6;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void iterativeMergeSort() {
        String testData = "link=abc(20)\nlink=bca(7)\nlink=ccc(10)\nlink=ccdc(3)\nlink=dsd(8)\nlink=dd(4)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);
        doc1.iterativeMergeSort(doc1.getWeights());
        testScanner.close();
    }

    @Test
    void iterativeMergeSort2() {
        String testData = "link=abc(20)\nlink=bca(7)\nlink=ccc(10)\nlink=ccdc(3)\nlink=dsd(8)\nlink=dd(4)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);

        int[] emptyArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        doc1.iterativeMergeSort(emptyArr);
        testScanner.close();
    }

    @Test
    void iterativeMergeSort3() {
        String testData = "link=abc(20)\nlink=bca(7)\nlink=ccc(10)\nlink=ccdc(3)\nlink=dsd(8)\nlink=dd(4)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);

        int[] emptyArr = new int[]{20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        doc1.iterativeMergeSort(emptyArr);
        testScanner.close();
    }

    @Test
    void iterativeMergeSort4() {
        String testData = "link=abc(20)\nlink=bca(7)\nlink=ccc(10)\nlink=ccdc(3)\nlink=dsd(8)\nlink=dd(4)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);

        int[] emptyArr = new int[]{};
        doc1.iterativeMergeSort(emptyArr);

        emptyArr = new int[]{1};
        doc1.iterativeMergeSort(emptyArr);

        emptyArr = new int[]{1, 1, 1};
        doc1.iterativeMergeSort(emptyArr);
        testScanner.close();

        emptyArr = new int[]{0, 0, 1};
        doc1.iterativeMergeSort(emptyArr);
        testScanner.close();

        emptyArr = new int[]{1, 0, 0};
        doc1.iterativeMergeSort(emptyArr);
        testScanner.close();
    }

    @Test
    void radixSort() {
        String testData = "link=abc(20)\nlink=bca(7)\nlink=ccc(10)\nlink=ccdc(3)\nlink=dsd(8)\nlink=dd(4)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);
        doc1.radixSort(doc1.getWeights());
        testScanner.close();
    }

    @Test
    void radixSort2() {
        String testData = "link=abc(2000)\nlink=bca(200)\nlink=ccc(20)\nlink=ccdc(2)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);
        doc1.radixSort(doc1.getWeights());
        testScanner.close();
    }

    @Test
    void radixSort3() {
        String testData = "link=abc(2000)\nlink=bca(200)\nlink=ccc(20)\nlink=ccdc(2)\nlink=daad\neod";
        ByteArrayInputStream in = new ByteArrayInputStream(testData.getBytes());
        Scanner testScanner = new Scanner(in);
        testScanner.useDelimiter("\n");
        Document doc1 = new Document("doc1", testScanner);

        int[] emptyArr = new int[]{0, 0, 1};
        doc1.radixSort(emptyArr);

        emptyArr = new int[]{0, 0, 0};
        doc1.radixSort(emptyArr);

        emptyArr = new int[]{};
        doc1.radixSort(emptyArr);

        emptyArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        doc1.radixSort(emptyArr);

        emptyArr = new int[]{999, 888, 100, 10, 1};
        doc1.radixSort(emptyArr);

        testScanner.close();
    }
}