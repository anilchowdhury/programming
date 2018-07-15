package oop.calendar.events;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public enum EventType {

    REMINDER(1), HOLIDAY(2), BIRTHDAY(3), MEETING(4);
    private int index;

    EventType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static EventType getEventType(int index) {
        EventType type;
        switch (index) {
            case 1:
                type = REMINDER; break;
            case 2:
                type = HOLIDAY; break;
            case 3:
                type = BIRTHDAY;break;
            case 4:
                type = MEETING;break;
            default: throw new RuntimeException("No event found");

        }
        return type;
    }
}
