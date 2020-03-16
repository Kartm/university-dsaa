package dsaa.lab_1;

import java.util.Scanner;

public class L1e1 {

    static Scanner scan;

    private static void drawPyramid(int n) {
        int currWhitespaceLength = n - 1;
        int currCrossLength = 1;
        while (currCrossLength <= n * 2 - 1) {
            for (int i = 0; i < currWhitespaceLength; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < currCrossLength; i++) {
                System.out.print("X");
            }
            System.out.print('\n');
            currWhitespaceLength--;
            currCrossLength += 2;
        }
    }

    private static void drawPyramid(int n, int padding) {
        int currWhitespaceLength = n - 1 + padding;
        int currCrossLength = 1;
        while (currCrossLength <= n * 2 - 1) {
            for (int i = 0; i < currWhitespaceLength; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < currCrossLength; i++) {
                System.out.print("X");
            }
            System.out.print('\n');
            currWhitespaceLength--;
            currCrossLength += 2;
        }
    }

    private static void drawChristmassTree(int n) {
        for (int i = 1; i <= n; i++) {
            drawPyramid(i, n - i);
        }
    }

    /***
     * commands: py size draw a pyramid with size ct size draw a christmas tree
     * with size ld documentName load document from standard input line by line.
     * Last line consists of only sequence "eod", which means end of document ha
     * halt program and finish execution
     *
     * @param args
     */

    public static void main(String[] args) {
        System.out.println("START");
        scan = new Scanner(System.in);
        boolean halt = false;
        while (!halt) {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);

            String word[] = line.split(" ");

            if (word[0].equalsIgnoreCase("py") && word.length == 2) {
                int value = Integer.parseInt(word[1]);
                drawPyramid(value);
                continue;
            }
            if (word[0].equalsIgnoreCase("ct") && word.length == 2) {
                int value = Integer.parseInt(word[1]);
                drawChristmassTree(value);
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }

            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }

}