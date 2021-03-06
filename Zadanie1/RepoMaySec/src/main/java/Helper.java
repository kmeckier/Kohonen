import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Helper {
    public static int findMinValueIndex(List<Double> list) {
        Integer i = 0;
        Double min = null;
        int minIndex = -1;
        for (Double x : list) {
            if ((x != null) && ((min == null) || (x < min))) {
                min = x;
                minIndex = i;
            }
            i++;
        }
        if (minIndex == -1)
            minIndex = 1;
        return minIndex;
    }

    public static int[] convert2darrayto1d(int[][] array2D) {
        int[] arrayToReturn = new int[array2D.length * array2D.length];

        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[0].length; j++) {
                arrayToReturn[i * array2D.length + j] = array2D[i][j];
            }
        }
        return arrayToReturn;
    }

    public static double[] generateRandomWeights(int weightsCount) {
        double[] weights = new double[weightsCount];
        Random random = new Random();
        for (int i = 0; i < weightsCount; i++) {
            weights[i] = random.nextDouble();
        }
        return weights;
    }

    public static int minimalNumberOfBits(double number) {
        return (int) Math.floor(Math.log(number)) + 1;
    }
}
