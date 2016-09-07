package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import pl.lodz.p.model.Currency;

import java.util.List;


public interface CurrencyDAO extends JpaRepository<Currency, Long> {

    String FIND_ID_CODE_NAME = "SELECT id, currency_code, currency_name FROM stockmarket.currency";
    String FIND_EXCHANGE_RATE = "SELECT c.currency_code, c.currency_name, v.currency_value FROM stockmarket.currency c, (SELECT id, currency_value, ref_currency_id from currency_value order by id desc limit 35) v where c.id=v.ref_currency_id order by c.id";
    String FIND_CURRENCY_CHART_DATA = "SELECT v.date, v.currency_value, c.currency_code, v.ref_currency_id FROM stockmarket.currency c, stockmarket.currency_value v WHERE v.ref_currency_id=c.id;";

    @Query(value = FIND_ID_CODE_NAME, nativeQuery = true)
    List<Object[]> findCurrencyIdCodeNameQuery();

    @Query(value = FIND_EXCHANGE_RATE, nativeQuery = true)
    List<Object[]> findExchangeRateQuery();

    @Query(value = FIND_CURRENCY_CHART_DATA, nativeQuery = true)
    List<Object[]> findCurrencyChartDataQuery();

}
