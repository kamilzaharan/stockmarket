package pl.lodz.p.neuralNetwork;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Approximation {

    @Autowired
    private NeuralNetworkConfiguration networkConf;
    @Autowired
    private NeuralNetwork approximationNetwork;
    @Autowired
    private NeuronConfiguration neuronConf;

    private static final Logger log = Logger.getLogger(Approximation.class);

    public List<Point> doApproximation(double[][] train, double[][] test) throws ConfigurationException {

        test = NeuralNetworkUtils.arraySort(test);
        double[][] trainInputs = NeuralNetworkUtils.getColumnArrayFromArray(0, train);
        double[][] trainOutputs = NeuralNetworkUtils.getColumnArrayFromArray(1, train);

        double[][] testInputs = NeuralNetworkUtils.getColumnArrayFromArray(0, test);

        double alpha = 0.2, beta = 1, momentum = 0.2, epsilon = 0.7;
        int inputNeurons = 1, outputNeurons = 1, howManyEpoch = 10000;
        int[] howManyHiddenNeurons = {15};

        neuronConf.setConfiguration(alpha, beta, momentum, true);
        networkConf.setConfiguration(inputNeurons, howManyHiddenNeurons, outputNeurons, true, epsilon);

        checkNeuralNetworkConfiguration(networkConf);
        checkNeuronConfiguration(neuronConf);

        List<Point> approximationResults = new ArrayList<>();
        List<Point> errPoints = new ArrayList<>();

        approximationNetwork.setNeuralNetwork(networkConf, neuronConf);
        approximationNetwork.learn(howManyEpoch, trainInputs, trainOutputs, errPoints);

        int variable = 0;

        for (double[] testInput : testInputs) {
            approximationNetwork.singleCompute(testInput);

            if (variable != 0) {
                approximationResults.add(new Point(testInput[0], approximationNetwork.returnSingleOutput()));
            }
            variable++;
        }

        Collections.sort(approximationResults);
        log.debug("Approximation result: " + approximationResults);

        return approximationResults;
    }

    private void checkNeuralNetworkConfiguration(NeuralNetworkConfiguration networkConf) throws ConfigurationException {

        if (networkConf.getHiddenLayersAmount().length < 1) {
            throw new ConfigurationException("Wrong number of hidden layers");
        }
        for (int i = 0; i < networkConf.getHiddenLayersAmount().length; i++) {
            if (networkConf.getHiddenLayersAmount()[i] < 1) {
                throw new ConfigurationException("Wrong number of neurons on hidden layer. Layer: " + i + ", Neurons: "
                        + networkConf.getHiddenLayersAmount()[i]);
            }
        }

        if (networkConf.getHowManyInputNeurons() <= 0 || networkConf.getHowManyInputNeurons() >= 10) {
            throw new ConfigurationException("Wrong number of input neurons");
        }
        if (networkConf.getHowManyOutputNeurons() <= 0 || networkConf.getHowManyOutputNeurons() >= 10) {
            throw new ConfigurationException("Wrong number of output neurons");
        }
        if (networkConf.getEpsilon() < 0 || networkConf.getEpsilon() > 1) {
            throw new ConfigurationException("Wrong epsilon");
        }
    }

    private void checkNeuronConfiguration(NeuronConfiguration neuronConf) throws ConfigurationException {
        if (neuronConf.getAlpha() < 0 || neuronConf.getAlpha() >= 1) {
            throw new ConfigurationException("Wrong alpha");
        }
        if (neuronConf.getBeta() < 0 || neuronConf.getBeta() > 1) {
            throw new ConfigurationException("Wrong beta");
        }
        if (neuronConf.getMomentum() < 0 || neuronConf.getMomentum() >= 1) {
            throw new ConfigurationException("Wrong momentum");
        }
    }
}