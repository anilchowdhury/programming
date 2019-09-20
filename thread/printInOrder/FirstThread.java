package thread.printInOrder;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class FirstThread extends Thread {
    private PrintInOrder orderPrinter;
    private Runnable runnable;

    FirstThread(PrintInOrder orderPrinter, Runnable runnable) {
        this.orderPrinter = orderPrinter;
        this.runnable = runnable;
    }

    public void run() {
        try {
            orderPrinter.first(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
