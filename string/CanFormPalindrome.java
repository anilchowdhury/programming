package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Check if characters of a given string can be rearranged to form a palindrome
 *
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class CanFormPalindrome {

    public static void main(String[] args) {
        CanFormPalindrome driver = new CanFormPalindrome();
        System.out.println(driver.canFormPalindrome("ava"));
    }

    private boolean canFormPalindrome(String input) {
        if (isEmpty(input)) {
            return true;
        }
        Map<Character, Integer> characterCount = new HashMap<>();
        int oddCount = 0;

        for (char c : input.toCharArray()) {
            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
            oddCount = characterCount.get(c) % 2 == 1 ? oddCount + 1 : oddCount - 1;
        }
        return oddCount <= 1;
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
