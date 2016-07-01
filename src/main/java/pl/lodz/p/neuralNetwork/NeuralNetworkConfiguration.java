package pl.lodz.p.neuralNetwork;

public class NeuralNetworkConfiguration {
	int howManyInputNeurons;
	int[] hiddenLayersAmount;
	int howManyOutputNeurons;
	boolean randomPattern;

	public NeuralNetworkConfiguration(int howManyInputNeurons,int[] hiddenLayersAmount,int howManyOutputNeurons, boolean randomPattern){
		this.howManyInputNeurons=howManyInputNeurons;
		this.hiddenLayersAmount=hiddenLayersAmount;
		this.howManyOutputNeurons= howManyOutputNeurons;
		this.randomPattern= randomPattern;
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
}
