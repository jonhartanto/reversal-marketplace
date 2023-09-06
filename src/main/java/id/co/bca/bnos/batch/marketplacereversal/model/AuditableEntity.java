package id.co.bca.bnos.batch.marketplacereversal.model;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
public abstract class AuditableEntity {

    // Feild field untuk audit
    @Column(name = "CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    // @JsonSerialize(using = DateSerializer.class)
    // @JsonDeserialize(using = DateDeserializer.class)
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(name = "CREATE_BY", length = 15)
    @CreatedBy
    private String createdBy;

    // @LastModifiedDate
    @Column(name = "MODIF_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected LocalDateTime modifiedDate;

    @Column(name = "MODIF_BY", length = 15)
    // @LastModifiedBy
    private String modifiedBy;

    @Column(name = "APPRV_BY", length = 15)
    private String approvedBy;

    @Column(name = "APPRV_DATE")
    protected LocalDateTime approvedDate;

    @Column(name = "ACTION", length = 1)
    private String action;

    @Column(name = "APPROVED")
    private Boolean approved;

    @Column(name = "ACTIVE")
    private Boolean active;

    // Field - field untuk operasional
    @Column(name = "FLAG")
    private Boolean releaseFlag;

    public Boolean getReleaseFlag() {
        return releaseFlag;
    }

    public void setReleaseFlag(Boolean releaseFlag) {
        this.releaseFlag = releaseFlag;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDateTime approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}