package dsaa.lab_12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher {
    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();
        char[] patternArr = pattern.toCharArray();
        char[] textArr = text.toCharArray();
        int[][] transitionFunction = computeTransitionFunction(patternArr);

        int state = 0;
        for (int i = 0; i < text.length(); i++) {
            state = transitionFunction[state][textArr[i]];

            if (state == pattern.length()) {
                result.add((i - pattern.length() + 1));
            }
        }
        return result;
    }

    private int[][] computeTransitionFunction(char[] patternArr) {
        int[][] transitionFunction = new int[patternArr.length + 1][225];

        for (int state = 0; state <= patternArr.length; ++state) {
            for (int character = 0; character < 225; ++character) {
                transitionFunction[state][character] = calculateTransition(patternArr, state, character);
            }
        }

        return transitionFunction;
    }

    private static int calculateTransition(char[] patternArr, int state, int character) {
        if (state < patternArr.length && character == patternArr[state]) {
            return state + 1;
        }

        for (int nextState = state; nextState > 0; nextState--) {
            if (patternArr[nextState - 1] == character) {
                int i = 0;
                while (i < nextState - 1) {
                    if (patternArr[i] != patternArr[state - nextState + 1 + i]) {
                        break;
                    }
                    i++;
                }
                if (i == nextState - 1) {
                    return nextState;
                }
            }
        }
        return 0;
    }
}
