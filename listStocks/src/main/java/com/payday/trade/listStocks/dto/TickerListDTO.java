package com.payday.trade.listStocks.dto;

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
        "ticker",
        "name",
        "market",
        "locale",
        "primary_exchange",
        "type",
        "active",
        "currency_name",
        "cik",
        "composite_figi",
        "share_class_figi",
        "last_updated_utc"
})
public class TickerListDTO {
     @JsonProperty("ticker")
      String ticker;
     @JsonProperty("name")
      String name;
     @JsonProperty("market")
      String market;
     @JsonProperty("locale")
      String locale;
     @JsonProperty("primary_exchange")
      String primaryExchange;
     @JsonProperty("type")
      String type;
     @JsonProperty("active")
      Boolean active;
     @JsonProperty("currency_name")
      String currencyName;
     @JsonProperty("cik")
      String cik;
     @JsonProperty("composite_figi")
      String compositeFigi;
     @JsonProperty("share_class_figi")
      String shareClassFigi;
     @JsonProperty("last_updated_utc")
      String lastUpdatedUtc;
}
