package thread.blockingArray;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is implementation of blocking queue which internally uses
 * Linked List to store the elements.
 * This is not a circular queue.
 *
 * @author Anil Chowdhury
 * Created on 10/8/2019
 */
public class SemaphoreBlockingList<E> implements ListBlockingQueue<E> {

    private LinkedList<E> elementQueue;
    private Semaphore addPermit;
    private Semaphore takePermit;
    private ReadWriteLock lock;

    SemaphoreBlockingList(int capacity) {
        this.elementQueue = new LinkedList<>();
        this.addPermit = new Semaphore(capacity);
        this.takePermit = new Semaphore(0);
        this.lock = new ReentrantReadWriteLock();
        System.out.println("---------------------- Semaphore Blocking List ----------------------");
    }

    @Override
    public void add(E item) throws InterruptedException {
        if (addPermit.availablePermits() == 0) {
            System.out.println(String.format("Queue is full. %s waiting for consumer to take ...",
                    Thread.currentThread().getName()));
        }
        try {
            addPermit.acquire();
            lock.writeLock().lock();
            elementQueue.add(item);
            System.out.println(String.format("%s added item - [%s]",
                    Thread.currentThread().getName(), item.toString()));
            takePermit.release(1);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /*
    * The below implementation by using Semaphore does not work.
    * Randomly its throw NoSuchElementException
    * Not sure why its happening, as same logic using array as
    * internal data structure is working fine
    * @thread.blockingArray.SemaphoreBlockingArray
    *
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
    */

    @Override
    public E take() throws InterruptedException {
        if (takePermit.availablePermits() == 0) {
            System.out.println(String.format("Queue is empty. %s waiting to be produced something ...",
                    Thread.currentThread().getName()));
        }
        E item;
        try {
            takePermit.acquire();
            lock.readLock().lock();
            item = elementQueue.remove();
            System.out.println(String.format("%s consumed item - [%s]",
                    Thread.currentThread().getName(), item.toString()));
            addPermit.release(1);
        } finally {
            lock.readLock().unlock();
        }
        return item;
    }

    /*
    * The below implementation by using Semaphore does not work.
    * Randomly its throw NoSuchElementException
    * Not sure why its happening, as same logic using array as
    * internal data structure is working fine
    * @thread.blockingArray.SemaphoreBlockingArray

    @Override
    public E take() throws InterruptedException {
        if (takePermit.availablePermits() == 0) {
            System.out.println(String.format("Queue is empty. %s waiting to be produced something ...",
                    Thread.currentThread().getName()));
        }
        takePermit.acquire();
        E item = elementQueue.remove();
        System.out.println(String.format("%s consumed item - [%s]",
                Thread.currentThread().getName(), item.toString()));
        addPermit.release(1);
        return item;
    }
    */
}
