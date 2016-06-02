package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import pl.lodz.p.model.Currency;

import java.util.List;

/**
 * Created by kamil on 5/31/16.
 */
public interface CurrencyDAO extends JpaRepository<Currency,Long> {

    String FIND_ID_CODE_NAME = "SELECT id, currency_code, currency_name FROM stockmarket.currency";

    @Query(value = FIND_ID_CODE_NAME,nativeQuery = true)
    List<Object[]> findCurrencyIdCodeNameQuery();
}
