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
    String FIND_EXCHANGE_RATE = "SELECT c.currency_code, c.currency_name, v.currency_value FROM stockmarket.currency c, stockmarket.currency_value v where c.id=v.id";

    @Query(value = FIND_ID_CODE_NAME,nativeQuery = true)
    List<Object[]> findCurrencyIdCodeNameQuery();

    @Query(value = FIND_EXCHANGE_RATE,nativeQuery = true)
    List<Object[]> findExchangeRateQuery();
}
