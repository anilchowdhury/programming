package codeChef.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class DigitJumpBFS {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(findMinimumJump(getNextLine(reader)));
    }

    private static int findMinimumJump(String input) {
        Map<Character, Boolean> digitVisitedMap = getDigitVisitedMap();
        char[] digits = input.toCharArray();
        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(0, 0);
        queue.add(node);
        int solution = 0;
        int inputLength = digits.length;

        while (!queue.isEmpty()) {
            Node processingNode = queue.remove();
            if (processingNode.index == inputLength - 1) {
                solution = processingNode.count;
                break;
            }
            if (!digitVisitedMap.get(digits[processingNode.index])) {
                for (int i = 0; i < inputLength; i++) {
                    if (digits[i] == digits[processingNode.index]) {
                        queue.add(new Node(processingNode.count + 1, i));
                    }
                }
                digitVisitedMap.put(digits[processingNode.index], true);
            }
            if (processingNode.index > 0 && !digitVisitedMap.get(digits[processingNode.index - 1])) {
                queue.add(new Node(processingNode.count + 1, processingNode.index - 1));

            }
            if (processingNode.index + 1 < inputLength && !digitVisitedMap.get(digits[processingNode.index + 1])) {
                queue.add(new Node(processingNode.count + 1, processingNode.index + 1));

            }
        }
        return solution;
    }

    private static Map<Character, Boolean> getDigitVisitedMap() {
        Map<Character, Boolean> digitVisitedMap = new HashMap<>();
        digitVisitedMap.put('0', false);
        digitVisitedMap.put('1', false);
        digitVisitedMap.put('2', false);
        digitVisitedMap.put('3', false);
        digitVisitedMap.put('4', false);
        digitVisitedMap.put('5', false);
        digitVisitedMap.put('6', false);
        digitVisitedMap.put('7', false);
        digitVisitedMap.put('8', false);
        digitVisitedMap.put('9', false);
        return digitVisitedMap;
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

    private static class Node {
        int index;
        int count;

        Node(int count, int index) {
            this.count = count;
            this.index = index;
        }
    }
}

