package project20280.exercises;

public class Fibonacci {

    //fibonacci
    public static int F(int n) {
        if (n <= 1)
            return n;
        return F(n - 1) + F(n - 2);
    }


    public static void main(String[] args) {

        int n = 5;
        long startTime = System.nanoTime();
        int result = F(n);
        long endTime = System.nanoTime();
        double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.println("fibonacci(" + n + ") = " + result);
        System.out.println("time taken: " + elapsedSeconds + " seconds");
    }



}