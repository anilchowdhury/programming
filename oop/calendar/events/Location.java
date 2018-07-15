package oop.calendar.events;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public class Location {

    private String name;
    private String address;

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
