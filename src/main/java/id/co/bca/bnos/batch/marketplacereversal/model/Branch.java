package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "BRANCH")
@Audited
public class Branch extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_seq")
    @SequenceGenerator(name = "branch_id_seq", sequenceName = "branch_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Branch CODE is mandatory")
    @Size(min = 4, max = 4, message = "Branch CODE must be 4 digits")
    @Column(name = "code", length = 4, nullable = false, unique = true)
    String code;

    @NotBlank(message = "Branch. NAME is mandatory")
    @Column(name = "name", length = 32, nullable = false, unique = true)
    String name;

    @NotBlank(message = "Branch Initial is mandatory")
    @Size(min = 3, max = 3, message = "Branch Initial must be 3 digits")
    @Column(name = "init", length = 3, nullable = false, unique = true)
    String initial;

    @NotBlank(message = "Status is mandatory")
    @Size(min = 1, max = 1, message = "Status must be 1 digits")
    @Column(name = "ho_status", length = 1, nullable = false)
    String hoStatus;

    @NotBlank(message = "KCU CODE is mandatory")
    @Size(min = 4, max = 4, message = "KCU CODE must be 4 digits")
    @Column(name = "main_branch", length = 4, nullable = false)
    String mainBranch;

    @Column(name = "soa_code", length = 15)
    String soaCode;

    @Column(name = "region_code", length = 5, nullable = true)
    String regionCode;

    @Column(name = "last_bill_no", nullable = false)
    Long lastBillNumber;

    @Version
    @Column(name = "version")
    Long version;

    @Column(name = "signon_time", nullable = true)
    private LocalTime  signOnTime;

    @Column(name = "signoff_time", nullable = true)
    private LocalTime  signOffTime;

    @Column(name = "flag_status", nullable = true)
    private Boolean flagStatus;

    @Column(name = "signed_on_by", nullable = true)
    private String signedOnBy;

    @Column(name = "signed_off_by", nullable = true)
    private String signedOffBy;

    @Column(name = "value_date")
    private LocalDate valueDate;
    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch",
                cascade = CascadeType.ALL, orphanRemoval = true)
    List<BranchDetails> branchDetails;

    
    
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getHoStatus() {
        return hoStatus;
    }

    public void setHoStatus(String hoStatus) {
        this.hoStatus = hoStatus;
    }

    public String getMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(String mainBranch) {
        this.mainBranch = mainBranch;
    }

    public String getSoaCode() {
        return soaCode;
    }

    public void setSoaCode(String soaCode) {
        this.soaCode = soaCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Long getLastBillNumber() {
        return lastBillNumber;
    }

    public void setLastBillNumber(Long lastBillNumber) {
        this.lastBillNumber = lastBillNumber;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalTime getSignOnTime() {
        return signOnTime;
    }

    public void setSignOnTime(LocalTime signOnTime) {
        this.signOnTime = signOnTime;
    }

    public LocalTime getSignOffTime() {
        return signOffTime;
    }

    public void setSignOffTime(LocalTime signOffTime) {
        this.signOffTime = signOffTime;
    }

    public Boolean getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(Boolean flagStatus) {
        this.flagStatus = flagStatus;
    }

    public String getSignedOnBy() {
        return signedOnBy;
    }

    public void setSignedOnBy(String signedOnBy) {
        this.signedOnBy = signedOnBy;
    }

    public String getSignedOffBy() {
        return signedOffBy;
    }

    public void setSignedOffBy(String signedOffBy) {
        this.signedOffBy = signedOffBy;
    }

    public List<BranchDetails> getBranchDetails() {
        return branchDetails;
    }

    public void setBranchDetails(List<BranchDetails> branchDetails) {
        this.branchDetails = branchDetails;
    }

    public Branch copy() {
        Branch nb = new Branch();
        nb.setId(getId());
        nb.setCode(getCode());
        nb.setName(getName());
        nb.setInitial(getInitial());
        nb.setHoStatus(getHoStatus());
        nb.setMainBranch(getMainBranch());
        nb.setSoaCode(getSoaCode());
        nb.setRegionCode(getRegionCode());
        nb.setLastBillNumber(getLastBillNumber());
        nb.setSignOnTime(nb.getSignOnTime());
        nb.setSignOffTime(nb.getSignOffTime());
        nb.setFlagStatus(nb.getFlagStatus());
        nb.setSignedOnBy(nb.getSignedOnBy());
        nb.setSignedOffBy(nb.getSignedOffBy());
        nb.setBranchDetails(getBranchDetails());
        return nb;
    }
}
