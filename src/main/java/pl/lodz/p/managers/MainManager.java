package pl.lodz.p.managers;

import org.xml.sax.SAXException;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by kamil on 5/22/16.
 */
public interface MainManager {
    void createCompany(CreateCompanyDTO quote);
    List<Company> getAllCompanies();
    List<CompanyStockValue> getAllStockValues();
    void createCompanyStockValue(QuoteResponseDTO quote);
    void getExchangeRate() throws IOException, ParserConfigurationException, SAXException;
}
