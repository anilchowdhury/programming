package oldNotes;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anil Chowdhury
 *         Created on 5/16/2018.
 */
public class AllCombinationsOfElements {

    public static void main(String[] args) {
        String input = "12345";
        printAllCombinations(input, 3);
    }

    private static void printAllCombinations(String input, int size) {
        char[] chars = input.toCharArray();
        if (chars.length == size) {
            System.out.println(input);
            return;
        }
        Set<String> result = new HashSet<>();
        printCombinationHelper(chars, 0, chars.length - 1, size, result, new char[size], 0);
        result.forEach(System.out::println);
    }

    private static void printCombinationHelper(char[] array, int startIndex, int endIndex, int size,
                                               Set<String> result, char[] temp, int tempIndex) {
        if (size == 0) {
            result.add(String.valueOf(temp));
            return;
        }

        for (int i = startIndex; i <= (endIndex - size + 1); i++) {
            temp[tempIndex] = array[i];
            printCombinationHelper(array, i + 1, endIndex, size - 1, result, temp, tempIndex + 1);
        }
    }
}


