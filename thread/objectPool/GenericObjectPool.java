package thread.objectPool;

/**
 * @author Anil Chowdhury
 * Created on 9/5/2019
 */
public interface GenericObjectPool<E> {

    E getElement();

    void returnElement(E object);
}
