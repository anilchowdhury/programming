package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anil Chowdhury
 *         Created on 8/8/2018.
 */
public class TextJustification {

    public static void main(String[] args) {
        TextJustification driver = new TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words1 = {"What","must","be","acknowledgment","shall","be"};
        String[] words2 = {"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"};
        print(driver.getJustifiedLines(words, 16));
        print(driver.getJustifiedLines(words1, 16));
        print(driver.getJustifiedLines(words2, 20));
        System.out.println(check());
    }

    private static int check() {
        int x = 8;
        try {
            return x + 1;
        } finally {
            System.out.println(x + 2);
        }
    }

    private List<String> getJustifiedLines(String[] words, int maxWidthOfLine) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int totalCharacterInThisLine = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                //1 is added for extra space after each word
                if (words[last].length() + totalCharacterInThisLine + 1 > maxWidthOfLine) break;
                totalCharacterInThisLine += words[last].length() + 1;
                last++;
            }

            StringBuilder builder = new StringBuilder();
            int numberOfWordsInThisLine = last - index - 1;

            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || numberOfWordsInThisLine == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i]).append(" ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < maxWidthOfLine; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaceToBeAddedForEachWord
                        = (maxWidthOfLine - totalCharacterInThisLine) / numberOfWordsInThisLine;
                int extraSpace = (maxWidthOfLine - totalCharacterInThisLine) % numberOfWordsInThisLine;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    //give one extra space to each word
                    int spaceForThisWord = spaceToBeAddedForEachWord + ((i - index) < extraSpace ? 1 : 0);
                    if (i < last - 1) {
                        for (int j = 0; j <= spaceForThisWord; j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        return lines;
    }

    private static void print(List<String> justifiedLines) {
        justifiedLines.forEach(System.out::println);
        System.out.println("--------------------");
    }
}
