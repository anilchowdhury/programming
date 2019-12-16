package oldNotes;

import java.util.Arrays;

/**
 * Given an array of integer & target sum, find how many pair{array[i], array[j]}
 * can be formed such that sum of array[i] + array[j] < sum
 *
 * @author Anil Chowdhury
 *         Created on 1/20/2019.
 */
public class PairWithLessSum {

    public static void main(String[] args){
        PairWithLessSum driver = new PairWithLessSum();
        System.out.println(driver.getPairsCount(new int[]{5, 1, 12, 3, 6}, 10)); //5
        System.out.println(driver.getPairsCount(new int[]{3, 1, 10, 11, 7, 9}, 7)); //1
        System.out.println(driver.getPairsCount(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 7)); //6

    }

    private int getPairsCount(int[] array, int sum) {
        if (array == null || array.length < 2) {
            return 0;
        }

        int pairCount = 0;
        int left = 0;
        int right = array.length - 1;

        Arrays.sort(array);
        while (left < right) {
            if (array[left] + array[right] >= sum) {
                right--;
            } else {
                pairCount += right - left;
                left++;
            }
        }
        return pairCount;
    }

    /*private int getPairsCount(int[] array, int sum) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int pairCount = 0;
        Arrays.sort(array);
        for (int i = 0, j = array.length - 1; i < j; ) {
            if (array[i] + array[j] <= sum) {
                pairCount += j - i;
                i++;
                j--;
            } else {
                j--;
            }
        }
        return pairCount;
    }*/
}

