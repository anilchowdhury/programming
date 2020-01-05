package javaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 10/23/2018.
 */
public class CollectionTest {

    public static void main(String[] args) {
        CollectionTest test = new CollectionTest();
        test.testNCopies();
    }

    private void testNCopies() {
        List<Integer> integers = Collections.nCopies(5, 2);
        System.out.println(integers.toString());

        /* throws UnSupportedOperation
        integers.add(6);
        System.out.println(integers.toString());
        */

        ArrayList<Integer> list = new ArrayList<>();
//        list.addAll(Collections.nCopies(5, 2).toArray(new Integer[1]));
        System.out.println(list);
    }
}
