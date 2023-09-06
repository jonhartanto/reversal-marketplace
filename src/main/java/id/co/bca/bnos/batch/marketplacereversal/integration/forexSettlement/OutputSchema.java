package id.co.bca.bnos.batch.marketplacereversal.integration.forexSettlement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.MoneySerializer;

import java.math.BigDecimal;

public class OutputSchema {
    
    @JsonProperty("transaction_id")
    public String transactionId;

    @JsonProperty("transaction_type")
    public String transactionType;

    @JsonProperty("deal_id")
    public String dealId;

    @JsonProperty("account_number")
    public String accountNumber;

    @JsonProperty("amount")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal amount;

    @JsonProperty("application_code")
    public String applicationCode;
    
}
