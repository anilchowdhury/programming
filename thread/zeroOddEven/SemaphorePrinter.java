package thread.zeroOddEven;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class SemaphorePrinter implements ZeroOddEven {

    private int input;
    private Semaphore zeroPermit = new Semaphore(1);
    private Semaphore oddPermit = new Semaphore(0);
    private Semaphore evenPermit = new Semaphore(0);

    SemaphorePrinter(int input) {
        this.input = input;
    }

    @Override
    public void zero(IntConsumer printNumber) throws InterruptedException {
        int count = 1;
        while (count <= input) {
            zeroPermit.acquire();
            printNumber.accept(0);
            if (count++ % 2 == 1) {
                oddPermit.release(1);
            } else {
                evenPermit.release(1);
            }
        }
    }

    @Override
    public void even(IntConsumer printNumber) throws InterruptedException {
        int evenOutPut = 2;
        while (evenOutPut <= input) {
            evenPermit.acquire();
            printNumber.accept(evenOutPut);
            evenOutPut += 2;
            zeroPermit.release(1);
        }
    }

    @Override
    public void odd(IntConsumer printNumber) throws InterruptedException {
        int oddOutPut = 1;
        while (oddOutPut <= input) {
            oddPermit.acquire();
            printNumber.accept(oddOutPut);
            oddOutPut += 2;
            zeroPermit.release(1);
        }
    }
}
