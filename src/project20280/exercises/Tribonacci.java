package project20280.exercises;

public class Tribonacci {

        public static int T(int n) {
            if (n == 0) return 0;
            if (n == 1) return 0;
            if (n == 2) return 1;

            return T(n-1) + T(n-2) + T(n-3);
        }

        public static void main(String[] args) {
            int n = 9;
            int result = T(n);
            System.out.println("tribonacci(" + n + ") = " + result);
        }
    }
