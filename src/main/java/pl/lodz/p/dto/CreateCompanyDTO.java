package pl.lodz.p.dto;

/**
 * Created by kamil on 5/22/16.
 */
public class CreateCompanyDTO {

    private String symbol;
    private String fullName;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
