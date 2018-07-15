package oop.calendar.exception;

/**
 * @author Anil Chowdhury
 *         Created on 6/23/2018.
 */
public class EventNotFoundException extends Exception{
    public EventNotFoundException(String message){
        super(message);
    }
}
