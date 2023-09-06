package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import id.co.bca.bnos.batch.marketplacereversal.util.BiCode;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@Audited
public class Customer extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq")
    private Long id;

    // @NotBlank(message = "Code is mandatory")
    @Size(max = 20, message = "CODE max 20 digits")
    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;

    // @NotBlank(message = "Name is mandatory")
    @Column(name = "name", length = 40, nullable = false)
    private String name;

    // @NotBlank(message = "Address is mandatory")
    @Column(name = "address", length = 60, nullable = false)
    private String address;

    // @NotBlank(message = "PIC is mandatory")
    @Column(name = "pic", length = 40, nullable = false)
    private String pic;

    // @NotNull(message = "Expired Date is mandatory")
    @Column(name = "expired_date", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiredDate;
    
    // @Enumerated(EnumType.STRING)
    @Column(name = "id_type", nullable = false)
    // IdType idType;
    private String idType;

    // @NotBlank(message = "Id Number is mandatory")
    @Column(name = "id_number", length = 16, nullable = false)
    private String idNumber;

    @JsonIgnore
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bi_code")
    private BiCode biCode;

    @Column(name = "settl_limit")
    private Long settlementLimit;

    // @Column(name = "fwd_limit", nullable = false)
    // Long forwardLimit;

    @Column(name = "email")
    private String email;

    @Column(name = "tipe_nasabah")
    private String tipeNasabah;

    @Column(name = "count_party")
    private String counterParty;

    @Column(name = "sts_nasabah")
    private String statusNasabah;

    @Column(name = "bdg_usaha")
    private String bidangUsaha;

    @Column(name = "ket_bdg_usaha")
    private String ketBidangUsaha;

    @Column(name = "jabatan")
    private String jabatan;

    @Column(name = "no_telp")
    private String noTelp;

    @Column(name = "no_ktp", length = 35)
    private String noKtp;

    @Column(name = "flag_status")
    private Boolean flagStatus;

    @Column(name = "cabang", length = 4)
    private String cabang;

    @Column(name = "value_date")
    private LocalDate valueDate;
    
    @Size(max = 20, message = "CIS_NO max 20 digits")
    @Column(name = "cis_no", length = 20, unique = true)
    private String cisNo;
    
    //LUN 20122022
    @Column(name = "siup_number")
    private String siupNumber;
    
    //LUN 05122022
    @Column(name = "jenis_nasabah")
    private String jenisNasabah;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "init_pay")
    // InitialPayment initialPayment;

    // @JsonIgnore
    @OneToMany(mappedBy = "custId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("id")
    private Set<@Valid CustomerAccount> custAcct;

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(final String pic) {
        this.pic = pic;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(final LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

	public String getIdType() {
        return idType;
    }

    public void setIdType(final String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(final String idNumber) {
        this.idNumber = idNumber;
    }

    public BiCode getBiCode() {
        return biCode;
    }

    public void setBiCode(final BiCode biCode) {
        this.biCode = biCode;
    }

    public Long getSettlementLimit() {
        return settlementLimit;
    }

    public void setSettlementLimit(final Long settlementLimit) {
        this.settlementLimit = settlementLimit;
    }

    // public Long getForwardLimit() {
    // return forwardLimit;
    // }

    // public void setForwardLimit(final Long forwardLimit) {
    // this.forwardLimit = forwardLimit;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    // public InitialPayment getInitialPayment() {
    // return initialPayment;
    // }

    // public void setInitialPayment(final InitialPayment initialPayment) {
    // this.initialPayment = initialPayment;
    // }

    public Set<CustomerAccount> getCustAcct() {
        if (this.custAcct == null)
            this.custAcct = new HashSet<CustomerAccount>();

        return custAcct;
    }

    public void setCustAcct(Set<CustomerAccount> custAcct) {
        this.custAcct = custAcct;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getStatusNasabah() {
        return statusNasabah;
    }

    public void setStatusNasabah(String statusNasabah) {
        this.statusNasabah = statusNasabah;
    }

    public String getBidangUsaha() {
        return bidangUsaha;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }

    public String getKetBidangUsaha() {
        return ketBidangUsaha;
    }

    public void setKetBidangUsaha(String ketBidangUsaha) {
        this.ketBidangUsaha = ketBidangUsaha;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getTipeNasabah() {
        return tipeNasabah;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public Boolean getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(Boolean flagStatus) {
        this.flagStatus = flagStatus;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

	public String getCisNo() {
		return cisNo;
	}

	public void setCisNo(String cisNo) {
		this.cisNo = cisNo;
	}

	//LUN 20122022
	public String getSiupNumber() {
		return siupNumber;
	}

	public void setSiupNumber(String siupNumber) {
		this.siupNumber = siupNumber;
	}
	
	//LUN 12052022
    public String getJenisNasabah() {
		return jenisNasabah;
	}

	public void setJenisNasabah(String jenisNasabah) {
		this.jenisNasabah = jenisNasabah;
	}
    

    // public CustomerAccount findCustAcct(String acctNumber) {
    // for (CustomerAccount acct : custAcct) {
    // if (acct.getAcctNumber().equals(acctNumber)) {
    // return acct;
    // }
    // }
    // return null;
    // }

    // public String getCustAcctValue(String acctNumber) {
    // for (CustomerAccount acct : custAcct) {
    // if (acct.getAcctNumber().equals(acctNumber)) {
    // return acct.getAcctCcy();
    // }
    // }

    // throw new RuntimeException("Acct Number" + acctNumber + " is not found in
    // Customer Account row.");
    // }

    // public void setCustAcct(String acctNumber, String acctCcy) {
    // for (CustomerAccount dtl : custAcct) {
    // if (dtl.getAcctNumber().equals(acctNumber)) {
    // dtl.getAcctCcy(acctCcy);
    // break;
    // }
    // }
    // }

}
