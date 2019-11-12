package thread.water.bookOfSemaphore.usingCyclicBarrier;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Anil Chowdhury
 * Created on 9/8/2019
 */
public class BondTask implements Runnable {

    private Semaphore oxygenPermit;
    private Semaphore hydrogenPermit;
    private ConcurrentLinkedQueue<Atom> waterMolecule;
    private static AtomicInteger count = new AtomicInteger();

    BondTask(ConcurrentLinkedQueue<Atom> waterMolecule, Semaphore oxygenPermit, Semaphore hydrogenPermit) {
        this.waterMolecule = waterMolecule;
        this.oxygenPermit = oxygenPermit;
        this.hydrogenPermit = hydrogenPermit;
    }

    @Override
    public void run() {
        count.addAndGet(1);
        System.out.println(String.format("Water molecule - %d is formed using -> [%s]\n",
                count.get(), getAtomsNames()));
        waterMolecule.clear();
        oxygenPermit.release(1);
        hydrogenPermit.release(2);
    }

    private String getAtomsNames() {
        return waterMolecule.stream().map(Thread::getName).collect(Collectors.joining("; "));
    }
}
