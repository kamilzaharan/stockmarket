package pl.lodz.p;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pl.lodz.p.dao.CurrencyDAO;
import pl.lodz.p.model.Currency;
import pl.lodz.p.model.CurrencyValue;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import pl.lodz.p.neuralNetwork.Approximation;

@SpringBootApplication
public class StockmarketApplication {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SpringApplication.run(StockmarketApplication.class, args);
//		Approximation aprox = new Approximation();
//		aprox.doApproximation();

	}


}
