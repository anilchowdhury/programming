package thread.blockingArray;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public interface CircularBlockingQueue<E> {

    void add(E item) throws InterruptedException;

    E take() throws InterruptedException;
}
