package thread.racing.usingThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Alternatively instead of using ConcurrentSkipListMap, we can also
 * use @{@link java.util.concurrent.ConcurrentLinkedQueue}
 *
 * Each Racer will call {@link RaceMonitor#finishedRace(Racer)} inside which
 * we can add the racer into ConcurrentLinkedQueue
 * Inorder to get the time to complete the race for each racer we can have following options
 * a)Let each Racer store raceCompletion time as internal field and let them set it once they
 *   are done with racing
 *
 * b)Create a inner class which will store the raceCompletionTime and Racer reference. Add this
 *   class instance in ConcurrentLinkedQueue. Race completion time can be calculated in the same
 *   way as its done in below implementation
 *
 * @author Anil Chowdhury
 * Created on 9/23/2019
 */
class RaceMonitor {

    private int numberOfRacers;
    private int raceTrackLength;
    private Semaphore racerPermit;
    private ConcurrentSkipListMap<Long, Racer> finishedRacer;
    private long raceStartTime;
    private CountDownLatch waitingLatch;

    RaceMonitor(int numberOfRacers, int raceTrackLength, CountDownLatch waitingLatch) {
        this.numberOfRacers = numberOfRacers;
        this.raceTrackLength = raceTrackLength;
        racerPermit = new Semaphore(0);
        finishedRacer = new ConcurrentSkipListMap<>();
        this.waitingLatch = waitingLatch;
    }

    void createRacer() {
        createRacers(numberOfRacers).forEach(Thread::start);
    }

    void signalStartOfRace() {
        System.out.println(String.format("Starting %d meters race with %d racers ... \n",
                raceTrackLength, numberOfRacers));
        raceStartTime = System.currentTimeMillis();
        racerPermit.release(numberOfRacers);
    }

    void waitForRaceSignal() throws InterruptedException {
        racerPermit.acquire();
    }

    void finishedRace(Racer racer) {
        long raceCompletionTime = System.currentTimeMillis() - raceStartTime;
        finishedRacer.put(raceCompletionTime, racer);
    }

    void markRaceAsCompleted() {
        waitingLatch.countDown();
    }

    void displayWinners() {
        Set<Map.Entry<Long, Racer>> entries = finishedRacer.entrySet();
        int winnerIndex = 0;
        int numberOfRacersToDisplay = 3;
        System.out.println("\n-----------------------------  WINNERS  -----------------------------");
        for (Map.Entry<Long, Racer> entry : entries) {
            displayWinner(getWinnerPosition(++winnerIndex), entry.getKey(), entry.getValue());
            if (winnerIndex == numberOfRacersToDisplay) {
                System.out.println("---------------------------------------------------------------------");
                break;
            }
        }
    }

    void monitorRacerProgress(int index, Racer racer) {
        if (isHalfWay(index)) {
            System.out.println(String.format("%s has reached half way in the race. Time = %d milliseconds",
                    racer.getName(), System.currentTimeMillis() - raceStartTime));
        }
    }

    int getRaceTrackLength() {
        return raceTrackLength;
    }

    private List<Racer> createRacers(int numberOfRacers) {
        List<Racer> racers = new ArrayList<>();
        for (int index = 1; index <= numberOfRacers; index++) {
            racers.add(new Racer(getName(index), this));
        }
        return racers;
    }

    private String getName(int index) {
        return String.format("Racer-%s%d", index < 10 ? "0" : "", index);
    }

    private void displayWinner(String winnerPosition, long raceCompletionTime, Racer racer) {
        assert racer != null;
        System.out.println(String.format("%s = %s. Time to complete the race = %d milliseconds",
                winnerPosition, racer.getName(), raceCompletionTime));
    }

    private boolean isHalfWay(int index) {
        return (raceTrackLength / 2) == index;
    }

    private String getWinnerPosition(int winnerIndex) {
        if (winnerIndex == 1) {
            return "1st prize";
        } else if (winnerIndex == 2) {
            return "2nd prize";
        } else if (winnerIndex == 3) {
            return "3rd prize";
        }
        return "";
    }
}
