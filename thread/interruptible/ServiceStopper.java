package thread.interruptible;

/**
 * @author Anil Chowdhury
 * Created on 9/29/2019
 */
public class ServiceStopper implements Runnable {

    private int stopDelay;
    private StoppableService service;
    private Thread interruptThread;

    ServiceStopper(int stopDelay, StoppableService service, Thread interruptThread) {
        this.stopDelay = stopDelay;
        this.service = service;
        this.interruptThread = interruptThread;
    }

    @Override
    public void run() {
        try {
            InterruptDriver.sleep(stopDelay);
            System.out.println(String.format("\nINTERRUPTING ... %s\n", interruptThread.getName()));
            interruptThread.interrupt();

            InterruptDriver.sleep(stopDelay);
            System.out.println(String.format("\nSTOPPING ... %s\n", service.getName()));
            service.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
