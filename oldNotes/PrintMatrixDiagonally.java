package oldNotes;

/**
 * @author Anil Chowdhury
 *         Created on 5/17/2018.
 */
public class PrintMatrixDiagonally {

    public static void main(String[] args) {

        PrintMatrixDiagonally driver = new PrintMatrixDiagonally();

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


        int[][] matrix3 = {{1, 2, 3, 4, 5}};

        driver.printDiagonally(matrix);
        driver.printDiagonally(matrix1);
        driver.printDiagonally(matrix2);
        driver.printDiagonally(matrix3);
    }

    private void printDiagonally(int[][] matrix) {

        int ROW_SIZE = matrix.length;
        int COLUMN_SIZE = matrix[0].length;

        for (int lines = 1; lines <= (ROW_SIZE + COLUMN_SIZE); lines++) {
            // if lines is greater than the ROW_SIZE, then consider only
            // last row elements
            int startRow = lines <= ROW_SIZE ? lines - 1 : ROW_SIZE - 1;

            // if lines is smaller than the ROW_SIZE, then consider only
            // first column
            int startCol = lines <= ROW_SIZE ? 0 : lines - ROW_SIZE;

            //print as along as the pointer is within the matrix
            while (isWithinMatrix(matrix, startRow, startCol)) {
                System.out.print(matrix[startRow--][startCol++] + "\t");
            }
            System.out.println();
        }
    }

    private boolean isWithinMatrix(int[][] matrix, int startRow, int startCol) {
        return (startRow >= 0) && (startCol < matrix[0].length);
    }

    /*private void printDiagonally(int[][] matrix) {
        int ROW_SIZE = matrix.length;
        int COLUMN_SIZE = matrix[0].length;

        for (int lines = 1; lines <= (ROW_SIZE + COLUMN_SIZE); lines++) {
            // if lines is greater than the ROW_SIZE, then consider only
            // last row elements
            int start_row = lines <= ROW_SIZE ? lines - 1 : ROW_SIZE - 1;

            // if lines is smaller than the ROW_SIZE, then consider only
            // first column
            int start_col = lines <= ROW_SIZE ? 0 : lines - ROW_SIZE;

            // Maximum possible elements in Line0 = 1, Line1 = 2, Line2 = 3
            // Maximum possible elements relative to rows = ROW_SIZE
            // Maximum possible elements relative to column = COLUMN_SIZE - start_col
            // So, total elements to be printed for the current line will be minimum
            // of all the 3 possibilities
            int count = Math.min(COLUMN_SIZE - start_col, start_row + 1);

            for (int j = 0; j < count; j++) {
                System.out.print(matrix[start_row--][start_col++] + " ");
            }
            System.out.println();
        }
    }*/
}

