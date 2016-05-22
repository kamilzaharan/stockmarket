package pl.lodz.p.managers;

import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;

import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */
public interface MainManager {
    void createCompany(String symbol, String fullName);
    List<Company> getAllCompanies();
    void createCompanyStockValue(QuoteResponseDTO quote);
}
