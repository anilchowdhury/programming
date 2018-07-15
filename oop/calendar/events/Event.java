package oop.calendar.events;

import com.sun.istack.internal.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public class Event implements Comparable<Event>{
    private final int id;
    private final EventType type;
    private EventProperties properties;
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Event(int id, int type) {
        this.id = id;
        this.type = EventType.getEventType(type);
    }

    public Event(int id, int type, int eventOwnerID, @NotNull String title, String start, String end) {
        this(id, type);
        properties = new EventProperties(eventOwnerID, title, start, end);
    }

    public Event(int id, int type, int eventOwnerID, @NotNull String title, String start, String end, String placeName, String address) {
        this(id, type);
        properties = new EventProperties(eventOwnerID, title, start, end, placeName, address);
    }

    public void updateEvent(Map<String, String> newProperties) {
        properties.updateProperties(newProperties);
    }

    public int getId() {
        return id;
    }

    public EventType getEventType() {
        return type;
    }

    public String getStart() {
        return properties.getStart();
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.parse(properties.getStart(), dateTimeFormatter);
    }

    public String getEnd() {
        return properties.getEnd();
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.parse(properties.getEnd(), dateTimeFormatter);
    }

    public Location getLocation() {
        return new Location(properties.getPlaceName(), properties.getAddress());
    }

    public int getOwner() {
        return Integer.valueOf(properties.getOwnerId());
    }

    public String getTitle() {
        return properties.getTitle();
    }

    public String getEventDetail() {
        return "Event ID = " + id + "\nEvent Detail = " + properties.getPropertiesInfo();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return other.id == id;
        }
        return false;
    }

    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Event otherEvent) {
        LocalDateTime startTime = LocalDateTime.parse(properties.getStart(), dateTimeFormatter);
        LocalDateTime otherEventStartTime = LocalDateTime.parse(otherEvent.getStart(), Event.dateTimeFormatter);
        return startTime.compareTo(otherEventStartTime);
    }
}
