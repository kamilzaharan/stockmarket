package pl.lodz.p.model;

import java.util.Set;

import javax.persistence.*;

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

    @OneToMany(mappedBy = "companyId", fetch = FetchType.LAZY)
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

    public CompanyStockValue[] getCompanyStockValuesArray() {
        CompanyStockValue[] strArr = new CompanyStockValue[companyStockValueList.size()];

        return companyStockValueList.toArray(strArr);
    }
}
