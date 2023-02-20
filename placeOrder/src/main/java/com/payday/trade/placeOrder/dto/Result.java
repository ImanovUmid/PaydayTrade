package com.payday.trade.placeOrder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "v",
        "vw",
        "o",
        "c",
        "h",
        "l",
        "t",
        "n"
})
public class Result {

    @JsonProperty("v")
    Double v;
    @JsonProperty("vw")
    Double vw;
    @JsonProperty("o")
    Double o;
    @JsonProperty("c")
    Double c;
    @JsonProperty("h")
    Double h;
    @JsonProperty("l")
    Double l;
    @JsonProperty("t")
    Long t;
    @JsonProperty("n")
    Integer n;


}
