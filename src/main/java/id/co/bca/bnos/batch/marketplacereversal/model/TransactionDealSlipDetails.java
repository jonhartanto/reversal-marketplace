package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "TXN_DEAL_SLIP_DETAILS")
@Audited
public class TransactionDealSlipDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dealslip_dtl_id_seq")
    @SequenceGenerator(name = "dealslip_dtl_id_seq", sequenceName = "dealslip_dtl_id_seq")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trxdealslip")
    TransactionDealSlip trxDealslip;

    @Column(name = "bill_no", nullable = false)
    String billNo;

    @NotBlank(message = "CCY. CODE is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "ccy_code", length = 3, nullable = false)
    String ccyCode;

    @NotBlank(message = "CCY. Condition is mandatory")
    @Size(min = 1, max = 1, message = "CCY. Condition must be 1 digits")
    @Column(name = "ccy_condition", length = 1, nullable = false)
    String ccyCondition;

    @NotNull(message = "Denom. is mandatory")
    @Column(name = "denom", nullable = false)
    BigDecimal denom;

    @NotNull(message = "Quantity. is mandatory")
    @Column(name = "quantity", nullable = false)
    BigDecimal quantity;

    @Column(name = "total_denom", nullable = false)
    BigDecimal totalDenom;

    @Column(name = "use_margin", nullable = false)
    BigDecimal useMargin;

    @Column(name = "amount", nullable = false)
    BigDecimal amount;

//    @NotNull(message = "Rate. is mandatory")
//    @Column(name = "ccy_rate", nullable = false)
//    String ccyRate;
//
//    @Column(name = "ccy_margin", nullable = false)
//    BigDecimal ccyMargin;
//
//    @NotNull(message = "Transaction Amount is mandatory")
//    @Column(name = "transaction_amount", nullable = false)
//    BigDecimal transactionAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionDealSlip getTrxDealslip() {
        return trxDealslip;
    }

    public void setTrxDealslip(TransactionDealSlip trxDealslip) {
        this.trxDealslip = trxDealslip;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public String getCcyCondition() {
        return ccyCondition;
    }

    public void setCcyCondition(String ccyCondition) {
        this.ccyCondition = ccyCondition;
    }

    public BigDecimal getDenom() {
        return denom;
    }

    public void setDenom(BigDecimal denom) {
        this.denom = denom;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalDenom() {
        return totalDenom;
    }

    public void setTotalDenom(BigDecimal totalDenom) {
        this.totalDenom = totalDenom;
    }

    public BigDecimal getUseMargin() {
        return useMargin;
    }

    public void setUseMargin(BigDecimal useMargin) {
        this.useMargin = useMargin;
    }


}
