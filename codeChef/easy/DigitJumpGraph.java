package codeChef.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 Chef loves games! But he likes to invent his own. Now he plays game "Digit Jump". Chef has sequence of digits
 S1, S2,..., SN,. He is staying in the first digit (S1) and want to reach the last digit (SN) in the minimal
 number of jumps.

 While staying in some digit x with index i (digit Si) Chef can jump into digits with indices i - 1 (Si-1) and
 i + 1 (Si+1) but he can't jump out from sequence. Or he can jump into any digit with the same value x.

 Help Chef to find the minimal number of jumps he need to reach digit SN from digit S1.

 Input
 -----
 Input contains a single line consist of string S of length N- the sequence of digits.

 Output
 ------
 In a single line print single integer - the minimal number of jumps he needs.

 Constraints
 -----------
 1 ≤ N ≤ 10^5
 Each symbol of S is a digit from 0 to 9.


 Example
 -------
 Input:
 01234567890

 Output:
 1

 Input:
 012134444444443

 Output:
 4
 */
class DigitJumpGraph {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inputString = getNextLine(reader);
            assert (inputString != null && !inputString.isEmpty());
            System.out.println(findMinimumJump(inputString));
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int findMinimumJump(String inputString) {
        int inputLength = inputString.length();
        if (inputLength <= 1) {
            return 0;
        }
        if (inputLength == 2) {
            return 1;
        }
        boolean[][] graph = new boolean[inputLength][inputLength];
        buildGraph(inputString, graph);

        boolean[] visited = new boolean[inputLength];
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> startingPoint = new ArrayList<>();
        startingPoint.add(0);
        queue.add(startingPoint);
        int minJump = 0;

        while (!queue.isEmpty()) {
            List<Integer> temp = queue.remove();
            List<Integer> childEntry = new ArrayList<>();

            for (int index : temp) {
                if (!visited[index]) {
                    for (int y = 0; y < inputLength; y++) {
                        if (graph[index][y]) {
                            childEntry.add(y);
                        }
                    }
                    visited[index] = true;
                }
            }
            minJump++;
            if (childEntry.contains(inputLength - 1)) {
                break;
            }
            queue.add(childEntry);
        }
        return minJump;
    }

    private static void buildGraph(String inputString, boolean[][] graph) {
        Map<Character, Integer> firstLocationFoundMap = new HashMap<>();
        firstLocationFoundMap.put(inputString.charAt(0), 0);
        for (int index = 1; index < inputString.length(); index++) {
            char currDigit = inputString.charAt(index);
            graph[index - 1][index] = true;
            graph[index][index - 1] = true;
            if (firstLocationFoundMap.containsKey(currDigit)) {
                graph[firstLocationFoundMap.get(currDigit)][index] = true;
            }else{
                firstLocationFoundMap.put(currDigit, index);
            }
        }
    }

    private static String getNextLine(BufferedReader reader) throws CodeChefException {
        String nextLine;
        try {
            nextLine = reader.readLine().trim();
        } catch (IOException ex) {
            throw new CodeChefException(ex.getMessage());
        }
        return nextLine;
    }

    private static class CodeChefException extends Exception {
        CodeChefException(String message) {
            super(message);
        }
    }
}
