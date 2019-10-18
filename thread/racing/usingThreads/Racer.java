package thread.racing.usingThreads;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Anil Chowdhury
 * Created on 9/23/2019
 */
public class Racer extends Thread {

    private RaceMonitor raceMonitor;

    Racer(String name, RaceMonitor raceMonitor) {
        super(name);
        this.raceMonitor = raceMonitor;
    }

    public void run() {
        try {
            raceMonitor.waitForRaceSignal();
            startRacing();
            raceMonitor.finishedRace(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            raceMonitor.markRaceAsCompleted();
        }
    }

    private void startRacing() {
        IntStream.rangeClosed(1, raceMonitor.getRaceTrackLength()).forEach(index -> {
            raceMonitor.monitorRacerProgress(index, this);
            sleep();
        });
    }

    private void sleep() {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
