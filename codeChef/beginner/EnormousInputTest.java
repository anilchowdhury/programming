package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 The purpose of this problem is to verify whether the method you are using to read input data is sufficiently fast to
 handle problems branded with the enormous Input/Output warning. You are expected to be able to process at least
 2.5MB of input data per second at runtime.

 Input
 -----
 The input begins with two positive integers n k (n, k<=107). The next n lines of input contain one positive integer ti,
 not greater than 109, each.

 Output
 ------
 Write a single integer to output, denoting how many integers ti are divisible by k.

 Example
 Input:
 7 3
 1
 51
 966369
 7
 9
 999996
 11

 Output:
 4

 *
 */
class EnormousInputTest {

    public static void main(String[] args) {
        System.out.println(getTotalCount());
    }

    private static int getTotalCount() {

        int i = 0, n, k, count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = getNextLine(reader);
        if (isEmpty(nextLine)) {
            return 0;
        }
        String[] split = nextLine.split("\\s");
        if (split.length == 0) {
            return 0;
        }
        n = Integer.parseInt(split[0]);
        k = Integer.parseInt(split[1]);

        while (i < n) {
            nextLine = getNextLine(reader);
            if (isEmpty(nextLine)) {
                break;
            }
            int nextNum = Integer.parseInt(nextLine);
            if (nextNum % k == 0) {
                count++;
            }
            i++;
        }
        return count;
    }

    private static boolean isEmpty(String nextLine) {
        return nextLine == null || nextLine.isEmpty();
    }

    private static String getNextLine(BufferedReader reader) {
        String nextLine = null;
        try {
            nextLine = reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nextLine;
    }
}
