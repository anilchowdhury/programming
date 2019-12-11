package oldNotes;

/**
 * Given a initial number x and two operations which are given below:
 * Multiply number by 2.
 * Subtract 1 from the number.
 * The task is to find out minimum number of operation required to convert number x into y using only above
 * two operations. We can apply these operations any number of times.
 * Constraints: 1 <= x, y <= 10000
 *
 * @author Anil Chowdhury
 *         Created on 2/19/2019.
 */
public class MinOperationsConvertXToY {
    public static void main(String[] args) {
        MinOperationsConvertXToY driver = new MinOperationsConvertXToY();
        System.out.println(driver.minOperationsXToY(4, 7));
        System.out.println(driver.minOperationsXToY(2, 5));
        System.out.println(driver.minOperationsXToY(3, 11));
        System.out.println(driver.minOperationsXToY(15, 20));
        System.out.println(driver.minOperationsXToY(12, 8));
        System.out.println(driver.minOperationsXToY(0, 8));
        System.out.println(driver.minOperationsXToY(13, 13));
    }

    private int minOperationsXToY(int source, int target) {
        return minOperationsXToYUtil(source, target);
    }

    private int minOperationsXToYUtil(int source, int target) {
        if (source == target) {
            return 0;
        }
        if (source <= 0) {
            return -1;
        }
        int minSteps = 0;
        int threshold = (target % 2 == 1) ? target / 2 + 1 : target / 2;
        while (source != target) {
            if (source <= threshold) {
                source = 2 * source;
            } else {
                source = source - 1;
            }
            minSteps++;
        }
        return minSteps;
    }
}
