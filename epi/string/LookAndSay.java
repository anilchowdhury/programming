package epi.string;

/**
 *
 * The look-and-say sequence starts with 1. Subsequent numbers are derived by de¬
 * scribing the previous number in terms of consecutive digits. Specifically, to generate
 * an entry of the sequence from the previous entry, read off the digits of the previ¬
 * ous entry, counting the number of digits in groups of the same digit. For exam¬
 * ple, 1; one 1; two Is; one 2 then one 1; one 1, then one 2, then two Is; three Is,
 * then two 2s, then one 1. The first eight numbers in the look-and-say sequence are
 * <1,11, 21,1211,111221,312211,13112221,1113213211>.
 *
 */
public class LookAndSay {
    public static void main(String[] args) {
        lookAndSay(1);
        lookAndSay(2);
        lookAndSay(3);
        lookAndSay(4);
        lookAndSay(5);
        lookAndSay(6);
        lookAndSay(7);
        lookAndSay(8);
        lookAndSay(9);
    }

    private static void lookAndSay(int n) {
        if (n == 1) {
            System.out.println("For [n = 1] --> 1");
            return;
        }
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = sayIt(result);
        }
        System.out.println("For [n = " + n + "] --> " + result);
    }

    private static String sayIt(String previousResult) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < previousResult.length(); i++) {
            int count = 1;
            while ((i + 1 < previousResult.length()) && (previousResult.charAt(i) == previousResult.charAt(i + 1))) {
                i++;
                count++;
            }
            result.append(count);
            result.append(previousResult.charAt(i));
        }
        return result.toString();
    }
}
