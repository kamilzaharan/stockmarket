package pl.lodz.p.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;
import java.util.Set;

/**
 * Created by Kaltair on 2016-08-09.
 */
@Service
public class StockValueManagerImpl implements StockValueManager {

    @Autowired
    private CompanyStockValueDAO companyStockValueDAO;

    @Override
    public void addStockValue(CompanyStockValue stockValue) {
        companyStockValueDAO.save(stockValue);
        companyStockValueDAO.flush();
    }

    @Override
    public void addListOfStockValue(Set<CompanyStockValue> stockValues) {

        for (CompanyStockValue stockValue : stockValues) {
            companyStockValueDAO.save(stockValue);
            companyStockValueDAO.flush();
        }
    }

    @Override
    public List<Object[]> getStockValueById(int companyId) {
        return companyStockValueDAO.findStockValueById(companyId);
    }
}
