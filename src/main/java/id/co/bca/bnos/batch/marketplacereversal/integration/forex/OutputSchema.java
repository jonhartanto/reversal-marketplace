package id.co.bca.bnos.batch.marketplacereversal.integration.forex;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.bca.bnos.batch.marketplacereversal.util.MoneySerializer;

import java.math.BigDecimal;

public class OutputSchema {
    
    @JsonProperty("transaction_id")
    public String transactionId;

    @JsonProperty("deal_id")
    public String dealId;

    @JsonProperty("application_code")
    public String applicationCode;

    @JsonProperty("transaction_date")
    // @DateTimeFormat(pattern = "yyyyMMdd")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    // @JsonSerialize(using = DateSerializer.class)
    // @JsonDeserialize(using = DateTimeDeserializer.class)
    public Integer transactionDate;

    @JsonProperty("transaction_time")
    // @DateTimeFormat(pattern = "HHmmss")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmmss")
    // @JsonSerialize(using = DateTimeSerializer.class)
    // @JsonDeserialize(using = DateTimeDeserializer.class)
    public Integer transactionTime;

    @JsonProperty("transaction_type")
    public String transactionType;

    @JsonProperty("maturity_date")
    // @DateTimeFormat(pattern = "yyyyMMdd")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    // @JsonSerialize(using = DateSerializer.class)
    // @JsonDeserialize(using = DateTimeDeserializer.class)
    public Integer maturityDate;

    @JsonProperty("buy_currency_code")
    public String buyCurrencyCode;
    
    @JsonProperty("buy_amount")
    @JsonSerialize(using = MoneySerializer.class)
    public Long buyAmount;
    
    @JsonProperty("sell_currency_code")
    public String sellCurrencyCode;
    
    @JsonProperty("sell_amount")
    @JsonSerialize(using = MoneySerializer.class)
    public Long sellAmount;
    
    @JsonProperty("rate")
    @JsonSerialize(using = MoneySerializer.class)
    public Integer rate;

    @JsonProperty("deal_purpose")
    public String dealPurpose;

    @JsonProperty("identity_type")
    public String identityType;

    @JsonProperty("identity_number")
    public String identityNumber;

    @JsonProperty("customer_name")
    public String customerName;

    @JsonProperty("flag_underlying_document")
    public String flagUnderlyingDocument;

    @JsonProperty("action")
    public String action;

    @JsonProperty("branch_code")
    public Integer branchCode;

    @JsonProperty("used_amount")
    @JsonSerialize(using = MoneySerializer.class)
    public BigDecimal usedAmount;
}
