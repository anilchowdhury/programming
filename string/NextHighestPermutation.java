package string;

/**
 * Print next highest permutation of the given string
 *
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class NextHighestPermutation {

    public static void main(String[] args) {
        NextHighestPermutation driver = new NextHighestPermutation();
        System.out.println(driver.nextPermute("1121"));
        System.out.println(driver.nextPermute("14253"));
        System.out.println(driver.nextPermute("51432"));
        System.out.println(driver.nextPermute("54632"));
        System.out.println(driver.nextPermute("54321"));
    }

    String nextPermute(String input) {
        if (isEmpty(input) || input.length() == 1) {
            return input;
        }
        int firstCharIndex = getFirstCharIndex(input);
        if (firstCharIndex == -1) {
            return "";
        }
        int secondCharIndex = getSecondCharIndex(input, firstCharIndex);
        input = String.valueOf(swap(input.toCharArray(), firstCharIndex, secondCharIndex));
        input = reverse(input, firstCharIndex + 1, input.length() - 1);
        return input;
    }

    //get rightmost character index which is smaller then its next character
    private int getFirstCharIndex(String input) {
        int length = input.length();
        int firstCharIndex = -1;
        for (int index = length - 1; index > 0; index--) {
            if (input.charAt(index - 1) < input.charAt(index)) {
                firstCharIndex = index - 1;
                break;
            }
        }
        return firstCharIndex;
    }

    //get the smallest character index which is greater than first character
    //between first character index & length of the string
    private int getSecondCharIndex(String input, int firstCharIndex) {
        int length = input.length();
        int secondCharIndex = firstCharIndex + 1;
        int index = firstCharIndex + 1;
        while (index < length) {
            if (input.charAt(firstCharIndex) < input.charAt(index)) {
                if (input.charAt(secondCharIndex) > input.charAt(index)) {
                    secondCharIndex = index;
                }
            }
            index++;
        }
        return secondCharIndex;
    }

    private String reverse(String input, int firstIndex, int secondIndex) {
        char[] array = input.toCharArray();
        while (firstIndex < secondIndex) {
            swap(array, firstIndex++, secondIndex--);
        }
        return String.valueOf(array);
    }

    private char[] swap(char[] input, int firstIndex, int secondIndex) {
        if ((firstIndex >= secondIndex) || !isInRange(input.length, firstIndex)
                || !isInRange(input.length, secondIndex)) {
            return input;
        }
        char temp = input[firstIndex];
        input[firstIndex] = input[secondIndex];
        input[secondIndex] = temp;
        return input;
    }

    private boolean isInRange(int length, int index) {
        return (0 <= index) && (index < length);
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
