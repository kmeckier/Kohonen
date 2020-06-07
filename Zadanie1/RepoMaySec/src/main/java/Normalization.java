import java.util.Arrays;

public class Normalization {

    public static double[] normalizeVector(double[] vector) {
        double[] normalizedVector = new double[vector.length];

        double euclideanNorm = calculateEuclideanNorm(vector);

        for (int i = 0; i < normalizedVector.length; i++) {
            normalizedVector[i] = vector[i] / euclideanNorm;
        }

        return normalizedVector;
    }

    public static double calculateEuclideanNorm(double[] vector) {
        double sum = Arrays.stream(vector).map(value -> Math.pow(value, 2)).sum();
        return Math.sqrt(sum);
    }

    public static int[] denormalizeVector(double[] vector, double value) {
        int[] vectorToReturn = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            vectorToReturn[i] = (int) Math.round(vector[i] * value);
        }
        return vectorToReturn;
    }
}
