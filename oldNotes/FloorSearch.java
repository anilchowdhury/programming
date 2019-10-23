package oldNotes;

/**
 * @author Anil Chowdhury
 *         Created on 1/22/2019.
 *
 * Given a sorted array and a value x, the floor of x is the largest element in array smaller than or equal to x.
 * Write efficient functions to find floor of x
 *
 */
public class FloorSearch {
    public static void main(String[] args) {
        FloorSearch driver = new FloorSearch();
        System.out.println(driver.floorSearch(new int[]{8}, 5)); //-1
        System.out.println(driver.floorSearch(new int[]{8}, 10)); //8
        System.out.println(driver.floorSearch(new int[]{3, 7}, 6)); // 3
        System.out.println(driver.floorSearch(new int[]{1, 2, 8, 10, 12, 19}, 15)); //12
        System.out.println(driver.floorSearch(new int[]{1, 2, 8, 10, 12, 19}, 20)); //19
        System.out.println(driver.floorSearch(new int[]{1, 2, 8, 10, 12, 19}, 11)); //10
        System.out.println(driver.floorSearch(new int[]{1, 2, 8, 10, 12, 14, 19}, 14)); //14
        System.out.println(driver.floorSearch(new int[]{1, 2, 8, 10, 22, 25, 29, 33, 35, 45, 55, 100, 125}, 34)); //33
        System.out.println(driver.floorSearch(new int[]{1, 2, 8, 10, 22, 25, 29, 33, 35, 45, 55, 100, 125}, 57)); //55
    }

    private int floorSearch(int[] array, int key) {
        return floorSearchHelper(array, key, 0, array.length - 1);
    }

    private int floorSearchHelper(int[] array, int key, int start, int end) {
        if (array[start] > key) {
            return -1;
        }
        if (array[end] <= key) {
            return array[end];
        }
        int floor = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] <= key) {
                floor = array[mid];
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return floor;
    }
}

