package thread.blockingArray;

import java.lang.reflect.Array;
import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class SemaphoreBlockingArray<E> implements CircularBlockingQueue<E> {

    private final E[] items;
    private int addIndex = 0;
    private int takeIndex = 0;
    private Semaphore addPermit;
    private Semaphore takePermit;

    SemaphoreBlockingArray(int capacity, Class<E[]> type) {
        items = type.cast(Array.newInstance(type.getComponentType(), capacity));
        addPermit = new Semaphore(capacity);
        takePermit = new Semaphore(0);
        System.out.println("---------------------- Semaphore Blocking Array ----------------------");
    }

    @Override
    public void add(E item) throws InterruptedException {
        if (addPermit.availablePermits() == 0) {
            System.out.println(String.format("Queue is full. %s waiting for consumer to take ...",
                    Thread.currentThread().getName()));
        }
        addPermit.acquire();
        items[addIndex++] = item;
        if (addIndex == items.length) {
            addIndex = 0;
        }
        System.out.println(String.format("%s added item - [%s]",
                Thread.currentThread().getName(), item.toString()));
        takePermit.release(1);
    }

    @Override
    public E take() throws InterruptedException {
        if (takePermit.availablePermits() == 0) {
            System.out.println(String.format("Queue is empty. %s waiting to be produced something ...",
                    Thread.currentThread().getName()));
        }
        takePermit.acquire();
        E item = items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        System.out.println(String.format("%s consumed item - [%s]",
                Thread.currentThread().getName(), item.toString()));
        addPermit.release(1);
        return item;
    }
}
