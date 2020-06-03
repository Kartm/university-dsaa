package dsaa.lab_12;

import java.util.Scanner;

public class LinesReader {
    String concatLines(int howMany, Scanner scanner) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < howMany; i++) {
            stringBuffer.append(scanner.nextLine());
        }
        return stringBuffer.toString();
    }

}