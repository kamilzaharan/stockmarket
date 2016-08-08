package pl.lodz.p.neuralNetwork;

import java.util.Random;

public class Neuron implements Cloneable{

	private static double alpha;
	private static double beta;
	public static double getBeta() {
		return beta;
	}

	public static void setBeta(double beta) {
		Neuron.beta = beta;
	}

	private static boolean isBias;
	private static double momentum;
	private static double bias;

	private double[] inputs;
	public double[] getInputs() {
		return inputs;
	}

	private double output;

	private double[] weights;
	private double[] prevoiusWeights;
	
	private double error = 0;

	public void setError(double error) {
		this.error = error;
	}

	Neuron(NeuronConfiguration neuronConfiguration) {
		alpha = neuronConfiguration.getAlpha();
		beta = neuronConfiguration.getBeta();
		isBias = neuronConfiguration.isBias();
		momentum = neuronConfiguration.getMomentum();
		initBias();
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	

	public void setInputs(double[] inputs) {
		this.inputs = inputs;
	}
	
	private void initBias() {
		Random random = new Random();

		if (isBias) {
			bias = random.nextDouble() - 0.5;
		}
	}

	public void drawWeights(int inputsAmount) {
		this.weights = new double[inputsAmount];
		this.prevoiusWeights = new double[inputsAmount];
		
		Random random = new Random();

		for (int i = 0; i < weights.length; i++) {
			weights[i] = random.nextDouble() - 0.5;
			prevoiusWeights[i] = random.nextDouble() - 0.5;
		}
	}
	
	public void setLinearWeights() {
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
			prevoiusWeights[i] = weights[i];
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
	
	public static double getAlpha() {
		return alpha;
	}

	public static void setAlpha(double alpha) {
		Neuron.alpha = alpha;
	}

	public double getPrevoiusWeights(int index) {
		return prevoiusWeights[index];
	}

	public void setPrevoiusWeights(double[] prevoiusWeights) {
		this.prevoiusWeights = prevoiusWeights;
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
