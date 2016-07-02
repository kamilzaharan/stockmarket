package pl.lodz.p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.lodz.p.neuralNetwork.Approximation;

@SpringBootApplication
public class StockmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketApplication.class, args);
		Approximation aprox = new Approximation();
		aprox.doApproximation();
	}
}
