package pl.lodz.p.managers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.controllers.AngularController;
import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.dao.CompanyStockValueDAO;
import pl.lodz.p.equations.StatisticEquations;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.neuralNetwork.Point;
import pl.lodz.p.utils.Utils;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kaltair on 2016-08-09.
 */

@Service
public class CompanyManagerImpl implements CompanyManager {

    private Logger log = Logger.getLogger(AngularController.class);
    List<Double> listOfStockValuesLastPrice = new ArrayList();

    @Autowired
    StatisticEquations statisticEquations;
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

        //TODO: jak inaczej zainicjalizowaÄ‡?
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

        //TODO: jak inaczej zainicjalizowaÄ‡?

        Company company = companyDAO.getCompany(BigInteger.valueOf(id));

        listOfStockValuesLastPrice.clear();
        listOfStockValuesLastPrice.addAll(company.getCompanyStockValueList().stream()
                                .map(CompanyStockValue::getLastPrice)
                                .collect(Collectors.toList()));

//        double average = getAverage(id);
//        double variance = getVariance(id);
//        double standardDeviation = getStandardDeviation(id);
//        double median = getMedian(id);
//
//        log.info("Srednia wartosc akcji dla tej firmy wynosi " + average);
//        log.info("Wariancja akcji dla tej firmy wynosi " + variance);
//        log.info("Odchylenie standardowe akcji dla tej firmy wynosi " + standardDeviation);
//        log.info("Mediana akcji dla tej firmy wynosi " + median);

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

    @Override
    public String getStandardDeviation(int companyId){
        double standardDeviation = statisticEquations.getStockValuesStandardDeviation(listOfStockValuesLastPrice);

        return new DecimalFormat("#.##").format(standardDeviation);
    }

    @Override
    public String getAverage(int companyId){
        double average = statisticEquations.getStockValuesAverage(listOfStockValuesLastPrice);

        return new DecimalFormat("#.##").format(average);
    }

    @Override
    public String getVariance(int companyId){
        double variance = statisticEquations.getStockValuesVariance(listOfStockValuesLastPrice);

        return new DecimalFormat("#.##").format(variance);
    }

    @Override
    public String getMedian(int companyId){
        double median;
        Double[] companyStockValueArray = new Double[listOfStockValuesLastPrice.size()];
        for(int i=0; i<listOfStockValuesLastPrice.size();i++){
            companyStockValueArray[i]=listOfStockValuesLastPrice.get(i);
        }
        Double[] companySortedStockValueArray = Utils.bubbleSortByValue(companyStockValueArray);
        median= statisticEquations.getStockValuesMedian(companySortedStockValueArray);
        return new DecimalFormat("#.##").format(median);
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
