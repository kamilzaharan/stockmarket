package pl.lodz.p.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.neuralNetwork.Point;

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
        double firstStockValue;
        double lastStockValue;
        double change;

        List<Object> companiesId = companyDAO.getCompaniesId();

        for (Object companyId : companiesId) {
            Company company = companyDAO.getCompany((BigInteger) companyId);

            CompanyStockValue[] strArr = company.getCompanyStockValuesArray();
            strArr = bubbleSort(strArr);

            firstStockValue = strArr[0].getLastPrice();
            lastStockValue = strArr[strArr.length - 1].getLastPrice();
            change = lastStockValue - firstStockValue;

            if (change > max) {
                max = change;
                companyWithMaxIncrease = company;
            }
        }

        return createPoints(companyWithMaxIncrease);
    }

    private List<Point> createPoints(Company companyWithMaxIncrease) {
        int day = 0;
        List<Point> points = new ArrayList<>();

        CompanyStockValue[] pointsArray = companyWithMaxIncrease.getCompanyStockValuesArray();
        pointsArray = bubbleSort(pointsArray);

        for (CompanyStockValue companyStockValue : pointsArray) {
            points.add((new Point(day, companyStockValue.getLastPrice())));

            //TODO: zamienić timestamp na dzień i wrzucić w miejsce x
            day += 1;
        }

        return points;
    }

    private CompanyStockValue[] bubbleSort(CompanyStockValue[] a) {
        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - 1; j++) {

                if (a[j].getId() > a[j + 1].getId()) {
                    CompanyStockValue temp;
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }

        return a;
    }
}
