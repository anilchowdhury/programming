package javaTest;

/**
 * @author Anil Chowdhury
 *         Created on 2/22/2019.
 */
public class TemperatureSensor {

    public TemperatureSensor() {
        AlarmListener listener = new AlarmListener() {
            @Override
            public void wakeup() {
                check();
            }
        };
    }

    private boolean check() {
        return false;
    }
}
