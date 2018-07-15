package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 Chef is a well known programmer. He owns a brand new Android phone of screen size of dimension n × m (n denotes the
 height of the phone and m denotes the width). Chef wants to design a program for painting the screen. He figured out
 c colors, which he will use to paint the screen. He wants to paint some rectangular section of pixels of the screen
 each with different color. Also Chef would like to use all of his c colors in painting.

 Can you help Chef in finding number of different dimensions of rectangular section he can paint? In other words, find
 number of pairs x, y such that Chef can paint a rectangular section of dimension x × y (x denotes height, y denotes
 width) on the screen. Please note that Chef uses a fix orientation of his phone and is not allowed to rotate it, i.e.
 height always stays n and width always stays m and not the other way around.

 Input
 -----
 First line of the input contains an integer T denoting the number of test cases. T test cases follow.
 Only line of each test case contains three space separated integers n, m, c.

 Output
 ------
 For each test case, output a single line containing the answer for the test case.
 Constraints
 1 ≤ T ≤ 100
 1 ≤ n, m ≤ 10^6
 1 ≤ c ≤ 10^6

 Subtasks
 --------
 Subtask #1: (40 points) 1 ≤ n, m ≤ 100, 1 ≤ c ≤ 104
 Subtask #2: (60 points) original constraints
 Example

 Input:
 2
 4 6 12
 3 3 10

 Output:
 3
 0

 Explanation
 -----------
 Test case 1. Possible pairs of dimensions are (2, 6), (3, 4) and (4, 3). Note that the rectangular section of dimension
 (1, 12) can't be painted as it can't fit into the screen, because 12 > 6.

 Test case 2. There does not exist any rectangle of desired dimensions which can have 10 different pixels painted.


 PROBLEM
 Find number of (x,y) pairs such that 1 ≤ x≤ H, 1 ≤ y ≤ W  and x∗y=K

 QUICK EXPLANATION
 Iterate over x and you can check if there exists a valid y in the desired range satisfying x⋅y=K or not.

 EXPLANATION
 O(H∗W) bruteforce solution
 Constraints on HH and WW are quite small. We can exploit these to get a simple bruteforce solution. We iterate over all
 (x,y) pairs and check whether their product x∗yx∗y is equal to KK or not. We can count such valid pairs in
 O(W∗H) time.

 Pseudo Code:

 ans = 0
 for x = 1 to H:
 for y = 1 to W:
 if x * y == K:
 ans++
 O(KlogK) solution
 Let us make a simple change in the last solution? What if we you stop iterating over yy when the value of x∗yx∗y
 exceeds KK. Will that improve time complexity?

 ans = 0
 for x = 1 to H:
 for y = 1 to W:
 if x * y > K:
 break;
 if x * y == K:
 ans++
 Yes, it will. Let us estimate it. From a casual look, it looks that its time complexity will still be O(H∗W)O(H∗W).
 But it's not. Let us delve into depth.

 For each x, the inner loop over y will run at most K/x times. So, total number of iterations the program will be run
 will be given by


 K/1+K/2+ ⋯ +K/H
 ≤K/1+K/2+ ⋯ +K/K
 ≤K⋅(1/1+1/2+⋯+1/K)

 The expression
 1/1+1/2+ ⋯ +1/n
  is also known as harmonic sum Hn. One can prove that Hn=O(logn)

 Hence, the time complexity will be O(KlogK).

 O(H)solution
 ------------
 Let us exploit the condition x∗y=Kx∗y=K. For a fixed x, do we really need to iterate over all yy's to check how many
 of them satisfy x∗y=K. It turns out, no. Firstly there will be at most a single y. If K is not divisible by x, then
 there can't exist such y. Otherwise y will be K/x.

 In summary, we iterate over only x values and find the corresponding y (if it exists), and check whether the y is ≥1
 and ≤H≤H.

 Time complexity of this method will be O(H), as are iterating over x values only once.

 Factorization based solutions
 If x⋅y=K, then both x and y should divide K. So we can find all the divisors of the K. Let x be one such divisor,
 then y will be K/x.

 You can find all the divisors of KK in O(sqrt(K)) time.
 */

class KeyboardPaint {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int numOfTest = Integer.parseInt(getNextLine(reader));
            int counter = 0;
            while (counter < numOfTest) {
                String[] inputs = getNextLine(reader).split("\\s");
                int result = processPixelPainting(inputs);
                System.out.println(result);
                counter++;
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int processPixelPainting(String[] inputs) throws CodeChefException {
        if (inputs.length != 3) {
            throw new CodeChefException("Invalid inputs");
        }
        int result = 0;
        int height = Integer.parseInt(inputs[0]);
        int width = Integer.parseInt(inputs[1]);
        int totalColors = Integer.parseInt(inputs[2]);

        for (int x = 1; x <= height; x++) {
            if (totalColors % x == 0) {
                int y = totalColors / x;
                if (y <= width) {
                    result++;
                }
            }
        }
        return result;
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
