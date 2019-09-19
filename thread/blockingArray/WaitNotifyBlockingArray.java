package thread.blockingArray;

import java.lang.reflect.Array;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class WaitNotifyBlockingArray<E> implements CircularBlockingArray<E> {

    private final E[] items;
    private int count = 0;
    private int addIndex = 0;
    private int takeIndex = 0;

    WaitNotifyBlockingArray(int capacity, Class<E[]> type) {
        items = type.cast(Array.newInstance(type.getComponentType(), capacity));
    }

    @Override
    public synchronized void add(E item) throws InterruptedException {
        while (isQueueFull()) {
            System.out.println(String.format("Queue is full. %s waiting for consumer to write ...",
                    Thread.currentThread().getName()));
            wait();
        }
        items[addIndex++] = item;

        if(isQueueEmpty()) {
            notifyAll();
        }

        count++;
        if (addIndex == items.length) {
            addIndex = 0;
        }
        System.out.println(String.format("%s added item - [%s]", Thread.currentThread().getName(), item.toString()));
    }

    @Override
    public synchronized E take() throws InterruptedException {
        while (isQueueEmpty()) {
            System.out.println(String.format("Queue is empty. %s waiting to be produced something ...",
                    Thread.currentThread().getName()));
            wait();
        }

        E item = items[takeIndex];
        items[takeIndex] = null;

        if (isQueueFull()) {
            notifyAll();
        }

        count--;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        System.out.println(String.format("%s consumed item - [%s]", Thread.currentThread().getName(), item.toString()));
        return item;
    }

    private boolean isQueueFull() {
        return count == items.length;
    }

    private boolean isQueueEmpty() {
        return count == 0;
    }
}
