package review7streams;

import review7.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        BinaryOperator<Integer> op = (integer, integer2) -> integer2 / integer;
        BiFunction<Integer, Integer, Double> fun = (n, m) -> (double)(m / n);
        BiFunction<Integer, String, List<String>> fun1 = (i, s) -> {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                list.add(s);
            }
            return list;
        };

        Integer myInt = 5;
        String  myStr = "I love Java!!!";

        System.out.println(fun1.apply(10, "Djigurda"));

        Stream.generate(() -> new Student("Alex", 'm', 32, 5, 7.7)).limit(14).forEach(System.out::println);

    }
}
