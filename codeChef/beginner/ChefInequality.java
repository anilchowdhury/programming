package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Chef likes inequalities. Please help him to solve next one.
 Given four integers a, b, c, d. Find number of solutions x < y, where a ≤ x ≤ b and c ≤ y ≤ d and x, y integers.

 Input
 -----
 The first line contains an integer T denoting number of tests.
 First line of each test case contains four positive integer numbers a, b, c and d.

 Output
 ------
 For each test case, output a single number each in separate line denoting number of integer solutions as asked in the problem.

 Constraints
 -----------
 1 ≤ T ≤ 20
 1 ≤ a, b, c, d ≤ 10^6

 Subtasks
 --------
 Subtask #1: (30 points) 1 ≤ a, b, c, d ≤ 103.
 Subtask #2: (70 points) Original constraints.
 Example

 Input:
 1
 2 3 3 4

 Output:
 3

 Input:
 1
 2 999999 1 1000000

 Output:
 499998500001
 */
class ChefInequality {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int numOfTest = Integer.parseInt(getNextLine(reader));
            for (int i = 0; i < numOfTest; i++) {
                String[] inputs = getNextLine(reader).split("\\s");
                int a = Integer.parseInt(inputs[0]);
                int b = Integer.parseInt(inputs[1]);
                int c = Integer.parseInt(inputs[2]);
                int d = Integer.parseInt(inputs[3]);
                System.out.println(solve(new Range(a, b), new Range(c, d)));
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static long solve(Range xRange, Range yRange) {
        long result;
        if ((xRange.left > yRange.right) || (xRange.left > xRange.right) || (yRange.left > yRange.right)) {
            //           a....b
            // c....d
            return 0;
        }

        if (xRange.left > yRange.left) {
            //     a....b
            // c..........d
            yRange.setLeft(xRange.left);
        }

        if (xRange.right > yRange.right) {
            //       a.........b
            // c..........d
            xRange.setRight(yRange.right);
        }

        if (isDisjointRange(xRange, yRange)) {
            // a....b .... c....d
            result = getDisjointRangeResult(xRange, yRange);
        } else if (xRange.right == yRange.left) {
            // a....b
            //      c....d
            result = getDisjointRangeResult(xRange, yRange) - 1;
        } else if (equalRange(xRange, yRange)) {
            // a....b
            // c....d
            result = getEqualRangeResult(xRange);
        } else if (xRange.left == yRange.left) {
            // a.....b
            // c.........d
            Range subYRangeRight = new Range(xRange.right + 1, yRange.right);
            result = getEqualRangeResult(xRange) + getDisjointRangeResult(xRange, subYRangeRight);
        } else if (xRange.right == yRange.right) {
            // a........b
            //     c....d
            Range subXRangeLeft = new Range(xRange.left, yRange.left - 1);
            result = getDisjointRangeResult(subXRangeLeft, yRange) + getEqualRangeResult(yRange);
        } else {
            // a........b
            //     c........d
            Range subXRangeLeft = new Range(xRange.left, yRange.left - 1);
            Range subXRangeRight = new Range(yRange.left, xRange.right);
            Range subYRangeRight = new Range(xRange.right + 1, yRange.right);
            result = getDisjointRangeResult(subXRangeLeft, yRange) + getEqualRangeResult(subXRangeRight) +
                    getDisjointRangeResult(subXRangeRight, subYRangeRight);
        }
        return result;
    }

    private static long getDisjointRangeResult(Range xRange, Range yRange) {
        return (xRange.right - xRange.left + 1) * (yRange.right - yRange.left + 1);
    }

    private static long getEqualRangeResult(Range range) {
        long n = range.right - range.left;
        return n * (n + 1) / 2;
    }

    private static boolean isDisjointRange(Range xRange, Range yRange) {
        return xRange.right < yRange.left;
    }

    private static boolean equalRange(Range xRange, Range yRange) {
        return (xRange.left == yRange.left) && (xRange.right == yRange.right);
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

    private static class Range {
        int left, right;

        Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        private void setLeft(int left) {
            this.left = left;
        }

        private void setRight(int right) {
            this.right = right;
        }
    }
}
