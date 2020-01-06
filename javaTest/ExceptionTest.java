package javaTest;

import java.io.IOException;

/**
 * @author Anil Chowdhury
 *         Created on 10/10/2018.
 */
public class ExceptionTest {
    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        test.test1();
        test.test2();
        System.out.print(test.test3());
    }

    /**
     * Although m1() is not throwing any exception but we are allowed to catch it
     */
    private void test1() {
        try {
            m1();
        }/* catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } */catch (Exception ex) {
            System.out.println("Exception");
        }
    }

    private void test2() {
        Child c = new Child();
        c.m2();
    }

    private int test3() {
        int x = 8;
        try {
//          return x; // will print 9(for finally) and 8
//          return x++;// will print 10(for finally) and 8
            return ++x;// will print 10(for finally) and 9
        } finally {
            System.out.println(x + 1);
        }
    }

    private void m1() {
        System.out.println("m1");
    }

    private static class Parent {
        void m2() throws IOException {
            System.out.println("m2: Parent");
        }
    }

    private static class Child extends Parent{
//      void m2() throws Exception {
        void m2() { // can remove throws clause from child method
            System.out.println("m2: Child");
        }
    }
}
