
public class Neuron {
    private double[] weights;
    private int winnerCounter;

    Neuron(double[] weights) {
        this.weights = weights;
        this.winnerCounter = 0;
    }

    double calculateOutput(double[] inputs) {
        double sum = 0;
        double inputsSum = 0;
        double weightsSum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += Math.pow(inputs[i] - weights[i], 2);
            inputsSum += inputs[i];
            weightsSum += weights[i];
        }
        // rozne sposoby obliczania odleglosci (podobienstwa) pomiedzy neuronem a ramka
        // return Math.pow((inputsSum/inputs.length) - (weightsSum/weights.length), 2);
        return Math.abs((inputsSum / inputs.length) - (weightsSum / weights.length));
        // return Math.sqrt(sum);
    }

    void modifyWeights(double[] inputs, double learningStep) {
        this.winnerCounter++;

        for (int i = 0; i < weights.length; i++) {
            weights[i] += learningStep * (inputs[i] - weights[i]);
        }

    }

    double[] getWeights() {
        return weights;
    }

    int getWinnerCounter() {
        return winnerCounter;
    }

}
