package id.co.bca.bnos.batch.marketplacereversal.model;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TXN_PENYERAHAN")
@Audited
public class TxnPenyerahan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penyerahan_id_seq")
    @SequenceGenerator(name = "penyerahan_id_seq", sequenceName = "penyerahan_id_seq")
    private Long id;

    @Column(name = "no_penyerahan", nullable = false, unique = true)
    String noPenyerahan;

    @Column(name = "branch", length = 4, nullable = false)
    String branch;

    @NotBlank(message = "PIC/Petugas is mandatory")
    @Column(name = "pic", nullable = false)
    String pic;

//    @NotBlank(message = "Opponent CODE is mandatory")
//    @Size(min = 1, max = 20, message = "Opponent CODE maximum is 20 digits")
    @Column(name = "opponent_code", length = 20, nullable = false)
    String opponentCode;

    @NotBlank(message = "CCY. CODE is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "ccy_code", length = 3, nullable = false)
    String ccyCode;

    @NotBlank(message = "CCY. Condition is mandatory")
    @Size(min = 1, max = 1, message = "CCY. Condition must be 1 digits")
    @Column(name = "ccy_condition", length = 1, nullable = false)
    String ccyCondition;

    @Column(name = "description", length = 50)
    String description;

    @Column(name = "transaction_date", columnDefinition = "DATE")
    LocalDateTime trxDate;

    @NotNull(message = "Total Input Denom is mandatory")
    @Column(name = "total_input_denom", nullable = false)
    BigDecimal totalInputDenom;

//    @Column(name = "total_found_denom")
//    BigDecimal totalFoundDenom;

    @Column(name = "status", length = 30)
    String status;

    @Column(name = "origin", length = 30, nullable = false)
    String origin;

    @Column(name = "kp_release_type", length = 30)
    String kpReleaseType;

    @Column(name = "kp_receive_type", length = 10)
    String kpReceiveType;

    @Column(name = "kp_kasir", length = 10)
    String kpKasirDone;

    @Column(name = "kp_status_match")
    String kpStatusMatch;

    @Column(name = "dt_prepare", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dtPrepare;

    @Column(name = "pic_prepare")
    String picPrepare;

    @Column(name = "dt_check", columnDefinition = "TIMESTAMP")
    LocalDateTime dtCheck;

    @Column(name = "pic_check")
    String picCheck;

    @Column(name = "dt_penyerahan", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dtPenyerahan;

    @Column(name = "pic_penyerahan")
    String picPenyerahan;

    @Column(name = "dt_penerimaan", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dtPenerimaan;

    @Column(name = "pic_penerimaan")
    String picPenerimaan;

    @Column(name = "pic_penerimaan_prepare")
    String picPenerimaanPrepare;

    @Column(name = "pic_penerimaan_done")
    String picPenerimaanDone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "txnPenyerahan",
            cascade = CascadeType.PERSIST)
    List<TransactionDealSlip> trxDealSlip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "txnPenyerahan",
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<TxnPenyerahanDetails> txnPenyerahanDetailsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "txnPenyerahan",
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<TxnPenerimaanDetails> txnPenerimaanDetailsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOpponentCode() {
        return opponentCode;
    }

    public void setOpponentCode(String opponentCode) {
        this.opponentCode = opponentCode;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public LocalDateTime getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(LocalDateTime trxDate) {
        this.trxDate = trxDate;
    }

    public BigDecimal getTotalInputDenom() {
        return totalInputDenom;
    }

    public void setTotalInputDenom(BigDecimal totalInputDenom) {
        this.totalInputDenom = totalInputDenom;
    }

//    public BigDecimal getTotalFoundDenom() {
//        return totalFoundDenom;
//    }
//
//    public void setTotalFoundDenom(BigDecimal totalFoundDenom) {
//        this.totalFoundDenom = totalFoundDenom;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TxnPenyerahanDetails> getTxnPenyerahanDetailsList() {
        return txnPenyerahanDetailsList;
    }

    public void setTxnPenyerahanDetailsList(List<TxnPenyerahanDetails> txnPenyerahanDetailsList) {
        this.txnPenyerahanDetailsList = txnPenyerahanDetailsList;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setKpReleaseType(String kpReleaseType) {
        this.kpReleaseType = kpReleaseType;
    }

    public String getKpReleaseType() {
        return kpReleaseType;
    }

    public List<TxnPenerimaanDetails> getTxnPenerimaanDetailsList() {
        return txnPenerimaanDetailsList;
    }

    public void setTxnPenerimaanDetailsList(List<TxnPenerimaanDetails> txnPenerimaanDetailsList) {
        this.txnPenerimaanDetailsList = txnPenerimaanDetailsList;
    }

    public LocalDateTime getDtPenyerahan() {
        return dtPenyerahan;
    }

    public void setDtPenyerahan(LocalDateTime dtPenyerahan) {
        this.dtPenyerahan = dtPenyerahan;
    }

    public LocalDateTime getDtPenerimaan() {
        return dtPenerimaan;
    }

    public void setDtPenerimaan(LocalDateTime dtPenerimaan) {
        this.dtPenerimaan = dtPenerimaan;
    }

    public String getPicPenyerahan() {
        return picPenyerahan;
    }

    public void setPicPenyerahan(String picPenyerahan) {
        this.picPenyerahan = picPenyerahan;
    }

    public String getPicPenerimaan() {
        return picPenerimaan;
    }

    public void setPicPenerimaan(String picPenerimaan) {
        this.picPenerimaan = picPenerimaan;
    }

    public String getCcyCondition() {
        return ccyCondition;
    }

    public void setCcyCondition(String ccyCondition) {
        this.ccyCondition = ccyCondition;
    }

    public LocalDateTime getDtPrepare() {
        return dtPrepare;
    }

    public void setDtPrepare(LocalDateTime dtPrepare) {
        this.dtPrepare = dtPrepare;
    }

    public String getPicPrepare() {
        return picPrepare;
    }

    public void setPicPrepare(String picPrepare) {
        this.picPrepare = picPrepare;
    }

    public LocalDateTime getDtCheck() {
        return dtCheck;
    }

    public void setDtCheck(LocalDateTime dtCheck) {
        this.dtCheck = dtCheck;
    }

    public String getPicCheck() {
        return picCheck;
    }

    public void setPicCheck(String picCheck) {
        this.picCheck = picCheck;
    }

   public List<TransactionDealSlip> getTrxDealSlip() {
       return trxDealSlip;
   }

   public void setTrxDealSlip(List<TransactionDealSlip> trxDealSlip) {
       this.trxDealSlip = trxDealSlip;
   }

    public String getNoPenyerahan() {
        return noPenyerahan;
    }

    public void setNoPenyerahan(String noPenyerahan) {
        this.noPenyerahan = noPenyerahan;
    }

    public String getKpReceiveType() {
        return kpReceiveType;
    }

    public void setKpReceiveType(String kpReceiveType) {
        this.kpReceiveType = kpReceiveType;
    }

    public String getKpKasirDone() {
        return kpKasirDone;
    }

    public void setKpKasirDone(String kpKasirDone) {
        this.kpKasirDone = kpKasirDone;
    }

    public String getKpStatusMatch() {
        return kpStatusMatch;
    }

    public void setKpStatusMatch(String kpStatusMatch) {
        this.kpStatusMatch = kpStatusMatch;
    }

    public String getPicPenerimaanPrepare() {
        return picPenerimaanPrepare;
    }

    public void setPicPenerimaanPrepare(String picPenerimaanPrepare) {
        this.picPenerimaanPrepare = picPenerimaanPrepare;
    }

    public String getPicPenerimaanDone() {
        return picPenerimaanDone;
    }

    public void setPicPenerimaanDone(String picPenerimaanDone) {
        this.picPenerimaanDone = picPenerimaanDone;
    }
}
