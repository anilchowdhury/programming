package oop;

import java.io.*;

/**
 * @author Anil Chowdhury
 *         Created on 6/25/2018.
 */
public class UserInputOutput {

    public static void main(String[] args) {
//      display();
        InputStream in = System.in;
        OutputStream out = System.out;

        byte[] buffer = new byte[4096];
        int r;
        try {
            out.write("Enter    ".getBytes());
            while ((r = in.read(buffer)) != -1) {
                out.write(buffer, 0, r);
                out.flush();
//              String s = Arrays.toString(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void display() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter ...");
                String input = reader.readLine();
                if ("q".equals(input)) {
                    System.out.println("Exit");
                    System.exit(0);
                }
                System.out.println("Input :: " + input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
