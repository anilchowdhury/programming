package string;

/**
 * @author Anil Chowdhury
 * Created on 4/10/2020
 *
 * The below code works only if the string does not common characters
 */
public class InterLeavingStrings {

    public static void main(String[] args) {
        InterLeavingStrings driver = new InterLeavingStrings();
        System.out.println(driver.interLeavingCount("ab", "")); //1
        System.out.println(driver.interLeavingCount("ab", "c")); //3
        System.out.println(driver.interLeavingCount("cd", "ab")); //6
        System.out.println(driver.interLeavingCount("cd", "c")); //6
    }

    private int interLeavingCount(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }

        if (s1.isEmpty() && !s2.isEmpty()) {
            return 1;
        }

        if (!s1.isEmpty() && s2.isEmpty()) {
            return 1;
        }
        return interLeavingCountHelper(s1, s2);
    }

    private int interLeavingCountHelper(String s1, String s2) {
        int row = s1.length();
        int col = s2.length();
        int[][] count = new int[row + 1][col + 1];

        count[0][0] = 0;
        for (int i = 1; i <= row; i++) {
            count[i][0] = 1;
        }
        for (int j = 1; j <= col; j++) {
            count[0][j] = 1;
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                count[i][j] = count[i][j - 1] + count[i - 1][j];
            }
        }
        return count[row][col];
    }
}
