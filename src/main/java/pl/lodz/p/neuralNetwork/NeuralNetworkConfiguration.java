package pl.lodz.p.neuralNetwork;

import org.springframework.stereotype.Component;

@Component
public class NeuralNetworkConfiguration {
	int howManyInputNeurons;
	int[] hiddenLayersAmount;
	int howManyOutputNeurons;
	boolean randomPattern;
	double epsilon;

	public void setConfiguration(int howManyInputNeurons,int[] hiddenLayersAmount,int howManyOutputNeurons,
                                         boolean randomPattern, double epsilon){
        this.howManyInputNeurons=howManyInputNeurons;
        this.hiddenLayersAmount=hiddenLayersAmount;
        this.howManyOutputNeurons= howManyOutputNeurons;
        this.randomPattern= randomPattern;
        this.epsilon=epsilon;
    }

	public int getHowManyInputNeurons() {
		return howManyInputNeurons;
	}
	public void setHowManyInputNeurons(int howManyInputNeurons) {
		this.howManyInputNeurons = howManyInputNeurons;
	}
	public int[] getHiddenLayersAmount() {
		return hiddenLayersAmount;
	}
	public void setHiddenLayersAmount(int[] hiddenLayersAmount) {
		this.hiddenLayersAmount = hiddenLayersAmount;
	}
	public int getHowManyOutputNeurons() {
		return howManyOutputNeurons;
	}
	public void setHowManyOutputNeurons(int howManyOutputNeurons) {
		this.howManyOutputNeurons = howManyOutputNeurons;
	}
	
	public boolean isRandomPattern() {
		return randomPattern;
	}

	public void setRandomPattern(boolean randomPattern) {
		this.randomPattern = randomPattern;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
}
