package epi.array;

/**
 *
 * In a particular board game, a player has to try to advance through a sequence of
 * positions. Each position has a non-negative integer associated with it, representing
 * the maximum you can advance from that position in one move. You begin at the first
 * position, and win by getting to the last position. For example, let A = (3,3,1,0, 2, 0,1}
 * represent the board game, i.e., the ith entry in A is the maximum we can advance
 * from i. Then the game can be won by the following sequence of advances through
 * A: take 1 step from A[0] to A[1], then 3 steps from A[l] to A[4], then 2 steps from
 * A[4] to A[6], which is the last position. Note that A[0] = 3 > 1, A[l] = 3 > 3, and
 * A[4] = 2 > 2, so all moves are valid. If A instead was (3, 2, 0,0, 2, 0,1), it would not
 * possible to advance past position 3, so the game cannot be won.
 * Write a program which takes an array of n integers, where A[i] denotes the maximum
 * you can advance from index i, and returns whether it is possible to advance to the
 * last index starting from the beginning of the array
 *
 */

public class AdvancingThroughAnArray {

    public static void main(String[] args) {
        int moves1[] = {3, 3, 1, 0, 2, 0, 1};
        int moves2[] = {3, 2, 0, 0, 2, 0, 1};
        int moves3[] = {2, 4, 1, 1, 0, 2, 3};
        System.out.println(possibleToMove(moves1) ? "Yes" : "No");
        System.out.println(possibleToMove(moves2) ? "Yes" : "No");
        System.out.println(possibleToMove(moves3) ? "Yes" : "No");

        System.out.println(epiSolution(moves1.length - 1, moves1) ? "Yes" : "No");
        System.out.println(epiSolution(moves2.length - 1, moves2) ? "Yes" : "No");
        System.out.println(epiSolution(moves3.length - 1, moves3) ? "Yes" : "No");
    }

    private static boolean possibleToMove(int[] moves) {
        return solve(0, moves.length - 1, moves);
    }

    private static boolean solve(int currentI, int destinationI, int[] moves) {
        if (currentI == destinationI) {
            return true;
        }
        for (int steps = 1; moves[currentI] != 0 && steps <= moves[currentI]; steps++) {
            int nextI = currentI + steps;
            if (solve(nextI, destinationI, moves)) {
                return true;
            }
        }
        return false;
    }

    private static boolean epiSolution(int destinationI, int[] moves) {
        int maxReachedIndex = 0;
        for (int i = 0; i <= maxReachedIndex && maxReachedIndex < destinationI; i++) {
            maxReachedIndex = Math.max(i + moves[i], maxReachedIndex);
        }
        return maxReachedIndex >= destinationI;
    }
}
