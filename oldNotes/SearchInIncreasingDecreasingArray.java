package oldNotes;

/**
 * @author Anil Chowdhury
 *         Created on 1/22/2019.
 *
 * Find an element in Bitonic array. A Bitonic Sequence is a sequence of numbers which is first strictly
 * increasing then after a point strictly decreasing
 *
 */
public class SearchInIncreasingDecreasingArray {

    public static void main(String[] args) {
        SearchInIncreasingDecreasingArray driver = new SearchInIncreasingDecreasingArray();
        System.out.println(driver.search(new int[]{6, 7, 8, 11, 9, 5, 2, 1}, 9)); // 4
        System.out.println(driver.search(new int[]{-3, 2, 4, 6, 10, 8, 7, 1}, 4)); //2
        System.out.println(driver.search(new int[]{-3, 2, 4, 6, 10, 8, 7, 1}, 15)); //-1
        System.out.println(driver.search(new int[]{-3, 2, 4, 6, 10, 8, 7, 1}, -3)); //0
        System.out.println(driver.search(new int[]{-3, 2, 4, 6, 10, 8, 7, 1}, 5)); //-1
        System.out.println(driver.search(new int[]{8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}, 500)); // 7
    }

    private int search(int[] array, int key) {
        return searchHelper(array, key, 0, array.length - 1);
    }

    private int searchHelper(int[] array, int key, int start, int end) {
        if (start == end) {
            // only single element in the array
            return array[start] == key ? start : -1;
        }
        if (start + 1 == end) {
            // only two elements in the array
            return array[start] == key ? start : (array[end] == key ? end : -1);
        }

        int mid = start + (end - start) / 2;
        if (array[mid] == key) {
            return mid;
        }
        if (isMaxElement(array, mid)) {
            int index = ascendingBinarySearch(array, key, start, mid - 1);
            return index != -1 ? index : descendingBinarySearch(array, key, mid + 1, end);
        }
        if (ascendingPart(array, mid)) {
            //mid is present on the ascending part of the array
            return (key < array[mid]) ? ascendingBinarySearch(array, key, start, mid - 1) :
                    searchHelper(array, key, mid + 1, end);
        } else {
            //mid is present on the descending part of the array
            return (key < array[mid]) ? descendingBinarySearch(array, key, mid + 1, end) :
                    searchHelper(array, key, start, mid - 1);
        }
    }

    private boolean isMaxElement(int[] array, int index) {
        return (array[index - 1] < array[index]) && (array[index] > array[index + 1]);
    }

    private boolean ascendingPart(int[] array, int index) {
        return (array[index - 1] < array[index]) && (array[index] < array[index + 1]);
    }

    private int ascendingBinarySearch(int[] array, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    private int descendingBinarySearch(int[] array, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (array[mid] < key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}


