package pl.lodz.p.neuralNetwork;

public class NeuronConfiguration {

	double alfa;
	double beta;
	double momentum;
	boolean isBias;

	public NeuronConfiguration(double alpha, double beta, double momentum, boolean isBias) {
		this.alfa = alpha;
		this.beta = beta;
		this.momentum = momentum;
		this.isBias = isBias;
	}

	public double getAlpha() {
		return alfa;
	}

	public void setAlpha(double alpha) {
		this.alfa = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getMomentum() {
		return momentum;
	}

	public void setMomentum(double momentum) {
		this.momentum = momentum;
	}

	public boolean isBias() {
		return isBias;
	}

	public void setBias(boolean isBias) {
		this.isBias = isBias;
	}
}
