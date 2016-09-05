package pl.lodz.p.managers;

import pl.lodz.p.dto.CreateCompanyDTO;
import pl.lodz.p.dto.QuoteResponseDTO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.model.Currency;
import pl.lodz.p.model.CurrencyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */
public interface MainManager {
    String getCurrentExchangeRateDate();
    void createCompany(CreateCompanyDTO quote);
    List<Company> getAllCompanies();
    List<Currency> getAllCurrencies();
    List<CurrencyValue> getAllCurrenciesValues();
    List<CompanyStockValue> getAllStockValues();
    List<Object[]> findCompanyIdNameSymbol();
    List<Object[]> findCurrencyIdCodeName();
    List<Object[]> checkIfCurrencyTableIsFilled();
    List<Object[]> getLastDate();
    List<Object[]> findExchangeRate();
    void createCompanyStockValue(QuoteResponseDTO quote);
    void getExchangeRate();
    ArrayList<Company> sort(ArrayList<Company> companyList, Integer sortType);

}
