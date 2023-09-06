package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "DENOM")
@Audited
public class Denom extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "denom_id_seq")
    @SequenceGenerator(name = "denom_id_seq", sequenceName = "denom_id_seq")
    private Long id;

    @NotBlank(message = "CCY. CODE is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "CCY_CODE", length = 3, nullable = false)
    private String ccyCode;

    @NotNull(message = "DENOM is mandatory")
    @Column(name = "DENOM", nullable = false)
    private Integer denom;

    @Column(name = "DENOM_TYPE", length = 2)
    private String denomType;

    @Column(name = "DENOMINASI")
    private String denominasi;

    @ManyToOne()
    @JoinColumn(name = "CONDITION_ID", referencedColumnName = "ID")
    private Condition kondisiId;

    @Column(name = "KONDISI")
    private String kondisi;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "KETERANGAN")
    private String keterangan;

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

    public Integer getDenom() {
        return denom;
    }

    public void setDenom(Integer denom) {
        this.denom = denom;
    }

    public String getDenomType() {
        return denomType;
    }

    public void setDenomType(String denomType) {
        this.denomType = denomType;
    }

    public String getDenominasi() {
        return denominasi;
    }

    public void setDenominasi(String denominasi) {
        this.denominasi = denominasi;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Condition getKondisiId() {
        return kondisiId;
    }

    public void setKondisiId(Condition kondisiId) {
        this.kondisiId = kondisiId;
    }

}