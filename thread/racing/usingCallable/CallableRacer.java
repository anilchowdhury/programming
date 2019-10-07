package thread.racing.usingCallable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/23/2019
 */
public class CallableRacer implements Callable<CallableRacerData> {

    private String name;
    private int raceLaneLength;
    private Semaphore racePermit;

    CallableRacer(String name, int raceLaneLength, Semaphore racePermit) {
        this.name = name;
        this.raceLaneLength = raceLaneLength;
        this.racePermit = racePermit;
    }

    @Override
    public CallableRacerData call() throws Exception {
        waitForRaceSignal();
        return startRacing();
    }

    private CallableRacerData startRacing() {
        long raceStartTime = System.currentTimeMillis();

        for (int index = 0; index < raceLaneLength; index++) {
            if (isHalfWay(index)) {
                System.out.println(String.format("%s has reached half way in the race", name));
            }
            sleep();
        }
        long raceEndTime = System.currentTimeMillis();
        return new CallableRacerData(name, raceEndTime - raceStartTime);
    }

    private void sleep() {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForRaceSignal() throws InterruptedException {
        racePermit.acquire();
    }

    private boolean isHalfWay(int index) {
        return (raceLaneLength / 2) == index;
    }
}
