package pl.lodz.p.equations;

import org.springframework.stereotype.Service;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;

/**
 * Created by Kaltair on 2016-08-23.
 */
@Service
public class StatisticEquations {

    public double getStockValuesAverage(List<Double> listOfStockValuesLastPrice) {

        double average=0;

        for(Double lastPrice: listOfStockValuesLastPrice){
            average+=lastPrice;
        }
        average/=listOfStockValuesLastPrice.size();
        return average;
    }

    public double getStockValuesVariance(List<Double> listOfStockValuesLastPrice) {
        double average=0;

        for(Double lastPrice: listOfStockValuesLastPrice){
            average+=lastPrice;
        }
        average/=listOfStockValuesLastPrice.size();

        double variance=0;

        for(Double lastPrice: listOfStockValuesLastPrice){
            variance+=Math.pow((lastPrice-average),2);
        }
        variance/=listOfStockValuesLastPrice.size();

        return variance;
    }

    public double getStockValuesStandardDeviation(List<Double> listOfStockValuesLastPrice) {

        double standardDeviation=0;
        standardDeviation= Math.sqrt(getStockValuesVariance(listOfStockValuesLastPrice));

        return standardDeviation;
    }

    public double getStockValuesMedian(Double[] companySortedArray ) {
        double median=0;
        int medianId = companySortedArray.length;
        if(medianId%2==0){
            medianId/=2;
            median= (companySortedArray[medianId]+companySortedArray[medianId+1])/2;
            return median;
        }
        else{
            medianId/=2;
            median =companySortedArray[medianId+1];
            return median;
        }
    }

}
