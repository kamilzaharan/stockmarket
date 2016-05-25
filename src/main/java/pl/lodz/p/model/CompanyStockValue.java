package pl.lodz.p.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by kamil on 5/22/16.
 */
@Entity
@Table(name = "COMPANY_STOCK_VALUE")
public class CompanyStockValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "LAST_PRICE")
    private double lastPrice;

    @Column(name = "CXHANGE")
    private double change;

    @Column(name = "CXHANGE_PERCENT")
    private double changePercent;

    @Column(name = "TIMESTAMP")
    private String timestamp;

    @Column(name = "MS_DATE")
    private double msDate;

    @Column(name = "MARKET_CAP")
    private double marketCap;

    @Column(name = "VOLUME")
    private int Volume;

    @Column(name = "CXHANGE_YTD")
    private double changeYTD;

    @Column(name = "CXHANGE_PERCENT_YTD")
    private double changePercentYTD;

    @Column(name = "HIGH")
    private double high;

    @Column(name = "LOW")
    private double low;

    @Column(name = "OPEN")
    private double open;

    @ManyToOne
    @JoinColumn(name = "REF_COMPANY_ID", referencedColumnName = "ID")
    private Company companyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getMsDate() {
        return msDate;
    }

    public void setMsDate(double msDate) {
        this.msDate = msDate;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }

    public double getChangeYTD() {
        return changeYTD;
    }

    public void setChangeYTD(double changeYTD) {
        this.changeYTD = changeYTD;
    }

    public double getChangePercentYTD() {
        return changePercentYTD;
    }

    public void setChangePercentYTD(double changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "CompanyStockValue{" +
                "id=" + id +
                ", lastPrice=" + lastPrice +
                ", change=" + change +
                ", changePercent=" + changePercent +
                ", timestamp='" + timestamp + '\'' +
                ", msDate=" + msDate +
                ", marketCap=" + marketCap +
                ", Volume=" + Volume +
                ", changeYTD=" + changeYTD +
                ", changePercentYTD=" + changePercentYTD +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", companyId=" + companyId +
                '}';
    }
}
