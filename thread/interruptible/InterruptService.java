package thread.interruptible;

/**
 * @author Anil Chowdhury
 * Created on 9/29/2019
 */
public class InterruptService implements Runnable {

    private String name;

    InterruptService(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(String.format("%s is running ... ", name));
                InterruptDriver.sleep();
            } catch (InterruptedException ignore) {
                //if we are not calling sleep(), then we do not need to again call interrupt
                //the first interrupt that is received from ServiceStopper is used for
                //interrupting this thread from sleep
                //Try running the program after commenting the below line
                //We will see in some cases the service will not stop
                //System.out.println(Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(String.format("%s is STOPPED ... ", name));
    }

    public String getName() {
        return name;
    }
}
