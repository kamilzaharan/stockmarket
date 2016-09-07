package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import pl.lodz.p.model.Company;
import pl.lodz.p.neuralNetwork.Point;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */
public interface CompanyDAO extends JpaRepository<Company, Long> {

    Company findBySymbol(String symbol);

    String FIND_ID_NAME_SYMBOL = "SELECT id, full_name, symbol FROM company";
    String GET_COMPANIES_ID = "SELECT id FROM company order by id";
    String GET_COMPANY = "SELECT * FROM company WHERE id= :id";

    @Query(value = FIND_ID_NAME_SYMBOL, nativeQuery = true)
    List<Object[]> findCompanyIdNameSymbolQuery();


    @Query(value = GET_COMPANIES_ID, nativeQuery = true)
    List<Object> getCompaniesId();

    @Query(value = GET_COMPANY, nativeQuery = true)
    Company getCompany(@Param("id") BigInteger id);

}
