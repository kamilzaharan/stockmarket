package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.model.Company;

/**
 * Created by kamil on 5/22/16.
 */
public interface CompanyDAO extends JpaRepository<Company,Long> {

    Company findBySymbol(String symbol);

}
