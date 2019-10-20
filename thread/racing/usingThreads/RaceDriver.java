package thread.racing.usingThreads;


import java.util.concurrent.CountDownLatch;

/**
 *
 * Multiple racers are participating in a race on a given track
 * Display the racers at the end in order in which they finish the race
 * Display the time for each racers they took to cover the track
 *
 * Consider each racer as a Thread
 *
 * @author Anil Chowdhury
 * Created on 9/24/2019
 */
public class RaceDriver {

    public static void main(String[] args) {
        race(10, 1000);
    }

    private static void race(int numberOfRacers, int raceTrackLength) {
        CountDownLatch waitForRaceToFinish = new CountDownLatch(numberOfRacers);
        RaceMonitor raceMonitor = new RaceMonitor(numberOfRacers, raceTrackLength, waitForRaceToFinish);
        raceMonitor.createRacer();
        raceMonitor.signalStartOfRace();
        try {
            waitForRaceToFinish.await();
            raceMonitor.displayWinners();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
