package thread.interruptible;

/**
 * @author Anil Chowdhury
 * Created on 9/29/2019
 */
public class StoppableService implements Runnable {

    private String name;
    private volatile boolean isStopped = false;

    StoppableService(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                System.out.println(String.format("%s is running ... ", name));
                InterruptDriver.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%s is STOPPED ... ", name));
    }

    void stop() {
        isStopped = true;
    }

    public String getName() {
        return name;
    }
}
