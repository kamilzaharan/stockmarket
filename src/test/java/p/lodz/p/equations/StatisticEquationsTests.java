package p.lodz.p.equations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lodz.p.equations.StatisticEquations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by Kaltair on 2016-08-24.
 */

@RunWith(MockitoJUnitRunner.class)
public class StatisticEquationsTests {

    List listOfValues;
    StatisticEquations statisticEquations = new StatisticEquations();

    @Before
    public void setListMock() {
        List list = new ArrayList();
        listOfValues = spy(list);
        for (double i = 0; i < 10; i++) {
            doReturn(i + 1).when(listOfValues).get((int) i);
        }
        doReturn(10).when(listOfValues).size();
    }

    @Test
    public void stockValueAverageTest() {
        double average;
        average = statisticEquations.getStockValuesAverage(listOfValues);
        assertThat(average).isEqualTo(5.5);
    }

    @Test
    public void stockValuesVarianceTest() {
        double variance;
        variance = statisticEquations.getStockValuesVariance(listOfValues);
        assertThat(variance).isBetween(8.24, 8.27);
    }

    @Test
    public void stockValuesStandardDeviationTest() {
        double standardDeviation;
        standardDeviation = statisticEquations.getStockValuesStandardDeviation(listOfValues);
        assertThat(standardDeviation).isBetween(2.7, 2.9);
    }

    @Test
    public void stockValuesMedianTest() {
        double median;
        Double[] companyStockValueArray = new Double[listOfValues.size()];
        for (int i = 0; i < listOfValues.size() - 1; i++) {
            companyStockValueArray[i] = (Double) listOfValues.get(i);
        }
        median = statisticEquations.getStockValuesMedian(companyStockValueArray);
        assertThat(median).isEqualTo(5.5);
    }
}

