package pl.lodz.p.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kamil on 5/31/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCurrencyDTO {

    @JsonProperty(value = "currency_code")
    private String code;
    @JsonProperty(value = "currency_name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
