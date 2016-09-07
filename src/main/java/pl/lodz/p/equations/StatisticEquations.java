package pl.lodz.p.equations;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;

/**
 * Created by Kaltair on 2016-08-23.
 */
@Component
public class StatisticEquations {

    public double getStockValuesAverage(List<Double> listOfStockValuesLastPrice) {

        double average = 0;

        for (int i = 0; i < listOfStockValuesLastPrice.size(); i++) {
            average += listOfStockValuesLastPrice.get(i);
        }
        average /= listOfStockValuesLastPrice.size();
        return average;
    }

    public double getStockValuesVariance(List<Double> listOfStockValuesLastPrice) {
        double average = getStockValuesAverage(listOfStockValuesLastPrice);

        double variance = 0;

        for (int i = 0; i < listOfStockValuesLastPrice.size(); i++) {
            variance += Math.pow((listOfStockValuesLastPrice.get(i) - average), 2);
        }
        variance /= listOfStockValuesLastPrice.size();

        return variance;
    }

    public double getStockValuesStandardDeviation(List<Double> listOfStockValuesLastPrice) {
        return Math.sqrt(getStockValuesVariance(listOfStockValuesLastPrice));
    }

    public double getStockValuesMedian(Double[] companySortedArray) {
        double median = 0;
        int medianId = companySortedArray.length;
        if (medianId % 2 == 0) {
            medianId /= 2;
            median = (companySortedArray[medianId - 1] + companySortedArray[medianId]) / 2;
            return median;
        } else {
            medianId /= 2;
            median = companySortedArray[medianId + 1];
            return median;
        }
    }

}
