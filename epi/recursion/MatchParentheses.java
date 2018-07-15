package epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anchowdh on 11/14/2017.
 *
 */
public class MatchParentheses {
    public static void main(String[] args) {
        generateParentheses(2);
    }

    private static void generateParentheses(int numPairs) {
        List<String> result = new ArrayList<>();
        generateHelper(numPairs, numPairs, "", result);
        System.out.println(Arrays.toString(result.toArray()));
    }

    private static void generateHelper(int leftCount, int rightCount, String prefix, List<String> result) {
        if (leftCount == 0 && rightCount == 0) {
            result.add(prefix);
            return;
        }

        if (leftCount > 0) {
            generateHelper(leftCount - 1, rightCount, prefix + "(", result);
        }
        if (leftCount < rightCount) {
            generateHelper(leftCount, rightCount - 1, prefix + ")", result);
        }
    }
}
