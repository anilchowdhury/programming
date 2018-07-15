package oop.calendar;

import oop.calendar.events.UserEventState;
import oop.calendar.storage.User;

import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 6/23/2018.
 */
public class UserCalendar {
    private User user;
    private List<UserEventState> schedules;

    public UserCalendar(User user, List<UserEventState> events) {
        this.user = user;
        schedules = events;
    }

    public User getUser() {
        return user;
    }

    public List<UserEventState> getSchedules() {
        return schedules;
    }
}
