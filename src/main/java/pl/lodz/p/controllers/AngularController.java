package pl.lodz.p.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.managers.MainManager;

/**
 * Created by kamil on 5/22/16.
 */

@RestController
public class AngularController {

    private Logger log = Logger.getLogger(AngularController.class);

    @Autowired
    private MainManager mainManager;

    @RequestMapping(value = "/showResultsFromDB", method = RequestMethod.GET, consumes = "application/json")
    public void showResultsFromDB(){
        log.info("Jestem w showResultsFromDB");
    }

    @RequestMapping(value = "/createCompany", method = RequestMethod.POST, consumes = "application/json")
    public void createCompany(@RequestBody CreateCompanyDTO createCompanyDTO){  //TODO change to object
        log.info("Jestem w createCompany");
        mainManager.createCompany(createCompanyDTO.getSymbol(), createCompanyDTO.getFullName());
    }
}
