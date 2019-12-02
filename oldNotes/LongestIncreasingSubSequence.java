package oldNotes;

/**
 * @author Anil Chowdhury
 * Created on 11/21/2019
 */
public class LongestIncreasingSubSequence {

    public static void main(String[] args) {
        LongestIncreasingSubSequence driver = new LongestIncreasingSubSequence();

        int[] array = {2, 5, 3};    //2
        int[] array1 = {2, 4, 6, 7, 8 };   //5
        int[] array2 = {10, 22, 9, 33, 21, 50, 41, 60, 80}; //6
        int[] array3 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};  //6

        System.out.println(driver.longestSubSequence(array));
        System.out.println(driver.longestSubSequence(array1));
        System.out.println(driver.longestSubSequence(array2));
        System.out.println(driver.longestSubSequence(array3));
    }

    private int longestSubSequence(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        return longestSubSequence(array, array.length);
    }

    private int longestSubSequence(int[] array, int length) {
        if (length == 1) {
            return 1;
        }
        int maxLength = 0;

        for (int index = 1; index < length; index++) {
            int result = longestSubSequence(array, index);
            if (array[length - 1] > array[index - 1]) {
                result += 1;
                maxLength = Math.max(maxLength, result);
            }
        }
        return maxLength;
    }
}
