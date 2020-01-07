package javaTest;

import java.lang.reflect.Array;

/**
 * @author Anil Chowdhury
 * Created on 9/8/2019
 */
public class GenericTest {

    private void test(){
//        String[] array = getArray(Person.class, 5);
        Object[] array1 = getArray(5);
        array1[0] = new Integer(1);
        array1[1] = new Double(1);
    }

    public <E> E[] getArray(Class<E> clazz, int size) {
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) Array.newInstance(clazz, size);

        return arr;
    }

    public <T> T[] getArray(int size) {
        T[] arr = (T[])new Object[size];
        return arr;
    }
}
