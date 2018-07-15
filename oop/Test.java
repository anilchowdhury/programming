package oop;

import com.sun.javafx.binding.StringFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Anil Chowdhury
 *         Created on 6/21/2018.
 */
public class Test {
    public static void main(String[] args) {

        String dateTime1 = "2018-06-18 15:59";
        String dateTime2 = "2018-06-18 23:01";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.parse(dateTime1, formatter);
        LocalDateTime localDateTime2 = LocalDateTime.parse(dateTime2, formatter);
        System.out.println(localDateTime1.compareTo(localDateTime2));

        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getHour());
        System.out.println(localDateTime1.getMinute());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getDayOfYear());
        System.out.println(localDateTime1);
        String name = "Holiday";
        System.out.println(StringFormatter.format("Event = %s; Date = %s", name, LocalDateTime.now()).getValue());

        System.out.println(printPropertiesInfo());
    }
    public static String printPropertiesInfo() {
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("TITLE","Movie");
        properties.put("START","2:30 PM");
        properties.put("END","5.30 PM");
        properties.put("ADDRESS","Inox");
        properties.put("PLACE_NAME","Marathahalli");

        StringBuilder detail = new StringBuilder();
        detail.append("{\n");
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            detail.append(entry.getKey()).append(" --> ").append(entry.getValue()).append("\n");
        }
        detail.append("}");
        return detail.toString();
    }
}
