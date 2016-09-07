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
import pl.lodz.p.model.CurrencyValue;
import pl.lodz.p.neuralNetwork.Approximation;
import pl.lodz.p.neuralNetwork.ConfigurationException;
import pl.lodz.p.neuralNetwork.Point;
import pl.lodz.p.utils.Utils;

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
    @Autowired
    private Approximation approx;


    @RequestMapping(value = "/companiesList/{sortType}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showCompanies(@PathVariable String sortType) {

        ArrayList<Company> allCompanies = new ArrayList<>();
        for (Object[] obj : mainManager.findCompanyIdNameSymbol()) {
            Company c = new Company();

            c.setId(((BigInteger) obj[0]).longValue());
            c.setFullName((String) obj[1]);
            c.setSymbol((String) obj[2]);
            allCompanies.add(c);
        }

        Integer sortTypeInt = Integer.parseInt(sortType);
        allCompanies = mainManager.sort(allCompanies, sortTypeInt);

        return new Gson().toJson(allCompanies);
    }

    @RequestMapping(value = "/getCompaniesById/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getCompaniesById(@PathVariable String id) {

        Integer companyId = Integer.parseInt(id);
        ArrayList<CompanyStockValue> allStockValues = createStockValueFromJSON(companyId);

        return new Gson().toJson(allStockValues);
    }

    @RequestMapping(value = "/getAllCurrencies", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showCurrenciesFromDB() {

        ArrayList<Currency> allCurrencies = new ArrayList<>();
        for (Object[] obj : mainManager.findCurrencyIdCodeName()) {
            Currency c = new Currency();
            c.setId(((BigInteger) obj[0]).longValue());
            c.setCurrencyCode((String) obj[1]);
            c.setCurrencyName((String) obj[2]);
            allCurrencies.add(c);
        }

        return new Gson().toJson(allCurrencies);
    }

    @RequestMapping(value = "/getExchangeRate", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showExchangeRate() {

        ArrayList<JsonObject> allData = new ArrayList<>();

        for (Object[] obj : mainManager.findExchangeRate()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", (String) obj[0]);
            jsonObject.addProperty("name", (String) obj[1]);
            jsonObject.addProperty("value", (String) obj[2]);
            allData.add(jsonObject);
        }

        return new Gson().toJson(allData);
    }

    @RequestMapping(value = "/getCurrencyChartData/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showCurrencyChartData(@PathVariable String id) {

        Integer currencyID = Integer.parseInt(id);

        ArrayList<JsonObject> data = new ArrayList<JsonObject>();
        for (Object[] obj : mainManager.findCurrencyChartData()) {
            if(currencyID==((BigInteger)obj[3]).longValue() ){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("x", (String) obj[0]);
                jsonObject.addProperty("y", (String) obj[1]);
                data.add(jsonObject);
            }
        }
        return new Gson().toJson(data);
    }

    @RequestMapping(value = "/getExchangeRateDate", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getExchangeRateDate() {

        List<Object[]> lastDateList = mainManager.getLastDate();
        CurrencyValue currencyValueLastDate = new CurrencyValue();
        if(lastDateList.size()>0){
            currencyValueLastDate = new CurrencyValue();
            currencyValueLastDate.setDate((String) lastDateList.get(0)[1]);
        }
        return new Gson().toJson(currencyValueLastDate);
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void createCompany(@RequestBody CreateCompanyDTO createCompanyDTO) {
        log.info("Jestem w createCompany");
        mainManager.createCompany(createCompanyDTO);
    }

    @RequestMapping(value = "/getApproximation/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getApproximationByID(@PathVariable String id) {

        Integer companyId = Integer.parseInt(id);
        double[][] allTrainData = createTrainingToAproxFromJSON(companyId);
        double[][] allTestData = createTestToAproxFromJSON(allTrainData);
        List<Point> approxResult = null;
        try {
            approxResult = approx.doApproximation(allTrainData, allTestData);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(approxResult);
    }

    @RequestMapping(value = "/companyDetails/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getCompanyDetailByID(@PathVariable String id) {

        Integer companyId = Integer.parseInt(id);
        List<Point> stockValues = companyManager.findStockValuesList(companyId);
        return new Gson().toJson(stockValues);
    }

    @RequestMapping(value = "/companyDetails/{id}/stats", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String getStatistics(@PathVariable String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("average", companyManager.getAverage());
        jsonObject.addProperty("variance", companyManager.getVariance());
        jsonObject.addProperty("standardDeviation", companyManager.getStandardDeviation());
        jsonObject.addProperty("median", companyManager.getMedian());

        return new Gson().toJson(jsonObject);
    }

    @RequestMapping(value = "/getApproximation", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String showApproximation() {

        List<Point> approxResult = null;
        try {
            approxResult = approx.doApproximation(null,null);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return new Gson().toJson(approxResult);
    }

    @RequestMapping(value = "/newCompany", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String newCompany() {

        ObjectMocks.CreateAllMocks();
        List<Company> companyList= Utils.readCompaniesFromCsv("companylist.csv");
        for(Company company:companyList){
            companyManager.addCompany(company);
        }
        log.info("Firmy zostały dodane do bazy danych");

        return new Gson().toJson("Firmy zostały dodane do bazy danych");
    }

    @RequestMapping(value = "/addMockStockValue", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    String addMockStockValue() {

        ObjectMocks.CreateAllMocks();

        companyManager.addCompany(ObjectMocks.MICROSOFT);
        companyManager.addCompany(ObjectMocks.APPLE);
        companyManager.addCompany(ObjectMocks.NETFLIX);

        stockValueManager.addListOfStockValue(ObjectMocks.NETFLIX_STOCK_VALUE_LIST);
        stockValueManager.addListOfStockValue(ObjectMocks.APPLE_STOCK_VALUE_LIST);

        return "DODALEM MOCKI";
    }

    @RequestMapping(value = "/companies/max", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String maxIncrease() {

        List<Point> stockValues = companyManager.findMaxIncrease();

        return new Gson().toJson(stockValues);
    }

    @RequestMapping(value = "/companies/min", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String maxDecrease() {

        List<Point> stockValues = companyManager.findMaxDecrease();

        return new Gson().toJson(stockValues);
    }

    public ArrayList<CompanyStockValue> createStockValueFromJSON(int companyId){
        ArrayList<CompanyStockValue> allStockValues = new ArrayList<>();

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
            stockValue.setTimestamp(obj[12].toString());
            allStockValues.add(stockValue);
        }
        return allStockValues;
    }

    public double[][] createTrainingToAproxFromJSON(int companyId){
        double[][] allStockValues = new double[stockValueManager.getStockValueById(companyId).size()][2];
        int i =0;

        for (Object[] obj : stockValueManager.getStockValueById(companyId)) {


            allStockValues[i][1] = ((Double) obj[7]).doubleValue();
            allStockValues[i][0] = i*10;
            i++;

        }
        return allStockValues;
    }

    public double[][] createTestToAproxFromJSON( double[][] allStockValues){

        double[][] allTestValues = new double [allStockValues.length+3][2];

        for(int i=0;i<allStockValues.length-1; i++){
            for(int j=0;j<2; j++){
                allTestValues[i][j]=allStockValues[i][j];
            }
        }

        for(int i=0;i<3;i++) {
            allTestValues[allStockValues.length+i][0] = allStockValues.length + i*10+10;
            allTestValues[allStockValues.length+i][1] = allStockValues.length + i*10+10;
        }

        return allTestValues;
    }
}
