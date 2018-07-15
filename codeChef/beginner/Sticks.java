package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 Chef and his little brother are playing with sticks. They have total N sticks. Length of i-th stick is Ai. Chef asks
 his brother to choose any four sticks and to make a rectangle with those sticks its sides. Chef warns his brother to
 not to break any of the sticks, he has to use sticks as a whole. Also, he wants that the rectangle formed should have
 the maximum possible area among all the rectangles that Chef's brother can make.

 Chef's little brother takes this challenge up and overcomes it. Can you also do so? That is, you have to tell whether
 it is even possible to create a rectangle? If yes, then you have to tell the maximum possible area of rectangle.

 Input
 -----
 The first line contains a single integer T denoting the number of test-cases. T test cases follow.
 The first line of each test case contains a single integer N denoting the number of sticks.
 The second line of each test case contains N space-separated integers A1, A2, ..., AN denoting the lengths of sticks.

 Output
 ------
 For each test case, output a single line containing an integer representing the maximum possible area for rectangle
 or -1 if it's impossible to form any rectangle using the available sticks.

 Constraints
 -----------
 1 ≤ T ≤ 100
 1 ≤ N ≤ 10^3
 1 ≤ sum of N's over all test-cases in a single test file ≤ 10^3
 1 ≤ Ai ≤ 10^3

 Example
 Input:
 2
 5
 1 2 3 1 2
 4
 1 2 2 3

 Output:
 2
 -1

 */
class Sticks {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int numOfTest = Integer.parseInt(getNextLine(reader));
            int counter = 0;
            while (counter < numOfTest) {
                int numOfStick = Integer.parseInt(getNextLine(reader));
                String[] input = getNextLine(reader).split("\\s+");
                System.out.println(solve(numOfStick, input));
                counter++;
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int solve(int numOfStick, String[] input) throws CodeChefException {
        if (input.length != numOfStick) {
            throw new CodeChefException("Invalid input");
        }
        return calculateMaxArea(input);
    }

    private static int calculateMaxArea(String[] input) {
        if (input.length < 4) {
            return -1;
        }
        int maxArea = -1;
        Map<Integer, Integer> stickLengthCountMap = getLengthCountMap(input);
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(stickLengthCountMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //Sort in descending order
                return Integer.compare(o2.getKey(), o1.getKey());
            }
        });

        int sideA = -1, sideB = -1;
        for (Map.Entry<Integer, Integer> entry : entries) {
            int sideLength = entry.getKey();
            int count = entry.getValue();

            if (count >= 4) {
                if (!isInitialized(sideA)) {
                    sideA = sideLength;
                    sideB = sideLength;
                } else {
                    sideB = sideLength;
                }
            } else if (count >= 2) {
                if (!isInitialized(sideA)) {
                    sideA = sideLength;
                } else {
                    sideB = sideLength;
                }
            }

            if (isInitialized(sideA) && isInitialized(sideB)) {
                break;
            }
        }

        if (isInitialized(sideA) && isInitialized(sideB)) {
            //calculate if both sides are initialized
            maxArea = sideA * sideB;
        }
        return maxArea;
    }

    private static boolean isInitialized(int sideLength) {
        return sideLength > 0;
    }

    private static Map<Integer, Integer> getLengthCountMap(String[] input) {
        Map<Integer, Integer> stickLengthCountMap = new HashMap<>();

        for (String lengthInput : input) {
            int length = Integer.parseInt(lengthInput);
            if (stickLengthCountMap.get(length) == null) {
                stickLengthCountMap.put(length, 1);
            } else {
                int currentCount = stickLengthCountMap.get(length);
                stickLengthCountMap.put(length, currentCount + 1);
            }
        }
        return stickLengthCountMap;
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
