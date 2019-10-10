package thread.blockingArray;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * This is implementation of blocking queue which internally uses Linked List to
 * store the elements.
 * This is not a circular queue.
 *
 * @author Anil Chowdhury
 * Created on 10/8/2019
 */
public class SemaphoreBlockingList<E> implements ListBlockingQueue<E> {

    private LinkedList<E> elementQueue;
    private Semaphore addPermit;
    private Semaphore takePermit;

    SemaphoreBlockingList(int capacity) {
        this.elementQueue = new LinkedList<>();
        this.addPermit = new Semaphore(capacity);
        this.takePermit = new Semaphore(0);
        System.out.println("---------------------- Semaphore Blocking List ----------------------");
    }

    @Override
    public void add(E item) throws InterruptedException {
        if (addPermit.availablePermits() == 0) {
            System.out.println(String.format("Queue is full. %s waiting for consumer to take ...",
                    Thread.currentThread().getName()));
        }
        addPermit.acquire();
        elementQueue.add(item);
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
        E first = elementQueue.remove();
        System.out.println(String.format("%s consumed item - [%s]",
                Thread.currentThread().getName(), first.toString()));
        addPermit.release(1);
        return first;
    }
}
