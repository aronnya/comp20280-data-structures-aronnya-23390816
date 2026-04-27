package project20280.exercises;

public class Foo {

    public static void foo(int x) {

        if (x / 2 == 0) {
            System.out.print(x);
            return;
        }
        // keep dividing by 2 (recursive)
        foo(x / 2);

        // print remainder
        System.out.print(x % 2);
    }

    public static void main(String[] args) {

        int x = 2468;

        System.out.print("project20280.exercises.Foo(" + x + ") = ");
        foo(x);
        System.out.println();
    }
}
