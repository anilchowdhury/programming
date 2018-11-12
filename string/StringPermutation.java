package string;

import java.util.*;

/**
 * Print all permutations of a given string
 *
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class StringPermutation {

    public static void main(String[] args) {
        StringPermutation driver = new StringPermutation();
//      System.out.println(driver.generateAllPermutation("abc"));
        System.out.println(driver.permute("abc"));
    }

    //Approach1
    Set<String> generateAllPermutation(String input) {
        if (isEmpty(input)) {
            return Collections.emptySet();
        }
        Set<String> result = new HashSet<>();
        generateAllPermutation(input, result, 0, input.length() - 1);
        return result;
    }

    private void generateAllPermutation(String input, Set<String> result, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            result.add(input);
        }
        for (int index = startIndex; index <= endIndex; index++) {
            input = swap(input, startIndex, index);
            generateAllPermutation(input, result, startIndex + 1, endIndex);
            input = swap(input, startIndex, index);
        }
    }

    private String swap(String input, int firstIndex, int secondIndex) {
        if (isEmpty(input) || !isInRange(input.length(), firstIndex)
                || !isInRange(input.length(), secondIndex)) {
            return input;
        }
        char[] array = input.toCharArray();
        char temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        return String.valueOf(array);
    }

    private boolean isInRange(int length, int index) {
        return (0 <= index) && (index < length);
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }

    //Approach2
    List<String> permute(String input) {
        if (isEmpty(input)) {
            return Collections.emptyList();
        }
        String prefix = "";
        List<String> result = new ArrayList<>();
        permuteHelper(prefix, input, result);
        System.out.println(result.size());
        return result;
    }

    private void permuteHelper(String prefix, String input, List<String> result) {
        if (input.length() == 0) {
//          System.out.println("\t Adding to result " +  prefix);
            result.add(prefix);
        }

        for (int i = 0; i < input.length(); i++) {
            System.out.println("\t next prefix : " + prefix + input.charAt(i));
            System.out.println("\t next string : " + input.substring(0, i) + input.substring(i + 1, input.length()));
            permuteHelper(prefix + input.charAt(i), input.substring(0, i) + input.substring(i + 1, input.length()), result);
        }
    }
}

