package id.co.bca.bnos.batch.marketplacereversal.integration.rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.DateTimeDeserializer;
import id.co.bca.bnos.batch.marketplacereversal.util.MoneySerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CurrencyRate {

    @JsonProperty("currency_code")
    public String currencyCode;

    @JsonProperty("mid_rate")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal midRate;
    
    @JsonProperty("buy")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal buy;
    
    @JsonProperty("sell")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal sell;

    @JsonProperty("last_update")
    // @JsonFormat(pattern = "dd MMM yyyy HH:mm:ss")
    // @JsonSerialize(using = RateDateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    public LocalDateTime lastUpdate;

    @JsonProperty("rate")
    List<Rate> rate;
}
