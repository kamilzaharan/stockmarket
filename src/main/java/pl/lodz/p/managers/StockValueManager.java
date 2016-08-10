package pl.lodz.p.managers;

import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;
import java.util.Set;

/**
 * Created by Kaltair on 2016-08-09.
 */
public interface StockValueManager {

    void addStockValue (CompanyStockValue companyStockValue);
    void addListOfStockValue (Set<CompanyStockValue> stockValues);
    List<Object[]> getStockValueById(int companyId);
}
