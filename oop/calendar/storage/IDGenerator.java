package oop.calendar.storage;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public class IDGenerator {

    public static final int ROOT = 0;
    private static int userID = 1;
    private static int eventID = 1;

    public static synchronized int getUserID() {
        return userID++;
    }

    public static synchronized int getEventID() {
        return eventID++;
    }
}
