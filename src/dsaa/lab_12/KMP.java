package dsaa.lab_12;

import java.util.LinkedList;

public class KMP implements IStringMatcher {
    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> shifts = new LinkedList<>();

        if (text == null || pattern == null || pattern.length() > text.length()) {
            return shifts;
        }

        int[] prefixFunction = computePrefixFunction(pattern);

        int textIndex = 0;
        int patternIndex = 0;

        // traverse the whole string
        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }

            if (patternIndex == pattern.length()) {
                shifts.add(textIndex - patternIndex);
                patternIndex = prefixFunction[patternIndex - 1];
            } else {
                if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                    if(patternIndex == 0) {
                        textIndex++;
                    } else {
                        patternIndex = prefixFunction[patternIndex - 1];
                    }
                }
            }
        }

        return shifts;
    }

    int[] computePrefixFunction(String pattern) {
        int[] prefixFunction = new int[pattern.length()];
        int i = 1;
        int length = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                prefixFunction[i] = length;
                i++;
            } else {
                if(length == 0) {
                    i++;
                } else {
                    length = prefixFunction[length - 1];
                }
            }
        }

        return prefixFunction;
    }
}
