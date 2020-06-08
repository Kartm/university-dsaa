package dsaa.lab_12;

import java.util.Scanner;

public class LinesReader {
    String concatLines(int howMany, Scanner scanner) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < howMany; i++) {
            result.append(scanner.nextLine());
        }
        return result.toString();
    }
}
