package string;

/**
 * Next higher palindromic number using the same set of digits
 *
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class NextHighestPalindrome {

    public static void main(String[] args) {
        NextHighestPalindrome driver = new NextHighestPalindrome();
        System.out.println(driver.getNextHighestPalindrome("4697557964"));
        System.out.println(driver.getNextHighestPalindrome("aabcbaa"));
        System.out.println(driver.getNextHighestPalindrome("ava"));
    }

    private String getNextHighestPalindrome(String input) {
        if (isEmpty(input)) {
            return "";
        }
        int midIndex = input.length() / 2;
        String firstHalf = input.substring(0, midIndex);
        firstHalf = new NextHighestPermutation().nextPermute(firstHalf);
        return firstHalf + (input.length() % 2 == 1 ? input.charAt(midIndex) : "")
                + new StringBuilder(firstHalf).reverse().toString();
    }

    private boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}


