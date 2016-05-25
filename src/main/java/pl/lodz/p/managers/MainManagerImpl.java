package pl.lodz.p.managers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */

@Service
public class MainManagerImpl implements MainManager{

    private Logger log = Logger.getLogger(MainManagerImpl.class);

    @Autowired
    private CompanyDAO companyDAO;

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
    public List<Company> getAllCompanies() {
        return companyDAO.findAll();
    }

    @Override
    public List<CompanyStockValue> getAllStockValues() {
        return  companyStockValueDAO.findAll();
    }
}
