package oop.calendar.storage;

import oop.calendar.events.Event;
import oop.calendar.events.EventState;
import oop.calendar.events.UserEventState;
import oop.calendar.exception.EventNotFoundException;
import oop.calendar.exception.StorageException;
import oop.calendar.exception.UserNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 6/22/2018.
 */
public interface IStorage {
    void addEvent(int eventOwnerID, Event event) throws StorageException;
    void deleteEvent(int eventID) throws StorageException;
    void rejectEvent(int userID, int eventID) throws StorageException;
    void updateEvent(int eventID, Map<String, String> properties) throws StorageException;
    void addUser(User user) throws StorageException;
    void acceptEvent(int userID, int eventID) throws StorageException;
    void inviteUsers(int eventID, List<Integer> users) throws StorageException;
    boolean userExist(int userID) throws StorageException;
    Collection<Event> getAllEvents() throws StorageException;
    Event getEvent(int eventID) throws EventNotFoundException;
    User getUser(int userID) throws UserNotFoundException;
    Map<Integer, EventState> getUserEvents(int userID) throws StorageException, UserNotFoundException;
    List<UserEventState> getUserEvents(int userID, String startDateTme, String endDateTime) throws StorageException;
}
