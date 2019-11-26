package thread.trafficSignal;

import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/9/2019
 */
public class LaneController extends Thread {

    private Lane laneToControl;
    private Semaphore allowTrafficPermit; // to change the signal to green on receiving message from Traffic Manager
    private volatile boolean stopTraffic;
    private volatile boolean shutdown;

    LaneController(String controllerName, Lane lanesToControl) {
        super(controllerName);
        this.laneToControl = lanesToControl;
        this.allowTrafficPermit = new Semaphore(0);
        this.stopTraffic = true;
        this.shutdown = false;
    }

    @Override
    public void run() {
        while (!shutdown) {
            try {
                allowTrafficPermit.acquire();
                while (!stopTraffic) {
                    allowTrafficToPass();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("Exiting controller %s", getName()));
    }

    public void start() {
        laneToControl.start();
        System.out.println(String.format("%s is handling traffic size = %d", getName(), laneToControl.trafficSize()));
        super.start();
    }

    void allowTraffic() {
        allowTrafficPermit.release(1);
        stopTraffic = false;
    }

    void stopTraffic() {
        stopTraffic = true;
        laneToControl.stopAllVehicles();
    }

    void shutdown() {
        stopTraffic = true;
        shutdown = true;
        // this is done to ensure if the controller which was waiting
        // for its turn should exist from the loop gracefully
        allowTrafficPermit.release(1);
    }

    private void allowTrafficToPass() {
        try {
            Thread.sleep(1000);
            laneToControl.allowVehicles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean hasAnyVehicle() {
        return laneToControl.hasAnyVehicle();
    }
}
