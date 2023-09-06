package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSTransferRequest {
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
    private String trailers;
    private String trailer1;
    private String trailer2;
    private String trailer3;
    private String trailer4;
    private String trailer5;
    private String trailer6;
    private String userId;
    private String acctName;
    private String srcApp;

    // @JsonProperty("txn_id")
    @JsonProperty("sme_chg_txn_id")
    public String getTxnId() {
        return txnId;
    }

    // @JsonProperty("txn_id")
    @JsonProperty("sme_chg_txn_id")
    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    // @JsonProperty("term_id")
    @JsonProperty("sme_chg_term_id")
    public String getTermId() {
        return termId;
    }

    // @JsonProperty("term_id")
    @JsonProperty("sme_chg_term_id")
    public void setTermId(String termId) {
        this.termId = termId;
    }

    // @JsonProperty("txn_code")
    @JsonProperty("sme_chg_txn_code")
    public String getTxnCode() {
        return txnCode;
    }

    // @JsonProperty("txn_code")
    @JsonProperty("sme_chg_txn_code")
    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    // @JsonProperty("log_type")
    @JsonProperty("sme_chg_log_type")
    public String getLogType() {
        return logType;
    }

    // @JsonProperty("log_type")
    @JsonProperty("sme_chg_log_type")
    public void setLogType(String logType) {
        this.logType = logType;
    }

    // @JsonProperty("acct_no")
    @JsonProperty("sme_chg_acct_no")
    public String getAcctNo() {
        return acctNo;
    }

    // @JsonProperty("acct_no")
    @JsonProperty("sme_chg_acct_no")
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    // @JsonProperty("benef_brch")
    @JsonProperty("sme_chg_benef_brch")
    public String getBenefBrch() {
        return benefBrch;
    }

    // @JsonProperty("benef_brch")
    @JsonProperty("sme_chg_benef_brch")
    public void setBenefBrch(String benefBrch) {
        this.benefBrch = benefBrch;
    }

    // @JsonProperty("curr_code")
    @JsonProperty("sme_chg_curr_code")
    public String getCurrCode() {
        return currCode;
    }

    // @JsonProperty("curr_code")
    @JsonProperty("sme_chg_curr_code")
    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    // @JsonProperty("kurs_char")
    @JsonProperty("sme_chg_kurs_char")
    public String getKursChar() {
        return kursChar;
    }

    // @JsonProperty("kurs_char")
    @JsonProperty("sme_chg_kurs_char")
    public void setKursChar(String kursChar) {
        this.kursChar = kursChar;
    }

    // @JsonProperty("amt_char")
    @JsonProperty("sme_chg_amt_char")
    public String getAmtChar() {
        return amtChar;
    }

    // @JsonProperty("amt_char")
    @JsonProperty("sme_chg_amt_char")
    public void setAmtChar(String amtChar) {
        this.amtChar = amtChar;
    }

    @JsonProperty("sme_busn_date")
    public String getBusnDate() {
        return busnDate;
    }

    @JsonProperty("sme_busn_date")
    public void setBusnDate(String busnDate) {
        this.busnDate = busnDate;
    }

    // @JsonProperty("ref_no")
    @JsonProperty("sme_chg_ref_no")
    public String getRefNo() {
        return refNo;
    }

    // @JsonProperty("ref_no")
    @JsonProperty("sme_chg_ref_no")
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    // @JsonProperty("trailer1")
    @JsonProperty("sme_chg_trailers")
    public String getTrailers() {
        return trailers;
    }

    @JsonProperty("sme_chg_trailers")
    public void setTrailers(String trailers) {
        this.trailers = trailers;
    }

    // @JsonProperty("trailer1")
    @JsonProperty("trailer1")
    public String getTrailer1() {
        return trailer1;
    }

    // @JsonProperty("trailer1")
    @JsonProperty("trailer1")
    public void setTrailer1(String trailer1) {
        this.trailer1 = trailer1;
    }

    // @JsonProperty("trailer2")
    @JsonProperty("trailer2")
    public String getTrailer2() {
        return trailer2;
    }

    // @JsonProperty("trailer2")
    @JsonProperty("trailer2")
    public void setTrailer2(String trailer2) {
        this.trailer2 = trailer2;
    }

    // @JsonProperty("trailer3")
    @JsonProperty("trailer3")
    public String getTrailer3() {
        return trailer3;
    }

    // @JsonProperty("trailer3")
    @JsonProperty("trailer3")
    public void setTrailer3(String trailer3) {
        this.trailer3 = trailer3;
    }

    // @JsonProperty("trailer4")
    @JsonProperty("trailer4")
    public String getTrailer4() {
        return trailer4;
    }

    // @JsonProperty("trailer4")
    @JsonProperty("trailer4")
    public void setTrailer4(String trailer4) {
        this.trailer4 = trailer4;
    }

    // @JsonProperty("trailer5")
    @JsonProperty("trailer5")
    public String getTrailer5() {
        return trailer5;
    }

    // @JsonProperty("trailer5")
    @JsonProperty("trailer5")
    public void setTrailer5(String trailer5) {
        this.trailer5 = trailer5;
    }

    // @JsonProperty("trailer6")
    @JsonProperty("trailer6")
    public String getTrailer6() {
        return trailer6;
    }

    // @JsonProperty("trailer6")
    @JsonProperty("trailer6")
    public void setTrailer6(String trailer6) {
        this.trailer6 = trailer6;
    }

    // @JsonProperty("user_id")
    @JsonProperty("sme_user_id")
    public String getUserId() {
        return userId;
    }

    // @JsonProperty("user_id")
    @JsonProperty("sme_user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public String getAcctName() {
        return acctName;
    }

    @JsonIgnore
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    @JsonIgnore
    public String getSrcApp() {
        return srcApp;
    }

    @JsonIgnore
    public void setSrcApp(String srcApp) {
        this.srcApp = srcApp;
    }

}