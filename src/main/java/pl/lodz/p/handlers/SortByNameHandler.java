package pl.lodz.p.handlers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.lodz.p.comparators.CompanyComparatorName;
import pl.lodz.p.model.Company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Katarzyna Hibner on 8/23/16.
 */
@Component("sortNameHandler")
public class SortByNameHandler implements Handler{
    private final static Logger logger = Logger.getLogger(SortByNameHandler.class);
    private final static String HANDLER_NAME = "Sort By Name Handler";

    @Override
    public ArrayList<Company> process(Integer sortType, ArrayList<Company> companyList) {

        if(sortType == 1) {
            logger.info("Sort by name start");
            Collections.sort(companyList, new CompanyComparatorName());
        } else {
            logger.info(HANDLER_NAME + ": it's not me");
        }

        return companyList;
    }

    @Override
    public String getHandlerName() {
        return HANDLER_NAME;
    }
}
