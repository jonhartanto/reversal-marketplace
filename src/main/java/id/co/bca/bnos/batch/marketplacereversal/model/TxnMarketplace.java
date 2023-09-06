package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "TXN_MARKETPLACE")
@Audited
public class TxnMarketplace implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEALSLIP_MARKETPLACE_ID_SEQ")
	@SequenceGenerator(name = "DEALSLIP_MARKETPLACE_ID_SEQ", sequenceName = "DEALSLIP_MARKETPLACE_ID_SEQ")
	private String id;

	@Column(name = "ADMIN_FEE_AFTER", nullable = false)
	private Long adminFeeAfter;

	@Column(name = "ADMIN_FEE_BEFORE", nullable = false)
	private Long adminFeeBefore;

	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;

	@Column(name = "AUTHORIZATION_CODE", nullable = true, length = 6)
	private String authorizationCode;

	@Column(name = "BILL_NO", nullable = false, length = 255)
	private String billNo;

	@Column(name = "BRANCH", nullable = false, length = 4)
	private String branch;

	@Column(name = "CCY_CODE", nullable = false, length = 3)
	private String ccyCode;

	@Column(name = "CCY_CONDITION", nullable = true, length = 1)
	private String ccyCond;

	@Column(name = "CHANNEL", nullable = true, length = 15)
	private String channel;

	@Column(name = "CUSTOMER_ACCOUNT_CIS", nullable = false, length = 11)
	private String customerAccountCis;

	@Column(name = "CUSTOMER_ACCOUNT_NO", length = 10)
	private String customerAccountNo;

	@Column(name = "CUSTOMER_ADDRESS1", length = 40)
	private String customerAddress1;

	@Column(name = "CUSTOMER_ADDRESS2", length = 35)
	private String customerAddress2;

	@Column(name = "CUSTOMER_BIRTHDATE")
	private String customerBirthdate;

	@Column(name = "CUSTOMER_ID_NO", length = 16)
	private String customerIdNo;

	@Column(name = "CUSTOMER_ID_TYPE", length = 13)
	String customerIdType;

	@Column(name = "CUSTOMER_NAME", length = 30)
	private String customerName;

	@Column(name = "CUSTOMER_NATIONALITY", length = 1)
	private String customerNationality;

	@Column(name = "CUSTOMER_ZIPCODE", length = 5)
	private String customerZipCode;

	@Column(name = "DENOM", nullable = false, length = 6)
	private Long denom;

	@Column(name = "DISCOUNT", length = 21)
	private BigDecimal discount;

	@Column(name = "DT_PENGAMBILAN", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime dtPengambilan;

	@Column(name = "EFORM_REFF_NUMBER", length = 23)
	private String eformReffNumber;

	@Column(name = "EMAIL", nullable = true, length = 255)
	private String email;

	@Column(name = "EXPIRED_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiredDate;

	@Column(name = "FLAG_PERWAKILAN")
	private Boolean flagPerwakilan;

	@Column(name = "FLAG_REVERSE")
	private Boolean flagReverse;

	@Column(name = "FLAG_REVERSE_REPORT")
	private int flagReverseReport;

	@Column(name = "KOMENTAR", nullable = true, length = 50)
	private String comment;

	@Column(name = "KOMISI_BCA")
	private String komisiBca;

	@Column(name = "KOMISI_MP")
	private String komisiMp;

	@Column(name = "MARGIN")
	Double margin;

	@Column(name = "MERCHANT_ID", nullable = true, length = 35)
	private String merchantId;

	@Column(name = "PAYMENT_DATE", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime paymentDate;

	@Column(name = "PAYMENT_LIMIT_TIME", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime paymentLimitTime;

	@Column(name = "PERWAKILAN", nullable = false, length = 1)
	private String perwakilan;

	@Column(name = "PERWAKILAN_ID", length = 16)
	private String perwakilanId;

	@Column(name = "PERWAKILAN_ID_TYPE")
	private String perwakilanIdType;

	@Column(name = "PERWAKILAN_NAME", nullable = true, length = 30)
	private String perwakilanName;

	@Column(name = "PERWAKILAN_UPDATED", nullable = true, length = 1)
	private Boolean perwakilanUpdated;

	@Column(name = "PHONE_NUMBER", nullable = true, length = 20)
	private String phoneNumber;

	@Column(name = "PURPOSE_DETAIL", nullable = true, length = 255)
	private String purposeDetail;

	@Column(name = "RATE_MODAL", nullable = true, length = 9)
	private BigDecimal rateModal;

	@Column(name = "RATE_TXN", nullable = false, length = 9)
	private BigDecimal rateTxn;

	@Column(name = "RATING", nullable = true, length = 1)
	private String rating;

	@Column(name = "RATING_DATE", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime ratingDate;

	@Column(name = "RECORD_STATUS", nullable = false, length = 3)
	private String recordStatus;

	@Column(name = "REVERSE_AMOUNT_IDR", nullable = true, length = 21)
	private BigDecimal reverseAmountIdr;

	@Column(name = "REVERSE_DATE", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime reverseDate;

	@Column(name = "REVERSE_RATE", nullable = true, length = 21)
	private BigDecimal reverseRate;

	@Column(name = "SELISIH_RATE", nullable = true, length = 9)
	private BigDecimal selisihRate;

	@Column(name = "TOTAL_AMOUNT", nullable = false, length = 21)
	private BigDecimal totalAmount;

	@Column(name = "TOTAL_DENOM", nullable = false, length = 21)
	private BigDecimal totalDenom;

	@Column(name = "TRANSACTION_DATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime transactionDate;

	@Column(name = "TXN_PURPOSE", nullable = true, length = 3)
	private String txnPurpose;

	@Column(name = "TXN_TYPE", nullable = true, length = 1)
	private String txnType;

	@Column(name = "UNIQUE_IDENTIFIER", nullable = false, length = 16)
	private String uniqueIdentifier;

	@Column(name = "USERID", nullable = false, length = 15)
	private String userId;

	@Column(name = "VALUE_DATE", nullable = true, columnDefinition = "DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime valueDate;

	@Column(name = "VA_NO", nullable = true, length = 23)
	private String vaNo;
	@Transient
	@JsonProperty
	private String merchantName;

	@OneToMany(mappedBy = "txnMarketplace", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderBy("id")
	private Set<@Valid TxnMarketplacePromotion> promotion_code;

	@OneToMany(mappedBy = "txnMarketplace", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<TxnMarketplaceDetails> txnMarketplaceDetailsList;

	public TxnMarketplace copy(String newBillNo) {
		TxnMarketplace txnMarketplace = new TxnMarketplace();
		txnMarketplace.setAdminFeeBefore(getAdminFeeBefore());
		txnMarketplace.setAdminFeeAfter(getAdminFeeAfter());
		txnMarketplace.setAmount(getAmount());
		txnMarketplace.setAuthorizationCode(getAuthorizationCode());
		txnMarketplace.setBillNo(newBillNo);
		txnMarketplace.setBranch(getBranch());
		txnMarketplace.setCcyCode(getCcyCode());
		txnMarketplace.setCcyCond(getCcyCond());
		txnMarketplace.setChannel(getChannel());
		txnMarketplace.setCustomerAccountCis(getCustomerAccountCis());
		txnMarketplace.setCustomerAccountNo(getCustomerAccountNo());
		txnMarketplace.setCustomerAddress1(getCustomerAddress1());
		txnMarketplace.setCustomerAddress2(getCustomerAddress2());
		txnMarketplace.setCustomerBirthdate(getCustomerBirthdate());
		txnMarketplace.setCustomerIdNo(getCustomerIdNo());
		txnMarketplace.setCustomerIdType(getCustomerIdType());
		txnMarketplace.setCustomerName(getCustomerName());
		txnMarketplace.setCustomerNationality(getCustomerNationality());
		txnMarketplace.setCustomerZipCode(getCustomerZipCode());
		txnMarketplace.setDenom(getDenom());
		txnMarketplace.setDiscount(getDiscount());
		txnMarketplace.setEformReffNumber(getEformReffNumber());
		txnMarketplace.setEmail(getEmail());
		txnMarketplace.setExpiredDate(getExpiredDate());
		txnMarketplace.setFlagPerwakilan(getFlagPerwakilan());
		txnMarketplace.setFlagReverse(getFlagReverse());
		txnMarketplace.setFlagReverseReport(getFlagReverseReport());
		txnMarketplace.setComment(getComment());
		txnMarketplace.setKomisiBca(getKomisiBca());
		txnMarketplace.setKomisiMp(getKomisiMp());
		txnMarketplace.setMargin(getMargin());
		txnMarketplace.setMerchantId(getMerchantId());
		txnMarketplace.setPaymentDate(getPaymentDate());
		txnMarketplace.setPaymentLimitTime(getPaymentLimitTime());
		txnMarketplace.setPerwakilan(getPerwakilan());
		txnMarketplace.setPerwakilanId(getPerwakilanId());
		txnMarketplace.setPerwakilanIdType(getPerwakilanIdType());
		txnMarketplace.setPerwakilanName(getPerwakilanName());
		txnMarketplace.setPerwakilanUpdated(getPerwakilanUpdated());
		txnMarketplace.setPhoneNumber(getPhoneNumber());
		txnMarketplace.setPurposeDetail(getPurposeDetail());
		txnMarketplace.setRateModal(getRateModal());
		txnMarketplace.setRateTxn(getRateTxn());
		txnMarketplace.setRating(getRating());
		txnMarketplace.setRatingDate(getRatingDate());
		txnMarketplace.setRecordStatus(getRecordStatus());
		txnMarketplace.setReverseAmountIdr(getReverseAmountIdr());
		txnMarketplace.setReverseDate(getReverseDate());
		txnMarketplace.setReverseRate(getReverseRate());
		txnMarketplace.setSelisihRate(getSelisihRate());
		txnMarketplace.setTotalAmount(getTotalAmount());
		txnMarketplace.setTotalDenom(getTotalDenom());
		txnMarketplace.setTransactionDate(getTransactionDate());
		txnMarketplace.setTxnPurpose(getTxnPurpose());
		txnMarketplace.setTxnType(getTxnType());
		txnMarketplace.setUniqueIdentifier(getUniqueIdentifier());
		txnMarketplace.setUserId(getUserId());
		txnMarketplace.setValueDate(getValueDate());
		txnMarketplace.setDtPengambilan(getDtPengambilan());
		txnMarketplace.setVaNo(getVaNo());

		List<TxnMarketplaceDetails> newTxnMarketplaceDetails = new ArrayList<>();
		for(TxnMarketplaceDetails txnMarketplaceDetails : getTxnMarketplaceDetailsList()) {
			TxnMarketplaceDetails modTxnMarketplaceDetails = new TxnMarketplaceDetails();
			modTxnMarketplaceDetails.setTxnMarketplace(txnMarketplace);
			modTxnMarketplaceDetails.setBillNo(newBillNo);
			modTxnMarketplaceDetails.setAmount(txnMarketplaceDetails.getAmount());
			modTxnMarketplaceDetails.setTotalDenom(txnMarketplaceDetails.getTotalDenom());
			modTxnMarketplaceDetails.setCcyCode(txnMarketplaceDetails.getCcyCode());
			modTxnMarketplaceDetails.setCcyCond(txnMarketplaceDetails.getCcyCond());
			modTxnMarketplaceDetails.setDenom(txnMarketplaceDetails.getDenom());
			modTxnMarketplaceDetails.setQuantity(txnMarketplaceDetails.getQuantity());
			newTxnMarketplaceDetails.add(modTxnMarketplaceDetails);
		}
		txnMarketplace.setTxnMarketplaceDetailsList(newTxnMarketplaceDetails);

		return txnMarketplace;
	}

	public Set<TxnMarketplacePromotion> getPromotion_code() {
		return promotion_code;
	}

	public void setPromotion_code(Set<TxnMarketplacePromotion> promotion_code) {
		this.promotion_code = promotion_code;
	}

	public List<TxnMarketplaceDetails> getTxnMarketplaceDetailsList() {
		return txnMarketplaceDetailsList;
	}

	public void setDetails(List<TxnMarketplaceDetails> details) {
		this.txnMarketplaceDetailsList = txnMarketplaceDetailsList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getAdminFeeBefore() {
		return adminFeeBefore;
	}

	public void setAdminFeeBefore(Long adminFeeBefore) {
		this.adminFeeBefore = adminFeeBefore;
	}

	public Long getAdminFeeAfter() {
		return adminFeeAfter;
	}

	public void setAdminFeeAfter(Long adminFeeAfter) {
		this.adminFeeAfter = adminFeeAfter;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCustomerAccountCis() {
		return customerAccountCis;
	}

	public void setCustomerAccountCis(String customerAccountCis) {
		this.customerAccountCis = customerAccountCis;
	}

	public String getCustomerAccountNo() {
		return customerAccountNo;
	}

	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = customerAccountNo;
	}

	public String getCustomerAddress1() {
		return customerAddress1;
	}

	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	public String getCustomerAddress2() {
		return customerAddress2;
	}

	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	public String getCustomerBirthdate() {
		return customerBirthdate;
	}

	public void setCustomerBirthdate(String customerBirthdate) {
		this.customerBirthdate = customerBirthdate;
	}

	public String getCustomerIdNo() {
		return customerIdNo;
	}

	public void setCustomerIdNo(String customerIdNo) {
		this.customerIdNo = customerIdNo;
	}

	public String getCustomerIdType() {
		return customerIdType;
	}

	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNationality() {
		return customerNationality;
	}

	public void setCustomerNationality(String customerNationality) {
		this.customerNationality = customerNationality;
	}

	public String getCustomerZipCode() {
		return customerZipCode;
	}

	public void setCustomerZipCode(String customerZipCode) {
		this.customerZipCode = customerZipCode;
	}

	public Long getDenom() {
		return denom;
	}

	public void setDenom(Long denom) {
		this.denom = denom;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public LocalDateTime getDtPengambilan() {
		return dtPengambilan;
	}

	public void setDtPengambilan(LocalDateTime dtPengambilan) {
		this.dtPengambilan = dtPengambilan;
	}

	public String getEformReffNumber() {
		return eformReffNumber;
	}

	public void setEformReffNumber(String eformReffNumber) {
		this.eformReffNumber = eformReffNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Boolean getFlagPerwakilan() {
		return flagPerwakilan;
	}

	public void setFlagPerwakilan(Boolean flagPerwakilan) {
		this.flagPerwakilan = flagPerwakilan;
	}

	public Boolean getFlagReverse() {
		return flagReverse;
	}

	public void setFlagReverse(Boolean flagReverse) {
		this.flagReverse = flagReverse;
	}

	public int getFlagReverseReport() {
		return flagReverseReport;
	}

	public void setFlagReverseReport(int flagReverseReport) {
		this.flagReverseReport = flagReverseReport;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getKomisiBca() {
		return komisiBca;
	}

	public void setKomisiBca(String komisiBca) {
		this.komisiBca = komisiBca;
	}

	public String getKomisiMp() {
		return komisiMp;
	}

	public void setKomisiMp(String komisiMp) {
		this.komisiMp = komisiMp;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getPaymentLimitTime() {
		return paymentLimitTime;
	}

	public void setPaymentLimitTime(LocalDateTime paymentLimitTime) {
		this.paymentLimitTime = paymentLimitTime;
	}

	public String getPerwakilan() {
		return perwakilan;
	}

	public void setPerwakilan(String perwakilan) {
		this.perwakilan = perwakilan;
	}

	public String getPerwakilanId() {
		return perwakilanId;
	}

	public void setPerwakilanId(String perwakilanId) {
		this.perwakilanId = perwakilanId;
	}

	public String getPerwakilanIdType() {
		return perwakilanIdType;
	}

	public void setPerwakilanIdType(String perwakilanIdType) {
		this.perwakilanIdType = perwakilanIdType;
	}

	public String getPerwakilanName() {
		return perwakilanName;
	}

	public void setPerwakilanName(String perwakilanName) {
		this.perwakilanName = perwakilanName;
	}

	public Boolean getPerwakilanUpdated() {
		return perwakilanUpdated;
	}

	public void setPerwakilanUpdated(Boolean perwakilanUpdated) {
		this.perwakilanUpdated = perwakilanUpdated;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPurposeDetail() {
		return purposeDetail;
	}

	public void setPurposeDetail(String purposeDetail) {
		this.purposeDetail = purposeDetail;
	}

	public BigDecimal getRateModal() {
		return rateModal;
	}

	public void setRateModal(BigDecimal rateModal) {
		this.rateModal = rateModal;
	}

	public BigDecimal getRateTxn() {
		return rateTxn;
	}

	public void setRateTxn(BigDecimal rateTxn) {
		this.rateTxn = rateTxn;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public LocalDateTime getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(LocalDateTime ratingDate) {
		this.ratingDate = ratingDate;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public BigDecimal getReverseAmountIdr() {
		return reverseAmountIdr;
	}

	public void setReverseAmountIdr(BigDecimal reverseAmountIdr) {
		this.reverseAmountIdr = reverseAmountIdr;
	}

	public LocalDateTime getReverseDate() {
		return reverseDate;
	}

	public void setReverseDate(LocalDateTime reverseDate) {
		this.reverseDate = reverseDate;
	}

	public BigDecimal getReverseRate() {
		return reverseRate;
	}

	public void setReverseRate(BigDecimal reverseRate) {
		this.reverseRate = reverseRate;
	}

	public BigDecimal getSelisihRate() {
		return selisihRate;
	}

	public void setSelisihRate(BigDecimal selisihRate) {
		this.selisihRate = selisihRate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalDenom() {
		return totalDenom;
	}

	public void setTotalDenom(BigDecimal totalDenom) {
		this.totalDenom = totalDenom;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTxnPurpose() {
		return txnPurpose;
	}

	public void setTxnPurpose(String txnPurpose) {
		this.txnPurpose = txnPurpose;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getValueDate() {
		return valueDate;
	}

	public void setValueDate(LocalDateTime valueDate) {
		this.valueDate = valueDate;
	}

	public String getVaNo() {
		return vaNo;
	}

	public void setVaNo(String vaNo) {
		this.vaNo = vaNo;
	}
	public void setTxnMarketplaceDetailsList(
			List<TxnMarketplaceDetails> txnMarketplaceDetailsList) {
		this.txnMarketplaceDetailsList = txnMarketplaceDetailsList;
	}

	public Set<TxnMarketplacePromotion> getPromoCode() {
		if (this.promotion_code == null)
			this.promotion_code = new HashSet<TxnMarketplacePromotion>();

		return promotion_code;
	}

	public void setPromocode(Set<TxnMarketplacePromotion> promoCode) {
		this.promotion_code = promoCode;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

}