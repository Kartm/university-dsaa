package dsaa.lab_1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L1e2 {

    static Scanner scan;
    static boolean isReadingDocument = false;
    static ArrayList<String[]> documentLines =new ArrayList<String[]>();

    public static void loadDocument(String name) {
        isReadingDocument = true;
    }

    public static void analyzeDocument() {
        isReadingDocument = false;
        Pattern linkPrefixPattern = Pattern.compile("(link=)", Pattern.CASE_INSENSITIVE);

        for(int i = 0; i < documentLines.size(); i++) {
            for(int j = 0; j < documentLines.get(i).length; j++) {
                String expr = documentLines.get(i)[j];
                Matcher matcher = linkPrefixPattern.matcher(expr);

                // getting first match with 'link=...'
                if(matcher.find() && matcher.group(1).length() > 0) {
                    String rawLink = matcher.replaceAll("");
                    if(correctLink(rawLink)) {
                        System.out.println(rawLink);
                    }
                }

            }
        }
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on
    // the begin)
    private static boolean correctLink(String link) {
        Pattern correctLinkPattern = Pattern.compile("(^[a-zA-Z]+([a-zA-Z]|[0-9]|[_])+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = correctLinkPattern.matcher(link);
        return matcher.matches();
    }

    private static void drawLine(int n, char ch) {

    }

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
            if(!isReadingDocument) {
                System.out.println("!" + line);
            }

            String[] word = line.split(" ");
            if(isReadingDocument) {
                if(word[0].equalsIgnoreCase("eod") && word.length == 1) {
                    analyzeDocument();
                } else {
                    documentLines.add(word);
                }
                continue;
            }
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
            // ld documentName
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                loadDocument(word[1]);
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }

            if(!isReadingDocument) {
                System.out.println("Wrong command");
            }
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }

}