package javaTest.stream;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

/**
 * @author Anil Chowdhury
 *         Created on 5/11/2018.
 */
public class StreamTest {

    private int x = 5;

    public static void main(String[] args) {
        List<Person> persons = asList(new Person("Max", 18), new Person("Peter", 23),
                new Person("Pamela", 25), new Person("Pony", 28), new Person("David", 12));
//      lambdaTest();
//      performFiltering(persons);
//      mapTest(persons);
//      performGrouping(persons);
//      convertToMap(persons);
//      flatMapTest(persons);
//      countLowerCase("AbacsedeDD");
//      performJoining(persons);
//      countMaxLowerCase(asList("AbbbaD", "UUUTUDa", "aa", "TyuTTTT"));
//      arrayStream();
//      effectivelyFinalTest();
        intStreamTest();
    }

    private static void intStreamTest() {
        Random random = new Random(1);
        IntStream stream = IntStream.generate(() -> random.nextInt(1000));

        //stream.forEach(System.out :: println); // this will run infinitely
        stream.limit(10).forEach(System.out :: println);
    }

    private static void lambdaTest() {
        Predicate<Integer> predicate = x -> x > 5;
        Predicate<String> stringPredicate = x -> {
            int length = x.toCharArray().length;
            return length > 5;
        };

        Consumer<Integer> consumer = x -> {
            int y = x * 100;
            System.out.println(y);
        };
        Consumer<String> stringConsumer = System.out::println;

        Function<String, Integer> function = string -> string.toCharArray()[0] - 'A';

        Supplier<Integer> supplier = () -> 5;
        Supplier<Person> personSupplier = () -> new Person("", 1);

    }

    private static void mapTest(List<Person> persons) {
        List<Integer> nameLengthCount = persons
                .stream()
                .map(p -> p.name.length())
                .collect(Collectors.toList());
        nameLengthCount.forEach(System.out :: println);

        Set<String> longestDepartment = new HashSet<>();
        persons.forEach(p -> p.getDepartments().forEach(d -> {
                    if (d.getDepartmentName().length() > 10) {
                        longestDepartment.add(d.getDepartmentName());
                    }
                }));


        persons.forEach(p ->
                        p.getDepartments()
                        .stream()
                        .filter(d -> d.getDepartmentName().length() >10)
                        .map(d-> d.getDepartmentName())
                        .forEach(n -> longestDepartment.add(n))
        );

        Set<String> departmentName =
                persons.stream()
                        .flatMap(person -> person.getDepartments().stream())
                        .filter(department -> department.getDepartmentName().length() > 5)
                        .map(department -> department.getDepartmentName())
                        .collect(Collectors.toSet());

    }

    private static void effectivelyFinalTest() {
        int x = 10;
    }

    private static void printBatch(BatchSupplier batchSupplier, int batchSize) {
        batchSupplier.getBatchSupplier(batchSize).forEach(System.out::print);
    }

    private static class BatchSupplier implements Supplier<Integer> {

        List<Integer> getBatchSupplier(int batchSize) {
            if (batchSize == 0) {
                return Collections.emptyList();
            }
            List<Integer> batch = new ArrayList<>(batchSize);
            Collections.fill(batch, get());
            return batch;
        }

        @Override
        public Integer get() {
            return 5;
        }
    }

    private static void arrayStream() {
        int[] matrix = {1, 7, 8, 9, 3, 6};
        int[] matrix1 = {8, 9, 3, 6};
        int[] matrix2 = {9, 3, 6};

        final int[] sum = {0};
        AtomicInteger sum1 = new AtomicInteger();

        Arrays.stream(matrix).forEach(x -> sum[0] += x);
        Arrays.stream(matrix1).forEach(sum1::addAndGet);

        System.out.println("Matrix Sum - " + sum[0]);
        System.out.println("Matrix1 sum - " + sum1.get());
        System.out.println("Matrix2 sum - " + Arrays.stream(matrix2).sum());

        copy2DList(new ArrayList<>());
    }

    private static void copy2DList(List<List<Character>> board) {
        List<List<Integer>> visited = new ArrayList<>(board.size());
//      visited.forEach(x -> x.add(new ArrayList<Integer>()));
        for(int i = 0; i < board.size(); i++) {
            visited.add(new ArrayList<>());
        }
    }

    private static void performJoining(List<Person> persons) {
        System.out.println(persons.stream().map(p->p.name).collect(Collectors.joining(", ","{ ", " }")));
        System.out.println(persons.stream().map(p->p.name.toUpperCase()).collect(Collectors.joining(", ","{ ", " }")));
        System.out.println(persons.stream().map(p -> p.name).map(String::toUpperCase).collect(Collectors.toList()));
    }

    private static void countMaxLowerCase(List<String> inputs) {
//      inputs.javaTest.stream().max(Comparator.comparing(input -> input. .filter(Character::isLowerCase).count()).get());
        System.out.println(inputs.stream().max((x, y) ->
                Long.compare(x.chars().filter(Character::isLowerCase).count(),
                        y.chars().filter(Character::isLowerCase).count())).get());

        System.out.println(inputs.stream().max(Comparator.comparing(input ->
                input.chars().filter(Character::isLowerCase).count())).get());

        System.out.println(inputs.stream().max(Comparator.comparing(StreamTest::countLowerCase)).get());
//      String[] array = String[]::new;
    }

    private static long countLowerCase(String input) {
        long count = input.chars().filter(Character::isLowerCase).count();
        System.out.println(count);
        return count;
    }

    private static void performFiltering(List<Person> persons) {
        persons.stream()
                .filter(t -> {
                    System.out.println(t.name);
                    return true;
                }); // this will not print

        long count = persons.stream()
                .filter(t -> {
                    System.out.println(t.name);
                    return t.age > 20;
                })
                .count();
        System.out.println("Number of person above 20 age = " + count);

        StreamTest test = new StreamTest();
        persons.stream().filter(t-> {test.x = 10; return true;});

        BinaryOperator<Integer> add = (x, y) -> x + y;
        System.out.println(add.apply(2,3));
        System.out.println(add.apply(5,3));


        /*List<Person> filtered = persons.javaTest.stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());
        System.out.println(filtered);
        String name  = "abc";
        Runnable run = () -> System.out.println(name);*/
//      name = "tu";
    }

    private static void performGrouping(List<Person> persons) {
        Map<Integer, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(p -> p.age));
        System.out.println(collect);
    }

    private static void convertToMap(List<Person> persons) {
        Map<Integer, String> collect = persons.stream().collect(Collectors.toMap(p -> p.age, p -> p.name,
                (n1, n2) -> n1 + ";" + n2));
        System.out.println(collect);
    }

    private static void flatMapTest(List<Person> persons) {
        persons.forEach(p-> IntStream.range(1,4)
                .forEach(i->p.depart.add(new Department(p.name + " :: Department " + i))));

        persons.stream()
                .flatMap(p->p.depart.stream())
                .forEach(System.out :: println);
        System.out.println("*******************************");

        persons.forEach(p-> p.depart.forEach(System.out::println));
//        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
