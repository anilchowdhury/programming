package javaTest.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 5/11/2018.
 */
class Person {
    String name;
    int age;
    List<Department> depart = new ArrayList<>();

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    List<Department> getDepartments() {
        return depart;
    }
}
