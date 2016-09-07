package pl.lodz.p.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by kamil on 5/31/16.
 */

@Entity
@Table(name = "CURRENCY_VALUE")
public class CurrencyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "CURRENCY_VALUE")
    private String currencyValue;

    @Column(name = "DATE")
    private String date;

    @ManyToOne
    @JoinColumn(name = "REF_CURRENCY_ID", referencedColumnName = "ID")
    private Currency currencyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {this.date = date; }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Currency currencyId) {
        this.currencyId = currencyId;
    }
}
