package pl.lodz.p.neuralNetwork;

import org.apache.log4j.Logger;
import pl.lodz.p.controllers.AngularController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Approximation {

    private Logger log = Logger.getLogger(AngularController.class);
    public List<Point> doApproximation(double[][] train, double[][] test) throws ConfigurationException {

        double[][] approximationTrain = train;
        double[][] approximationTest = test;


        approximationTest = NetworkUtils.arraySort(approximationTest);
        NetworkUtils.saveArraysToFile("approximation_test_sorted.txt", approximationTest);
        NetworkUtils.saveArraysToFile("approximation_train_sorted.txt", approximationTrain);

        double[][] trainInputs = NetworkUtils.getColumnArrayFromArray(0, approximationTrain);
        double[][] trainOutputs = NetworkUtils.getColumnArrayFromArray(1, approximationTrain);

        double[][] testInputs = NetworkUtils.getColumnArrayFromArray(0, approximationTest);

        double alpha = 0.2, beta = 1, momentum = 0.2;

        if (alpha < 0 || alpha >= 1) {
            throw new ConfigurationException("Wrong alpha");
        }
        if (beta < 0 || beta > 1) {
            throw new ConfigurationException("Wrong beta");
        }
        if (momentum < 0 || momentum >= 1) {
            throw new ConfigurationException("Wrong momentum");}

        NeuronConfiguration neuronConf = new NeuronConfiguration(alpha, beta, momentum, true);

        int[] howManyHiddenNeurons = {15};

        if (howManyHiddenNeurons.length < 1) {
            throw new ConfigurationException("Wrong number of hidden layers");
        }
        for (int i = 0; i < howManyHiddenNeurons.length; i++) {
            if (howManyHiddenNeurons[i] < 1) {
                throw new ConfigurationException("Wrong number of neurons on hidden layer. Layer: " + i + ", Neurons: " + howManyHiddenNeurons[i]);
            }
        }

        int inputNeurons = 1, outputNeurons = 1;
        double epsilon = 0.7;

        if (inputNeurons <= 0 || inputNeurons >= 10) {
            throw new ConfigurationException("Wrong number of input neurons");
        }
        if (outputNeurons <= 0 || outputNeurons >= 10) {
            throw new ConfigurationException("Wrong number of output neurons");
        }
        if (epsilon < 0 || epsilon > 1) {
            throw new ConfigurationException("Wrong epsilon");
        }


        NeuralNetworkConfiguration networkConf = new NeuralNetworkConfiguration(inputNeurons, howManyHiddenNeurons, outputNeurons, true, epsilon);
 
        int howManyEpoch = 10000;

        List<Point> approximationResults = new ArrayList<>();
        List<Point> errPoints = new ArrayList();

        int i=10;
            NeuralNetwork approximationNetwork = new NeuralNetwork(networkConf, neuronConf);
            approximationNetwork.learn(howManyEpoch, trainInputs, trainOutputs, errPoints);
            for (double[] testInput : testInputs) {
                approximationNetwork.singleCompute(testInput);
                approximationResults.add(new Point(testInput[0], approximationNetwork.returnSingleOutput()));
            }

            Collections.sort(approximationResults);
            NetworkUtils.savePoints("approximation" + i, approximationResults);

        return approximationResults;
    }

}
