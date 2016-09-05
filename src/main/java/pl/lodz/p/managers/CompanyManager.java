package pl.lodz.p.managers;

import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.model.Company;
import pl.lodz.p.neuralNetwork.Point;

import java.util.List;

/**
 * Created by Kaltair on 2016-08-09.
 */
public interface CompanyManager {

     void addCompany(Company company);

     List<Point> findMaxIncrease();

     List<Point> findMaxDecrease();

     List<Point> findStockValuesList(int id);

     String getAverage(int id);

     String getVariance(int id);

     String getStandardDeviation(int id);

     String getMedian(int id);

     void setCompanyDAO(CompanyDAO companyDAO);

}


