package epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anchowdh on 11/14/2017.
 *
 */
public class SubsetOfSizeK {

    public static void main(String[] args) {
        List<Integer> inputs = new ArrayList<>();
        inputs.add(1);
        inputs.add(2);
        inputs.add(3);
        inputs.add(4);
        inputs.add(5);
        printAllSubsetSizeK(inputs, 3);
    }

    private static void printAllSubsetSizeK(List<Integer> inputs, int subSetSize) {
        int inputSize = inputs.size();
        List<Integer> subSetSoFar = new ArrayList<>();
        generateAllSubsetOfSizeK(inputs, 0, inputSize, subSetSize, subSetSoFar);
    }

    private static void generateAllSubsetOfSizeK(List<Integer> inputs, int start, int inputSize,
                                                 int subSetSize, List<Integer> subSetSoFar) {

        if (subSetSize == 0) {
            System.out.println(Arrays.toString(subSetSoFar.toArray()));
            return;
        }

        for (int i = start; (inputSize - i) >= subSetSize; i++) {
            subSetSoFar.add(inputs.get(i));
            generateAllSubsetOfSizeK(inputs, i + 1, inputSize, subSetSize - 1, subSetSoFar);
            subSetSoFar.remove(inputs.get(i));
        }
    }
}
