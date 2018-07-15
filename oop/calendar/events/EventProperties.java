package oop.calendar.events;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 6/22/2018.
 */
public class EventProperties {

    private static final String OWNER_ID = "OWNER_ID";
    private static final String TITLE = "TITLE";
    private static final String START = "START_TIME";
    private static final String END = "END_TIME";
    private static final String PLACE_NAME = "PLACE_NAME";
    private static final String ADDRESS = "ADDRESS";

    private Map<String, String> properties;

    public EventProperties(int eventOwnerID, String title, String start, String end) {
        this(eventOwnerID, title, start, end, "", "");
    }

    public EventProperties(int eventOwnerID, String title, String start, String end, String placeName, String address) {
        properties = new LinkedHashMap<>();
        properties.put(OWNER_ID, String.valueOf(eventOwnerID));
        properties.put(TITLE, title);
        properties.put(START, start);
        properties.put(END, end);
        properties.put(PLACE_NAME, placeName);
        properties.put(ADDRESS, address);
    }

    public void updateProperties(Map<String, String> newProperties) {
        properties.putAll(newProperties);
    }

    public String getTitle() {
        return properties.get(TITLE);
    }

    public String getStart() {
        return properties.get(START);
    }

    public String getEnd() {
        return properties.get(END);
    }

    public String getPlaceName() {
        return properties.get(PLACE_NAME);
    }

    public String getAddress() {
        return properties.get(ADDRESS);
    }

    public String getOwnerId() {
        return properties.get(OWNER_ID);
    }

    public String getPropertiesInfo() {
        StringBuilder detail = new StringBuilder();
        detail.append("{\n");
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            detail.append(entry.getKey()).append("-->").append(entry.getValue()).append("\n");
        }
        detail.append("}");
        return detail.toString();
    }
}
