package oldNotes;

/**
 * @author Anil Chowdhury
 *         Created on 1/22/2019.
 * Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x,
 * and the floor is the greatest element smaller than or equal to x. Assume than the array is sorted in
 * non-decreasing order. Write efficient functions to find floor and ceiling of x
 *
 */
public class CeilSearch {

    public static void main(String[] args) {
        CeilSearch driver = new CeilSearch();
        System.out.println(driver.ceilSearch(new int[]{8}, 5)); // 8
        System.out.println(driver.ceilSearch(new int[]{8}, 10)); //-1
        System.out.println(driver.ceilSearch(new int[]{3, 7}, 6)); // 7
        System.out.println(driver.ceilSearch(new int[]{1, 2, 8, 10, 12, 19}, 15)); //19
        System.out.println(driver.ceilSearch(new int[]{1, 2, 8, 10, 12, 19}, 20)); //-1
        System.out.println(driver.ceilSearch(new int[]{1, 2, 8, 10, 12, 19}, 11)); //12
        System.out.println(driver.ceilSearch(new int[]{1, 2, 8, 10, 12, 14, 19}, 14)); //14
        System.out.println(driver.ceilSearch(new int[]{1, 2, 8, 10, 22, 25, 29, 33, 35, 45, 55, 100, 125}, 34)); //35
        System.out.println(driver.ceilSearch(new int[]{1, 2, 8, 10, 22, 25, 29, 33, 35, 45, 55, 100, 125}, 57)); //100
    }

    private int ceilSearch(int[] array, int key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        return ceilSearchHelper(array, key, 0, array.length - 1);
    }

    private int ceilSearchHelper(int[] array, int key, int start, int end) {
        if (array[start] >= key) {
            return array[start];
        }
        if (array[end] < key) {
            return -1;
        }
        int ceil = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] >= key) {
                ceil = array[mid];
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ceil;
    }
}

