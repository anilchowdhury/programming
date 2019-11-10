package thread.water.bookOfSemaphore.usingCyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Anil Chowdhury
 * Created on 9/8/2019
 */
class Atom extends Thread {

    private CyclicBarrier bondBarrier;
    private final ConcurrentLinkedQueue<Atom> waterMolecule;
    Random random = new Random();

    Atom(String name, CyclicBarrier bondBarrier, ConcurrentLinkedQueue<Atom> waterMolecule) {
        super(name);
        this.bondBarrier = bondBarrier;
        this.waterMolecule = waterMolecule;
    }

    void waitForBondFormation() {
        waterMolecule.add(this);
        try {
            System.out.println(String.format("%s is waiting for bond formation ... ", getName()));
            bondBarrier.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            ex.printStackTrace();
        }
    }
}
