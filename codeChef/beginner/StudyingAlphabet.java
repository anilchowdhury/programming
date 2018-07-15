package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 Not everyone probably knows that Chef has younder brother Jeff. Currently Jeff learns to read.
 He knows some subset of the letter of Latin alphabet. In order to help Jeff to study, Chef gave him a book with the
 text consisting of N words. Jeff can read a word iff it consists only of the letters he knows.
 Now Chef is curious about which words his brother will be able to read, and which are not. Please help him!

 Input
 -----
 The first line of the input contains a lowercase Latin letter string S, consisting of the letters Jeff can read.
 Every letter will appear in S no more than once.
 The second line of the input contains an integer N denoting the number of words in the book.
 Each of the following N lines contains a single lowecase Latin letter string Wi, denoting the ith word in the book.

 Output
 ------
 For each of the words, output "Yes" (without quotes) in case Jeff can read it, and "No" (without quotes) otherwise.

 Constraints
 -----------
 1 ≤ |S| ≤ 26
 1 ≤ N ≤ 1000
 1 ≤ |Wi| ≤ 12
 Each letter will appear in S no more than once.
 S, Wi consist only of lowercase Latin letters.

 Subtasks
 Subtask #1 (31 point): |S| = 1, i.e. Jeff knows only one letter.
 Subtask #2 (69 point)	: no additional constraints
 Example

 Input:
 act
 2
 cat
 dog

 Output:
 Yes
 No
 */
class StudyingAlphabet {

    private static final String YES = "Yes";
    private static final String NO = "No";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String lettersKnownToJeff = getNextLine(reader);
            Set<Character> dictionary = new HashSet<>();
            for (int i = 0; i < lettersKnownToJeff.length(); i++) {
                dictionary.add(lettersKnownToJeff.charAt(i));
            }
            int numOfTest = Integer.parseInt(getNextLine(reader));
            int counter = 0;
            while (counter < numOfTest) {
                String word = getNextLine(reader);
                System.out.println(canRead(word, dictionary) ? YES : NO);
                counter++;
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean canRead(String word, Set<Character> dictionary) {
        for (int i = 0; i < word.length(); i++) {
            if (!dictionary.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
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
