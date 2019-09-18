package thread.oddEven;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class OddEvenWaitNotify {

    public static void main(String[] args) {
        test(5);
        test(8);
    }

    private static void test(int input) {
        OddEvenWaitNotify driver = new OddEvenWaitNotify();
        AtomicInteger counter = new AtomicInteger(1);
        Odd odd = driver.new Odd(input, counter);
        Even even = driver.new Even(input, counter);
        odd.start();
        even.start();
        try {
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Odd extends Thread {
        private int input;
        private final AtomicInteger counter;

        Odd(int input, AtomicInteger counter) {
            this.input = input;
            this.counter = counter;
        }

        public void run() {
            while (counter.get() < input) {
                synchronized (counter) {
                    while (counter.get() % 2 == 0) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Odd -> " + counter.get());
                    counter.addAndGet(1);
                    counter.notify();
                }
            }
        }
    }


    private class Even extends Thread {
        private int input;
        private final AtomicInteger counter;

        Even(int input, AtomicInteger counter) {
            this.input = input;
            this.counter = counter;
        }

        public void run() {
            while (counter.get() < input) {
                synchronized (counter) {
                    while (counter.get() % 2 == 1) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Even -> " + counter.get());
                    counter.addAndGet(1);
                    counter.notify();
                }
            }
        }
    }
}
