package id.co.bca.bnos.batch.marketplacereversal.integration.rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.DateTimeDeserializer;
import id.co.bca.bnos.batch.marketplacereversal.util.MoneySerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Rate {

    @JsonProperty("currency_code")
    public String currencyCode;

    @JsonProperty("mid_rate")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal midRate;

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
    // @JsonFormat(pattern = "dd MMM yyyy HH:mm:ss")
    // @JsonSerialize(using = RateDateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    public LocalDateTime lastUpdate;

    @JsonProperty("rate_detail")
    List<RateDetails> rateDetails;
}
