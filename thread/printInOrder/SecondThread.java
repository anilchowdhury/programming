package thread.printInOrder;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class SecondThread extends Thread{
    private PrintInOrder orderPrinter;
    private Runnable runnable;

    SecondThread(PrintInOrder orderPrinter, Runnable runnable) {
        this.orderPrinter = orderPrinter;
        this.runnable = runnable;
    }

    public void run() {
        try {
            orderPrinter.second(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
