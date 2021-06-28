package review7;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("privet");
        list.add("poka");
        list.add("OK");
        list.add("Uchim Java");
        list.add("A imenno lambdas");
        String s = list.stream().reduce((ac, el) -> ac +" "+ el).get();
        System.out.println(s);

    }
}
