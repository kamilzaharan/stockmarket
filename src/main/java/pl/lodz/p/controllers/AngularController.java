package pl.lodz.p.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.managers.MainManager;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.Currency;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by kamil on 5/22/16.
 */

@RestController
public class AngularController {

    private Logger log = Logger.getLogger(AngularController.class);

    @Autowired
    private MainManager mainManager;


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
}
