package thread.water.bookOfSemaphore.usingSemaphore;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public abstract class Atom extends Thread {

    protected String name;
    Semaphore oxygenPermit;
    Semaphore hydrogenPermit;
    private Semaphore bondPermit;
    final ConcurrentLinkedQueue<Atom> waterMolecule;
    Random random = new Random();
    private static AtomicInteger count = new AtomicInteger();

    Atom(String name, Semaphore oxygenPermit, Semaphore hydrogenPermit,
         Semaphore bondPermit, ConcurrentLinkedQueue<Atom> waterMolecule) {
        super(name);
        this.oxygenPermit = oxygenPermit;
        this.hydrogenPermit = hydrogenPermit;
        this.bondPermit = bondPermit;
        this.waterMolecule = waterMolecule;
    }

    void bondFormation() {
        if (waterMolecule.size() == 3) {
            createWaterMolecule();
        } else {
            try {
                System.out.println(String.format("%s is waiting for bond formation ... ", getName()));
                bondPermit.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWaterMolecule() {
        count.addAndGet(1);
        System.out.println(String.format("Water molecule - %d is formed using -> [%s]\n",
                count.get(), getAtomsNames()));
        waterMolecule.clear();
        bondPermit.release(2);
        hydrogenPermit.release(2);
        oxygenPermit.release(1);
    }

    private String getAtomsNames() {
        StringBuilder builder = new StringBuilder();
        for (Atom atom : waterMolecule) {
            builder.append((atom.getName())).append(", ");
        }
        return builder.toString().substring(0, builder.toString().lastIndexOf(", "));
    }
}
