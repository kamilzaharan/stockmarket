package pl.lodz.p.managers;

import org.xml.sax.SAXException;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.model.Currency;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by kamil on 5/22/16.
 */
public interface MainManager {
    String getCurrentExchangeRateDate();
    void createCompany(CreateCompanyDTO quote);
    List<Company> getAllCompanies();
    List<Currency> getAllCurrencies();
    List<CompanyStockValue> getAllStockValues();
    List<Object[]> findCompanyIdNameSymbol();
    List<Object[]> findCurrencyIdCodeName();
    List<Object[]> findExchangeRate();
    void createCompanyStockValue(QuoteResponseDTO quote);
    void getExchangeRate();

}
