package javaTest;

/**
 * @author Anil Chowdhury
 *         Created on 10/14/2018.
 */
public class SystemExitTest {

    public static void main(String[] args) {
        SystemExitTest test = new SystemExitTest();
        test.test();
        test.test1();
    }

    private int test() {
        try {
            System.out.println("In try block");
            return 1;
        } finally {
            System.out.println("In finally block");
        }
    }

    private void test1() {
        try {
            System.out.println("In try block");
            System.exit(0);
        } finally {
            System.out.println("In finally block");
        }
    }
}


