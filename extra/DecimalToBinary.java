package oldNotes;

import java.util.Calendar;

/**
 * @author Anil Chowdhury
 *         Created on 5/30/2018.
 */
public class DecimalToBinary {

    public static void main(String[] args) {
        System.out.println(convertDecimalToBinary(5));
        System.out.println(convertDecimalToBinary(6));
        System.out.println(convertDecimalToBinary(16));
        System.out.println(convertDecimalToBinary(35));
    }

    private static String convertDecimalToBinary(int number) {

        if (number <= 1) {
            return Integer.toString(number);
        }
        return  convertDecimalToBinary(number / 2) + Integer.toString(number % 2);
    }
}



