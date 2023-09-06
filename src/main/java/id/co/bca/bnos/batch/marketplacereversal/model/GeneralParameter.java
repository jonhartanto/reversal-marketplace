package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENERAL_PARAMETER", uniqueConstraints = @UniqueConstraint(columnNames = { "APPL", "ITEM" }))
@Relation(value = "result")
@Audited

public class GeneralParameter extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gnrl_id_seq")
    @SequenceGenerator(name = "gnrl_id_seq", sequenceName = "gnrl_id_seq")
    private Long id;

    @NotBlank(message = "TABLE ID is mandatory")
    @Size(max = 15, message = "TABLE ID max 15 digit")
    @Column(name = "APPL", length = 15, nullable = false)
    String table;

    @NotBlank(message = "ITEM is mandatory")
    @Size(max = 15, message = "ITEM max 15 digit")
    @Column(name = "ITEM", length = 15)
    private String item;

    @Column(name = "NOTES", length = 255)
    private String notes;

    @Column(name = "TICK_MARK")
    private Boolean tmark;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("seqNumber")
    private Set<@Valid GeneralParameterDetail> details;

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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Set<GeneralParameterDetail> getDetails() {
        if (this.details == null)
            this.details = new HashSet<GeneralParameterDetail>();

        return details;
    }

    public void setDetails(Set<GeneralParameterDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "generalParam = " + this.table + ", item = " + this.item;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public GeneralParameterDetail findDetail(String keyName) {
        for (GeneralParameterDetail dtl : details) {
            if (dtl.getKeyName().equals(keyName)) {
                return dtl;
            }
        }
        return null;
    }

    public String getDetailValue(String keyName) {
        for (GeneralParameterDetail dtl : details) {
            if (dtl.getKeyName().equals(keyName)) {
                return dtl.getValue();
            }
        }

        throw new RuntimeException("Keyname " + keyName + " is not found in generalParameter row.");
    }

    public void setDetail(String keyName, String keyValue) {
        for (GeneralParameterDetail dtl : details) {
            if (dtl.getKeyName().equals(keyName)) {
                dtl.setValue(keyValue);
                break;
            }
        }
    }

    public Boolean isTmark() {
        return tmark;
    }

    public void setTmark(Boolean tmark) {
        this.tmark = tmark;
    }

}