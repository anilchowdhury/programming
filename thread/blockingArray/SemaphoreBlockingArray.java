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
    }

    @Override
    public void add(E item) throws InterruptedException {
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
