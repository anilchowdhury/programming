package thread.racing.usingCallable;

/**
 * @author Anil Chowdhury
 * Created on 9/23/2019
 */
class CallableRacerData {

    private String racerName;
    private long timeToCompleteTheRace;

    CallableRacerData(String racerName, long timeToCompleteTheRace) {
        this.racerName = racerName;
        this.timeToCompleteTheRace = timeToCompleteTheRace;
    }

    String getRacerName() {
        return racerName;
    }

    long getTimeToCompleteTheRace() {
        return timeToCompleteTheRace;
    }
}
