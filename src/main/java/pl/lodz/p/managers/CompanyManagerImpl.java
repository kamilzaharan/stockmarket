package pl.lodz.p.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.neuralNetwork.Point;
import pl.lodz.p.utils.Utils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Kaltair on 2016-08-09.
 */
@Service
public class CompanyManagerImpl implements CompanyManager {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanyStockValueDAO companyStockValueDAO;

    @Override
    public void addCompany(Company company){
        companyDAO.save(company);
        companyDAO.flush();
    }

    @Override
    public List<Point> findMaxIncrease() {

        //TODO: jak inaczej zainicjalizować?
        Company companyWithMaxIncrease = null;

        double max = -1;
        double change;

        List<Object> companiesId = companyDAO.getCompaniesId();

        for (Object companyId : companiesId) {
            Company company = companyDAO.getCompany((BigInteger) companyId);

            change = getChangeBetweenStockValues(company);

            if (change > max) {
                max = change;
                companyWithMaxIncrease = company;
            }
        }

        return Utils.createPoints(companyWithMaxIncrease);
    }

    @Override
    public List<Point> findStockValuesList(int id) {

        //TODO: jak inaczej zainicjalizować?

        Company company = companyDAO.getCompany(BigInteger.valueOf(id));
        return Utils.createPoints(company);
    }

    @Override
    public List<Point> findMaxDecrease() {
        Company companyWithMaxDecrease = null;

        double min = 0;
        double change;

        List<Object> companiesId = companyDAO.getCompaniesId();

        for (Object companyId : companiesId) {
            Company company = companyDAO.getCompany((BigInteger) companyId);
            change = getChangeBetweenStockValues(company);

            if (change < min) {
                min = change;
                companyWithMaxDecrease = company;
            }
        }

        return Utils.createPoints(companyWithMaxDecrease);
    }

    private double getChangeBetweenStockValues(Company company) {
        double firstStockValue;
        double lastStockValue;

        CompanyStockValue[] strArr = company.getCompanyStockValuesArray();
        strArr = Utils.bubbleSort(strArr);

        firstStockValue = strArr[0].getLastPrice();
        lastStockValue = strArr[strArr.length - 1].getLastPrice();

        return lastStockValue - firstStockValue;
    }


}
