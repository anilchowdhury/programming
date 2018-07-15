package oldNotes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anil Chowdhury
 *         Created on 5/17/2018.
 */
public class PrintMatrixDiagonally {

    public static void main(String[] args) {
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

        printDiagonally(matrix);
    }

    private static void printDiagonally(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        for (int lines = 0; lines <= (row + column - 1); lines++) {
            int start_row = lines <= row - 1 ? lines : row - 1;
            int start_col = lines <= row - 1 ? 0 : lines + 1 - row;
            int count = Math.min(lines + 1, Math.min(column - start_col, row));

            for (int j = 0; j < count; j++) {
                System.out.print(matrix[start_row--][start_col++] + " ");
            }
            System.out.println();
        }
    }
}
