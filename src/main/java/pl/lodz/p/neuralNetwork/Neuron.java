package pl.lodz.p.neuralNetwork;

import java.util.Random;

public class Neuron {

	private static double alpha;
	private static double beta;
	private static boolean isBias;
	private static double momentum;
	private static double bias;

	private double[] inputs;
	private double output;

	private double[] weights;
	private double[] prevoiusWeights;

	Neuron(NeuronConfiguration neuronConfiguration, double[] inputs) {
		alpha = neuronConfiguration.alpha;
		beta = neuronConfiguration.beta;
		isBias = neuronConfiguration.isBias;
		momentum = neuronConfiguration.momentum;

		this.inputs = inputs;
		this.weights = new double[inputs.length];
		this.prevoiusWeights = new double[inputs.length];

		initBias();
		drawWeights();
	}

	private void initBias() {
		Random random = new Random();

		if (isBias) {
			bias = random.nextDouble() - 0.5;
		}
	}

	private void drawWeights() {
		Random random = new Random();

		for (int i = 0; i < inputs.length; i++) {
			weights[i] = random.nextDouble() - 0.5;
			prevoiusWeights[i] = random.nextDouble() - 0.5;
		}
	}
	
}
