package review7;

import java.util.Scanner;

public class Test1 {
    String s =new Scanner(System.in).nextLine();
    void def(I i) {
        System.out.println(i.abc(s));
    }

    public static void main(String[] args) {

    }
}

interface I {
    String abc(String s);
}
