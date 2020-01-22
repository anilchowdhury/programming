package javaTest;

import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/9/2019
 */
public class ThreadTest {

    public static void main(String[] args) {
        multipleThreadStartAndJoin();
        semaPhoreDrainTest();
    }

    private static void multipleThreadStartAndJoin() {
        Semaphore s = new Semaphore(4);
        Thread t1 = new Thread(() -> {
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, "t2");
        t1.start();
        t2.start();
        /**
         * will throw java.lang.IllegalThreadStateException
         */
//      t1.start();
        try {
            t1.join();
            t2.join();
            /**
             * this is fine
             */
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void semaPhoreDrainTest() {
        Semaphore s = new Semaphore(5);
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s.availablePermits());
            s.drainPermits();
            System.out.println(Thread.currentThread().getName() + " " + s.availablePermits());
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
//                Thread.sleep(1000);
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s.availablePermits());
            s.drainPermits();
            System.out.println(Thread.currentThread().getName() + " " + s.availablePermits());
        }, "t2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
