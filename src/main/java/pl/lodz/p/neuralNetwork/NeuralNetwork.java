package pl.lodz.p.neuralNetwork;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NeuralNetwork {

    private boolean randomPattern;
    private Layer inputLayer;
    private Layer[] hidedLayers;
    private Layer outputLayer;
    private double epsilon;

    public void setNeuralNetwork(NeuralNetworkConfiguration networkConfiguration, NeuronConfiguration neuronConfiguration) {
        this.randomPattern = networkConfiguration.isRandomPattern();

        this.epsilon = networkConfiguration.getEpsilon();
        int[] howManyNeuronInHiddenLayersTable = networkConfiguration.getHiddenLayersAmount();
        int howManyNeuronsInOutputLayer = networkConfiguration.getHowManyOutputNeurons();
        int howManyNeuronsInInputLayer = networkConfiguration.getHowManyInputNeurons();

        Neuron baseNeuron = new Neuron(neuronConfiguration);

        inputLayer = new Layer(baseNeuron, howManyNeuronsInInputLayer, howManyNeuronsInInputLayer);
        hidedLayers = new Layer[howManyNeuronInHiddenLayersTable.length];
        hidedLayers[0] = new Layer(baseNeuron, howManyNeuronsInInputLayer, howManyNeuronInHiddenLayersTable[0]);
        for (int i = 1; i < hidedLayers.length; i++) {
            hidedLayers[i] = new Layer(baseNeuron, howManyNeuronInHiddenLayersTable[i - 1], howManyNeuronInHiddenLayersTable[i]);
        }

        outputLayer = new Layer(baseNeuron, howManyNeuronInHiddenLayersTable[howManyNeuronInHiddenLayersTable.length - 1], howManyNeuronsInOutputLayer);

    }

    public void learn(int howManyEpoch, double[][] inputs, double[][] outputs, List<Point> err) {
        double error = 0;
        for (int j = 0; j < howManyEpoch; j++) {

            if (randomPattern) {
                NeuralNetworkUtils.randomizePatterns(inputs, outputs);
            }
            for (int i = 0; i < inputs.length; i++) {

                singleCompute(inputs[i]);
                error = error + outputLayer.getError(outputs[i]);
                backPropagation(outputs[i]);
            }

            if (j % 100 == 0) {
                err.add(new Point(j, error / inputs.length));
            }
            error = 0;
        }
    }


    public int learn(double networkConf, double[][] inputs, double[][] outputs, List<Point> err) {
        double error = 0;
        double[] errorCheck = new double[5];

        int howManyEpoch = 0;
        do {

            error = 0;

            if (randomPattern) {
                NeuralNetworkUtils.randomizePatterns(inputs, outputs);
            }
            for (int i = 0; i < inputs.length; i++) {
                singleCompute(inputs[i]);
                error = error + outputLayer.getError(outputs[i]);
                backPropagation(outputs[i]);
            }

            howManyEpoch++;
            errorCheck[howManyEpoch % 5] = error / inputs.length;
            if (howManyEpoch % 5 == 4) {
                int count = 0;
                for (int i = 0; i < 3; i++) {
                    if (errorCheck[i] == errorCheck[i + 1]) {
                        count++;
                    }
                }
                if (count > 3) {
                    return -1;
                }
            }
            if (howManyEpoch % 100 == 0) {
                err.add(new Point(howManyEpoch, error / inputs.length));
            }

        } while ((Math.abs(error / inputs.length) > epsilon));
        err.add(new Point(howManyEpoch, error / inputs.length));

        return howManyEpoch;
    }

    public void backPropagation(double[] expectedResults) {

        outputLayer.setLinnearError(0, expectedResults[0]);
        outputLayer.sendErrors(hidedLayers[hidedLayers.length - 1]);

        for (int i = hidedLayers.length - 1; i > 0; i--) {
            hidedLayers[i].sendErrors(hidedLayers[i - 1]);
        }

        for (Layer hidedLayer1 : hidedLayers) {
            hidedLayer1.calcWeights();
        }
        outputLayer.calcWeights();
    }

    public void compute(double[][] inputs) {
        for (double[] input : inputs) {
            singleCompute(input);
        }
    }

    public void singleCompute(double[] input) {

        inputLayer.setInputs(input);
        inputLayer.calcLinearOutputs();
        inputLayer.sendOutputs(hidedLayers[0]);

        for (int i = 0; i < hidedLayers.length - 1; i++) {
            hidedLayers[i].calcLinearOutputs();
            hidedLayers[i].sendOutputs(hidedLayers[i + 1]);
        }

        hidedLayers[hidedLayers.length - 1].calcOutputs();
        hidedLayers[hidedLayers.length - 1].sendOutputs(outputLayer);

        outputLayer.calcLinearOutputs();
    }

    public double returnSingleOutput() {
        return outputLayer.getSingleOutput(0);
    }

}