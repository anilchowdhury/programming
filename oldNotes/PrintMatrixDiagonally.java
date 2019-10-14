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

        driver.printDiagonally(matrix);
        driver.printDiagonally(matrix1);
        driver.printDiagonally(matrix2);
    }

    private void printDiagonally(int[][] matrix) {
        int ROW_SIZE = matrix.length;
        int COLUMN_SIZE = matrix[0].length;

        for (int lines = 0; lines < (ROW_SIZE + COLUMN_SIZE); lines++) {
            // If lines is greater than the ROW_SIZE, then consider only
            // last row elements
            int start_row = lines < ROW_SIZE ? lines : ROW_SIZE - 1;

            // If lines is smaller than the ROW_SIZE, then consider only
            // first column
            int start_col = lines < ROW_SIZE ? 0 : lines - ROW_SIZE + 1;

            // Maximum possible elements in Line0 = 1, Line1 = 2, Line2 = 3
            // Maximum possible elements relative to rows = ROW_SIZE
            // Maximum possible elements relative to column = COLUMN_SIZE - start_col
            // So, total elements to be printed for the current line will be minimum
            // of all the 3 possibilities
            int count = Math.min(lines + 1, Math.min(COLUMN_SIZE - start_col, ROW_SIZE));

            for (int j = 0; j < count; j++) {
                System.out.print(matrix[start_row--][start_col++] + " ");
            }
            System.out.println();
        }
    }

    private boolean isLessThanRowSize(int row, int lines) {
        return lines < row;
    }
}

