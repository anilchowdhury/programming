package oop.calendar.exception;

/**
 * @author Anil Chowdhury
 *         Created on 6/23/2018.
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
