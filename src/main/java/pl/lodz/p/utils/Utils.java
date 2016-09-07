package pl.lodz.p.utils;
import com.opencsv.CSVReader;
import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;
import pl.lodz.p.neuralNetwork.Point;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaltair on 2016-08-13.
 */
public class Utils {
    public static List<Company> readCompaniesFromCsv(String path) {

        List<Company> listOfCompanys = new ArrayList<>();
        CSVReader reader = null;
        int i=1;

        try {
            reader = new CSVReader(new FileReader(path));
            String[] line;

            while ((line = reader.readNext()) != null) {
                Company company=new Company();
                company.setId(i);
                company.setFullName(line[1]);
                company.setSymbol(line[0]);
                listOfCompanys.add(company);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfCompanys;
    }

    public static List<Point> createPoints(Company company) {
        int day = 0;
        List<Point> points = new ArrayList<>();

        if(company != null) {

            CompanyStockValue[] pointsArray = company.getCompanyStockValuesArray();
            pointsArray = bubbleSort(pointsArray);

            for (CompanyStockValue companyStockValue : pointsArray) {
                points.add((new Point(day, companyStockValue.getLastPrice())));

                day += 1;
            }
        }
        return points;
    }

    public static CompanyStockValue[] bubbleSort(CompanyStockValue[] a) {
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

    public static Double[] bubbleSortByValue(Double[] a) {
        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - 1; j++) {

                if (a[j] > a[j + 1]) {
                    Double temp;
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }
}
