package oop.calendar.events;

import oop.calendar.UserCalendar;
import oop.calendar.exception.EventNotFoundException;
import oop.calendar.exception.StorageException;
import oop.calendar.exception.UserNotFoundException;
import oop.calendar.storage.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public interface IEventOperation {
    User createUser(int id, String firstName, String lastName, String email) throws StorageException;
    Event createEvent(int eventOwnerID, int eventType, String title, String start, String end) throws StorageException;
    Event createEvent(int eventOwnerID, int eventType, String title, String start, String end, String placeName, String address) throws StorageException;
    void deleteEvent(int eventID) throws StorageException;
    void updateEvent(int eventID, Map<String, String> properties) throws StorageException;
    Collection<Event> getAllEvents() throws StorageException;
    void acceptEvent(int userID, int eventID) throws StorageException;
    void rejectEvent(int userID, int eventID) throws StorageException;
    void inviteUsers(int eventID, List<Integer> users) throws StorageException;
    String getEventDetails(int eventID) throws StorageException;
    UserCalendar getCalendar(int userID, String startDate, String endDate) throws UserNotFoundException, StorageException;
    List<String> getFreeSlots(List<Integer> userIDs, String date) throws UserNotFoundException, StorageException, EventNotFoundException;
}

