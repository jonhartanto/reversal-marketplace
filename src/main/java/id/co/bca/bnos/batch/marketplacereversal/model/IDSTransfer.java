package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "IDSTRSF")
public class IDSTransfer extends AuditableEntity {

    public static final String TXN_TYPE_DEBIT = "828";
    public static final String TXN_TYPE_CREDIT = "827";

    public static final String APP_SOURCE_TXN = "TXN";
    //public static final String APP_SOURCE_AMENDMENT = "AMD";
    //public static final String APP_SOURCE_CANCEL = "CNC";
    //public static final String APP_SOURCE_REJECT = "REJ";
    //public static final String APP_SOURCE_IFA = "IFA";
    //public static final String APP_SOURCE_IFAEOM = "IFAEOM";
    //public static final String APP_SOURCE_REBATE = "REBATE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idstrsf_id_seq")
    @SequenceGenerator(name = "idstrsf_id_seq", sequenceName = "idstrsf_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "TXN_ID", length = 4)
    private String txnId;

    @Column(name = "TERM_ID", length = 5)
    private String termId;

    @Column(name = "TXN_CODE", length = 3)
    private String txnCode;

    @Column(name = "LOG_TYPE", length = 1)
    private String logType;

    @Column(name = "SRC_APP", length = 10)
    private String srcApp;

    @Column(name = "ACCT_NO", length = 10)
    private String acctNo;

    @Column(name = "ACCT_NAME", length = 35)
    private String acctName;

    @Column(name = "BENEF_BRCH", length = 4)
    private String benefBrch;

    @Column(name = "CURR_CODE", length = 3)
    private String currCode;

    @Column(name = "KURS_CHAR", length = 9)
    private String kursChar;

    @Column(name = "KURS_NUM", precision = 7, scale = 2)
    private BigDecimal kursNum;

    @Column(name = "AMT_CHAR", length = 17)
    private String amtChar;

    @Column(name = "AMT_NUM", precision = 15, scale = 2)
    private BigDecimal amtNum;

    @Column(name = "BUSN_CHAR", length = 8)
    private String busnChar;

    @Column(name = "BUSN_DATE")
    private LocalDate busnDate;

    @Column(name = "REF_NO", length = 11)
    private String refNo;

    @Column(name = "TRAILER1", length = 18)
    private String trailer1;

    @Column(name = "TRAILER2", length = 18)
    private String trailer2;

    @Column(name = "TRAILER3", length = 18)
    private String trailer3;

    @Column(name = "TRAILER4", length = 18)
    private String trailer4;

    @Column(name = "TRAILER5", length = 18)
    private String trailer5;

    @Column(name = "TRAILER6", length = 18)
    private String trailer6;

    @Column(name = "USER_ID", length = 10)
    private String userId;

    @Column(name = "RES_ARR_COUNT", precision = 2, scale = 0)
    BigDecimal resArrCount;

    @Column(name = "RES_REFERENCE", precision = 11, scale = 0)
    BigDecimal resRef;
    
    @Column(name = "RES_LOG_TYPE", length = 1)
    String resLogType;
    
    @Column(name = "RES_ADD_INFO", length = 32)
    String resAddInf;
    
    @Column(name = "RES_SHARP1", length = 01)
    String resSharp1;
    
    @Column(name = "RES_SHARP2", length = 01)
    String resSharp2;
    
    @Column(name = "RES_DELIMETER", length = 01)
    String resDelimeter;

    @Column(name = "ERROR_CODE", length = 10)
    private String errorCode;

    @Column(name = "ERROR_MSG_ID", length = 35)
    private String errorMsgId;
    
    @Column(name = "ERROR_MSG_EN", length = 35)
    private String errorMsgEn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getBenefBrch() {
        return benefBrch;
    }

    public void setBenefBrch(String benefBrch) {
        this.benefBrch = benefBrch;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String getKursChar() {
        return kursChar;
    }

    public void setKursChar(String kursChar) {
        this.kursChar = kursChar;
    }

    public BigDecimal getKursNum() {
        return kursNum;
    }

    public void setKursNum(BigDecimal kursNum) {
        this.kursNum = kursNum;
    }

    public String getAmtChar() {
        return amtChar;
    }

    public void setAmtChar(String amtChar) {
        this.amtChar = amtChar;
    }

    public BigDecimal getAmtNum() {
        return amtNum;
    }

    public void setAmtNum(BigDecimal amtNum) {
        this.amtNum = amtNum;
    }

    public String getBusnChar() {
        return busnChar;
    }

    public void setBusnChar(String busnChar) {
        this.busnChar = busnChar;
    }

    public LocalDate getBusnDate() {
        return busnDate;
    }

    public void setBusnDate(LocalDate busnDate) {
        this.busnDate = busnDate;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getTrailer1() {
        return trailer1;
    }

    public void setTrailer1(String trailer1) {
        this.trailer1 = trailer1;
    }

    public String getTrailer2() {
        return trailer2;
    }

    public void setTrailer2(String trailer2) {
        this.trailer2 = trailer2;
    }

    public String getTrailer3() {
        return trailer3;
    }

    public void setTrailer3(String trailer3) {
        this.trailer3 = trailer3;
    }

    public String getTrailer4() {
        return trailer4;
    }

    public void setTrailer4(String trailer4) {
        this.trailer4 = trailer4;
    }

    public String getTrailer5() {
        return trailer5;
    }

    public void setTrailer5(String trailer5) {
        this.trailer5 = trailer5;
    }

    public String getTrailer6() {
        return trailer6;
    }

    public void setTrailer6(String trailer6) {
        this.trailer6 = trailer6;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getResArrCount() {
        return resArrCount;
    }

    public void setResArrCount(BigDecimal resArrCount) {
        this.resArrCount = resArrCount;
    }

    public BigDecimal getResRef() {
        return resRef;
    }

    public void setResRef(BigDecimal resRef) {
        this.resRef = resRef;
    }

    public String getResLogType() {
        return resLogType;
    }

    public void setResLogType(String resLogType) {
        this.resLogType = resLogType;
    }

    public String getResAddInf() {
        return resAddInf;
    }

    public void setResAddInf(String resAddInf) {
        this.resAddInf = resAddInf;
    }

    public String getResSharp1() {
        return resSharp1;
    }

    public void setResSharp1(String resSharp1) {
        this.resSharp1 = resSharp1;
    }

    public String getResSharp2() {
        return resSharp2;
    }

    public void setResSharp2(String resSharp2) {
        this.resSharp2 = resSharp2;
    }

    public String getResDelimeter() {
        return resDelimeter;
    }

    public void setResDelimeter(String resDelimeter) {
        this.resDelimeter = resDelimeter;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsgId() {
        return errorMsgId;
    }

    public void setErrorMsgId(String errorMsgId) {
        this.errorMsgId = errorMsgId;
    }

    public String getErrorMsgEn() {
        return errorMsgEn;
    }

    public void setErrorMsgEn(String errorMsgEn) {
        this.errorMsgEn = errorMsgEn;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getSrcApp() {
        return srcApp;
    }

    public void setSrcApp(String srcApp) {
        this.srcApp = srcApp;
    }


}