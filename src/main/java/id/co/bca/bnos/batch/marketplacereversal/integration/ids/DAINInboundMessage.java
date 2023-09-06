package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class DAINInboundMessage {
    private String txnId;
    private String termId;
    private String txnCode;
    private String logType;
    private String acctNo;
    private String benefBrch;
    private String currCode;
    private String kursChar;
    private String amtChar;
    private String busnDate;
    private String refNo;
    private List<String> trailers;
   
    private String userId;
    private Map<String, Long> info;
    // private String acctName;
    // private String srcApp;

    // @JsonProperty("txn_id")
    @JsonProperty("transaction_id")
    public String getTxnId() {
        return txnId;
    }

    // @JsonProperty("txn_id")
    @JsonProperty("transaction_id")
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    // @JsonProperty("term_id")
    @JsonProperty("terminal_id")
    public String getTermId() {
        return termId;
    }

    // @JsonProperty("term_id")
    @JsonProperty("terminal_id")
    public void setTermId(String termId) {
        this.termId = termId;
    }

    // @JsonProperty("txn_code")
    @JsonProperty("transaction_code")
    public String getTxnCode() {
        return txnCode;
    }

    // @JsonProperty("txn_code")
    @JsonProperty("transaction_code")
    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    // @JsonProperty("log_type")
    @JsonProperty("log_type")
    public String getLogType() {
        return logType;
    }

    // @JsonProperty("log_type")
    @JsonProperty("log_type")
    public void setLogType(String logType) {
        this.logType = logType;
    }

    // @JsonProperty("acct_no")
    @JsonProperty("account_number")
    public String getAcctNo() {
        return acctNo;
    }

    // @JsonProperty("acct_no")
    @JsonProperty("account_number")
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    // @JsonProperty("benef_brch")
    @JsonProperty("beneficiary_branch_code")
    public String getBenefBrch() {
        return benefBrch;
    }

    // @JsonProperty("benef_brch")
    @JsonProperty("beneficiary_branch_code")
    public void setBenefBrch(String benefBrch) {
        this.benefBrch = benefBrch;
    }

    // @JsonProperty("curr_code")
    @JsonProperty("currency_code")
    public String getCurrCode() {
        return currCode;
    }

    // @JsonProperty("curr_code")
    @JsonProperty("currency_code")
    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    // @JsonProperty("kurs_char")
    @JsonProperty("kurs")
    public String getKursChar() {
        return kursChar;
    }

    // @JsonProperty("kurs_char")
    @JsonProperty("kurs")
    public void setKursChar(String kursChar) {
        this.kursChar = kursChar;
    }

    // @JsonProperty("amt_char")
    @JsonProperty("amount")
    public String getAmtChar() {
        return amtChar;
    }

    // @JsonProperty("amt_char")
    @JsonProperty("amount")
    public void setAmtChar(String amtChar) {
        this.amtChar = amtChar;
    }

    @JsonProperty("business_date")
    public String getBusnDate() {
        return busnDate;
    }

    @JsonProperty("business_date")
    public void setBusnDate(String busnDate) {
        this.busnDate = busnDate;
    }

    @JsonProperty("reference_number")
    public String getRefNo() {
        return refNo;
    }

    @JsonProperty("reference_number")
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    // @JsonProperty("trailer1")
    @JsonProperty("trailers")
    public List<String> getTrailers() {
        return trailers;
    }

    @JsonProperty("trailers")
    public void setTrailers(List<String> trailers) {
        this.trailers = trailers;
    }


    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("info")
    public Map<String, Long> getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(Map<String, Long> info) {
        this.info = info;
    }
}