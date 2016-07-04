package pl.lodz.p.neuralNetwork;

import static org.assertj.core.api.Assertions.assertThat; 

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class NeuralNetworkTests {
	
	NeuralNetwork network;
	
	@Mock
	NeuronConfiguration neuronConfiguration;
	
	@Mock 
	NeuralNetworkConfiguration networkConfiguration;


    @Before
	public void setMock(){
		Mockito.when(neuronConfiguration.getAlpha()).thenReturn(0.6);
		Mockito.when(neuronConfiguration.getBeta()).thenReturn(1.0);
		Mockito.when(neuronConfiguration.getMomentum()).thenReturn(0.2);
		Mockito.when(neuronConfiguration.isBias()).thenReturn(true);
		
		Mockito.when(networkConfiguration.getEpsilon()).thenReturn(0.7);
		Mockito.when(networkConfiguration.getHowManyInputNeurons()).thenReturn(1);
		int[] howManyHiddenNeurons = {15};
		Mockito.when(networkConfiguration.getHiddenLayersAmount()).thenReturn(howManyHiddenNeurons);
		Mockito.when(networkConfiguration.getHowManyOutputNeurons()).thenReturn(1);
		Mockito.when(networkConfiguration.isRandomPattern()).thenReturn(true);
	}
    
    @Before 
	public void setNetwork(){
    	network= new NeuralNetwork(networkConfiguration, neuronConfiguration);
	}

	@Test
	public void initializationTest() {
	    double[][] inputs=new double[1][3] ;
	    inputs[1][1]=1;
	    inputs[1][2]=2;
	    inputs[1][3]=3;
	    double outputBeforeCompute=network.outputLayer.getSingleOutput(0);
        network.singleCompute(inputs[0]);
        double outputAfterCompute=network.outputLayer.getSingleOutput(0); 
	}

}
