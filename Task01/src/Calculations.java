public class Calculations {
    public static long power(int number) {
        long result = 1L;

        for (int i = 0; i < number; i++) {
            result *= 2L;
        }

        return result;
    }

    public static long sum(int startingNumber, int endingNumber) {
        long result = 0L;

        for (int i = startingNumber; i <= endingNumber; i++) {
            result += i;
        }

        return result;
    }
}
