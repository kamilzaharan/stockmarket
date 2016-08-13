package pl.lodz.p.model;


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by kamil on 5/22/16.
 */
@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "SYMBOL")
    private String symbol;

    @OneToMany(mappedBy = "companyId")
    private Set<CompanyStockValue> companyStockValueList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Set<CompanyStockValue> getCompanyStockValueList() {
        return companyStockValueList;
    }

    public void setCompanyStockValueList(Set<CompanyStockValue> companyStockValueList) {
        this.companyStockValueList = companyStockValueList;
    }
}
