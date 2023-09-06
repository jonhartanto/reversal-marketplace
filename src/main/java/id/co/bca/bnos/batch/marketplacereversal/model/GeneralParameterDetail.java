package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "GENERAL_PARAMETER_DETAIL")
@Audited

public class GeneralParameterDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gnrl_dtl_id_seq")
    @SequenceGenerator(name = "gnrl_dtl_id_seq", sequenceName = "gnrl_dtl_id_seq")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "GNRLP_HDR")
    private GeneralParameter header;

    @Column(name = "SEQNO")
    Long seqNumber;

    @NotBlank(message = "KEY VALUE is mandatory")
    @Size(max = 35, message = "KEY VALUE max 35 digit")
    @Column(name = "KEY_VALUE", length = 35)
    String value;

    @NotBlank(message = "KEY NAME is mandatory")
    @Size(max = 35, message = "KEY NAME max 35 digit")
    @Column(name = "KEY_NAME", length = 35)
    String keyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneralParameter getHeader() {
        return header;
    }

    public void setHeader(GeneralParameter header) {
        this.header = header;
    }

    public Long getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(Long seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}