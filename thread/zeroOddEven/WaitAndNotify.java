package thread.zeroOddEven;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
class WaitAndNotify implements ZeroOddEven {
    private int input;
    private AtomicBoolean zeroTurn = new AtomicBoolean(true);
    private AtomicInteger current = new AtomicInteger();
    private final Object oddLock = new Object();
    private final Object evenLock = new Object();

    WaitAndNotify(int input) {
        this.input = input;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (current.get() < input) {
            while (!zeroTurn.get()) {
                synchronized (this) {
                    wait();
                }
            }
            printNumber.accept(0);
            if (current.get() == 0) {
                current.addAndGet(1);
            }
            zeroTurn.compareAndSet(true, false);
            if (isOdd()) {
                synchronized (oddLock) {
                    oddLock.notify();
                }
            } else {
                synchronized (evenLock) {
                    evenLock.notify();
                }
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (current.get() < input) {
            synchronized (oddLock) {
                while (!isOdd() || current.get() == 0) {
                    oddLock.wait();
                }
                printNumber.accept(current.get());
                synchronized (this) {
                    current.addAndGet(1);
                    notify();
                    zeroTurn.compareAndSet(false, true);
                }
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (current.get() < input) {
            synchronized (evenLock) {
                while (isOdd() || current.get() == 0) {
                    evenLock.wait();
                }
                printNumber.accept(current.get());
                synchronized (this) {
                    current.addAndGet(1);
                    notify();
                    zeroTurn.compareAndSet(false, true);
                }
            }
        }
    }

    private boolean isOdd() {
        return ((current.get() & 1) == 1);
    }
}
