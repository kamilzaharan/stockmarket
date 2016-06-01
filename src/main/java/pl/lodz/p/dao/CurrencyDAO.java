package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.model.Currency;

/**
 * Created by kamil on 5/31/16.
 */
public interface CurrencyDAO extends JpaRepository<Currency,Long> {

}
