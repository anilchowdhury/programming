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

            @Override
            public Child overRideTest(Parent parentInstance) {
                return null;
            }

            @Override
            public Child overRideTest1(Parent parentInstance) {
                return null;
            }

            /* This does not work. We can not pass as child instance as argument
               as it will not override
            @Override
            public Child overRideTest1(Child parentInstance) {
                return null;
            }*/
        };
    }

    private boolean check() {
        return false;
    }

    public Parent calcualte(Parent instance) {
        return null;
    }

    public Child calcualte(Child instance) {
        return null;
    }
}
