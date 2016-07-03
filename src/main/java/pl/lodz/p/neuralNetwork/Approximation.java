package pl.lodz.p.neuralNetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;


public class Approximation {

    public List<Point> doApproximation() throws ConfigurationException {

        double[][] approximationTrain1 = Utils.getArraysFromFile("approximation_train_1.txt");
        double[][] approxiamtionTrain2 = Utils.getArraysFromFile("approximation_train_2.txt");
        double[][] approximationTest = Utils.getArraysFromFile("approximation_test.txt");

        approximationTest = Utils.arraySort(approximationTest);
        Utils.saveArraysToFile("approximation_test_sorted.txt", approximationTest);

        double[][] inputs1 = Utils.getColumnArrayFromArray(0, approximationTrain1);
        double[][] outputs1 = Utils.getColumnArrayFromArray(1, approximationTrain1);

        double[][] inputs2 = Utils.getColumnArrayFromArray(0, approxiamtionTrain2);
        double[][] outputs2 = Utils.getColumnArrayFromArray(1, approxiamtionTrain2);

        double[][] trainInputs = ArrayUtils.addAll(inputs1, inputs2);
        double[][] trainOutputs = ArrayUtils.addAll(outputs1, outputs2);

        double[][] testInputs = Utils.getColumnArrayFromArray(0, approximationTest);
        double[][] testOutputs = Utils.getColumnArrayFromArray(1, approximationTest);

        double alpha = 0.2, beta = 1, momentum = 0.2;
        if (alpha < 0 || alpha >= 1) throw new ConfigurationException("Wrong alpha");
        if (beta < 0 || beta > 1) throw new ConfigurationException("Wrong beta");
        if (momentum < 0 || momentum >= 1) throw new ConfigurationException("Wrong momentum");

        NeuronConfiguration neuronConf = new NeuronConfiguration(alpha, beta, momentum, true);

        int[] howManyHiddenNeurons = {15};
        NeuralNetworkConfiguration networkConf = new NeuralNetworkConfiguration(1, howManyHiddenNeurons, 1, true, 0.7);
 
        int howManyEpoch = 10000;

        List<Point> approximationResults = new ArrayList<>();

        //for (int i = 1; i <= 20; i++) {
        List<Point> errPoints = new ArrayList();

        int i=10;
            NeuralNetwork approximationNetwork = new NeuralNetwork(networkConf, neuronConf);
            
            //dla treningowych 1,2 i obie
            // approximationNetwork.learn(howManyEpoch, inputs1, outputs1, errPoints);
            // approximationNetwork.learn(howManyEpoch, inputs2, outputs2, errPoints);
            approximationNetwork.learn(howManyEpoch, trainInputs, trainOutputs, errPoints);
            // Utils.savePoints("app_error_Train_"+i, errPoints);
            //errPoints.clear();
            
            //dane testowe
            // approximationNetwork.learn(howManyEpoch, testInputs, testOutputs, errPoints);
            //  Utils.savePoints("app_error_Test_" + i, errPoints);
            // errPoints.clear();
            for (double[] testInput : testInputs) {
                approximationNetwork.singleCompute(testInput);
                approximationResults.add(new Point(testInput[0], approximationNetwork.returnSingleOutput()));
            }

            Collections.sort(approximationResults);
            Utils.savePoints("approximation" + i, approximationResults);
return approximationResults;
//            approximationResults.clear();
       // }
    }

}
