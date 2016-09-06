package pl.lodz.p.managers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import pl.lodz.p.handlers.Handler;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.model.Currency;
import pl.lodz.p.model.CurrencyValue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */

@Service
public class MainManagerImpl implements MainManager {

    private String currentExchangeRateDate;
    private MainManager mainManager;
    private boolean isNewDay;

    private Logger log = Logger.getLogger(MainManagerImpl.class);

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CurrencyValueDAO currencyValueDAO;

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private CompanyStockValueDAO companyStockValueDAO;

    @Autowired
    @Qualifier("sortSymbolHandler")
    private Handler sortSymbolHandler;


    public void createCompany(CreateCompanyDTO quote) {
        Company company = new Company();
        company.setSymbol(quote.getSymbol());
        company.setFullName(quote.getFullName());
        companyDAO.save(company);
        log.info("Stworzono obiekt Company w bazie danych");
    }

    public void createCompanyStockValue(QuoteResponseDTO quote) {
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

    private void addCurrencyToDB(Element eElement){
        Currency currency = new Currency();
        currency.setCurrencyName(eElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent());
        currency.setCurrencyCode(eElement.getElementsByTagName("kod_waluty").item(0).getTextContent());
        currencyDAO.save(currency);
        addCurrencyValueToDB(eElement,currency);
    }

    private void addCurrencyValueToDB(Element eElement, Currency currency){
        CurrencyValue currencyValue = new CurrencyValue();
        currencyValue.setCurrencyValue(eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent());
        currencyValue.setCurrencyId(currency);
        currencyValue.setDate(currentExchangeRateDate);
        currencyValueDAO.save(currencyValue);
    }

    private void checkIfNewDay(CurrencyValue cv){
        if (cv.getDate().equals(currentExchangeRateDate)) isNewDay = false;
        else isNewDay = true;
    }

    private Document prepareFile(URL file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file.openStream());
        NodeList data = document.getElementsByTagName("data_publikacji");

        Element element = (Element) data.item(0);
        currentExchangeRateDate=element.getTextContent();
        return document;
    }

    private void addNewDataToDB(Document document, List<Currency> currencyList){
        NodeList nList = document.getElementsByTagName("pozycja");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if(currencyList.size()==0){
                    addCurrencyToDB(eElement);
                }else{
                    if(isNewDay) addCurrencyValueToDB(eElement, currencyList.get(i));
                }
            }
        }
    }

    @Override
    public void getExchangeRate() {
        List<Currency> allCurrencies=getAllCurrencies();
        List<CurrencyValue> allCurValues =getAllCurrenciesValues();
        try {
            URL file = new URL("http://nbp.pl/kursy/xml/LastA.xml");
            Document document = prepareFile(file);
            if(allCurValues.size()>0) checkIfNewDay(allCurValues.get(allCurValues.size()-1));
            addNewDataToDB(document,allCurrencies);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Company> sort(ArrayList<Company> companyList, Integer sortType) {
        return sortSymbolHandler.process(sortType, companyList);
    }
    
    @Override
    public String getCurrentExchangeRateDate(){
        return currentExchangeRateDate;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDAO.findAll();
    }


    @Override
    public List<Object[]> findCompanyIdNameSymbol() {
        return companyDAO.findCompanyIdNameSymbolQuery();
    }

    @Override
    public List<Object[]> findCurrencyIdCodeName() {
        return currencyDAO.findCurrencyIdCodeNameQuery();
    }

    @Override
    public List<Object[]> getLastDate() { return currencyValueDAO.getLastDateQuery(); }

    @Override
    public List<Object[]> findCurrencyChartData() {
        return currencyDAO.findCurrencyChartDataQuery();
    }

    @Override
    public List<Object[]> findExchangeRate() {
        return currencyDAO.findExchangeRateQuery();
    }

    @Override
    public List<CurrencyValue> getAllCurrenciesValues() {
        return currencyValueDAO.findAll();
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDAO.findAll();
    }

    @Override
    public List<CompanyStockValue> getAllStockValues() {
        return companyStockValueDAO.findAll();
    }
}
