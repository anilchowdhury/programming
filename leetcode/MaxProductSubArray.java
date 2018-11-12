package leetcode;

/**
 * @author Anil Chowdhury
 *         Created on 8/7/2018.
 */
public class MaxProductSubArray {

    public static void main(String[] args) {
        MaxProductSubArray driver = new MaxProductSubArray();
        int[] array1 = {6, -3, -10, 0, 2};
        int[] array2 = {-1, -3, -10, 0, 60};
        int[] array3 = {-2, -3, 0, -2, -40};
        int[] array4 = {0, -4, 0, -2};
        int[] array5 = {2, 3, -2, 4};
        int[] array6 = {2, -1, 1, 1};

        System.out.println(driver.maxProduct(array1));
        System.out.println(driver.maxProduct(array2));
        System.out.println(driver.maxProduct(array3));
        System.out.println(driver.maxProduct(array4));
        System.out.println(driver.maxProduct(array5));
        System.out.println(driver.maxProduct(array6));
    }

    private int maxProduct(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int iMax = array[0], iMin = array[0], result = array[0];
        for (int index = 1; index < array.length; index++) {
            if (array[index] < 0) {
                int temp = iMax;
                iMax = iMin;
                iMin = temp;
            }

            iMax = Math.max(array[index], array[index] * iMax);
            iMin = Math.min(array[index], array[index] * iMin);
            result = Math.max(result, iMax);
        }
        return result;
    }
}

