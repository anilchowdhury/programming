package javaTest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 1/8/2019.
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        test();
    }


    private static void test() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("C", 0);
        map.put("A", 1);
        map.put("B", 2);

        map.remove("C");
        map.put("C", 0);

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " --> "  + entry.getValue());
            System.out.println();
        }
    }
}
