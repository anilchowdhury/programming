package thread.blockingArray;

import java.lang.reflect.Array;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class WaitNotifyBlockingArray<E> implements CircularBlockingArray<E> {

    private final Object[] items;
    private int count = 0;
    private int addIndex = 0;
    private int takeIndex = 0;

    WaitNotifyBlockingArray(int capacity, Class<E[]> type) {
        items = type.cast(Array.newInstance(type.getComponentType(), capacity));
//      items = new Object[capacity];
    }

    @Override
    public synchronized void add(E item) {
        while (count == items.length) {
            try {
                System.out.println(String.format("Queue is full. %s waiting for consumer to write ...",
                        Thread.currentThread().getName()));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        items[addIndex++] = item;
        count++;
        if (addIndex == items.length) {
            addIndex = 0;
        }
        System.out.println(String.format("%s added item - [%s]", Thread.currentThread().getName(), item.toString()));
        notifyAll();
    }

    @Override
    public synchronized E take() {
        while (count == 0) {
            try {
                System.out.println(String.format("Queue is empty. %s waiting to be produced something ...",
                        Thread.currentThread().getName()));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @SuppressWarnings("unchecked")
        E item = (E) items[takeIndex];
        items[takeIndex] = null;
        count--;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        System.out.println(String.format("%s consumed item - [%s]", Thread.currentThread().getName(), item.toString()));
        notifyAll();
        return item;
    }
}
