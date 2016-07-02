package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.model.CurrencyValue;

/**
 * Created by kamil on 5/31/16.
 */
public interface CurrencyValueDAO extends JpaRepository<CurrencyValue,Long> {
}
