package pl.lodz.p.handlers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.comparators.CompanyComparatorSymbol;
import pl.lodz.p.model.Company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Katarzyna Hibner on 8/23/16.
 */
@Component("sortSymbolHandler")
public class SortBySymbolHandler implements Handler{

    private final static Logger logger = Logger.getLogger(SortBySymbolHandler.class);
    private static final String HANDLER_NAME = "Sort By Symbol Handler";

    @Autowired
    private Handler sortNameHandler;

    @Override
    public ArrayList<Company> process(Integer sortType, ArrayList<Company> companyList) {

        if(sortType == 2) {
            logger.info("Sort by symbol start");
            Collections.sort(companyList, new CompanyComparatorSymbol());

            return companyList;

        } else {
            logger.info(HANDLER_NAME + ": it's not me");

            return sortNameHandler.process(sortType, companyList);
        }
    }

    @Override
    public String getHandlerName() {
        return HANDLER_NAME;
    }
}
