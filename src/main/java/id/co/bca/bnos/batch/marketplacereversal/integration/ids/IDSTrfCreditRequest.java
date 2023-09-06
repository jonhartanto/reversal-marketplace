package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSTrfCreditRequest {
    private String irType;
    private String irNumber;
    private String acctNo;
    private String currency;
    // devisa branch code
    private String devisaBranch;
    // value date (TAG 32A)
    private LocalDate valueDate;
    private LocalDateTime appDate;
    private BigDecimal amount;
    // reference number (TAG 50-1)
    private String ordCust1;
    // reference number (TAG 50-2)
    private String ordCust2;
    // remittance info (TAG 70-1)
    private String remitInfo;
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

    public String getDevisaBranch() {
        return devisaBranch;
    }

    public void setDevisaBranch(String devisaBranch) {
        this.devisaBranch = devisaBranch;
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

    public String getOrdCust1() {
        return ordCust1;
    }

    public void setOrdCust1(String ordCust1) {
        this.ordCust1 = ordCust1;
    }

    public String getOrdCust2() {
        return ordCust2;
    }

    public void setOrdCust2(String ordCust2) {
        this.ordCust2 = ordCust2;
    }

    public String getRemitInfo() {
        return remitInfo;
    }

    public void setRemitInfo(String remitInfo) {
        this.remitInfo = remitInfo;
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