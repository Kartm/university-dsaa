package dsaa.lab_12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher {

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> shifts = new LinkedList<>();
        char[] textArr = text.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int[][] transitionFunction = computeTransitionFunction(patternArr);

        int q = 0;

        for (int i = 0; i < textArr.length; i++) {
            q = transitionFunction[q][textArr[i]];
            if (q == patternArr.length) {
                shifts.add(i - patternArr.length);
            }
        }

        return shifts;
    }

    // assume codes from 0 to 225
    public int[][] computeTransitionFunction(char[] patternArr) {
        int[][] transitionFunction = new int[226][226];

        for(int q = 0; q < patternArr.length; q++) {
            for(int a = 0; a < 226; a++) { // assumed input alphabet
                int k = Math.min(patternArr.length + 1, q + 2);

                String pattern = String.copyValueOf(patternArr);
                // Pk = the substring of pattern ending at k
                String Pk = pattern.substring(0, k - 1);
                String Pqa = pattern.substring(0, q) + (char)a;

                while(!isSuffix(Pk, Pqa)) {
                    k--;

                    Pk = pattern.substring(0, k);
                    Pqa = pattern.substring(0, q) + (char)a;
                    /*
                    It then decreases k until Pk ⊐ Pqa, which must eventually occur,
                    since P0 D " is a suffix of every string
                    */


                }

                transitionFunction[q][a] = k;
            }
        }

        return transitionFunction;
    }

    private boolean isSuffix(String smallString, String fullString) {
        return fullString.endsWith(smallString);
    }
}