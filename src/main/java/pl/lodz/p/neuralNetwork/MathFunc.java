package pl.lodz.p.neuralNetwork;

public class MathFunc {

	public static double sigmoid(double t, double beta) {
		return 1 / (1 + Math.exp(-t * beta));
	}
}
