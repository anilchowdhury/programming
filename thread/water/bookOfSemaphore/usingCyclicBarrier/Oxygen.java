package thread.water.bookOfSemaphore.usingCyclicBarrier;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/8/2019
 */
public class Oxygen extends Atom {

    private Semaphore oxygenPermit;

    Oxygen(String name, Semaphore oxygenPermit, CyclicBarrier bondBarrier,
           ConcurrentLinkedQueue<Atom> waterMolecule) {
        super(name, bondBarrier, waterMolecule);
        this.oxygenPermit = oxygenPermit;
    }

    public void run() {
        try {
            Thread.sleep(random.nextInt(5000));
            oxygenPermit.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForBondFormation();
    }
}
