package thread.trafficSignal;

import java.util.List;

/**
 *
 *
 *                      |           |           |
 *                      |     /|\               |
 *                      |      |    |           |
 *                      |      |
 *                      |  Lane 4      Lane5    |
 *                      |           |    |      |
 *                      |                |      |
 *                      |           |   \|/     |
 * ---------------------                        ---------------------
 *
 *      Lane 3 ---->                                 Lane 6 ---->
 *
 * -    -   -   -   -   -  - [Traffic Manager] -  -  -  -   -    -   -
 *
 *      <--- Lane 2                                  <--- Lane 7
 *
 * ---------------------                         ---------------------
 *                      |           |           |
 *                      |     /|\               |
 *                      |      |    |           |
 *                      |      |                |
 *                      |  Lane 1      Lane 8   |
 *                      |           |    |      |
 *                      |                |      |
 *                      |           |   \|/     |
 *                      |           |           |
 *
 *  Vehicle in lane 1 can go to {Lane 2, Lane 4, Lane 6}
 *  Vehicle in lane 3 can go to {Lane 4, Lane 6, Lane 8}
 *  Vehicle in lane 5 can go to {Lane 6, Lane 8, Lane 2}
 *  Vehicle in lane 7 can go to {Lane 8, Lane 2, Lane 4}
 *
 * @author Anil Chowdhury
 * Created on 9/9/2019
 */
public class TrafficManager extends Thread {

    private List<LaneController> laneControllers;
    private volatile boolean shutdown;

    TrafficManager(List<LaneController> laneControllers) {
        this.laneControllers = laneControllers;
    }

    @Override
    public void run() {
        while (!shutdown) {
            if (allVehiclesHasPassed()) {
                laneControllers.forEach(LaneController::shutdown);
                shutdown();
            } else {
                allowTraffics();
            }
        }
    }

    private void allowTraffics() {
        for (LaneController controller : laneControllers) {
            if (shutdown) {
                return;
            }
            System.out.println(String.format(
                    "******  Allowing traffic through Lane controller - [%s]  ******", controller.getName()));

            controller.allowTraffic();
            waitForTrafficToPass();
            controller.stopTraffic();

            System.out.println(String.format(
                    "******  Stopping traffic through Lane controller - [%s]  ******\n", controller.getName()));
        }
    }

    private void waitForTrafficToPass() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        laneControllers.forEach(LaneController::start);
        super.start();
    }

    private boolean allVehiclesHasPassed() {
        for (LaneController controller : laneControllers) {
            if (controller.hasAnyVehicle()) {
                return false;
            }
        }
        return true;
    }

    private void shutdown() {
        this.shutdown = true;
        System.out.println("Shutting down traffic manager");
    }
}
