package pl.lodz.p.handlers;

import pl.lodz.p.model.Company;

import java.util.ArrayList;

/**
 * Created by Katarzyna Hibner on 8/23/16.
 */
public interface Handler {
    ArrayList<Company> process(Integer sortType, ArrayList<Company> companyList);
    String getHandlerName();
}
