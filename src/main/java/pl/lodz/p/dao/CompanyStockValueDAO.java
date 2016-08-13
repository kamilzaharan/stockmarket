package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.lodz.p.model.CompanyStockValue;

import java.util.List;

/**
 * Created by kamil on 5/22/16.
 */
public interface CompanyStockValueDAO extends JpaRepository<CompanyStockValue,Long> {

    String FIND_STOCK_VALUE_BY_ID = "SELECT * FROM company_stock_value WHERE ref_company_id= :id";

    @Query(value= FIND_STOCK_VALUE_BY_ID, nativeQuery = true)
    List<Object[]> findStockValueById(@Param("id") int id);
}
