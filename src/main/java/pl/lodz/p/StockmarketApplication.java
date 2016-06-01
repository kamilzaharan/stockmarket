package pl.lodz.p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class StockmarketApplication {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        SpringApplication.run(StockmarketApplication.class, args);
        getExchangeRate();
    }

    public static void getExchangeRate() throws IOException, ParserConfigurationException, SAXException {

        URL file = new URL("http://nbp.pl/kursy/xml/LastA.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file.openStream());

        System.out.println("Root element :" + document.getDocumentElement().getNodeName());
        System.out.println("Root element :" + document.getDocumentElement().getAttribute("uid"));


        NodeList nList = document.getElementsByTagName("pozycja");

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                System.out.println("Nazwa waluty : " + eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                System.out.println("Kod waluty : " + eElement.getElementsByTagName("kod_waluty").item(0).getTextContent());
                System.out.println("Kurs średni : " + eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent());

            }
        }
    }
}


