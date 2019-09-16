package thread.zeroOddEven;

import java.util.function.IntConsumer;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public interface ZeroOddEven {
    void zero(IntConsumer printNumber) throws InterruptedException;  // only output 0's

    void even(IntConsumer printNumber) throws InterruptedException;  // only output even numbers

    void odd(IntConsumer printNumber) throws InterruptedException;   // only output odd numbers
}
