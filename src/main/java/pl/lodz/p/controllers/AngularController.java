package pl.lodz.p.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.managers.CompanyManager;
import pl.lodz.p.managers.MainManager;
import pl.lodz.p.managers.StockValueManager;
import pl.lodz.p.mocks.ObjectMocks;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.model.Currency;
import pl.lodz.p.neuralNetwork.Approximation;
import pl.lodz.p.neuralNetwork.ConfigurationException;
import pl.lodz.p.neuralNetwork.Point;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */

@RestController
public class AngularController {

    private Logger log = Logger.getLogger(AngularController.class);

    @Autowired
    private MainManager mainManager;
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private StockValueManager stockValueManager;



    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showResultsGosiaFromDB() {

        ArrayList<Company> allCompanies = new ArrayList<Company>();
        for (Object[] obj : mainManager.findCompanyIdNameSymbol()) {
            Company c = new Company();

            c.setId(((BigInteger) obj[0]).longValue());
            c.setFullName((String) obj[1]);
            c.setSymbol((String) obj[2]);
            allCompanies.add(c);
        }

        String json = new Gson().toJson(allCompanies);
        return json;
    }

    @RequestMapping(value = "/getCompaniesById/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getCompaniesById(@PathVariable String id) {

        ArrayList<CompanyStockValue> allStockValues = new ArrayList();
        Integer companyId = Integer.parseInt(id);

        for (Object[] obj : stockValueManager.getStockValueById(companyId)) {
            CompanyStockValue stockValue = new CompanyStockValue();

            stockValue.setId(((BigInteger) obj[0]).longValue());
            stockValue.setVolume(((Integer) obj[1]).doubleValue());
            stockValue.setChange(((Double) obj[2]).doubleValue());
            stockValue.setChangePercent(((Double) obj[3]).doubleValue());
            stockValue.setChangePercentYTD(((Double) obj[4]).doubleValue());
            stockValue.setChangeYTD(((Double) obj[5]).doubleValue());
            stockValue.setHigh(((Double) obj[6]).doubleValue());
            stockValue.setLastPrice(((Double) obj[7]).doubleValue());
            stockValue.setLow(((Double) obj[8]).doubleValue());
            stockValue.setMarketCap(((Double) obj[9]).doubleValue());
            stockValue.setMsDate(((Double) obj[10]).doubleValue());
            stockValue.setOpen(((Double) obj[11]).doubleValue());
            allStockValues.add(stockValue);
        }

        String json = new Gson().toJson(allStockValues);
        return json;
    }

    @RequestMapping(value = "/getAllCurrencies", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showCurrenciesFromDB() {

        ArrayList<Currency> allCurrencies = new ArrayList<Currency>();
        for (Object[] obj : mainManager.findCurrencyIdCodeName()) {
            Currency c = new Currency();
            c.setId(((BigInteger) obj[0]).longValue());
            c.setCurrencyCode((String) obj[1]);
            c.setCurrencyName((String) obj[2]);
            allCurrencies.add(c);
        }

        String json = new Gson().toJson(allCurrencies);
        return json;

    }

    @RequestMapping(value = "/getExchangeRate", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showExchangeRate() {

        ArrayList<JsonObject> allData = new ArrayList<JsonObject>();

        for (Object[] obj : mainManager.findExchangeRate()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", (String) obj[0]);
            jsonObject.addProperty("name", (String) obj[1]);
            jsonObject.addProperty("value", (String) obj[2]);
            allData.add(jsonObject);
        }

        String json = new Gson().toJson(allData);
        return json;

    }


    @RequestMapping(value = "/getExchangeRateDate", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getExchangeRateDate() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", mainManager.getCurrentExchangeRateDate());

        String json = new Gson().toJson(jsonObject);
        return json;

    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void createCompany(@RequestBody CreateCompanyDTO createCompanyDTO) {
        log.info("Jestem w createCompany");
        mainManager.createCompany(createCompanyDTO);
    }

    @RequestMapping(value = "/getApproximation", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showApproximation() {
        Approximation aprox = new Approximation();
        List<Point> approxResult = null;
        try {
            approxResult = aprox.doApproximation();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        String json = new Gson().toJson(approxResult);
        return json;
    }



    @RequestMapping(value = "/newcompany", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String newCompany() {

        ObjectMocks.CreateAllMocks();
        companyManager.addCompany(ObjectMocks.APPLE);
        stockValueManager.addStockValue(ObjectMocks.APPLE_STOCK_VALUE);
        stockValueManager.addListOfStockValue(ObjectMocks.APPLE_STOCK_VALUE_LIST);
        companyManager.addCompany(ObjectMocks.NETFLIX);
        stockValueManager.addStockValue(ObjectMocks.NETFLIX_STOCK_VALUE);
        stockValueManager.addListOfStockValue(ObjectMocks.NETFLIX_STOCK_VALUE_LIST);

        String howManyStockValues = ObjectMocks.APPLE.getCompanyStockValueList().size()+"  ";
        howManyStockValues += ObjectMocks.NETFLIX.getCompanyStockValueList().size()+"";
        return howManyStockValues;
    }
}
