package oop.calendar.storage;

import oop.calendar.TimeSlot;
import oop.calendar.events.Event;
import oop.calendar.events.EventState;
import oop.calendar.events.UserEventState;
import oop.calendar.exception.EventNotFoundException;
import oop.calendar.exception.StorageException;
import oop.calendar.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Anil Chowdhury
 *         Created on 6/22/2018.
 */
public class InMemoryStorage implements IStorage {

    private Map<Integer, User> userTable;//USER_ID --> User
    private Map<Integer, Event> eventTable;//EVENT_ID --> Event
    private Map<Integer, Map<Integer, EventState>> userToEventMapping;//USER_ID --> {EVENT_ID, STATE}

    public InMemoryStorage() {
        eventTable = new HashMap<>();
        userTable = new HashMap<>();
        userToEventMapping = new HashMap<>();
    }

    @Override
    public void addEvent(int eventOwnerID, Event event) throws StorageException {
        eventTable.put(event.getId(), event);
        userToEventMapping.get(eventOwnerID).put(event.getId(), EventState.ACCEPTED);
    }

    @Override
    public void deleteEvent(int eventID) throws StorageException {
        eventTable.remove(eventID);
        for (Map<Integer, EventState> value : userToEventMapping.values()) {
            value.entrySet().stream().filter(entry -> entry.getKey() == eventID).forEach(entry ->
                    value.remove(eventID));
        }
    }

    @Override
    public void rejectEvent(int userID, int eventID) throws StorageException {
        updateUserEventState(userID, eventID, EventState.REJECTED);
    }

    @Override
    public void updateEvent(int eventID, Map<String, String> properties) throws StorageException {
        eventTable.get(eventID).updateEvent(properties);
    }

    @Override
    public void addUser(User user) throws StorageException {
        userTable.put(user.getId(), user);
        userToEventMapping.putIfAbsent(user.getId(), new HashMap<>());
    }

    @Override
    public void acceptEvent(int userID, int eventID) {
        updateUserEventState(userID, eventID, EventState.ACCEPTED);
    }

    @Override
    public void inviteUsers(int eventID, List<Integer> users) throws StorageException {
        for (int userID : users) {
            userToEventMapping.get(userID).put(eventID, EventState.NEUTRAL);
        }
    }

    @Override
    public boolean userExist(int userID) throws StorageException {
        return userTable.containsKey(userID);
    }

    @Override
    public Collection<Event> getAllEvents() throws StorageException {
        return eventTable.values();
    }

    @Override
    public Event getEvent(int eventID) throws EventNotFoundException {
        if (eventTable.get(eventID) == null) {
            throw new EventNotFoundException(String.format("Event ID = %s does not exist", eventID));
        }
        return eventTable.get(eventID);
    }

    @Override
    public User getUser(int userID) throws UserNotFoundException {
        if (eventTable.get(userID) == null) {
            throw new UserNotFoundException(String.format("User ID = %s does not exist", userID));
        }
        return userTable.get(userID);
    }

    @Override
    public Map<Integer, EventState> getUserEvents(int userID) throws StorageException, UserNotFoundException {
        if (eventTable.get(userID) == null) {
            throw new UserNotFoundException(String.format("User ID = %s does not exist", userID));
        }
        return userToEventMapping.get(userID);
    }

    @Override
    public List<UserEventState> getUserEvents(int userID, String startDateTme, String endDate) throws StorageException {
        List<UserEventState> userEvents = new ArrayList<>();
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTme, Event.dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, Event.dateTimeFormatter);
        Map<Integer, EventState> eventStateMap = userToEventMapping.get(userID);
        eventStateMap.entrySet().forEach(e -> {
            Event event = eventTable.get(e.getKey());
            if (TimeSlot.isInRange(startDateTime, endDateTime, event.getStartDateTime(), event.getEndDateTime())) {
                userEvents.add(new UserEventState(event, e.getValue()));
            }
        });
        Collections.sort(userEvents);
        return userEvents;
    }

    private void updateUserEventState(int userID, int eventID, EventState state) {
        Map<Integer, EventState> userEventStates = userToEventMapping.get(userID);
        userEventStates.entrySet().stream().filter(e -> e.getKey() == eventID).forEach(
                e -> userEventStates.put(eventID, state));
    }
}

