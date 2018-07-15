package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 The faculty of application management and consulting services (FAMCS) of the Berland State University (BSU) has
 always been popular among Berland's enrollees. This year, N students attended the entrance exams, but no more than
 K will enter the university. In order to decide who are these students, there are series of entrance exams. All the
 students with score strictly greater than at least (N-K) students' total score gets enrolled.

 In total there are E entrance exams, in each of them one can score between 0 and M points, inclusively.
 The first E-1 exams had already been conducted, and now it's time for the last tribulation.

 Sergey is the student who wants very hard to enter the university, so he had collected the information about the
 first E-1 from all N-1 enrollees (i.e., everyone except him). Of course, he knows his own scores as well.

 In order to estimate his chances to enter the University after the last exam, Sergey went to a fortune teller. From
 the visit, he learnt about scores that everyone except him will get at the last exam. Now he wants to calculate the
 minimum score he needs to score in order to enter to the university. But now he's still very busy with minimizing
 the amount of change he gets in the shops, so he asks you to help him.

 Input

 The first line of the input contains an integer T denoting the number of test cases. The description of T test
 cases follows.

 The first line of each test case contains four space separated integers N, K, E, M denoting the number of students,
 the maximal number of students who'll get enrolled, the total number of entrance exams and maximal number of points
 for a single exam, respectively.

 The following N-1 lines will contain E integers each, where the first E-1 integers correspond to the scores of the
 exams conducted. The last integer corresponds to the score at the last exam, that was predicted by the fortune-teller.

 The last line contains E-1 integers denoting Sergey's score for the first E-1 exams.

 Output
 For each test case, output a single line containing the minimum score Sergey should get in the last exam in order to
 be enrolled. If Sergey doesn't have a chance to be enrolled, output "Impossible" (without quotes).

 Constraints
 -----------
 1 ≤ T ≤ 5
 1 ≤ K < N ≤ 10^4
 1 ≤ M ≤ 10^9
 1 ≤ E ≤ 4

 Example

 Input:
 1
 4 2 3 10
 7 7 7
 4 6 10
 7 10 9
 9 9

 Output:
 4
 */

class EntranceExam {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int totalTestCases = Integer.parseInt(getNextLine(reader));
            int counter = 0;
            while (counter < totalTestCases) {
                String[] inputs = getNextLine(reader).split("\\s+");
                int numOfStudents = Integer.parseInt(inputs[0]);
                int acceptableStudents = Integer.parseInt(inputs[1]);
                int numOfExam = Integer.parseInt(inputs[2]);
                long maxMarks = Integer.parseInt(inputs[3]);
                System.out.println(solve(numOfStudents, acceptableStudents, numOfExam, maxMarks, reader));
                counter++;
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String solve(int numOfStudents, int acceptableStudents, int numOfExam, long maxMarks,
                                BufferedReader reader) throws CodeChefException {
        if (acceptableStudents >= numOfStudents) {
            return String.valueOf(0);
        }

        List<Long> totalMarksOfAllStudents = new ArrayList<>(numOfStudents - 1);
        for (int student = 0; student < numOfStudents - 1; student++) {
            totalMarksOfAllStudents.add(getTotalMarks(numOfExam, reader));
        }
        long totalMarksForSergey = getTotalMarks(numOfExam - 1, reader);
        Collections.sort(totalMarksOfAllStudents);
        Long markToBeat = totalMarksOfAllStudents.get(numOfStudents - acceptableStudents - 1);
        long markToObtainInFinalExam = Math.max(markToBeat - totalMarksForSergey + 1, 0);

        if(markToObtainInFinalExam >= maxMarks) {
            return "Impossible";
        }
        return String.valueOf(markToObtainInFinalExam);
    }

    private static long getTotalMarks(int numOfExam, BufferedReader reader) throws CodeChefException {
        String[] marks = getNextLine(reader).split("\\s+");
        if (marks.length != numOfExam) {
            throw new CodeChefException("Invalid input");
        }
        long totalMarks = 0;
        for (int j = 0; j < numOfExam; j++) {
            totalMarks += Long.parseLong(marks[j]);
        }
        return totalMarks;
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
