package thread.objectPool;

import java.util.Random;

/**
 * @author Anil Chowdhury
 * Created on 9/6/2019
 */
public class PoolTesting {
    public static void main(String[] args) {
        testPersonPool(3);
    }

    private static void testPersonPool(int maxCapacity) {
        PersonPool pool = new PersonPool(maxCapacity);

        Thread client1 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < maxCapacity; i++) {
                try {
                    Thread.sleep(random.nextInt(1000));
                    Person element1 = pool.getElement();
                    System.out.println(String.format("%s got %s from the pool",
                            Thread.currentThread().getName(), element1.toString()));

                    Person element2 = pool.getElement();
                    System.out.println(String.format("%s got %s from the pool",
                            Thread.currentThread().getName(), element2.toString()));

                    Thread.sleep(random.nextInt(5000));
                    System.out.println(String.format("%s returned %s to the pool",
                            Thread.currentThread().getName(), element1.toString()));
                    pool.returnElement(element1);

                    Thread.sleep(random.nextInt(5000));
                    System.out.println(String.format("%s returned %s to the pool",
                            Thread.currentThread().getName(), element2.toString()));
                    pool.returnElement(element2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s is done.", Thread.currentThread().getName()));
        }, "Client1");


        Thread client2 = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < maxCapacity; i++) {
                try {
                    Thread.sleep(random.nextInt(100));
                    Person element = pool.getElement();
                    System.out.println(String.format("%s got %s from the pool",
                            Thread.currentThread().getName(), element.toString()));

                    Thread.sleep(random.nextInt(6000));
                    System.out.println(String.format("%s returned %s to the pool",
                            Thread.currentThread().getName(), element.toString()));
                    pool.returnElement(element);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s is done.", Thread.currentThread().getName()));
        }, "Client2");

        client1.start();
        client2.start();

        try {
            client1.join();
            client2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
