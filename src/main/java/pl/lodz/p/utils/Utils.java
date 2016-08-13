package pl.lodz.p.utils;
import com.opencsv.CSVReader;
import pl.lodz.p.model.Company;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaltair on 2016-08-13.
 */
public class Utils {
    public static List<Company> readCompaniesFromCsv(String path) {

        String csvFile = path;
        List<Company> listOfCompanys = new ArrayList();
        CSVReader reader = null;
        int i=1;
        try {
            reader = new CSVReader(new FileReader(csvFile));
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
}
