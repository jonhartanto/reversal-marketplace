package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "CONDITION")
@Audited
public class Condition extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_id_seq")
    @SequenceGenerator(name = "condition_id_seq", sequenceName = "condition_id_seq")
    private Long id;

    @NotBlank(message = "CCY. CODE is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "CCY_CODE", length = 3, nullable = false)
    private String ccyCode;

    @NotNull(message = "CONDITION is mandatory")
    // @Size(max = 4, message = "CONDITION. max 1 digits")
    @Column(name = "CONDITION", unique = true)
    private String condition;

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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}