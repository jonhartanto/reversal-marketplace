package id.co.bca.bnos.batch.marketplacereversal.integration.rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.DateTimeDeserializer;
import id.co.bca.bnos.batch.marketplacereversal.util.MoneySerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OutputSchema {

    @JsonProperty("rate_type")
    public String rateType;
    
    @JsonProperty("currency_code")
    public String currencyCode;
    
    @JsonProperty("mid_rate")
    public BigDecimal midRate;

    @JsonProperty("rate")
    public List<Rate> rate = new ArrayList<Rate>();

    @JsonProperty("currency")
    public List<CurrencyRate> currency = new ArrayList<CurrencyRate>();

    @JsonProperty("invalid_rate_type")
    public BigDecimal invalidRateType;
    
    @JsonProperty("invalid_currency_code")
    public BigDecimal invalidCurrencyCode;

    @JsonProperty("result")
    public String result;

    @JsonProperty("buy")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal buy;
    
    @JsonProperty("sell")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal sell;
    
    @JsonProperty("session_id")
    public String condition;

    @JsonProperty("last_update")
    // @JsonFormat(pattern = "dd MMM yyyy HH:mm:ss")
    // @JsonSerialize(using = RateDateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    public LocalDateTime lastUpdate;

}

