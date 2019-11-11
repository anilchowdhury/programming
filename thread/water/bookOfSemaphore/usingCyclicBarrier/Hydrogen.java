package thread.water.bookOfSemaphore.usingCyclicBarrier;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/8/2019
 */
public class Hydrogen extends Atom {

    private Semaphore hydrogenPermit;

    Hydrogen(String name, Semaphore hydrogenPermit,
             CyclicBarrier bondBarrier, ConcurrentLinkedQueue<Atom> waterMolecule) {
        super(name, bondBarrier, waterMolecule);
        this.hydrogenPermit = hydrogenPermit;
    }

    public void run() {
        try {
            Thread.sleep(random.nextInt(5000));
            hydrogenPermit.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForBondFormation();
    }
}
