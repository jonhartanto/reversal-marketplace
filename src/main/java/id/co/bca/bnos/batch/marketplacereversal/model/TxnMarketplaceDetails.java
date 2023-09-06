package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TXN_MARKETPLACE_DETAILS")
@Audited
public class TxnMarketplaceDetails {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEALSLIP_MARKETPLACE_ID_SEQ")
	@SequenceGenerator(name = "DEALSLIP_MARKETPLACE_ID_SEQ", sequenceName = "DEALSLIP_MARKETPLACE_ID_SEQ")
	private Long Id;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "BILL_NO")
	private String billNo;

	@Column(name = "CCY_CODE")
	private String ccyCode;

	@Column(name = "CCY_CONDITION")
	private String ccyCond;

	@Column(name = "DENOM")
	private BigDecimal denom;

	@Column(name = "QUANTITY")
	private BigDecimal quantity;

	@Column(name = "TOTAL_DENOM")
	private BigDecimal totalDenom;

	@Column(name = "USE_MARGIN")
	BigDecimal useMargin;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_TRXMP",referencedColumnName="ID")
	TxnMarketplace txnMarketplace;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getCcyCode() {
		return ccyCode;
	}

	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}

	public String getCcyCond() {
		return ccyCond;
	}

	public void setCcyCond(String ccyCond) {
		this.ccyCond = ccyCond;
	}

	public BigDecimal getDenom() {
		return denom;
	}

	public void setDenom(BigDecimal denom) {
		this.denom = denom;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalDenom() {
		return totalDenom;
	}

	public void setTotalDenom(BigDecimal totalDenom) {
		this.totalDenom = totalDenom;
	}

	public BigDecimal getUseMargin() {
		return useMargin;
	}

	public void setUseMargin(BigDecimal useMargin) {
		this.useMargin = useMargin;
	}

	public TxnMarketplace getTxnMarketplace() {
		return txnMarketplace;
	}

	public void setTxnMarketplace(TxnMarketplace txnMarketplace) {
		this.txnMarketplace = txnMarketplace;
	}
}
