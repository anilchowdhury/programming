package epi.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Each digit, apart from 0 and 1, in a phone keypad corresponds to one of three or four
 * letters of the alphabet, as shown in Figure 7.1 on the next page. Since words are easier
 * to remember than numbers, it is natural to ask if a 7 or 10-digit phone number can
 * be represented by a word. For example, "2276696" corresponds to "ACRONYM" as
 * well as "ABPOMZN".
 * Write a program which takes as input a phone number, specified as a string of digits,
 * and returns all possible character sequences that correspond to the phone number.
 * The cell phone keypad is specified by a mapping that takes a digit and returns the
 * corresponding set of characters. The character sequences do not have to be legal
 * words or phrases.
 */

public class PhoneMnemonic {

    private static final String mapping[] = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static void main(String[] args) {
        printMnemonic(finaAllMnemonics("27"));
    }

    private static void printMnemonic(List<StringBuilder> mnemonics) {
        for (StringBuilder mnemonic : mnemonics) {
            System.out.println(mnemonic);
        }
    }

    private static List<StringBuilder> finaAllMnemonics(String phoneNumber) {
        Queue<StringBuilder> mnemonics = new LinkedList<>();
        mnemonics.add(new StringBuilder(""));

        //this loop is for processing each digit of phone number
        for (int i = 0; i < phoneNumber.length(); i++) {
            char digit = phoneNumber.charAt(i);
            int size = mnemonics.size();

            //this loop is processing each intermediate result formed so far
            for (int j = 0; j < size; j++) {
                String mappedCharacters = mapping[(digit - '0')];
                StringBuilder intermediateResult = mnemonics.poll();

                //this loop is for appending mapped character to all the intermediate results one by one
                for (int k = 0; k < mappedCharacters.length(); k++) {
                    mnemonics.add(new StringBuilder(intermediateResult.toString() + mappedCharacters.charAt(k)));
                }
            }
        }
        return new ArrayList<>(mnemonics);
    }
}
