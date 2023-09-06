package id.co.bca.bnos.batch.marketplacereversal.model;

import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name="STOCK_DENOM")
@Audited
public class StockDenom extends AuditableEntity{
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_denom_id_seq")
    @SequenceGenerator(name = "stock_denom_id_seq", sequenceName = "stock_denom_id_seq")
    private Long id;

    @NotBlank(message = "CCY. CODE BANKNOTES is mandatory")
    @Size(min = 3, max = 3, message = "CCY. CODE must be 3 digits")
    @Column(name = "CCY_CODE", length = 3, nullable = false)
    private String ccyCode;

    @NotBlank(message = "STATUS STOCK is mandatory")
    @Size(min = 1, max = 1, message = "STATUS STOCK must be 1 digits")
    @Column(name = "STATUS", length=1)
    private String status;

    @NotNull(message = "CONDITION is mandatory")
    @Size(min = 1, max = 1, message = "CONDITION. must be 1 digits")
    @Column(name = "CONDITION", length = 1)
    private String condition;

    @Column(name = "SKU")
    private String sku;
    
    @Column(name = "KETERANGAN")
    private String keterangan;

    @NotNull(message = "DENOM is mandatory")
    @Column(name = "DENOM")
    private Integer denom;

    @NotNull(message = "DEST. BRANCH is mandatory")
    @Size(max = 4, message = "DEST. BRANCH max 4 digits")
    @Column(name = "BRANCH_DEST", length = 4)
    private String branchDest;

    @NotNull(message = "BRANCH is mandatory")
    @Size(max = 4, message = "BRANCH. max 4 digits")
    @Column(name = "BRANCH", length = 4)
    private String branch;

    @NotNull(message = "BALANCE is mandatory")
    @Column(name = "BALANCE", precision = 19, scale = 2)
    private BigDecimal balance;

    @NotNull(message = "OPEN BALANCE is mandatory")
    @Column(name = "OPEN_BALANCE", precision = 19, scale = 2)
    private BigDecimal openBalance;

    @Version
    @Column(name = "VERSION")
    private Long version;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public Integer getDenom() {
        return denom;
    }

    public void setDenom(Integer denom) {
        this.denom = denom;
    }

    public String getBranchDest() {
        return branchDest;
    }

    public void setBranchDest(String branchDest) {
        this.branchDest = branchDest;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getOpenBalance() {
        return openBalance;
    }

    public void setOpenBalance(BigDecimal openBalance) {
        this.openBalance = openBalance;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}