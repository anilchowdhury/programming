package codeChef.beginner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**

 Chef is a private detective. He was asked to investigate a case of murder in the city of Frangton.
 Chef arrived in Frangton to find out that the mafia was involved in the case. Chef spent some time watching for people
 that belong to the clan and was able to build a map of relationships between them. He knows that a mafia's
 organizational structure consists of a single Don, heading a hierarchical criminal organization. Each member reports
 exactly to one other member of the clan. It's obvious that there are no cycles in the reporting system of the mafia.

 There are N people in the clan, for simplicity indexed from 1 to N, and Chef knows who each of them report to.
 Member i reports to member Ri.

 Now, Chef needs to identfy all potential killers to continue his investigation. Having considerable knowledge about
 the mafia's activities, Chef knows that the killer must be a minor criminal, that is, one of the members who nobody
 reports to. Please find the list of potential killers for Chef. Since Don reports to nobody, his Ri will be equal to 0.

 Input
 -----
 The first line of input contains one integer N.
 Next line has N space-separated integers, the ith integer denotes Ri — the person whom the ith member reports to.

 Output
 ------
 Output a list of space-separated integers in ascending order — the indices of potential killers.

 Constraints
 -----------
 1 ≤ N ≤ 10^5
 1 ≤ Ri ≤ N except for Don, whose Ri equals to 0.
 It is guaranteed that there are no cycles in the reporting structure.

 Subtasks
 Subtask #1 [50 points]: N ≤ 10000
 Subtask #2 [50 points]: No additional constraints
 Example

 Input:
 6
 0 1 1 2 2 3

 Output:
 4 5 6

 */
class ChefDetective {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int totalMembers = Integer.parseInt(getNextLine(reader));
            String[] inputs = getNextLine(reader).split("\\s");

            if (inputs.length != totalMembers) {
                throw new CodeChefException("Invalid input");
            }

            List<Integer> data = new ArrayList<>();

            for (String input : inputs) {
                data.add(Integer.parseInt(input));
            }

            if (totalMembers == 1) {
                System.out.println(1);
                return;
            }
            Collections.sort(data);
            for (int i = 1; i <= totalMembers; i++) {
                int keyLocation = Collections.binarySearch(data, i);
                if(keyLocation < 0) {
                    System.out.print(i + " ");
                }
            }
        } catch (CodeChefException ex) {
            System.out.println(ex.getMessage());
        }
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