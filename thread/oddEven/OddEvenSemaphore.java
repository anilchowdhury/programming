package thread.oddEven;

import java.util.concurrent.Semaphore;

/**
 * Print odd & even number by separate thread
 *
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class OddEvenSemaphore {

    public static void main(String[] args) {
        test(5);
        test(10);
    }

    private static void test(int input) {
        OddEvenSemaphore driver = new OddEvenSemaphore();
        Semaphore oddPermit = new Semaphore(1);
        Semaphore evenPermit = new Semaphore(0);
        Odd odd = driver.new Odd(oddPermit, evenPermit, input);
        Even even = driver.new Even(oddPermit, evenPermit, input);
        odd.start();
        even.start();
        try {
            odd.join();
            even.join();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Odd extends Thread {
        private int input;
        private Semaphore oddPermit;
        private Semaphore evenPermit;

        Odd(Semaphore oddPermit, Semaphore evenPermit, int input) {
            this.oddPermit = oddPermit;
            this.evenPermit = evenPermit;
            this.input = input;
        }

        /*public void run() {
            int count = 0;
            int loopTimes = (input % 2 == 1) ? (input / 2 + 1) : (input / 2);
            int oddOutPut = 1;
            while (count < loopTimes) {
                try {
                    oddPermit.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Odd -> " + oddOutPut);
                count++;
                oddOutPut += 2;
                evenPermit.release(1);
            }
        }*/
        public void run() {
            int oddOutPut = 1;
            while (oddOutPut <= input) {
                try {
                    oddPermit.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Odd -> " + oddOutPut);
                oddOutPut += 2;
                evenPermit.release(1);
            }
        }
    }


    private class Even extends Thread {
        private int input;
        private Semaphore oddPermit;
        private Semaphore evenPermit;

        Even(Semaphore oddPermit, Semaphore evenPermit, int input) {
            this.oddPermit = oddPermit;
            this.evenPermit = evenPermit;
            this.input = input;
        }

        /*public void run() {
            int count = 0;
            int evenOutPut = 2;
            while (count < (input / 2)) {
                try {
                    evenPermit.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Even -> " + evenOutPut);
                count++;
                evenOutPut += 2;
                oddPermit.release(1);
            }
        }*/
        public void run() {
            int evenOutPut = 2;
            while (evenOutPut <= input) {
                try {
                    evenPermit.acquire();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Even -> " + evenOutPut);
                evenOutPut += 2;
                oddPermit.release(1);
            }
        }
    }
}
