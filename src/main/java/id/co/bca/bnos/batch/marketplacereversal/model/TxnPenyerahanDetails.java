package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "TXN_PENYERAHAN_DETAILS")
@Audited
public class TxnPenyerahanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penyerahan_details_id_seq")
    @SequenceGenerator(name = "penyerahan_details_id_seq", sequenceName = "penyerahan_details_id_seq")
    private Long id;

//    @Column(name = "bill_no", nullable = false)
//    String billNo;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "bill_no")
//    TransactionDealSlip trxDealSlip;

//    @Column(name = "total_denom_org", nullable = false)
//    BigDecimal totalDenomOrg;
//
//    @Column(name = "total_denom_use", nullable = false)
//    BigDecimal totalDenomUse;

    @Column(name = "input_sku")
    String inputSku;

    @Column(name = "input_mtu")
    String inputMtu;

    @Column(name = "input_denom")
    int inputDenom;

    @Column(name = "input_denom_lembar")
    Integer inputDenomLembar;

    @Column(name = "input_condition")
    String inputCondition;

    @Column(name = "input_product_name")
    String inputProductName;

    @Column(name = "input_total")
    Long inputTotal;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_txn_penyerahan")
    TxnPenyerahan txnPenyerahan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public TransactionDealSlip getTrxDealSlip() {
//        return trxDealSlip;
//    }
//
//    public void setTrxDealSlip(TransactionDealSlip trxDealSlip) {
//        this.trxDealSlip = trxDealSlip;
//    }

//    public BigDecimal getTotalDenomOrg() {
//        return totalDenomOrg;
//    }
//
//    public void setTotalDenomOrg(BigDecimal totalDenomOrg) {
//        this.totalDenomOrg = totalDenomOrg;
//    }
//
//    public BigDecimal getTotalDenomUse() {
//        return totalDenomUse;
//    }
//
//    public void setTotalDenomUse(BigDecimal totalDenomUse) {
//        this.totalDenomUse = totalDenomUse;
//    }

    public TxnPenyerahan getTxnPenyerahan() {
        return txnPenyerahan;
    }

    public void setTxnPenyerahan(TxnPenyerahan txnPenyerahan) {
        this.txnPenyerahan = txnPenyerahan;
    }

    public String getInputSku() {
        return inputSku;
    }

    public void setInputSku(String inputSku) {
        this.inputSku = inputSku;
    }

    public String getInputMtu() {
        return inputMtu;
    }

    public void setInputMtu(String inputMtu) {
        this.inputMtu = inputMtu;
    }

    public int getInputDenom() {
        return inputDenom;
    }

    public void setInputDenom(int inputDenom) {
        this.inputDenom = inputDenom;
    }

    public Integer getInputDenomLembar() {
        return inputDenomLembar;
    }

    public void setInputDenomLembar(Integer inputDenomLembar) {
        this.inputDenomLembar = inputDenomLembar;
    }

    public String getInputCondition() {
        return inputCondition;
    }

    public void setInputCondition(String inputCondition) {
        this.inputCondition = inputCondition;
    }

    public String getInputProductName() {
        return inputProductName;
    }

    public void setInputProductName(String inputProductName) {
        this.inputProductName = inputProductName;
    }

    public Long getInputTotal() {
        return inputTotal;
    }

    public void setInputTotal(Long inputTotal) {
        this.inputTotal = inputTotal;
    }
}
