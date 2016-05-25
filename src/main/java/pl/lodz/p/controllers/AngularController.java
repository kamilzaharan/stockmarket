package pl.lodz.p.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.managers.MainManager;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

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


    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Company> showResultsFromDB() {
        return mainManager.getAllCompanies();  //TODO blad z opakowaniem listy w obiekt jsonowy
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void createCompany(@RequestBody CreateCompanyDTO createCompanyDTO) {
        log.info("Jestem w createCompany");
        mainManager.createCompany(createCompanyDTO);
    }
}
