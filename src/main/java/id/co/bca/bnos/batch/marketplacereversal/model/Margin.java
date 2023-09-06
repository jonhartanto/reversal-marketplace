package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "MARGIN")
@Audited
public class Margin extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "margin_id_seq")
    @SequenceGenerator(name = "margin_id_seq", sequenceName = "margin_id_seq")
    private Long id;

    @NotBlank(message = "CCY. CODE BANKNOTES is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "CCY_CODE", length = 3, nullable = false)
    private String ccyCode;

    @NotBlank(message = "NAME BANKNOTES is mandatory")
    @Size(max = 35, message = "NAME BANKNOTES max 35 digits")
    @Column(name = "CCY_NAME", length = 35)
    private String ccyName;

    @ManyToOne()
    @JoinColumn(name = "CONDITION_ID", referencedColumnName = "ID")
    private Condition conditionId;

    @NotNull(message = "CONDITION is mandatory")
    @Size(max = 4, message = "CONDITION. max 4 digits")
    @Column(name = "CONDITION", length = 4)
    private String condition;

    @NotNull(message = "MARGIN JUAL is mandatory")
    @Column(name = "MARGIN_SELL", precision = 12, scale = 2)
    private BigDecimal marginSell;

    @NotNull(message = "MARGIN BELI is mandatory")
    @Column(name = "MARGIN_BUY", precision = 12, scale = 2)
    private BigDecimal marginBuy;

    @NotNull(message = "MARGIN TYPE is mandatory")
    @Column(name = "MARGIN_TYPE", length = 2)
    private String marginType;

    @Column(name = "MARGIN_LEVEL", length = 5)
    private String marginLevel;

    @Column(name = "value_date")
    private LocalDate valueDate;

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public String getCcyName() {
        return ccyName;
    }

    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public BigDecimal getMarginSell() {
        return marginSell;
    }

    public void setMarginSell(BigDecimal marginSell) {
        this.marginSell = marginSell;
    }

    public BigDecimal getMarginBuy() {
        return marginBuy;
    }

    public void setMarginBuy(BigDecimal marginBuy) {
        this.marginBuy = marginBuy;
    }

    public String getMarginType() {
        return marginType;
    }

    public void setMarginType(String marginType) {
        this.marginType = marginType;
    }

    public String getMarginLevel() {
        return marginLevel;
    }

    public void setMarginLevel(String marginLevel) {
        this.marginLevel = marginLevel;
    }

    public Condition getConditionId() {
        return conditionId;
    }

    public void setConditionId(Condition conditionId) {
        this.conditionId = conditionId;
    }

}