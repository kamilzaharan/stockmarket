package pl.lodz.p.neuralNetwork;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;  


public class MathFuncTests {

	@Test
	public void sigmoidCountTest() {
		ArrayList<Double> listOfExamples= new ArrayList<Double>();
		ArrayList<Double> resultArrays= new ArrayList<Double>();
		for(int i=0;i<10;i++){
			double randomValue = ThreadLocalRandom.current().nextDouble(-5000, 5000);
			listOfExamples.add(randomValue);
		}
		for(int i=0;i<10;i++){
			double resultOfSigmoid = MathFunc.sigmoid((double)listOfExamples.get(i), 1);
			resultArrays.add(resultOfSigmoid);
			assertThat(resultOfSigmoid).isBetween(-1.0, 1.0);
		}
		double sigmoidZero= MathFunc.sigmoid(0, 1);
		assertThat(sigmoidZero).isEqualTo(0.5);
	}
	
	@Test
	public void dSigmoidCountTest() {
		double dSigmoidZero= MathFunc.dsigmoid(0, 1);
		assertThat(dSigmoidZero).isEqualTo(0.0);
		double randomValue = ThreadLocalRandom.current().nextDouble(-5000, 5000);
		while(randomValue==0.0){	
			randomValue = ThreadLocalRandom.current().nextDouble(-5000, 5000);
		}
		double dSigmoidRand =MathFunc.dsigmoid(randomValue, 1);
		assertThat(dSigmoidRand).isNotEqualTo(0.0);
	}

}
