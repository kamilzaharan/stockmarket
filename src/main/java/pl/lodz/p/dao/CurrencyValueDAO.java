package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import pl.lodz.p.model.CurrencyValue;

import java.util.List;

/**
 * Created by kamil on 5/31/16.
 */
public interface CurrencyValueDAO extends JpaRepository<CurrencyValue,Long> {
    String GET_LAST_DATE="SELECT id, date FROM currency_value ORDER BY id DESC LIMIT 1;";

    @Query(value = GET_LAST_DATE,nativeQuery = true)
    List<Object[]> getLastDateQuery();
}
