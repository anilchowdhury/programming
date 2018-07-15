package oop.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static oop.calendar.events.Event.dateTimeFormatter;

/**
 * @author Anil Chowdhury
 *         Created on 6/23/2018.
 */
public enum TimeSlot {
    SLOT0("00:01", "01:00"),
    SLOT1("01:01", "02:00"),
    SLOT2("02:01", "03:00"),
    SLOT3("03:01", "04:00"),
    SLOT4("04:01", "05:00"),
    SLOT5("05:01", "06:00"),
    SLOT6("06:01", "07:00"),
    SLOT7("07:01", "08:00"),
    SLOT8("08:01", "09:00"),
    SLOT9("09:01", "10:00"),
    SLOT10("10:01", "11:00"),
    SLOT11("11:01", "12:00"),
    SLOT12("12:01", "13:00"),
    SLOT13("13:01", "14:00"),
    SLOT14("14:01", "15:00"),
    SLOT15("15:01", "16:00"),
    SLOT16("16:01", "17:00"),
    SLOT17("17:01", "18:00"),
    SLOT18("18:01", "19:00"),
    SLOT19("19:01", "20:00"),
    SLOT20("20:01", "21:00"),
    SLOT21("21:01", "22:00"),
    SLOT22("22:01", "23:00"),
    SLOT23("23:01", "00:00");

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    TimeSlot(String start, String end) {
        startDateTime = LocalDateTime.parse(start, dateTimeFormatter);
        endDateTime = LocalDateTime.parse(end, dateTimeFormatter);
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public static Map<TimeSlot, Boolean> getSlotAvailabilityMap() {
        Map<TimeSlot, Boolean> availabilityMap = new HashMap<>();
        for (TimeSlot slot : values()) {
            availabilityMap.put(slot, false);
        }
        return availabilityMap;
    }

    public static List<TimeSlot> getTimeSlot(String start, String end) {
        List<TimeSlot> slots = new ArrayList<>();
        LocalDateTime qStartDateTime = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime qEndDateTime = LocalDateTime.parse(end, dateTimeFormatter);

        for(TimeSlot slot : values()) {
            if(isInRange(qStartDateTime, qEndDateTime, slot.getStartDateTime(), slot.getEndDateTime())){
                slots.add(slot);
            }
        }
        return slots;
    }

    public static boolean isInRange(LocalDateTime firstStartDateTime, LocalDateTime firstEndDateTime,
                              LocalDateTime secondStartDateTime, LocalDateTime secondEndDateTime) {
        return !((secondStartDateTime.compareTo(firstEndDateTime) > 0 ) || (secondEndDateTime.compareTo(firstStartDateTime) < 0));
    }
}
