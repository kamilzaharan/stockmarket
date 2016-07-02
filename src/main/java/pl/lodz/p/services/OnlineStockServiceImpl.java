package pl.lodz.p.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.managers.MainManager;
import pl.lodz.p.model.Company;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;


/**
 * Created by kamil on 5/22/16.
 */
@EnableScheduling
@Service
public class OnlineStockServiceImpl implements OnlineStockService {

    private Logger log = Logger.getLogger(OnlineStockService.class);

    @Autowired
    MainManager mainManager;

    @Scheduled(fixedRateString = "60000")
    public void onlineCheck() throws ParserConfigurationException, SAXException, IOException {
        mainManager.getExchangeRate();
        List<Company> companyList = mainManager.getAllCompanies();
        log.info(companyList.size());
        RestTemplate restTemplate = new RestTemplate();
        //QuoteResponseDTO quote = restTemplate.postForObject("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json",)
        for (Company company : companyList
             ) {
            QuoteResponseDTO quote = restTemplate.getForObject("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="+company.getSymbol(), QuoteResponseDTO.class);
            log.info(quote.toString());
            mainManager.createCompanyStockValue(quote);
        }
    }

    public void importCurrenciesName(){
        RestTemplate restTemplate = new RestTemplate();


    }

    @Override
    public void getInfoFromStock() {

    }
}
