package oldNotes;

/**
 * @author Anil Chowdhury
 * Created on 10/13/2019
 *
 * Print the boundary elements in spiral ways
 *          ----------------------->
 *        /|\                      |
 *         |                       |
 *         |                       |
 *         |                      \|/
 *         <------------------------
 *
 * Once reach to the starting point reduce
 * the length & width of matrix by size 1
 * and repeat the algorithm
 */
public class PrintMatrixSpiral {

    public static void main(String[] args) {
        PrintMatrixSpiral driver = new PrintMatrixSpiral();

        int[][] matrix = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20}};

        int[][] matrix1 = {{1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 10}};

        int[][] matrix2 = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}};

        int[][] matrix3 = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        driver.printSpiral(matrix);
        driver.printSpiral(matrix1);
        driver.printSpiral(matrix2);
        driver.printSpiral(matrix3);
    }

    private void printSpiral(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        System.out.println("\n---------- Spiral Order ----------");

        int[][] direction = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        int dirIndex = 0;
        int matrixWidth = matrix.length;
        int matrixLength = matrix[0].length;

        int xLowerBound = 0, xUpperBound = matrix.length - 1;
        int yLowerBound = 0, yUpperBound = matrix[0].length - 1;
        int nextX, nextY, x = 0, y = 0;

        for (int index = 0; index < matrixWidth * matrixLength; index++) {

            System.out.print(matrix[x][y] + "\t");

            nextX = x + direction[dirIndex][0];
            nextY = y + direction[dirIndex][1];

            if (outOfBound(xLowerBound, xUpperBound, yLowerBound,
                    yUpperBound, nextX, nextY)) {
                dirIndex++;
                nextX = x + direction[dirIndex][0];
                nextY = y + direction[dirIndex][1];
            }

            if (arrivedAtStartingPoint(xLowerBound, yLowerBound, nextX, nextY)) {
                xLowerBound++;yLowerBound++;xUpperBound--;yUpperBound--;
                nextX = xLowerBound;
                nextY = yLowerBound;
                dirIndex = 0;
                System.out.println();
            }
            x = nextX;
            y = nextY;
        }
    }

    private boolean outOfBound(int xLowerBound, int xUpperBound, int yLowerBound,
                               int yUpperBound, int nextX, int nextY) {
        return !(nextX >= xLowerBound && nextX <= xUpperBound
                && nextY >= yLowerBound && nextY <= yUpperBound);
    }

    private boolean arrivedAtStartingPoint(int xLowerBound, int yLowerBound, int nextX, int nextY) {
        return nextX == xLowerBound && nextY == yLowerBound;
    }
}
