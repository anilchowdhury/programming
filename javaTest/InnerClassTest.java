package javaTest;

/**
 * @author Anil Chowdhury
 *         Created on 10/14/2018.
 */
public class InnerClassTest {

    private int x = 2;

    public static void main(String[] args){
        InnerClassTest test = new InnerClassTest();
        test.test();
    }

    private void test() {
        InnerInner inn = new InnerInner();
        inn.print();
    }

    private class Inner {
        protected int x = 3;
    }

    private class InnerInner extends Inner {
//      protected int x = 4;
        private void print() {
            System.out.println(x);
            System.out.println(super.x);
            System.out.println(InnerClassTest.this.x);
            System.out.println(this.x);
        }
    }
}
