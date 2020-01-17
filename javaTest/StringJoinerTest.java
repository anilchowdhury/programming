package javaTest;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Anil Chowdhury
 * Created on 11/5/2019
 */
public class StringJoinerTest {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        System.out.println(String.join("; ", names));
        boolean isPresent = true;
        System.out.println(String.format("isA = [%s]", isPresent));
        isPresent = false;
        System.out.println(String.format("isA = [%s]", isPresent));


        List<String> schemas = new ArrayList<>();
        schemas.add("dbo");
//        schemas = null;
        printMessage(schemas);
    }

    private static void printMessage(Object... args) {
        String error = "Schemas not loaded in Catalog : {0}";
        MessageFormat formatter = new MessageFormat(error, Locale.ENGLISH);
        String msg = formatter.format(args);
        System.out.println(msg);
    }
}
