package thread.trafficSignal;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Anil Chowdhury
 * Created on 9/9/2019
 */
public class TrafficDriver {

    public static void main(String[] args) {
        testTraffic(50);
    }

    private static void testTraffic(int numberOfVehicles) {
        TrafficDriver driver = new TrafficDriver();

        List<Lane> lanes = driver.getLanes();
        VehicleMovementManager.getInstance().initialize(lanes);

        List<Vehicle> vehicles = driver.getVehicles(numberOfVehicles);
        driver.distributeVehiclesOnLanes(vehicles);

        List<LaneController> laneControllers = driver.getLaneController();
        TrafficManager trafficManager = new TrafficManager(laneControllers);
        trafficManager.start();

        driver.waitForShutdown(vehicles, laneControllers, trafficManager);
    }

    private List<Lane> getLanes() {
        List<Lane> lanes = new ArrayList<>();
        IntStream.range(1, 9).forEach(index -> lanes.add(new Lane(String.format("Lane %d", index), index)));
        return lanes;
    }

    private List<Vehicle> getVehicles(int numberOfVehicles) {
        List<Vehicle> vehicles = new ArrayList<>();
        IntStream.range(1, numberOfVehicles + 1).forEach(index -> vehicles.add(
                new Vehicle(String.format("Vehicle %d", index), getRandomDirection())));
        Collections.shuffle(vehicles);
        return vehicles;
    }

    private List<LaneController> getLaneController() {
        List<LaneController> controllers = new ArrayList<>();
        AtomicInteger laneId = new AtomicInteger(-1);
        IntStream.range(1, 5).forEach(index -> {
            laneId.addAndGet(2);
            Lane lane = VehicleMovementManager.getInstance().getLane(laneId.get());
            controllers.add(new LaneController(String.format("Controller %d", index), lane));
        });
        return controllers;
    }

    private void distributeVehiclesOnLanes(List<Vehicle> vehicles) {
        Map<Integer, Lane> oddLanes = new HashMap<>();
        oddLanes.put(1, VehicleMovementManager.getInstance().getLane(1));
        oddLanes.put(2, VehicleMovementManager.getInstance().getLane(3));
        oddLanes.put(3, VehicleMovementManager.getInstance().getLane(5));
        oddLanes.put(4, VehicleMovementManager.getInstance().getLane(7));

        Random random = new Random();
        for (Vehicle v : vehicles) {
            int key = random.nextInt(4) + 1;
            Lane lane = oddLanes.get(key);
            lane.moveVehicle(v);
        }
    }

    private void waitForShutdown(List<Vehicle> vehicles, List<LaneController> laneControllers,
                                 TrafficManager trafficManager) {
        vehicles.forEach(v -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        laneControllers.forEach(lc -> {
            try {
                lc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            trafficManager.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Direction getRandomDirection() {
        Random random = new Random();
        int directionIndex = random.nextInt(3);
        return Direction.values()[directionIndex];
    }
}
