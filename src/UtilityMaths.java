import java.util.zip.ZipError;

public class UtilityMaths {
    private UtilityMaths() {
        throw new UnsupportedOperationException("Utility class, should not be instantiated.");
    }

    public static boolean isSumGreaterThanMax(double numOne, double numTwo, double max){
        boolean isValid;
        double sum = numOne + numTwo;

        isValid = sum > max;

        return isValid;
    }

    public static double findAverage(double... numbers) {
        int count = numbers.length;
        if (count == 0) {
            System.out.println("Cannot calculate average for an empty array.");
            return -1;
        }

        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }

        return sum / count;
    }

}