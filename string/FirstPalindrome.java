package string;

import java.util.*;

/**
 * Print lexicographically first palindromic string
 *
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class FirstPalindrome {

    public static void main(String[] args) {
        FirstPalindrome driver = new FirstPalindrome();
        System.out.println(driver.generateFirstPalindrome("malayalam"));
        System.out.println(driver.generateFirstPalindrome("baaab"));
    }

    private String generateFirstPalindrome(String input) {
        if (isEmpty(input)) {
            return "";
        }
        int length = input.length();
        if (length == 1) {
            return input;
        } else if (length == 2) {
            return input.charAt(0) == input.charAt(1) ? input : "";
        } else {
            return generate(input);
        }
    }

    //tree map is used to ensure that keys are return in sorted order
    private String generate(String input) {
        Map<Character, Integer> characterCount = new TreeMap<>();
        int oddCount = 0;
        char oddChar = 0;
        for (char c : input.toCharArray()) {
            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
            oddCount = characterCount.get(c) % 2 == 1 ? oddCount + 1 : oddCount - 1;
        }
        if (oddCount > 1) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (char c : characterCount.keySet()) {
            int count = characterCount.get(c);
            if (count % 2 == 1) {
                oddChar = c;
            }
            append(builder, c, characterCount.get(c) / 2);
        }
        return builder.toString() + (oddCount == 1 ? oddChar : "") + builder.reverse().toString();
    }

    private void append(StringBuilder builder, char input, int count) {
        while (count > 0) {
            builder.append(input);
            count--;
        }
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
