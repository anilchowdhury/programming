package oldNotes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary matrix of size NxN where 1 denotes that the number i can be converted to j, and 0 denotes it
 * cannot be converted to. Also given are two numbers X(<N)and Y(<N), the task is to find the minimum number of
 * steps required to convert the number X to Y. If there is no such way possible, print -1
 *
 * @author Anil Chowdhury
 *         Created on 2/18/2019.
 */
public class MinStepsXtoY {
    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

        int[][] matrix1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

        MinStepsXtoY driver = new MinStepsXtoY();
        System.out.println(driver.minSteps(matrix, 2, 3));
        System.out.println(driver.minSteps(matrix1, 8, 4));
    }


    private int minSteps(int[][] matrix, int x, int y) {
        if (matrix == null) {
            return 0;
        }

        int minSteps = 0;
        boolean[] visited = new boolean[matrix.length];
        Queue<QItem> currentNodes = new LinkedList<>();
        currentNodes.add(new QItem(x, 0));
        visited[x] = true;

        while (!currentNodes.isEmpty() && x != y) {
            QItem current = currentNodes.poll();
            x = current.x;
            minSteps = current.steps;

            for (int j = 0; j < matrix[0].length; j++) {
                if ((matrix[x][j] == 1) && !visited[j]) {
                    currentNodes.add(new QItem(j, current.steps + 1));
                    visited[j] = true;
                }
            }
        }
        return x == y ? minSteps : -1;
    }

    private static class QItem {
        private int x;
        private int steps;

        private QItem(int x, int steps) {
            this.x = x;
            this.steps = steps;
        }
    }
}
