package dsaa.lab_12;

import dsaa.lab_8.Link;

import java.util.LinkedList;

public class KMP implements IStringMatcher {

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> shifts = new LinkedList<>();

        char[] textArr = text.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int[] prefixFunction = calculatePrefixFunction(patternArr);

        int q = 0;

        for(int i = 0; i < text.length(); i++) {
            while(q > 0 && patternArr[q + 1] != textArr[i]) {
                q = prefixFunction[q];
            }
            if(patternArr[q] == textArr[i]) { // patternArr[q + 1] == textArr[i]
                q++;
            }
            if(q == patternArr.length) {
                shifts.add(i-patternArr.length);
                q = prefixFunction[q];
            }
        }

        return shifts;
    }

    private int[] calculatePrefixFunction(char[] patternArr) {
        int[] prefix = new int[patternArr.length];
        prefix[0] = 0;

        int k = 0;

        for(int q = 1; q < patternArr.length; q++) {
            while(k > 0 && patternArr[k + 1] != patternArr[q]) {
                k = prefix[k];
            }
            if(patternArr[k] == patternArr[q]) {
                k++;
            }
            prefix[q] = k;
        }

        return prefix;
    }
}