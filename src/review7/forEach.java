package review7;

import java.util.ArrayList;
import java.util.List;

public class forEach {
    public static void main(String[] args) {
        List<String> list = List.of("privet", "poka", "kak dela?", "normalno", "poka");
        list.forEach(s -> System.out.println(s));

        List<Integer> list2 = List.of(1,2,3,4,5);
        list2.forEach(i -> {
            System.out.println(i);
            i=i*2;
            System.out.println(i);
        });
        System.out.println(list2);
    }
}
