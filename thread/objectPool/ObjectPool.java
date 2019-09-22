package thread.objectPool;

import java.lang.reflect.Array;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public abstract class ObjectPool<E> implements GenericObjectPool<E> {

//  private Object[] objectsPool;
    private E[] objectsPool;
    private int maxCapacity;
    private int currentSize = 0;
    private int numberOfObjectCreated = 0;
    private int getIndex = 0;
    private int putIndex = 0;

    /*ObjectPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        objectsPool = new Object[maxCapacity];

    }*/

    /*ObjectPool(Class<E> clazz) {
        this.maxCapacity = 5;
        objectsPool = (E[]) (Array.newInstance(clazz, maxCapacity));
    }*/

    ObjectPool(int maxCapacity, Class<E[]> clazz) {
        this.maxCapacity = maxCapacity;
//      objectsPool = (E[]) new  Object[maxCapacity];
        objectsPool = clazz.cast(Array.newInstance(clazz.getComponentType(), maxCapacity));
    }

    public synchronized E getElement() {
        if (!poolIsEmpty()) {
            return getElementFromThePool();
        }
        if (haveMoreCapacity()) {
            System.out.println("Pool is empty but still has capacity hence creating new object");
            return createNewObject();
        }
        waitingForObjectToReturnFromBorrower();
        return getElementFromThePool();
    }

    public synchronized void returnElement(E object) {
        objectsPool[putIndex++] = object;
        currentSize++;
        if (putIndex == objectsPool.length) {
            putIndex = 0;
        }
        notify();
    }

    protected abstract E createCustomObject();

    private E createNewObject() {
        numberOfObjectCreated++;
        return createCustomObject();
    }

    private synchronized void waitingForObjectToReturnFromBorrower() {
        while (poolIsEmpty()) {
            try {
                System.out.println("Pool is empty & has no capacity hence waiting for any " +
                        "client to return the borrowed object.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private E getElementFromThePool() {
        E item = objectsPool[getIndex++];
        if (getIndex == objectsPool.length) {
            getIndex = 0;
        }
        currentSize--;
        return item;
    }

    private boolean poolIsEmpty() {
        return currentSize == 0;
    }

    private boolean haveMoreCapacity() {
        return numberOfObjectCreated < maxCapacity;
    }
}
