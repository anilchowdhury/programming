package thread.trafficSignal;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/9/2019
 */
public class Lane {

    private String name;
    private int laneId;
    private ConcurrentLinkedQueue<Vehicle> vehicles;
    private Semaphore lanePermit; // to control when vehicle can pass through the lane
    private static final int maxVehicleToAllowSimultaneously = 1;

    Lane(String name, int laneId) {
        this.name = name;
        this.laneId = laneId;
        vehicles = new ConcurrentLinkedQueue<>();
        lanePermit = new Semaphore(0);
    }

    void moveVehicle(Vehicle vehicle) {
        if(vehicle.getCurrentLane() != null) {
            vehicle.getCurrentLane().removeVehicle(vehicle);
        }
        vehicles.add(vehicle);
        vehicle.setCurrentLane(this);
    }

    void allowVehicles() {
        lanePermit.release(maxVehicleToAllowSimultaneously);
    }

    void stopAllVehicles() {
        lanePermit.drainPermits();
    }

    public String getName() {
        return name;
    }

    int getLaneId(){
        return laneId;
    }

    public void start() {
        vehicles.forEach(Thread::start);
    }

    private void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    boolean hasAnyVehicle() {
        return vehicles.size() > 0;
    }

    int trafficSize() {
        return vehicles.size();
    }

    void waitForGreenLight() {
        try {
            lanePermit.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
