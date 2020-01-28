package javaTest;

/**
 * @author Anil Chowdhury
 *         Created on 2/27/2019.
 */
class Parent {
    private static Parent singleton;

    Parent() throws Exception {
        if (getClass().getName().equals("Child")) {
            throw new Exception();
        }
    }

    private Parent(int i) {
    }

    public static Parent instance() throws Exception {
        if (singleton == null) {
            singleton = new Parent(1);
        }
        return singleton;
    }

    public void print() {
        System.out.println("Parent");
    }
}
