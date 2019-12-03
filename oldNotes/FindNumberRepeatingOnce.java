package oldNotes;

/**
 * @author Anil Chowdhury
 *         Created on 1/17/2019.
 *
 * Given an array where every element occurs three times, except one element which occurs only once. Find the element
 * that occurs once. Expected time complexity is O(n) and O(1) extra space.
 * Examples:
 * Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
 * Output: 2
 *
 */
public class FindNumberRepeatingOnce {

    public static void main(String[] args) {
        FindNumberRepeatingOnce driver = new FindNumberRepeatingOnce();
        System.out.println(driver.getResult(new int[]{1, 1, 1, 3, 3, 3, 20, 4, 4, 4})); //20
        System.out.println(driver.getResult(new int[]{2, 1, 4, 5, 1, 4, 2, 2, 4, 1})); //5
        System.out.println(driver.getResult(new int[]{12, 1, 12, 3, 12, 1, 1, 2, 3, 3})); //2
        System.out.println(driver.getResult(new int[]{4, 8, 4, 4})); //8
        System.out.println(driver.getResult(new int[]{4, 5, 5, 7, 4, 4, 5})); //7
    }

    private int getResult(int[] array) {
        int ones = 0;
        int twos = 0;
        int not_threes;
        int x;

        for (int anArray : array) {
            x = anArray;
            twos |= ones & x;
            ones ^= x;

            // if the number has been seen 3 times, then its bits will be
            // set in both ones & twos. Since, we are not interested in
            // these types of numbers (which are appearing 3 times),
            // we erase them from both ones & twos by disabling the bits
            // present in these numbers
            // Negate the set bits in these numbers and disable them from
            // ones & twos by taking bitwise AND with the negation

            not_threes = ~(ones & twos);
            ones &= not_threes;
            twos &= not_threes;
            System.out.println("Ones = " + ones + "; Twos = " + twos);
        }
        return ones;
    }
}
