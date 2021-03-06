package pl.lodz.p.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kamil on 5/22/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponseDTO {

    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Symbol")
    private String symbol;
    @JsonProperty(value = "LastPrice")
    private double lastPrice;
    @JsonProperty(value = "Change")
    private double change;
    @JsonProperty(value = "ChangePercent")
    private double changePercent;
    @JsonProperty(value = "Timestamp")
    private String timestamp;
    @JsonProperty(value = "MSDate")
    private double msDate;
    @JsonProperty(value = "MarketCap")
    private double marketCap;
    @JsonProperty(value = "Volume")
    private double Volume;
    @JsonProperty(value = "ChangeYTD")
    private double changeYTD;
    @JsonProperty(value = "ChangePercentYTD")
    private double changePercentYTD;
    @JsonProperty(value = "High")
    private double high;
    @JsonProperty(value = "Low")
    private double low;
    @JsonProperty(value = "Open")
    private double open;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
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

    @Override
    public String toString() {
        return "QuoteResponseDTO{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
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
                '}';
    }
}
