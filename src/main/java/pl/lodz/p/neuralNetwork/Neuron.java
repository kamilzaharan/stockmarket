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
	
	private double error = 0;

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
	
	private double total() {
		double total = 0;

		for (int i = 0; i < inputs.length; i++) {
			total += inputs[i] * weights[i];
		}

		total += bias;

		return total;
	}
	
	public void setErrorOnOutputLayer(double out) {
		error = MathFunc.dsigmoid(output, 1) * (out - output);
	}

	public void setErrorOnHiddenLayer(double sum) {
		error = MathFunc.dsigmoid(output, beta) * sum;
	}
	
	public void setLinearError(double out) {
		error = out - output;
	}

	public void calcNewWeights() {
		double weight;
		
		for (int i = 0; i < weights.length; i++) {
			weight = weights[i];
			weights[i] = weights[i] + alpha * error * inputs[i] + momentum * (weights[i] - prevoiusWeights[i]);
			prevoiusWeights[i] = weight;
		}
	}
	
	public void calcOutput() {
		double total = total();
		output = MathFunc.sigmoid(total, beta);
	}
	
	public void calcLinearOutput() {
		
		output = total();
	}
	
	public double getError() {
		
		return error;
	}
	
	public double getOutput() {
		
		return output;
	}
	
	public double getWeight(int index) {
		
		return weights[index];
	}

}
