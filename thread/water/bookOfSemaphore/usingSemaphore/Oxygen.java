package thread.water.bookOfSemaphore.usingSemaphore;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class Oxygen extends Atom {

    Oxygen(String name, Semaphore oxygenPermit, Semaphore hydrogenPermit,
           Semaphore bondPermit, ConcurrentLinkedQueue<Atom> waterMolecule) {
        super(name, oxygenPermit, hydrogenPermit, bondPermit, waterMolecule);
    }

    public void run() {
        try {
            Thread.sleep(random.nextInt(100000));
            oxygenPermit.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waterMolecule.add(this);
        bondFormation();
    }
}
