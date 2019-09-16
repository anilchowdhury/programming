package thread.zeroOddEven;

import java.util.function.IntConsumer;

/**
 * @author Anil Chowdhury
 * Created on 9/3/2019
 */
public class WaitAndNotifyOptimized implements ZeroOddEven {
    private int input;
    private boolean zeroTurn = true;
    private boolean oddTurn = false;

    WaitAndNotifyOptimized(int input) {
        this.input = input;
    }

    @Override
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < input; i++) {
            synchronized (this) {
                while (!zeroTurn) {
                    wait();
                }
                printNumber.accept(0);
                zeroTurn = false;
                oddTurn = !oddTurn;
                notifyAll();
            }
        }
    }

    @Override
    public void even(IntConsumer printNumber) throws InterruptedException {
        int evenOutput = 2;
        while (evenOutput <= input) {
            synchronized (this) {
                while (oddTurn || zeroTurn) {
                    wait();
                }
                printNumber.accept(evenOutput);
                zeroTurn = true;
                evenOutput += 2;
                notifyAll();
            }
        }
    }

    @Override
    public void odd(IntConsumer printNumber) throws InterruptedException {
        int oddOutput = 1;
        while (oddOutput <= input) {
            synchronized (this) {
                while (!oddTurn || zeroTurn) {
                    wait();
                }
                printNumber.accept(oddOutput);
                zeroTurn = true;
                oddOutput += 2;
                notifyAll();
            }
        }
    }
}
