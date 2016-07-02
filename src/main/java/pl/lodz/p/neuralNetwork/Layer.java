package pl.lodz.p.neuralNetwork;

public class Layer {

	Neuron[] neurons;

	public Layer(Neuron neuron, int inputsAmount, int neuronsAmount) {
		neurons = new Neuron[neuronsAmount];
		neurons[0] = neuron;

		setNeurons(inputsAmount);
	}

	private void setNeurons(int inputsAmount) {
		for (int i = 0; i < neurons.length; i++) {

			try {
				Neuron neuronCopy = (Neuron) neurons[0].clone();
				neuronCopy.drawWeights(inputsAmount);
				neurons[i] = neuronCopy;

			} catch (CloneNotSupportedException e) {
				// TODO coś musimy wymyślić, jeśli wywali błąd
				e.printStackTrace();
			}
		}
	}

	public void setInputs(double[] inputs) {
		for (int i = 0; i < neurons.length; i++) {

			neurons[i].setInputs(inputs);
		}
	}

	public void calcOutputs() {
		for (Neuron neuron : neurons) {
			neuron.calcOutput();
		}
	}

	public void calcLinearOutputs() {
		for (Neuron neuron : neurons) {
			neuron.calcLinearOutput();
		}
	}

	public void calcWeights() {
		for (Neuron neuron : neurons) {
			neuron.calcNewWeights();
		}
	}

	public void setLinearWeights() {
		for (Neuron neuron : neurons) {
			neuron.setLinearWeights();
		}
	}

	public void sendOutputs(Layer layer) {
		int receiveLayerNeuronsAmount = layer.getneuronsAmount();
		double[] inputs = new double[neurons.length];

		for (int i = 0; i < neurons.length; i++) {
			inputs[i] = neurons[i].getOutput();
		}

		for (int i = 0; i < receiveLayerNeuronsAmount; i++) {
			layer.getNeuron(i).setInputs(inputs);
		}
	}

	public void sendErrors(Layer layer) {
		double total;
		int neuronAmount = layer.getneuronsAmount();

		for (int i = 0; i < neuronAmount; i++) {
			total = 0;
			for (Neuron neuron : neurons) {
				total += neuron.getWeight(i) * neuron.getError();
			}

			layer.getNeuron(i).setErrorOnHiddenLayer(total);
		}
	}

	public void setErrorToOutputLayer(double[] outputs) {
		for (int i = 0; i < outputs.length; i++) {
			neurons[i].setErrorOnOutputLayer(outputs[i]);
		}
	}

	public double getError(double[] output) {
		double total = 0;

		for (int i = 0; i < neurons.length; i++) {
			total += Math.pow(neurons[i].getOutput() - output[i], 2);
		}

		return total / neurons.length;
	}

	   public double getSingleOutput(int whichNeuron) {
	        return neurons[whichNeuron].getOutput();
	    }

	public int getneuronsAmount() {

		return neurons.length;
	}

	public Neuron getNeuron(int index) {

		return neurons[index];
	}

    public void setLinnearError(int i, double d) {
        neurons[i].setLinearError(d);
    }

}
