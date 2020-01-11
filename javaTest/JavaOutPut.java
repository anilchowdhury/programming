package javaTest;

import java.util.*;

/**
 * @author Anil Chowdhury
 *         Created on 8/11/2018.
 */

public class JavaOutPut {

    public static void main(String[] args) {
        JavaOutPut driver = new JavaOutPut();

//        finallyTest(driver);
//        typeCastingNullTest();
//        genericObjectCollectionTest();
//        hashingNewElementTest();

//        integerMaxMinTest();
//        equalityTest();
//        List<LinkedList<Integer>> adjacencyList= new ArrayList<>();

//        joinerTest();
        mapEntryToStringTest();

    }

    private static void mapEntryToStringTest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");

        map.entrySet().forEach(System.out :: println);
    }

    private static void joinerTest() {
        String[] array = {"5", "4", "6", "7", "8", "9"};
        System.out.println(String.join("::", Arrays.asList(array)));
    }

    private static void equalityTest() {
        int x = 5;
        int y = 5;

        if(x > y) {
            System.out.println("X is greater");
        }

        if(x < y) {
            System.out.println("X is smaller");
        }
    }

    private static void integerMaxMinTest() {
        int integerMax = Integer.MAX_VALUE;
        int integerMin = Integer.MIN_VALUE;
        System.out.println("Integer.MAX -> " + integerMax);
        //this will append 1 as character
        System.out.println("Adding 1 to Integer.MAX -> " + integerMax + 1);
        System.out.println("Adding 1 to Integer.MAX -> " + (integerMax + 1));

        System.out.println("Integer.MIN -> " + integerMin);
        //this does not work, as we cannot perform subtraction on string
        //System.out.println("Subtracting 1 from Integer.MIN -> " + integerMin - 1);
        System.out.println("Subtracting 1 from Integer.MIN -> " + (integerMin - 1));
    }

    private static void hashingNewElementTest() {
        System.out.println("Hashing test");
        Set<Integer> test = new HashSet<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.forEach(x -> System.out.print(x + " "));

        test.remove(new Integer(2));
        test.forEach(x -> System.out.print(x + " "));
    }

    private static void genericObjectCollectionTest() {
        Set<Object> s = new HashSet<>();
        s.add("a");
        s.add(2);
    }

    private static void typeCastingNullTest() {
        System.out.println((String[])null);
    }

    private static void finallyTest(JavaOutPut driver) {
        System.out.println(driver.finallyAndReturn());
    }

    private int finallyAndReturn() {
        int x = 8;
        try {
            return x + 1;
        } finally {
            System.out.println(x + 2);
        }
    }
}
