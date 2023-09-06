package id.co.bca.bnos.batch.marketplacereversal.integration.rate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.DateTimeSerializer;
import id.co.bca.bnos.batch.marketplacereversal.util.MoneySerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RateDetails {

    public String settlementType;
    public String cis;
    public String category;

    @JsonProperty("currency_code")
    public String currencyCode;

    @JsonProperty("currency_name")
    public String currencyName;

    @JsonProperty("middle")
    public String middle;

    @JsonProperty("mid_rate")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal midRate;

    @JsonProperty("mainframe_code")
    public String mainframeCode;

    @JsonProperty("rate_type")
    public String rateType;
    
    @JsonProperty("buy")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal buy;
    
    @JsonProperty("sell")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal sell;
    
    @JsonProperty("capital_buy")
    public BigDecimal capitalBuy;
    
    @JsonProperty("capital_sell")
    public BigDecimal capitalSell;

    @JsonProperty("condition")
    public String condition;

    @JsonProperty("last_update")
    @JsonFormat(pattern = "yyMMdd HH:mm:ss")
    @JsonSerialize(using = DateTimeSerializer.class)
    // @JsonDeserialize(using = DateTimeDeserializer.class)
    public LocalDateTime lastUpdate;

    @JsonProperty("result")
    public String result;
}
