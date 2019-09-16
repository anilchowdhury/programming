package thread.zeroOddEven;

import java.util.function.IntConsumer;

/**
 * Suppose you are given the following code:
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer. Modify the given
 * program to output the series 010203040506... where the length of the series must be 2n.
 *
 * Example 1:
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(),
 * the other calls even(), and the last one calls odd(). "0102" is the correct output.
 *
 * Example 2:
 * Input: n = 5
 * Output: "0102030405"
 *
 *
 * @author Anil Chowdhury
 * Created on 9/3/2019
 *
 */
public class ZeroOddEvenDriver {
    public static void main(String[] args) {
        test(5);
    }

    private static void test(int input) {
        IntConsumer printNumber = System.out::print;
        ZeroOddEven controller = getController(input, 0);
        Thread zero = new Thread(() -> {
            try {
                controller.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread odd = new Thread(() -> {
            try {
                controller.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread even = new Thread(() -> {
            try {
                controller.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        zero.start();
        odd.start();
        even.start();
        try {
            zero.join();
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ZeroOddEven getController(int input, int type) {
        ZeroOddEven controller = null;
        switch (type) {
            case 0:
                controller = new WaitAndNotify(input);
                break;
            case 1:
                controller = new WaitAndNotifyOptimized(input);
                break;
            case 2:
                controller = new SemaphorePrinter(input);
                break;
            default:
                break;

        }
        return controller;
    }
}
