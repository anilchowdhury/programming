package oldNotes;

/**
 * Check if it is possible to transform one string to another
 * Given two strings s1 and s2(call letters in uppercase). Check if it is possible to
 * convert s1 to s2 by performing following operations.
 * 1. Make some lowercase letters uppercase.
 * 2. Delete all the lowercase letters.
 *
 * Examples:
 * Input : s1 = daBcd s2 = ABC
 * Output : yes
 * Explanation : daBcd -> dABCd -> ABC
 * Covert a and b at index 1 and 3 to
 * upper case, delete the rest those are
 * lowercase. We get the string s2.
 *
 * Input : s1 = argaju    s2 = RAJ
 * Output : yes
 * Explanation : argaju -> aRgAJu -> RAJ
 * convert index 1, 3 and 4 to uppercase
 * and then delete. All lowercase letters
 *
 * Input : s1 = ABcd s2= BCD
 * Output : NO
 *--------------------------------------------------------------------------------------------
 *
 * Scan from right both the string
 * If source char is Upper case, then is must match with target character
 *  (as we cannot delete the uppercase character
 *
 * If source char is Lower case, then it may or may not match with target character,
 * as we can delete lower case character. We need to convert the source character to
 * upper case before matching with target character
 *
 * Once scan is over, if there are characters left is target then return false
 * If there are characters left in source, then they must all be in lower case
 * else return false
 *
 * @author Anil Chowdhury
 * Created on 10/29/2019
 *
 */
public class TransformOneStringToAnother {

    public static void main(String[] args) {
        TransformOneStringToAnother driver = new TransformOneStringToAnother();
        System.out.println(driver.isTransformationPossible("daBcd", "ABC") ? "Yes" : "No");//true
        System.out.println(driver.isTransformationPossible("argaju", "RAJ") ? "Yes" : "No");//true
        System.out.println(driver.isTransformationPossible("ABcd", "BCD") ? "Yes" : "No");//false
        System.out.println(driver.isTransformationPossible("Bcd", "EBCD") ? "Yes" : "No");//false
        System.out.println(driver.isTransformationPossible("aBcd", "BCD") ? "Yes" : "No");//true
        System.out.println(driver.isTransformationPossible("ABcd", "BCD") ? "Yes" : "No");//false
    }

    private boolean isTransformationPossible(String source, String target) {
        int sourceIndex = source.length() - 1;
        int targetIndex = target.length() - 1;

        while (sourceIndex >= 0 && targetIndex >= 0) {
            char sourceCharacter = source.charAt(sourceIndex);
            char targetCharacter = target.charAt(targetIndex);

            if (Character.isUpperCase(sourceCharacter)) {
                if (sourceCharacter != targetCharacter) {
                    return false;
                }
                targetIndex--;
            } else {
                if (Character.toUpperCase(sourceCharacter) == targetCharacter) {
                    targetIndex--;
                }
                //if target character does not match then we can delete
                //them as the source character is lowercase
            }
            sourceIndex--;
        }

        if (targetIndex >= 0) {
            //target still has character left
            return false;
        }

        if (sourceIndex >= 0) {
            //source has character left, but if all are lowercase then it can be deleted
            return allLowerCase(source, sourceIndex);
        }
        return true;
    }

    private boolean allLowerCase(String source, int sourceIndex) {
        for (int index = sourceIndex; index >= 0; index--) {
            if (Character.isUpperCase(source.charAt(index))) {
                return false;
            }
        }
        return true;
    }
}
