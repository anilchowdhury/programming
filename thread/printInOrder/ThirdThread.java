package thread.printInOrder;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class ThirdThread extends Thread {
    private PrintInOrder orderPrinter;
    private Runnable runnable;

    ThirdThread(PrintInOrder orderPrinter, Runnable runnable) {
        this.orderPrinter = orderPrinter;
        this.runnable = runnable;
    }

    public void run() {
        try {
            orderPrinter.third(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
