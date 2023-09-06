package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSTrfDebitRequest {
    private String irType;
    private String irNumber;
    private String acctNo;
    private String currency;
    // value date (TAG 32A)
    private LocalDate valueDate;
    private LocalDateTime appDate;
    private BigDecimal amount;
    // reference number (TAG 20)
    private String refNo;
    // beneficiary customer (TAG 59-2)
    private String benCust;
    // acct name
    private String acctName;
    // source app
    private String srcApp;

    public String getIrType() {
        return irType;
    }

    public void setIrType(String irType) {
        this.irType = irType;
    }

    public String getIrNumber() {
        return irNumber;
    }

    public void setIrNumber(String irNumber) {
        this.irNumber = irNumber;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public LocalDateTime getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDateTime appDate) {
        this.appDate = appDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getBenCust() {
        return benCust;
    }

    public void setBenCust(String benCust) {
        this.benCust = benCust;
    }

    public String getSrcApp() {
        return srcApp;
    }

    public void setSrcApp(String srcApp) {
        this.srcApp = srcApp;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

}