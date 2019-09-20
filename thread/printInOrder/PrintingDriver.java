package thread.printInOrder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 *
 * The same instance of Foo will be passed to three different threads. Thread A will call first(),
 * thread B will call second(), and thread C will call third(). Design a mechanism and modify the
 * program to ensure that second() is executed after first(), and third() is executed after second().
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: "firstsecondthird"
 * Explanation: There are three threads being fired asynchronously. The input [1,2,3] means
 * thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird"
 * is the correct output.
 *
 * Example 2:
 * Input: [1,3,2]
 * Output: "firstsecondthird"
 * Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and
 * thread C calls second(). "firstsecondthird" is the correct output.
 *
 * @author Anil Chowdhury
 * Created on 9/3/2019
 *
 */
public class PrintingDriver {
    public static void main(String[] args) {
        test("123");
        test("321");
        test("213");
        test("132");
    }

    private static void test(String input) {
        List<Thread> orderThreads = new ArrayList<>();
        PrintInOrder controller = new PrintInOrder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '1':
                    orderThreads.add(new FirstThread(controller, new FirstRunnable()));
                    break;
                case '2':
                    orderThreads.add(new SecondThread(controller, new SecondRunnable()));
                    break;
                case '3':
                    orderThreads.add(new ThirdThread(controller, new ThirdRunnable()));
                    break;
                default:
                    break;
            }
        }
        orderThreads.forEach(Thread::start);
        orderThreads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println();
    }
}
