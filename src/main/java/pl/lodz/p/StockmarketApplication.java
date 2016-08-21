package pl.lodz.p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

@SpringBootApplication
public class StockmarketApplication {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SpringApplication.run(StockmarketApplication.class, args);
    }

}
