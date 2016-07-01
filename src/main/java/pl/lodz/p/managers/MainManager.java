package pl.lodz.p.managers;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */
public interface MainManager {
    void createCompany(CreateCompanyDTO quote);
    List<Company> getAllCompanies();
    List<CompanyStockValue> getAllStockValues();
    void createCompanyStockValue(QuoteResponseDTO quote);
    
}
