package javaTest;

import java.util.ArrayList;

/**
 * @author Anil Chowdhury
 * Created on 11/10/2019
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(2);
        temp.add(3);
        temp.add(4);

        result.add(temp);

        temp.clear();
        temp.add(5);
        temp.add(6);
        result.add(temp);

        result.forEach(t -> t.forEach(System.out::println));
    }
}
