package javaTest;

import java.util.*;

/**
 * @author Anil Chowdhury
 *         Created on 8/4/2018.
 */
public class BasicLibraryTesting {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        String s = "Australia";
        int[] array = new int[s.length()];
//      array[s.charAt(1)] = 5;
        char c = 'f' + 2;
        System.out.println(c);

        /*stringTest();
        arrayListTest();
        linkedListTest();
        primitiveTest();*/
        mathTest();

    }

    private static void mathTest() {
        System.out.println(Math.floorDiv(5, 2));
        System.out.println(Math.floorDiv(9, 3));
        System.out.println(Math.floorMod(8, 3));
        System.out.println(Math.log(2));
        System.out.println(Math.log10(100));
        System.out.println(Math.log10(125));

        LinkedHashSet<Integer> t = new LinkedHashSet<>();

    }

    private static void primitiveTest() {
        Integer x = 11;
        System.out.println(x.byteValue());
        System.out.println(Integer.bitCount(15));
        System.out.println(Integer.highestOneBit(5));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toOctalString(15));
        System.out.println(Integer.toHexString(26));
    }

    private static void linkedListTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(4);
        list.forEach(System.out::println);

        list.addFirst(2);
        list.forEach(System.out::println);

//      Deque a = (Deque) list;

        /*Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }*/
        /*System.out.println(list.element());
        System.out.println(list.element());
        System.out.println(list.element());*/
//      list.forEach();
        Iterator<Integer> dItr = list.descendingIterator();
        while(dItr.hasNext()){
            System.out.println(dItr.next());
        }
    }

    private static void stringTest() {
        String str = "Australia";
        System.out.println(new String(str.getBytes(), 2, 3));
    }

    private static void arrayListTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator itr = list.iterator();

        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }

        ListIterator<Integer> listIterator = list.listIterator();
        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        ListIterator<Integer> listIteratorIndex = list.listIterator(2);
        while(listIteratorIndex.hasNext()) {
            System.out.println(listIteratorIndex.next());
        }

        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());
    }
}
