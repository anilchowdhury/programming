package oop.calendar.storage;

import oop.calendar.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Event> events = new ArrayList<>();

    public User(int id, String firstName, String lastName, String emailName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && ((User) obj).getId() == id;
    }
}
