package pl.lodz.p.neuralNetwork;

public class MathFunc {

	public static double sigmoid(double x, double beta) {
		return 1 / (1 + Math.exp(-x * beta));
	}
	
	public static double dsigmoid(double sigmoid, double beta) {
		return beta * (sigmoid * (1 - sigmoid));
	}
}
