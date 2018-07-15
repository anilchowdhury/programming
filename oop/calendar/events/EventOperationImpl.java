package oop.calendar.events;

import oop.calendar.TimeSlot;
import oop.calendar.UserCalendar;
import oop.calendar.exception.EventNotFoundException;
import oop.calendar.exception.StorageException;
import oop.calendar.exception.UserNotFoundException;
import oop.calendar.storage.IDGenerator;
import oop.calendar.storage.IStorage;
import oop.calendar.storage.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public class EventOperationImpl implements IEventOperation {

    private IStorage storage;

    public EventOperationImpl(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public User createUser(int id, String firstName, String lastName, String email) throws StorageException {
        User user = new User(IDGenerator.getUserID(), firstName, lastName, email);
        storage.addUser(user);
        return user;
    }

    @Override
    public Event createEvent(int eventOwnerID, int eventType, String title, String start, String end) throws StorageException {
        Event event = new Event(IDGenerator.getEventID(), eventType, eventOwnerID, title, start, end);
        storage.addEvent(eventOwnerID, event);
        return event;
    }

    public Event createEvent(int eventOwnerID, int eventType, String title, String start, String end, String
            placeName, String address) throws StorageException {
        Event event = new Event(IDGenerator.getEventID(), eventType, eventOwnerID, title, start, end, placeName, address);
        storage.addEvent(eventOwnerID, event);
        return event;
    }

    @Override
    public void deleteEvent(int eventID) throws StorageException {
        storage.deleteEvent(eventID);
    }

    @Override
    public void updateEvent(int eventID, Map<String, String> properties) throws StorageException {
        storage.updateEvent(eventID, properties);
    }

    @Override
    public void acceptEvent(int userID, int eventID) throws StorageException {
        storage.acceptEvent(userID, eventID);
    }

    @Override
    public void rejectEvent(int userID, int eventID) throws StorageException {
        storage.rejectEvent(userID, eventID);
    }

    @Override
    public void inviteUsers(int eventID, List<Integer> users) throws StorageException {
        storage.inviteUsers(eventID, users);
    }

    @Override
    public Collection<Event> getAllEvents() throws StorageException {
        return storage.getAllEvents();
    }

    @Override
    public String getEventDetails(int eventID) throws StorageException {
        try {
            Event event = storage.getEvent(eventID);
            return event.getEventDetail();
        } catch (EventNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public UserCalendar getCalendar(int userID, String startDateTime, String endDateTime) throws UserNotFoundException, StorageException {
        if (!storage.userExist(userID)) {
            throw new UserNotFoundException(String.format("User with ID = %s does not exist", userID));
        }
        return new UserCalendar(storage.getUser(userID), storage.getUserEvents(userID, startDateTime, endDateTime));
    }

    @Override
    public List<String> getFreeSlots(List<Integer> userIDs, String date) throws UserNotFoundException, StorageException, EventNotFoundException {
        List<String> freeTimeSlot = new ArrayList<>();
        List<UserEventState> allUserEvents = getAllUSerEvents(userIDs, date + "00:00", date + "23:59");
        Map<TimeSlot, Boolean> slotAvailabilityMap = TimeSlot.getSlotAvailabilityMap();
        for(UserEventState eventState : allUserEvents) {
            Event event = eventState.getEvent();
            List<TimeSlot> timeSlot = TimeSlot.getTimeSlot(event.getStart(), event.getEnd());
            timeSlot.stream().forEach(slot -> slotAvailabilityMap.put(slot, true));
        }
        slotAvailabilityMap.entrySet().stream().filter(Map.Entry::getValue).forEach(
                entry -> String.format("[%s  -   %s]", entry.getKey().getStartDateTime(), entry.getKey().getEndDateTime()));
        return freeTimeSlot;
    }

    private List<UserEventState> getAllUSerEvents(List<Integer> userIDs, String startDateTime,  String endDateTime) throws
            UserNotFoundException, StorageException, EventNotFoundException {
        List<UserEventState> allUserEvents = new ArrayList<>();
        for (int userID : userIDs) {
            allUserEvents.addAll(storage.getUserEvents(userID, startDateTime, endDateTime));
        }
        return allUserEvents;
    }
}
