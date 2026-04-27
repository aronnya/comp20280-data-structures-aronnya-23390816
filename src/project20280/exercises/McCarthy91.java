package project20280.exercises;

public class McCarthy91 {

    public static int M(int n) {

        if (n > 100)
            return n - 10;
        else
            return M(M(n + 11));
    }

    public static void main(String[] args) {

        int n = 87;
        int result = M(n);

        System.out.println("M(" + n + ") = " + result);
    }
}
