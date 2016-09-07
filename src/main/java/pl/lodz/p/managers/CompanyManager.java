package pl.lodz.p.managers;

import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.equations.StatisticEquations;
import pl.lodz.p.model.Company;
import pl.lodz.p.neuralNetwork.Point;

import java.util.List;

/**
 * Created by Kaltair on 2016-08-09.
 */
public interface CompanyManager {

    List<Point> findMaxIncrease();

    List<Point> findMaxDecrease();

    List<Point> findStockValuesList(int id);

    String getAverage();

    String getVariance();

    String getStandardDeviation();

    String getMedian();

    void addCompany(Company company);

    void setCompanyDAO(CompanyDAO companyDAO);

    void setStatisticEquations(StatisticEquations statisticEquations);
}


