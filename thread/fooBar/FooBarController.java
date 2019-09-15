package thread.fooBar;

import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
class FooBarController {

    private int n;
    private Semaphore fooPermit = new Semaphore(1);
    private Semaphore barPermit = new Semaphore(0);

    FooBarController(int n) {
        this.n = n;
    }

    void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooPermit.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barPermit.release(1);
        }
    }

    void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barPermit.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooPermit.release(1);
        }
    }
}
