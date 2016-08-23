package pl.lodz.p.equations;

import org.springframework.stereotype.Service;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

/**
 * Created by Kaltair on 2016-08-23.
 */
@Service
public class StatisticEquations {

    public double getStockValuesAverage(Company company) {
        double average=0;

        for(CompanyStockValue stockValue: company.getCompanyStockValueList()){
            average+=stockValue.getLastPrice();
        }
        average/=company.getCompanyStockValueList().size();
        return average;
    }

    public double getStockValuesVariance(Company company) {
        double average=0;

        for(CompanyStockValue stockValue: company.getCompanyStockValueList()){
            average+=stockValue.getLastPrice();
        }
        average/=company.getCompanyStockValueList().size();

        double variance=0;

        for(CompanyStockValue stockValue: company.getCompanyStockValueList()){
            variance+=Math.pow((stockValue.getLastPrice()-average),2);
        }
        variance/=company.getCompanyStockValueList().size();

        return variance;
    }

    public double getStockValuesStandardDeviation(Company company) {

        double standardDeviation=0;
        standardDeviation= Math.sqrt(getStockValuesVariance(company));

        return standardDeviation;
    }

}
