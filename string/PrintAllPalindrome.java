package string;

import java.util.*;

import static java.util.Collections.emptySet;

/**
 * Print all palindrome permutations of a string
 *
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class PrintAllPalindrome {

    public static void main(String[] args) {
        PrintAllPalindrome driver = new PrintAllPalindrome();
        System.out.println(driver.printAllPalindrome("noon"));
        System.out.println(driver.printAllPalindrome("level"));
        System.out.println(driver.printAllPalindrome("abcabcc"));
        System.out.println(driver.printAllPalindrome("aabbcadad"));
    }

    private Set<String> printAllPalindrome(String input) {
        Set<String> result = new HashSet<>();
        if (isEmpty(input)) {
            return result;
        }

        int length = input.length();
        if (length == 1) {
            result.add(input);
        } else if (length == 2) {
            result.add(input.charAt(0) == input.charAt(1) ? input : "");
        } else {
            return generateAllPalindrome(input);
        }
        return result;
    }

    private Set<String> generateAllPalindrome(String input) {
        Map<Character, Integer> characterCount = new HashMap<>();
        Set<String> results = new HashSet<>();
        int oddCount = 0;
        char oddChar = 0;
        for (char c : input.toCharArray()) {
            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
            oddCount = characterCount.get(c) % 2 == 1 ? oddCount + 1 : oddCount - 1;
        }
        if (oddCount > 1) {
            return emptySet();
        }

        StringBuilder leftHalf = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : characterCount.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                oddChar = entry.getKey();
            }
            append(leftHalf, entry.getKey(), entry.getValue() / 2);
        }

        String leftPartOfPalindrome = leftHalf.toString();
        Set<String> allPermutation = new StringPermutation().generateAllPermutation(leftPartOfPalindrome);

        for (String left : allPermutation) {
            results.add(left + (oddCount == 1 ? String.valueOf(oddChar) : "") + reverse(left));
        }
        return results;
    }

    private String reverse(String input) {
        if (isEmpty(input) || input.length() == 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int startIndex = 0, endIndex = input.length() - 1;
        while (startIndex < endIndex) {
            char temp = array[startIndex];
            array[startIndex++] = array[endIndex];
            array[endIndex--] = temp;
        }
        return String.valueOf(array);
    }

    private void append(StringBuilder builder, char c, int numberOfTimes) {
        for (int index = 0; index < numberOfTimes; index++) {
            builder.append(c);
        }
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
