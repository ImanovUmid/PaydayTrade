package com.payday.trade.placeOrder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ticker",
        "queryCount",
        "resultsCount",
        "adjusted",
        "results",
        "status",
        "request_id",
        "count"
})
public class TickerInfoDTO {

    @JsonProperty("ticker")
    String ticker;
    @JsonProperty("queryCount")
    Integer queryCount;
    @JsonProperty("resultsCount")
    Integer resultsCount;
    @JsonProperty("adjusted")
    Boolean adjusted;
    @JsonProperty("results")
    List<Result> results;
    @JsonProperty("status")
    String status;
    @JsonProperty("request_id")
    String requestId;
    @JsonProperty("count")
    Integer count;

}
