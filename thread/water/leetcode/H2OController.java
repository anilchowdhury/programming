package thread.water.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anil Chowdhury
 * Created on 9/2/2019
 */

class H2OController {

    private Semaphore hydrogenPermit = new Semaphore(2);
    private Semaphore oxygenPermit = new Semaphore(1);
    private AtomicInteger count = new AtomicInteger();

    H2OController() {
    }

    void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hydrogenPermit.acquire();
        count.addAndGet(1);
        releaseHydrogen.run();
        releasePermits();
    }

    void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        oxygenPermit.acquire();
        count.addAndGet(1);
        releaseOxygen.run();
        releasePermits();
    }

    private void releasePermits() {
        if (count.get() == 3) {
            count.compareAndSet(3, 0);
            hydrogenPermit.release(2);
            oxygenPermit.release(1);
        }
    }
}





