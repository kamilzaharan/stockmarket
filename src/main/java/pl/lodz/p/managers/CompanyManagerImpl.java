package pl.lodz.p.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.dao.CompanyDAO;
import pl.lodz.p.model.Company;

/**
 * Created by Kaltair on 2016-08-09.
 */
@Service
public class CompanyManagerImpl implements CompanyManager {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public void addCompany(Company company){
        companyDAO.save(company);
        companyDAO.flush();
    }
}
