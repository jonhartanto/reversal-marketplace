package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ACCOUNT")
@Audited
public class CustomerAccount extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_acct_id_seq")
    @SequenceGenerator(name = "cust_acct_id_seq", sequenceName = "cust_acct_id_seq")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUST_ID")
    private Customer custId;

    // @NotBlank(message = "ACCT NUMBER is mandatory")
    @Column(name = "ACCT_NUMBER")
    private String acctNumber;

    // @NotBlank(message = "ACCT CCY is mandatory")
    @Column(name = "ACCT_CCY")
    private String acctCcy;
    
    @Column(name = "ACCT_TYPE")
    private String acctType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustId() {
        return custId;
    }

    public void setCustId(Customer custId) {
        this.custId = custId;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getAcctCcy() {
        return acctCcy;
    }

    public void setAcctCcy(String acctCcy) {
        this.acctCcy = acctCcy;
    }

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
    
}