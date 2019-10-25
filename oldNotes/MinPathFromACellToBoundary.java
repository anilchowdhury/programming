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
 * Search all the boundary elements
 *      if its 2, then 2 is already at the boundary return 0
 *      if its 0, then recursively try to find the smallest path from this cell
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

        int[][] matrix4 = {
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 0, 0, 1},
        };

        MinPathFromACellToBoundary driver = new MinPathFromACellToBoundary();
        System.out.println("Min path length = " + driver.minSteps(matrix)); //print 2
        System.out.println("Min path length = " + driver.minSteps(matrix1)); //print -1
        System.out.println("Min path length = " + driver.minSteps(matrix2)); //print 4
        System.out.println("Min path length = " + driver.minSteps(matrix3)); //print 0
        System.out.println("Min path length = " + driver.minSteps(matrix4)); //print -1
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

        if (!isInRange(x, y, row, column) || matrix[x][y] == 1) {
            //if outside range or there is no path
            return Integer.MAX_VALUE;
        }
        if (visited[x][y]) {
            return dp[x][y];
        }

        visited[x][y] = true;
        if (matrix[x][y] == 2) {
            dp[x][y] = 0;
            return dp[x][y];
        }

        int minLength = Integer.MAX_VALUE;
        int minPathFromTop = findMinPathToCell2(matrix, x - 1, y, row, column, dp, visited);
        int minPathFromRight = findMinPathToCell2(matrix, x, y + 1, row, column, dp, visited);
        int minPathFromBottom = findMinPathToCell2(matrix, x + 1, y, row, column, dp, visited);
        int minPathFromLeft = findMinPathToCell2(matrix, x, y - 1, row, column, dp, visited);

        int minLengthFromAllDirections = Math.min(minPathFromLeft, Math.min(minPathFromRight,
                Math.min(minPathFromTop, minPathFromBottom)));

        if (minLengthFromAllDirections != Integer.MAX_VALUE) {
            minLength = 1 + minLengthFromAllDirections;
        }

        return dp[x][y] = minLength;
    }

    private void initializeWithIntegerMax(int[][] dp) {
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
    }

    private boolean isInRange(int x, int y, int row, int column) {
        return (x >= 0 && x < row) && (y >= 0 && y < column);
    }
}
