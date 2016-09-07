package pl.lodz.p.managers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.equations.StatisticEquations;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.neuralNetwork.Point;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Kaltair on 2016-09-02.
 */

@RunWith(MockitoJUnitRunner.class)
public class CompanyManagerTests {
    CompanyManager companyManager = new CompanyManagerImpl();
    CompanyDAO companyDAO;
    StatisticEquations statisticEquationMock;

    private ArrayList<ArrayList<Double>> setCompanyStockValues() {
        ArrayList<ArrayList<Double>> arrayOfLastValues = new ArrayList<>();
        ArrayList<Double> arrayOfValues1 = new ArrayList<>(asList(1.0, 2.0, 3.0));
        ArrayList<Double> arrayOfValues2 = new ArrayList<>(asList(3.0, 2.0, 1.0));
        ArrayList<Double> arrayOfValues3 = new ArrayList<>(asList(1.0, 4.0, 6.0));
        arrayOfLastValues.add(arrayOfValues1);
        arrayOfLastValues.add(arrayOfValues2);
        arrayOfLastValues.add(arrayOfValues3);
        return arrayOfLastValues;
    }

    private List<Object> setArrayOfIndexes() {
        List<Object> companiesIdList = new ArrayList();

        for (int i = 0; i < 3; i++) {
            companiesIdList.add(BigInteger.valueOf(i));
        }
        return companiesIdList;
    }

    @Before
    public void setListMock() {

        companyDAO = mock(CompanyDAO.class);
        when(companyDAO.getCompaniesId()).thenReturn(setArrayOfIndexes());
        statisticEquationMock = mock(StatisticEquations.class);
        when(statisticEquationMock.getStockValuesStandardDeviation(anyList())).thenReturn(1.23345321)
                .thenReturn(322.221)
                .thenReturn(111111.0);

        Company company1 = mock(Company.class);
        Company company2 = mock(Company.class);
        Company company3 = mock(Company.class);

        ArrayList<ArrayList<Double>> arrayOfLastValues = setCompanyStockValues();

        ArrayList<CompanyStockValue[]> companyStockValueListOfArrays = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            CompanyStockValue[] companyStockValueArray = new CompanyStockValue[3];
            for (int i = 0; i < 3; i++) {
                CompanyStockValue companyStockValue;
                companyStockValue = mock(CompanyStockValue.class);
                Double lastPrice = arrayOfLastValues.get(j).get(i);
                when(companyStockValue.getLastPrice()).thenReturn(lastPrice);
                companyStockValueArray[i] = companyStockValue;
            }
            companyStockValueListOfArrays.add(companyStockValueArray);
        }
        when(company1.getCompanyStockValuesArray()).thenReturn(companyStockValueListOfArrays.get(0));
        when(company2.getCompanyStockValuesArray()).thenReturn(companyStockValueListOfArrays.get(1));
        when(company3.getCompanyStockValuesArray()).thenReturn(companyStockValueListOfArrays.get(2));

        Integer integer1 = 0;
        BigInteger bigInt1 = BigInteger.valueOf(integer1.intValue());
        Integer integer2 = 1;
        BigInteger bigInt2 = BigInteger.valueOf(integer2.intValue());
        Integer integer3 = 2;
        BigInteger bigInt3 = BigInteger.valueOf(integer3.intValue());

        when(companyDAO.getCompany(bigInt1)).thenReturn(company1);
        when(companyDAO.getCompany(bigInt2)).thenReturn(company2);
        when(companyDAO.getCompany(bigInt3)).thenReturn(company3);
    }

    @Test
    public void stockMaxIncreaseTest() {
        companyManager.setCompanyDAO(companyDAO);
        List<Point> listOfPoints = companyManager.findMaxIncrease();
        ArrayList<Double> arrayOfValues = new ArrayList<>();
        for (Point point : listOfPoints) {
            arrayOfValues.add(point.getY());
        }

        assertThat(arrayOfValues).containsExactly(1.0, 4.0, 6.0);
    }

    @Test
    public void stockMaxDecreaseTest() {
        companyManager.setCompanyDAO(companyDAO);
        List<Point> listOfPoints = companyManager.findMaxDecrease();
        ArrayList<Double> arrayOfValues = new ArrayList<>();
        for (Point point : listOfPoints) {
            arrayOfValues.add(point.getY());
        }

        assertThat(arrayOfValues).containsExactly(3.0, 2.0, 1.0);
    }

    @Test
    public void decimalFormatTest() {
        companyManager.setStatisticEquations(statisticEquationMock);
        assertThat(companyManager.getStandardDeviation()).isEqualTo("1.23")
                .isNotEqualTo("1,23")
                .isInstanceOf(String.class)
                .hasSize(4);

        assertThat(companyManager.getStandardDeviation()).isEqualTo("322.22")
                .isNotEqualTo("322,22")
                .isInstanceOf(String.class)
                .hasSize(6);

        assertThat(companyManager.getStandardDeviation()).isEqualTo("111111")
                .isNotEqualTo("111111.00")
                .isNotEqualTo("111111,00")
                .isInstanceOf(String.class)
                .hasSize(6);
    }
}
