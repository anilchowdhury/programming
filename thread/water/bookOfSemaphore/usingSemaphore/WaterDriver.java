package thread.water.bookOfSemaphore.usingSemaphore;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * There are two kinds of threads, oxygen and hydrogen. In order to assemble
 * these threads into water molecules, we have to create a barrier that makes each
 * thread wait until a complete molecule is ready to proceed.
 * As each thread passes the barrier, it should invoke bond. You must guarantee
 * that all the threads from one molecule invoke bond before any of the threads
 * from the next molecule do.
 *
 * In other words:
 *      • If an oxygen thread arrives at the barrier when no hydrogen threads are
 *        present, it has to wait for two hydrogen threads.
 *      • If a hydrogen thread arrives at the barrier when no other threads are
 *        present, it has to wait for an oxygen thread and another hydrogen thread.
 *
 * We don’t have to worry about matching the threads up explicitly; that is,
 * the threads do not necessarily know which other threads they are paired up
 * with. The key is just that threads pass the barrier in complete sets; thus, if we
 * examine the sequence of threads that invoke bond and divide them into groups
 * of three, each group should contain one oxygen and two hydrogen threads.
 *
 * @author Anil Chowdhury
 * Created on 9/4/2019
 */
public class WaterDriver {

    public static void main(String[] args) {
        test(5);
    }

    private static void test(int moleculeCount) {
        WaterDriver driver = new WaterDriver();
        List<Atom> atoms = new ArrayList<>();
        Semaphore oxygenPermit = new Semaphore(1);
        Semaphore hydrogenPermit = new Semaphore(2);
        Semaphore bondPermit = new Semaphore(0);
        ConcurrentLinkedQueue<Atom> waterMolecule = new ConcurrentLinkedQueue<>();

        List<String> oxygenNames = driver.randomNameGenerator("Oxygen", moleculeCount);
        List<String> hydrogenNames = driver.randomNameGenerator("Hydrogen", 2 * moleculeCount);

        atoms.addAll(oxygenNames.stream().map(oxygenName -> new Oxygen(oxygenName, oxygenPermit,
                hydrogenPermit, bondPermit, waterMolecule)).collect(Collectors.toList()));
        atoms.addAll(hydrogenNames.stream().map(hydrogenName -> new Hydrogen(hydrogenName, oxygenPermit,
                hydrogenPermit, bondPermit, waterMolecule)).collect(Collectors.toList()));
        Collections.shuffle(atoms);

        atoms.forEach(Thread::start);
        atoms.forEach(atom -> {
            try {
                atom.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> randomNameGenerator(String namePrefix, int size) {
        List<String> names = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            names.add(namePrefix + "-" + i);
        }
        return names;
    }
}
