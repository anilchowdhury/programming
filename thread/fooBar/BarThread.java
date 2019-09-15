package thread.fooBar;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class BarThread extends Thread {
    private FooBarController controller;
    private Runnable runnable;

    BarThread(FooBarController controller, Runnable runnable) {
        this.controller = controller;
        this.runnable = runnable;
    }

    public void run() {
        try {
            controller.bar(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
