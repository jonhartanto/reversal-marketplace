package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class DAINOutputSchema {
    
    private BigDecimal arrCount;
    private BigDecimal reference;
    private String logType;
    private String add_inf;
    private String sharp1;
    private String sharp2;
    private String delimeter1;
    private String delimeter2;
    private BigDecimal balance;
    private Integer controlNumber;

    @JsonProperty("array_count")
    public BigDecimal getArrCount() {
        return arrCount;
    }

    @JsonProperty("array_count")
    public void setArrCount(BigDecimal arrCount) {
        this.arrCount = arrCount;
    }

    @JsonProperty("reference_number")
    public BigDecimal getReference() {
        return reference;
    }

    @JsonProperty("reference_number")
    public void setReference(BigDecimal reference) {
        this.reference = reference;
    }

    @JsonProperty("log_type")
    public String getLogType() {
        return logType;
    }

    @JsonProperty("log_type")
    public void setLogType(String logType) {
        this.logType = logType;
    }

    @JsonProperty("additional_information")
    public String getAdd_inf() {
        return add_inf;
    }

    @JsonProperty("additional_information")
    public void setAdd_inf(String add_inf) {
        this.add_inf = add_inf;
    }

    @JsonProperty("sharp_1")
    public String getSharp1() {
        return sharp1;
    }

    @JsonProperty("sharp_1")
    public void setSharp1(String sharp1) {
        this.sharp1 = sharp1;
    }

    @JsonProperty("sharp_2")
    public String getSharp2() {
        return sharp2;
    }

    @JsonProperty("sharp_2")
    public void setSharp2(String sharp2) {
        this.sharp2 = sharp2;
    }

    @JsonProperty("delimiter_1")
    public String getDelimeter1() {
        return delimeter1;
    }

    @JsonProperty("delimiter_1")
    public void setDelimeter1(String delimeter1) {
        this.delimeter1 = delimeter1;
    }

    @JsonProperty("delimiter_2")
    public String getDelimeter2() {
        return delimeter2;
    }

    @JsonProperty("delimiter_2")
    public void setDelimeter2(String delimeter2) {
        this.delimeter2 = delimeter2;
    }

    @JsonProperty("balance")
    public BigDecimal getBalance() {
        return balance;
    }

    @JsonProperty("balance")
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @JsonProperty("control_number")
    public Integer getControlNumber() {
        return controlNumber;
    }

    @JsonProperty("control_number")
    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    
}
