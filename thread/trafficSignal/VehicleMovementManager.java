package thread.trafficSignal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will calculate the lane to which a vehicle will go after the crossing the signal point
 * 
 * @author Anil Chowdhury
 * Created on 9/10/2019
 */
class VehicleMovementManager {

    private static VehicleMovementManager instance = new VehicleMovementManager();
    private Map<Integer, Lane> laneIdToLane = new HashMap<>();

    private VehicleMovementManager() {
    }

    static VehicleMovementManager getInstance() {
        return instance;
    }

    void initialize(List<Lane> lanes) {
        for (Lane lane : lanes) {
            laneIdToLane.put(lane.getLaneId(), lane);
        }
    }


    Lane getLaneToMoveTo(Vehicle vehicle) {
        int laneId = vehicle.getCurrentLane().getLaneId();
        int newLaneId;

        switch (vehicle.getDirection()) {
            case LEFT:
                newLaneId = laneId + 1;
                break;

            case STRAIGHT:
                newLaneId = laneId + 3;
                newLaneId = newLaneId > 8 ? newLaneId % 8 : newLaneId;
                break;

            case RIGHT:
                newLaneId = laneId + 5;
                newLaneId = newLaneId > 8 ? newLaneId % 8 : newLaneId;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + vehicle.getDirection());
        }
        return laneIdToLane.get(newLaneId);
    }

    Lane getLane(int laneId) {
        for (Integer key : laneIdToLane.keySet()) {
            if (key == laneId) {
                return laneIdToLane.get(key);
            }
        }
        return null;
    }
}
