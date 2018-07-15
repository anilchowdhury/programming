package codeChef;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Anil Chowdhury
 *         Created on 7/9/2018.
 */
public class NumbersWithUniqueDigits {

    public static void main(String[] args) {
      System.out.println(countNumbersWithUniqueDigitsInPowerOf10(4));
      System.out.println(countNumbersWithUniqueDigitsInRange(0, 10000));
    }

    public static int countNumbersWithUniqueDigitsInPowerOf10(int n) {
        int totalCount = 10;
        if (n == 1) {
            return totalCount;
        }

        for (int x = 2; x <= n; x++) {
            totalCount = totalCount + getUniqueCount(x);
        }

        return totalCount;
    }

    private static int getUniqueCount(int x) {
        int uniqueCount = 9;
        int multiplier = 9;
        while (x > 1) {
            uniqueCount = uniqueCount * multiplier;
            multiplier--;
            x--;
        }
        return uniqueCount;
    }

    public static int countNumbersWithUniqueDigitsInRange(int start, int end) {
        Map<String, Boolean> result = new HashMap<>();
        int totalCount = 0;
        for (int number = start; number <= end; number++) {
            String s = String.valueOf(number);
            if (s.length() == 1) {
                result.put(s, true);
                totalCount++;
                continue;
            }

            if (result.get(s.substring(1)) == null || result.get(s.substring(1))) {
                if (allUniqueDigits(s)) {
                    result.put(s, true);
                    totalCount++;
                } else {
                    result.put(s, false);
                }
            } else {
                result.put(s, false);
            }
        }
        return totalCount;
    }

    private static boolean allUniqueDigits(String s) {
        int i = 0;
        Set<Character> unique = new HashSet<>();

        while (i < s.length()) {
            if (unique.contains(s.charAt(i))) {
                return false;
            } else {
                unique.add(s.charAt(i));
            }
            i++;
        }
        return unique.size() == s.length();
    }

    private static int getEnd(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * 10;
        }
        return result;
    }
}
