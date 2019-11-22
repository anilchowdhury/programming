package thread.trafficSignal;

/**
 * @author Anil Chowdhury
 * Created on 9/9/2019
 */
public class Vehicle extends Thread {

    private Lane currentLane;
    private Direction direction;

    Vehicle(String name, Direction direction) {
        super(name);
        this.direction = direction;
    }

    @Override
    public void run() {
        currentLane.waitForGreenLight();
        System.out.println(String.format("%s is on lane - [%s]. Direction of vehicle - [%s]", getName(),
                currentLane.getName(), getDirection().name()));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Lane laneToMoveTo = VehicleMovementManager.getInstance().getLaneToMoveTo(this);
        laneToMoveTo.moveVehicle(this);
        System.out.println(String.format("%s is moved to lane - [%s]\n", getName(), currentLane.getName()));
    }

    Lane getCurrentLane() {
        return currentLane;
    }

    void setCurrentLane(Lane currentLane) {
        this.currentLane = currentLane;
    }

    Direction getDirection() {
        return direction;
    }
}
