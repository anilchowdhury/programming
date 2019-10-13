package thread.racing.usingCallable;

/**
 *
 * Multiple racers are participating in a race on a given track
 * Display the racers at the end in order in which they finish the race
 * Display the time for each racers they took to cover the track
 *
 * Consider each racer as a Callable
 *
 * @author Anil Chowdhury
 * Created on 9/23/2019
 */
public class CallableRaceDriver {

    public static void main(String[] args) {
        race(10, 300);
    }

    private static void race(int numberOfRacers, int raceTrackLength) {
        CallableRaceMonitor raceMonitor = new CallableRaceMonitor(numberOfRacers, raceTrackLength);
        raceMonitor.createRacer();
        raceMonitor.signalStartOfRace();
        raceMonitor.monitorRaceProgress();
    }
}
