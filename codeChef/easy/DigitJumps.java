package codeChef.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 Chef loves games! But he likes to invent his own. Now he plays game "Digit Jump". Chef has sequence of digits
 S1, S2,..., SN,. He is staying in the first digit (S1) and want to reach the last digit (SN) in the minimal
 number of jumps.

 While staying in some digit x with index i (digit Si) Chef can jump into digits with indices i - 1 (Si-1) and
 i + 1 (Si+1) but he can't jump out from sequence. Or he can jump into any digit with the same value x.

 Help Chef to find the minimal number of jumps he need to reach digit SN from digit S1.

 Input
 -----
 Input contains a single line consist of string S of length N- the sequence of digits.

 Output
 ------
 In a single line print single integer - the minimal number of jumps he needs.

 Constraints
 -----------
 1 ≤ N ≤ 10^5
 Each symbol of S is a digit from 0 to 9.


 Example
 -------
 Input:
 01234567890

 Output:
 1

 Input:
 012134444444443

 Output:
 4
 */

class DigitJumps {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inputString = getNextLine(reader);
            assert (inputString != null && !inputString.isEmpty());
            System.out.println(findMinimumJump(inputString));
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int findMinimumJump(String inputString) {
        int inputLength = inputString.length();
        if (inputLength <= 1) {
            return 0;
        }
        if (inputLength == 2) {
            return 1;
        }

        Map<Integer, Integer> indexToMinimumJumpMap = new HashMap<>(inputLength);
        indexToMinimumJumpMap.put(0, 0);//for first digit no jump
        indexToMinimumJumpMap.put(1, 1);//for second digit one jump

        Map<Character, Integer> digitToFirstLocationIndexMap = new HashMap<>(10);
        digitToFirstLocationIndexMap.put(inputString.charAt(0), 0);
        char prevDigit = inputString.charAt(1);
        if (!digitToFirstLocationIndexMap.containsKey(prevDigit)) {
            digitToFirstLocationIndexMap.put(prevDigit, 1);
        }

        char currDigit;
        int minJump;
        for (int index = 2; index < inputLength; index++) {
            currDigit = inputString.charAt(index);
            int prevDigitMinJump = indexToMinimumJumpMap.get(index - 1);

            if (!digitToFirstLocationIndexMap.containsKey(currDigit)) {
                minJump = prevDigitMinJump + 1;
                digitToFirstLocationIndexMap.put(currDigit, index);
            } else {
                int digitFirstLocationIndex = digitToFirstLocationIndexMap.get(currDigit);
                int prevFoundLocationMinJump = indexToMinimumJumpMap.get(digitFirstLocationIndex);
                if (digitFirstLocationIndex + 1 == index) {
                    minJump = Math.min(prevFoundLocationMinJump + 1, prevDigitMinJump + 1);
                } else {
                    minJump = min(prevFoundLocationMinJump + 1, prevDigitMinJump + 1,
                            indexToMinimumJumpMap.get(digitFirstLocationIndex + 1) + 2);
                }
            }
            indexToMinimumJumpMap.put(index, minJump);
        }
        return indexToMinimumJumpMap.get(inputLength - 1);
    }

    private static int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }

    private static String getNextLine(BufferedReader reader) throws CodeChefException {
        String nextLine;
        try {
            nextLine = reader.readLine().trim();
        } catch (IOException ex) {
            throw new CodeChefException(ex.getMessage());
        }
        return nextLine;
    }

    private static class CodeChefException extends Exception {
        CodeChefException(String message) {
            super(message);
        }
    }
}
