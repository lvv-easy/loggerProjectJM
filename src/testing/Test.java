package testing;


import java.util.*;
import java.util.function.*;

public class Test {

    public static void main(String[] args) {

    }
    interface Human {
        void walkin(String s);
        default void walk() {
            System.out.println("I'm walkin' here!!!");
        }
    }
}
