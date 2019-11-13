package thread.water.bookOfSemaphore.usingCyclicBarrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * @author Anil Chowdhury
 * Created on 9/8/2019
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
        ConcurrentLinkedQueue<Atom> waterMolecule = new ConcurrentLinkedQueue<>();
        CyclicBarrier bondBarrier = new CyclicBarrier(3, new BondTask(waterMolecule, oxygenPermit, hydrogenPermit));

        List<String> oxygenNames = driver.randomNameGenerator("Oxygen", moleculeCount);
        List<String> hydrogenNames = driver.randomNameGenerator("Hydrogen", 2 * moleculeCount);

        atoms.addAll(oxygenNames.stream().map(oxygenName -> new Oxygen(oxygenName, oxygenPermit, bondBarrier,
                waterMolecule)).collect(Collectors.toList()));
        atoms.addAll(hydrogenNames.stream().map(hydrogenName -> new Hydrogen(hydrogenName, hydrogenPermit,
                bondBarrier, waterMolecule)).collect(Collectors.toList()));
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
