package pl.lodz.p.neuralNetwork;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;  // main one

@RunWith(MockitoJUnitRunner.class)
public class NeuronTests {

	private Neuron neuron;
	
	@Mock
	NeuronConfiguration configuration;

	
	@Before
	public void setMock(){
		Mockito.when(configuration.getAlpha()).thenReturn(0.6);
		Mockito.when(configuration.getBeta()).thenReturn(1.0);
		Mockito.when(configuration.getMomentum()).thenReturn(0.2);
		Mockito.when(configuration.isBias()).thenReturn(true);
	}

	@Before 
	public void setNeuron(){
		neuron= new Neuron(configuration);
	}
	
	@Test
	public void weightsAreNotNull() {	
		neuron.drawWeights(5);
		for(int i=0;i<5;i++){
			assertThat(neuron.getWeight(i)).isNotNull();
		}
	}
	
	@Test 	
	public void calcLinearOutputTest(){
		double[] inputs= {1,2,3};
		neuron.setInputs(inputs);
		neuron.drawWeights(3);
		neuron.calcLinearOutput();
		assertThat(neuron.getOutput()).isNotNull();
	}
	
	@Test 
	public void calcNewWeightsTest(){
		double[] inputs= {1,2,3};
		neuron.setInputs(inputs);
		neuron.drawWeights(3);
		neuron.setError(0.5);
		double weight = neuron.getWeight(1);
		neuron.calcNewWeights();
		assertThat(neuron.getPrevoiusWeights(1)).isEqualTo(weight);
		assertThat(neuron.getPrevoiusWeights(1)).isNotEqualTo(neuron.getWeight(1));
	}
	
	
		

}
