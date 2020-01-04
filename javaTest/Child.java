package javaTest;

/**
 * @author Anil Chowdhury
 *         Created on 2/27/2019.
 */
public class Child extends Parent {

    private static Child singleton;

    private Child() throws Exception {
        super();
    }

    public static Child instance() throws Exception {
        if (singleton == null) {
            singleton = new Child();
        }
        return singleton;
    }

    public static void main(String[] args) {
        try {
            Child driver = instance();
            driver.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test() throws Exception {
        Parent p1 = new Parent();
        Parent p2 = new Parent();
        System.out.println("p1 -> " + p1.hashCode());
        System.out.println("p2 -> " + p2.hashCode());
    }
}
