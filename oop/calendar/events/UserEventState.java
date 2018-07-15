package oop.calendar.events;

import java.time.LocalDateTime;

import static oop.calendar.events.Event.dateTimeFormatter;

/**
 * @author Anil Chowdhury
 *         Created on 6/23/2018.
 */
public class UserEventState implements Comparable<UserEventState> {

    private Event event;
    private EventState state;

    public UserEventState(Event event, EventState state) {
        this.event = event;
        this.state = state;
    }

    public Event getEvent() {
        return event;
    }

    public EventState getState() {
        return state;
    }

    @Override
    public int compareTo(UserEventState otherEvent) {
        LocalDateTime startTime = LocalDateTime.parse(event.getStart(), dateTimeFormatter);
        LocalDateTime otherEventStartTime = LocalDateTime.parse(otherEvent.getEvent().getStart(), dateTimeFormatter);
        return startTime.compareTo(otherEventStartTime);
    }
}
