package pl.lodz.p.comparators;

import pl.lodz.p.model.Company;

import java.util.Comparator;

/**
 * Created by Katarzyna Hibner on 8/22/16.
 */
public class CompanyComparatorSymbol implements Comparator<Company> {

    @Override
    public int compare(Company company1, Company company2) {

        return company1.getSymbol().compareTo(company2.getSymbol());
    }
}
