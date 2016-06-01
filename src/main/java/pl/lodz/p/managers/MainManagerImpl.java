package pl.lodz.p.managers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.dao.CurrencyDAO;
import pl.lodz.p.dao.CurrencyValueDAO;
import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.model.Currency;
import pl.lodz.p.model.CurrencyValue;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by kamil on 5/22/16.
 */

@Service
public class MainManagerImpl implements MainManager{

    private Logger log = Logger.getLogger(MainManagerImpl.class);

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CurrencyValueDAO currencyValueDAO;

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private CompanyStockValueDAO companyStockValueDAO;

    public void createCompany(CreateCompanyDTO quote){
        Company company = new Company();
        company.setSymbol(quote.getSymbol());
        company.setFullName(quote.getFullName());
        companyDAO.save(company);
        log.info("Stworzono obiekt Company w bazie danych");
    }

    public void createCompanyStockValue(QuoteResponseDTO quote){
        CompanyStockValue companyStockValue = new CompanyStockValue();
        companyStockValue.setChange(quote.getChange());
        companyStockValue.setChangePercent(quote.getChangePercent());
        companyStockValue.setChangePercentYTD(quote.getChangePercentYTD());
        companyStockValue.setHigh(quote.getHigh());
        companyStockValue.setLastPrice(quote.getLastPrice());
        companyStockValue.setLow(quote.getLow());
        companyStockValue.setMarketCap(quote.getMarketCap());
        companyStockValue.setMsDate(quote.getMsDate());
        companyStockValue.setOpen(quote.getOpen());
        companyStockValue.setTimestamp(quote.getTimestamp());
        companyStockValue.setVolume(quote.getVolume());
        Company company = companyDAO.findBySymbol(quote.getSymbol());
        companyStockValue.setCompanyId(company);
        companyStockValueDAO.save(companyStockValue);
        log.info("Stworzono obiekt CompanyStockValue w bazie danych");
    }

    @Override
    public void getExchangeRate() throws IOException, ParserConfigurationException, SAXException {

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

                Currency currency = new Currency();
                currency.setCurrencyName(eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                currency.setCurrencyCode(eElement.getElementsByTagName("kod_waluty").item(0).getTextContent());
                CurrencyValue currencyValue = new CurrencyValue();
                currencyValue.setCurrencyValue(eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent());
                currencyValue.setCurrencyId(currency);
                currencyDAO.save(currency);
                currencyValueDAO.save(currencyValue);
                System.out.println("Nazwa waluty : " + eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
                System.out.println("Kod waluty : " + eElement.getElementsByTagName("kod_waluty").item(0).getTextContent());
                System.out.println("Kurs średni : " + eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent());


            }
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDAO.findAll();
    }

    @Override
    public List<CompanyStockValue> getAllStockValues() {
        return  companyStockValueDAO.findAll();
    }
}
