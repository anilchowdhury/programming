package thread.printInOrder;

import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/2/2019
 */
class PrintInOrder {

    private Semaphore secondRunPermit = new Semaphore(0);
    private Semaphore thirdRunPermit = new Semaphore(0);

    PrintInOrder() {
    }

    void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        secondRunPermit.release(1);
    }

    void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        secondRunPermit.acquire();
        printSecond.run();
        thirdRunPermit.release(1);
    }

    void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        thirdRunPermit.acquire();
        printThird.run();
    }
}
