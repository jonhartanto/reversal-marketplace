package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "CURRENCY")
@Audited
public class Currency extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_seq")
    @SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq")
    private Long id;

    @NotBlank(message = "CCY. CODE is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "CODE", length = 3, nullable = false, unique = true)
    private String code;

    @NotBlank(message = "DESCR. is mandatory")
    @Size(max = 35, message = "DESCR. max 35 digits")
    @Column(name = "DESCR", length = 35)
    private String description;

    @Column(name = "KODE_BC06", length = 2)
    private String kodeBC06;

    // @Column(name = "JENIS_KONDISI", length = 4)
    // private String jenisKondisi;

    @Column(name = "MID_RATE_TR06", precision = 12, scale = 2)
    private BigDecimal midRateTr06;

    @Column(name = "RATE_BC06_SELL", precision = 12, scale = 2)
    private BigDecimal rateBC06Sell;

    @Column(name = "RATE_BC06_BUY", precision = 12, scale = 2)
    private BigDecimal rateBC06Buy;

    @Column(name = "MID_RATE_BC06", precision = 12, scale = 2)
    private BigDecimal midrateBC06;

    @Column(name = "RATE_DEVISA_SELL", precision = 12, scale = 2)
    private BigDecimal rateDevisaSell;

    @Column(name = "RATE_DEVISA_BUY", precision = 12, scale = 2)
    private BigDecimal rateDevisaBuy;

    @Column(name = "RATE_GL_OPEN", precision = 12, scale = 2)
    private BigDecimal rateGlOpen;

    @Column(name = "RATE_GL_CLOSE", precision = 12, scale = 2)
    private BigDecimal rateGlClose;

    @Column(name = "JENIS_CCY", length = 10, nullable = true)
    private String jenisCcy;

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

    public BigDecimal getMidrateBC06() {
        return this.midrateBC06;
    }

    public void setMidrateBC06(BigDecimal midrateBC06) {
        this.midrateBC06 = midrateBC06;
    }

    public Currency id(Long id) {
        this.id = id;
        return this;
    }

    public Currency code(String code) {
        this.code = code;
        return this;
    }

    public Currency description(String description) {
        this.description = description;
        return this;
    }

    public Currency kodeBC06(String kodeBC06) {
        this.kodeBC06 = kodeBC06;
        return this;
    }

    public Currency rateBC06Sell(BigDecimal rateBC06Sell) {
        this.rateBC06Sell = rateBC06Sell;
        return this;
    }

    public Currency rateBC06Buy(BigDecimal rateBC06Buy) {
        this.rateBC06Buy = rateBC06Buy;
        return this;
    }

    public Currency midrateBC06(BigDecimal midrateBC06) {
        this.midrateBC06 = midrateBC06;
        return this;
    }

    public Currency rateDevisaSell(BigDecimal rateDevisaSell) {
        this.rateDevisaSell = rateDevisaSell;
        return this;
    }

    public Currency rateDevisaBuy(BigDecimal rateDevisaBuy) {
        this.rateDevisaBuy = rateDevisaBuy;
        return this;
    }

    public Currency rateGlOpen(BigDecimal rateGlOpen) {
        this.rateGlOpen = rateGlOpen;
        return this;
    }

    public Currency rateGlClose(BigDecimal rateGlClose) {
        this.rateGlClose = rateGlClose;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Currency)) {
            return false;
        }
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(code, currency.code)
                && Objects.equals(description, currency.description) && Objects.equals(kodeBC06, currency.kodeBC06)
                && Objects.equals(rateBC06Sell, currency.rateBC06Sell)
                && Objects.equals(rateBC06Buy, currency.rateBC06Buy)
                && Objects.equals(midrateBC06, currency.midrateBC06)
                && Objects.equals(rateDevisaSell, currency.rateDevisaSell)
                && Objects.equals(rateDevisaBuy, currency.rateDevisaBuy)
                && Objects.equals(rateGlOpen, currency.rateGlOpen) && Objects.equals(rateGlClose, currency.rateGlClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, kodeBC06, rateBC06Sell, rateBC06Buy, midrateBC06, rateDevisaSell,
                rateDevisaBuy, rateGlOpen, rateGlClose);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", code='" + getCode() + "'" + ", description='" + getDescription() + "'"
                + ", kodeBC06='" + getKodeBC06() + "'" + ", rateBC06Sell='" + getRateBC06Sell() + "'"
                + ", rateBC06Buy='" + getRateBC06Buy() + "'" + ", midrateBC06='" + getMidrateBC06() + "'"
                + ", rateDevisaSell='" + getRateDevisaSell() + "'" + ", rateDevisaBuy='" + getRateDevisaBuy() + "'"
                + ", rateGlOpen='" + getRateGlOpen() + "'" + ", rateGlClose='" + getRateGlClose() + "'" + "}";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRateBC06Sell() {
        return rateBC06Sell;
    }

    public void setRateBC06Sell(BigDecimal rateBC06Sell) {
        this.rateBC06Sell = rateBC06Sell;
    }

    public BigDecimal getRateBC06Buy() {
        return rateBC06Buy;
    }

    public void setRateBC06Buy(BigDecimal rateBC06Buy) {
        this.rateBC06Buy = rateBC06Buy;
    }

    public BigDecimal getRateDevisaSell() {
        return rateDevisaSell;
    }

    public void setRateDevisaSell(BigDecimal rateDevisaSell) {
        this.rateDevisaSell = rateDevisaSell;
    }

    public BigDecimal getRateDevisaBuy() {
        return rateDevisaBuy;
    }

    public void setRateDevisaBuy(BigDecimal rateDevisaBuy) {
        this.rateDevisaBuy = rateDevisaBuy;
    }

    public BigDecimal getRateGlOpen() {
        return rateGlOpen;
    }

    public void setRateGlOpen(BigDecimal rateGlOpen) {
        this.rateGlOpen = rateGlOpen;
    }

    public BigDecimal getRateGlClose() {
        return rateGlClose;
    }

    public void setRateGlClose(BigDecimal rateGlClose) {
        this.rateGlClose = rateGlClose;
    }

    public BigDecimal getMidRateBC06() {
        return midrateBC06;
    }

    public void setMidRateBC06(BigDecimal midrateBC06) {
        this.midrateBC06 = midrateBC06;
    }

    public String getKodeBC06() {
        return kodeBC06;
    }

    public void setKodeBC06(String kodeBC06) {
        this.kodeBC06 = kodeBC06;
    }

    // public String getJenisKondisi() {
    // return jenisKondisi;
    // }

    // public void setJenisKondisi(String jenisKondisi) {
    // this.jenisKondisi = jenisKondisi;
    // }

    public String getJenisCcy() {
        return jenisCcy;
    }

    public void setJenisCcy(String jenisCcy) {
        this.jenisCcy = jenisCcy;
    }

    public BigDecimal getMidrateTr06() {
        return midRateTr06;
    }

    public void setMidrateTr06(BigDecimal midRateTr06) {
        this.midRateTr06 = midRateTr06;
    }
}
