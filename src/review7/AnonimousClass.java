package review7;

public class AnonimousClass {
    public static void main(String[] args) {

        Math math = new Math() {
            @Override
            public int doOperation(int a, int b) {
                return a*b;
            }
        };

        System.out.println(math.doOperation(4, 5));

    }

    interface Math {
        int doOperation(int a, int b);
    }
}
