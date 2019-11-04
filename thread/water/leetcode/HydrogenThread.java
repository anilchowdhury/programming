package thread.water.leetcode;

/**
 * @author Anil Chowdhury
 * Created on 9/2/2019
 */
public class HydrogenThread extends Thread {

    private H2OController controller;
    private Runnable runnable;

    HydrogenThread(H2OController controller, Runnable runnable) {
        this.controller = controller;
        this.runnable = runnable;
    }

    public void run() {
        try {
            controller.hydrogen(runnable);
        } catch (InterruptedException ignored) {
        }
    }
}
