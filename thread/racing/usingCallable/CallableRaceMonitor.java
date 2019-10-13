package thread.racing.usingCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Anil Chowdhury
 * Created on 9/23/2019
 */
class CallableRaceMonitor {

    private int numberOfRacers;
    private int raceTrackLength;
    private Semaphore racerPermit;
    private ExecutorService racersPool;
    private ExecutorCompletionService<CallableRacerData> raceAdministrator;

    CallableRaceMonitor(int numberOfRacers, int raceTrackLength) {
        this.numberOfRacers = numberOfRacers;
        this.raceTrackLength = raceTrackLength;
        racerPermit = new Semaphore(0);
        racersPool = Executors.newFixedThreadPool(numberOfRacers);
        raceAdministrator = new ExecutorCompletionService<>(racersPool);
    }

    void createRacer() {
        createRacers(numberOfRacers, raceTrackLength, racerPermit).
                forEach(raceAdministrator::submit);
        racersPool.shutdown();
    }

    void signalStartOfRace() {
        System.out.println(String.format("Starting %d meters race with %d racers ... \n",
                raceTrackLength, numberOfRacers));
        racerPermit.release(numberOfRacers);
    }

    void monitorRaceProgress() {
        int raceCompletion = 1;
        while (raceCompletion <= numberOfRacers) {
            try {
                CallableRacerData racerData = raceAdministrator.take().get();
                System.out.print(raceCompletion++ == 1 ? "\n" : "");
                System.out.println(String.format("%s has finished the race. Total time for completing the race = " +
                        "%d milliseconds", racerData.getRacerName(), racerData.getTimeToCompleteTheRace()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private List<CallableRacer> createRacers(int numberOfRacers, int raceTrackLength, Semaphore racerPermit) {
        List<CallableRacer> callableRacers = new ArrayList<>();
        for (int index = 1; index <= numberOfRacers; index++) {
            callableRacers.add(new CallableRacer(getName(index), raceTrackLength, racerPermit));
        }
        return callableRacers;
    }

    private String getName(int index) {
        return String.format("Racer-%s%d", index < 10 ? "0" : "", index);
    }
}
