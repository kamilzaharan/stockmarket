package pl.lodz.p.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.model.CompanyStockValue;

/**
 * Created by kamil on 5/22/16.
 */
public interface CompanyStockValueDAO extends JpaRepository<CompanyStockValue,Long> {
}
