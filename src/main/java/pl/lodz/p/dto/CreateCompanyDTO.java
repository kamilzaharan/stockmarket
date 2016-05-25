package pl.lodz.p.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kamil on 5/22/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCompanyDTO {

    @JsonProperty(value = "Symbol")
    private String symbol;
    @JsonProperty(value = "Name")
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
