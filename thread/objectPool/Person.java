package thread.objectPool;

/**
 * @author Anil Chowdhury
 * Created on 9/5/2019
 */
public class Person {

    private String firstName;

    Person(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString(){
        return String.format("[%s]", firstName);
    }
}
