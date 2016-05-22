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
    private String lastPrice;

    @Column(name = "CXHANGE")
    private String change;

    @Column(name = "CXHANGE_PERCENT")
    private String changePercent;

    @Column(name = "TIMESTAMP")
    private String timestamp;

    @Column(name = "MS_DATE")
    private String msDate;

    @Column(name = "MARKET_CAP")
    private String marketCap;

    @Column(name = "VOLUME")
    private int Volume;

    @Column(name = "CXHANGE_YTD")
    private String changeYTD;

    @Column(name = "CXHANGE_PERCENT_YTD")
    private String changePercentYTD;

    @Column(name = "HIGH")
    private String high;

    @Column(name = "LOW")
    private String low;

    @Column(name = "OPEN")
    private String open;

    @ManyToOne
    @JoinColumn(name = "REF_COMPANY_ID", referencedColumnName = "ID")
    private Company companyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsDate() {
        return msDate;
    }

    public void setMsDate(String msDate) {
        this.msDate = msDate;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }

    public String getChangeYTD() {
        return changeYTD;
    }

    public void setChangeYTD(String changeYTD) {
        this.changeYTD = changeYTD;
    }

    public String getChangePercentYTD() {
        return changePercentYTD;
    }

    public void setChangePercentYTD(String changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }
}
