package javaTest;

/**
 * @author Anil Chowdhury
 *         Created on 11/28/2018.
 */
public class ArrayTest {
    public static void main(String[] args) {
        ArrayTest driver = new ArrayTest();
        driver.test();
    }

    private void test() {
//        arrayTest();
        array2DTest();
    }

    private void arrayTest() {
        int[] arr = new int[5];
        arr[1] = 5;
        System.out.println(arr.toString());
        System.out.println(int[][].class.getName());
        System.out.println(String[][].class.getName());
    }

    private void array2DTest() {
        int[][] array = {{1, 2},{3, 4}};
        System.out.println(array[0][0]);
        modify(array);
        System.out.println(array[0][0]);
    }

    private void modify(int[][] array) {
        array[0][0] = 5;
    }

    private void primitiveArrayNullTest() {
        Integer[] interWrapper = new Integer[5];
        int[] primitiveIntArray = new int[5];

        interWrapper[0] = null; // this is fine
//      primitiveIntArray[0] = null;//this is not allowed
    }
}
