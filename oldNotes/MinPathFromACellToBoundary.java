package oldNotes;

import java.util.Arrays;

/**
 * Given an N X M matrix, where ai, j = 1 denotes the cell is not empty, ai, j = 0
 * denotes the cell is empty and ai, j = 2, denotes that you are standing at that
 * cell. You can move vertically up or down and horizontally left or right to any
 * cell which is empty. The task is to find the minimum number of steps to reach
 * any boundary edge of the matrix. Print -1 if not possible to reach any of the
 * boundary edges.
 *
 * https://www.geeksforgeeks.org/minimum-steps-to-reach-any-of-the-boundary-edges-of-a-matrix/
 *
 * @author Anil Chowdhury
 * Created on 10/24/2019
 */
public class MinPathFromACellToBoundary {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 0, 1},
                {1, 0, 2, 0, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 1, 0}};

        int[][] matrix1 = {
                {1, 1, 1, 1, 1},
                {1, 0, 2, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1},
        };

        int[][] matrix2 = {
                {1, 1, 1, 1, 1},
                {1, 0, 2, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 0},
                {1, 1, 0, 0, 1},
        };

        int[][] matrix3 = {
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 2},
                {1, 1, 0, 0, 1},
        };

        MinPathFromACellToBoundary driver = new MinPathFromACellToBoundary();
        System.out.println("Min path length = " + driver.minSteps(matrix)); //print 2
        System.out.println("Min path length = " + driver.minSteps(matrix1)); //print -1
        System.out.println("Min path length = " + driver.minSteps(matrix2)); //print 4
        System.out.println("Min path length = " + driver.minSteps(matrix3)); //print 0
    }

    private int minSteps(int[][] matrix) {
        if (matrix == null) {
            return -1;
        }

        int minLength = Integer.MAX_VALUE;
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp = new int[row][column];
        boolean[][] visited = new boolean[row][column];

        initializeWithIntegerMax(dp);

        //loop for all boundary cells
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; ) {
                if (matrix[x][y] == 2) {
                    // 2 is already at the boundary
                    return 0;
                }

                if (matrix[x][y] == 0) {
                    int minLengthFromThisCell
                            = findMinPathToCell2(matrix, x, y, row, column, dp, visited);
                    minLength = Math.min(minLength, minLengthFromThisCell);
                }
                //select last column if its not first or last row
                y = (x == 0 || x == row - 1) ? (y + 1) : (y + column - 1);
            }
        }
        return minLength != Integer.MAX_VALUE ? minLength : -1;
    }

    private int findMinPathToCell2(int[][] matrix, int x, int y, int row,
                                   int column, int[][] dp, boolean[][] visited) {
        if (visited[x][y]) {
            return dp[x][y];
        }
        visited[x][y] = true;

        if (matrix[x][y] == 1) {
            return dp[x][y];
        }
        if (matrix[x][y] == 2) {
            dp[x][y] = 0;
            return dp[x][y];
        }

        int minLength, minPathFromLeft, minPathFromRight, minPathFromTop, minPathFromBottom;
        minLength = minPathFromLeft = minPathFromRight = minPathFromTop
                = minPathFromBottom = Integer.MAX_VALUE;

        if ((x - 1) >= 0) {
            minPathFromTop = findMinPathToCell2(matrix, x - 1, y, row, column, dp, visited);
        }
        if ((y + 1) < column) {
            minPathFromRight = findMinPathToCell2(matrix, x, y + 1, row, column, dp, visited);
        }
        if ((x + 1) < row) {
            minPathFromBottom = findMinPathToCell2(matrix, x + 1, y, row, column, dp, visited);
        }
        if ((y - 1) >= 0) {
            minPathFromLeft = findMinPathToCell2(matrix, x, y - 1, row, column, dp, visited);
        }

        int minLengthFromAllDirections = Math.min(minPathFromLeft, Math.min(minPathFromRight,
                Math.min(minPathFromTop, minPathFromBottom)));

        if (minLengthFromAllDirections != Integer.MAX_VALUE) {
            minLength = 1 + minLengthFromAllDirections;
        }

        dp[x][y] = minLength;
        return minLength;
    }

    private void initializeWithIntegerMax(int[][] dp) {
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
    }
}