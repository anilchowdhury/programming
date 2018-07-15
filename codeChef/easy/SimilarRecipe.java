package codeChef.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 Chef has learned a new technique for comparing two recipes. A recipe contains a list of ingredients in increasing order
 of the times they will be processed. An ingredient is represented by a letter 'a'-'z'. The i-th letter in a recipe
 denotes the i-th ingredient. An ingredient can be used multiple times in a recipe.

 The technique is as follows. Compare two recipes by comparing their respective lists. If the sets of ingredients used
 in both recipes are equal and each ingredient is used the same number of times in both of them (processing order does
 not matter), they are declared as granama recipes. ("granama" is the Chef-ian word for "similar".)

 Chef took two recipes he invented yesterday. He wanted to compare them using the technique. Unfortunately, Chef forgot
 to keep track of the number of times each ingredient has been used in a recipe. He only compared the ingredients but
 NOT their frequencies. More precisely, Chef considers two recipes as granama if there are no ingredients which are
 used in one recipe and not used in the other recipe.

 Your task is to report whether Chef has correctly classified the two recipes (as granama or not granama) although he
 forgot to keep track of the frequencies.

 Input
 -----
 The first line of the input contains a single integer T denoting the number of test cases. The description for T test
 cases follows. Each test case consists of a single line containing two space-separated strings R and S denoting the
 two recipes.

 Output
 ------
 For each test case, output a single line containing "YES" (quotes for clarity) if Chef correctly classified the two
 recipes as granama or not granama. Otherwise, output a single line containing "NO" (quotes for clarity) if Chef
 declared two recipes as granama when they actually are not.

 Constraints
 -----------
 1 ≤ T ≤ 100
 1 ≤ |R|, |S| ≤ 1000

 Example
 Input:
 3
 alex axle
 paradise diapers
 alice bob

 Output:
 -------
 YES
 NO
 YES

 Explanation:
 ------------
 Example case 1: Chef declared them as granama recipes. They are actually granama because the sets of ingredients and
 the number of times each ingredient has been used are equal. The Chef got it right!

 Example case 2: Chef declared them as granama recipes because both sets of ingredients are equal. But they are NOT
 granama since ingredient 'a' has been used twice in the first recipe but only once in the second. The Chef was
 incorrect!

 Example case 3: Chef declare them as not granama. They are not granama as the sets of ingredients are different.
 Hence, the Chef was right!

 */
class SimilarRecipe {

    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final Character[] charArray = {'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'};

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int numOfTest = Integer.parseInt(getNextLine(reader));
            while (numOfTest > 0) {
                String[] split = getNextLine(reader).split("\\s+");
                assert split.length == 2;
                System.out.println(verifyResult(split[0], split[1]) ? YES : NO);
                numOfTest--;
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean verifyResult(String firstRecipe, String secondRecipe) {
        return correctMethod(firstRecipe, secondRecipe) == wrongChefMethod(firstRecipe, secondRecipe);
    }

    private static boolean correctMethod(String firstRecipe, String secondRecipe) {
        Map<Character, Integer> firstRecipeCountMap = getAlphabetCountMap(firstRecipe);
        Map<Character, Integer> secondRecipeCountMap = getAlphabetCountMap(secondRecipe);

        for (char c : charArray) {
            int countInFirstRecipe = firstRecipeCountMap.get(c);
            int countInSecondRecipe = secondRecipeCountMap.get(c);
            if (countInFirstRecipe != countInSecondRecipe) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> getAlphabetCountMap(String recipe) {
        Map<Character, Integer> countMap = getEmptyCountMap();
        for (char c : recipe.toCharArray()) {
            countMap.put(c, countMap.get(c) + 1);

        }
        return countMap;
    }

    private static Map<Character, Integer> getEmptyCountMap() {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : charArray) {
            countMap.put(c, 0);
        }
        return countMap;
    }

    private static boolean wrongChefMethod(String firstRecipe, String secondRecipe) {
        Map<Character, Boolean> firstRecipePresenceMap = getAlphatbetPresenceMap(firstRecipe);
        Map<Character, Boolean> secondRecipePresenceMap = getAlphatbetPresenceMap(secondRecipe);
        for (char c : charArray) {
            if (firstRecipePresenceMap.get(c) != secondRecipePresenceMap.get(c)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Boolean> getAlphatbetPresenceMap(String recipe) {
        Map<Character, Boolean> presenceMap = getEmptyPresenceMap();
        for (char c : recipe.toCharArray()) {
            presenceMap.put(c, true);
        }
        return presenceMap;
    }

    private static Map<Character, Boolean> getEmptyPresenceMap() {
        Map<Character, Boolean> digitPresenceMap = new HashMap<>();
        for (char c : charArray) {
            digitPresenceMap.put(c, false);
        }
        return digitPresenceMap;
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
