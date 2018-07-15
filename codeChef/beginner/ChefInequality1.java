package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 Input:
 10
 2 4 6 9
 2 5 5 8
 2 6 4 8
 2 5 2 7
 2 6 2 6
 2 6 2 5
 3 8 1 4
 5 9 2 5
 7 9 1 5
 4 2 8 5

 Output:
 12
 15
 19
 14
 10
 6
 1
 0
 0
 0

 *
 */
class ChefInequality1 {
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
                System.out.println(solve(a, b, c, d));
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static long solve(int a, int b, int c, int d) {
        long result = 0;
        for (int i = a; i <= b; i++) {
            if (i < d) {
                result += d - i;
                if (c > i) {
                    result -= c - i - 1;
                }
            }
        }
        return result;
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
