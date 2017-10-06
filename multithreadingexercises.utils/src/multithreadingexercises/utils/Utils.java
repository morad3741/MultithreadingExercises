package multithreadingexercises.utils;

import java.util.Random;

/**
 * Created by LD on 06/10/2017.
 */
public class Utils {

    private static Random rnd;

    static {
        rnd = new Random();
    }

    private Utils() {
    }

    public static int getRandomNumber(int low, int high) {
        return rnd.nextInt(high - low) + low;
    }

    public static int[] fillArrayWithRandom(int[] array, int low, int high) {
        for (int i = 0; i < array.length; i++)
            array[i] = getRandomNumber(low, high);
        return array;
    }


    public static int getMaxDivisorsOfNumber(int num) {
        int divisorCount = 0;
        for (int j = 1; j <= num; j++) {
            if (num % j == 0)
                divisorCount++;
        }
        return divisorCount;
    }

    public static int getNumberWithMaxDivisorsWithinRange(int startNumber, int endNumber) {
        int maxDivisors;  // Maximum number of divisors seen so far.
        int numWithMax;   // A value of N that had the given number of divisors.

        maxDivisors = startNumber;  // Start with the fact that 1 has 1 divisor.
        numWithMax = getMaxDivisorsOfNumber(startNumber);

        for (int i = startNumber; i <= endNumber; i++) {
            int divisorCount = getMaxDivisorsOfNumber(i);
            if (divisorCount > maxDivisors) {
                maxDivisors = divisorCount;
                numWithMax = i;
            }
        }
        return numWithMax;
    }
}