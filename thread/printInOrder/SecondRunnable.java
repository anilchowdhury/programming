package thread.printInOrder;

/**
 * @author Anil Chowdhury
 * Created on 9/2/2019
 */
public class SecondRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Second");
    }
}
