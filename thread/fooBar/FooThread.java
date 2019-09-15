package thread.fooBar;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class FooThread extends Thread{

    private FooBarController controller;
    private Runnable runnable;

    FooThread(FooBarController controller, Runnable runnable) {
        this.controller = controller;
        this.runnable = runnable;
    }

    public void run() {
        try {
            controller.foo(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
