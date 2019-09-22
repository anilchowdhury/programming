package thread.objectPool;

/**
 * @author Anil Chowdhury
 * Created on 9/5/2019
 */
public class PersonPool extends ObjectPool<Person> {

    private int index = 1;

    PersonPool(int maxCapacity) {
        super(maxCapacity, Person[].class);
    }

    protected Person createCustomObject() {
        return new Person("Person Name -" + index++);
    }
}
