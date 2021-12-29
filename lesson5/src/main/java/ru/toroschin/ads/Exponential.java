package ru.toroschin.ads;

public class Exponential {

    public static double get(int number, int degree) {
        long result = exponentiation(number, Math.abs(degree));
        if (degree < 0) {
            return 1 / (double) result;
        }
        return result;
    }

    private static long exponentiation(int number, int degree) {
        if (degree == 1) {
            return number;
        }
        return number * exponentiation(number, degree - 1);
    }
}
