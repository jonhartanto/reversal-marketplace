package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.bca.bnos.batch.marketplacereversal.model.AuditableEntity;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TXN_DEAL_SLIP")
@Audited
public class TransactionDealSlip extends AuditableEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dealslip_id_seq")
    @SequenceGenerator(name = "dealslip_id_seq", sequenceName = "dealslip_id_seq")
    private Long id;

    @Column(name = "bill_no", nullable = false, unique = true)
    String billNo;

    @Column(name = "txn_id", nullable = false)
    String txnId;

    @Pattern(regexp = "^[J|B]{1}$", message ="Jenis transaksi hanya Jual/Beli")
    @NotBlank(message = "Jenis transaksi tidak boleh kosong.")
    @Size(min = 1, max = 1, message = "Jenis transaksi hanya format salah.")
    @Column(name = "txn_type", length = 1, nullable = false)
    String txnType;

    @Column(name = "market_rate_usd")
    BigDecimal marketRateUsd;

    @Pattern(regexp = "^[N|Y]{1}$", message ="Dokumen hanya Ya/Tidak")
    @NotBlank(message = "Dokumen tidak boleh kosong.")
    @Size(min = 1, max = 1, message = "Dokumen format salah")
    @Column(name = "doc_required", length = 1, nullable = false)
    String docRequired;

    @Pattern(regexp = "^[N|C]{1}$", message ="Lawan transaksi hanya N/C")
    @NotBlank(message = "Lawan transaksi tidak boleh kosong.")
    @Size(min = 1, max = 1, message = "Lawan transaksi hanya N/C")
//@Pattern(regexp = "^[N|C]{1}$", message ="Gagal simpan data baru.")
//@NotBlank(message = "Gagal simpan data baru.")
//@Size(min = 1, max = 1, message = "Gagal simpan data baru.")
    @Column(name = "opponent_type", length = 1, nullable = false)
    String opponentType;

    @NotBlank(message = "Kode lawan tidak boleh kosong.")
    @Size(min = 1, max = 20, message = "Kode lawan maksimal 20 karakter.")
//@NotBlank(message = "Gagal simpan data baru.")
//@Size(min = 1, max = 20, message = "Gagal simpan data baru.")
    @Column(name = "opponent_code", length = 20, nullable = false)
    String opponentCode;

    @Column(name = "opponent_name", length = 40, nullable = false)
    String opponentName;

    @Column(name = "dealerid", length = 20)
    String dealerId;

    //@NotNull(message = "Jatuh tempo tidak boleh kosong")
    @Column(name = "value_date", columnDefinition = "DATE", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate valueDate;

    @Column(name = "value_time", columnDefinition = "TIMESTAMP", nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    LocalTime valueTime;

    @Column(name = "transaction_date", columnDefinition = "DATE")
    LocalDate trxDate;

    @Column(name = "bongkaran_date", length = 50)
    String bongkaranDate;

    //@NotEmpty(message = "'isTxnKcp' is mandatory")
    @Column(name = "is_txn_kcp", length = 1, nullable = false)
    String isTxnKcp;

    @Column(name = "kcp_code", length = 4)
    String kcpCode;

    @Column(name = "branch", length = 4, nullable = false)
    String branch;

    @NotBlank(message = "Mata uang tidak boleh kosong.")
    @Size(min = 3, max = 3, message = "Mata uang maksimal 3 karakter.")
//@NotBlank(message = "Gagal simpan data baru.")
//@Size(min = 3, max = 3, message = "Gagal simpan data baru.")
    @Column(name = "ccy_code", length = 3, nullable = false)
    String ccyCode;

    @NotBlank(message = "Kondisi tidak boleh kosong")
    @Size(min = 1, max = 1, message = "Kondisi maksimal 1 karakter")
//@NotBlank(message = "Gagal simpan data baru.")
//@Size(min = 1, max = 1, message = "Gagal simpan data baru.")
    @Column(name = "ccy_condition", length = 1, nullable = false)
    String ccyCondition;

    @Pattern(regexp = "^[C|K|B|Y]{1}$", message ="Jenis rate hanya C/K/B/Y")
    @NotNull(message = "Jenis rate tidak boleh kosong.")
//@Pattern(regexp = "^[C|K|B|Y]{1}$", message ="Gagal simpan data baru.")
//@NotNull(message = "Gagal simpan data baru.")
    @Column(name = "rate_type", length = 1, nullable = false)
    String rateType;

    @Column(name = "exchange_rate", nullable = false)
    BigDecimal exchangeRate;

    @Column(name = "tr88_rate")
    Double tr88Rate;

    @Column(name = "tr88_rate_org")
    Double tr88RateOrg;

    @Column(name = "margin_modal")
    Double marginModal;

    @Column(name = "rate_counter")
    BigDecimal rateCounter;

    @Column(name = "amount")
    BigDecimal amount;

    // ADDED 060622
    @Column(name = "eq_usd")
    BigDecimal eq_usd;

    // 'Y': stock cabang, 'N': transaksi MRR
    @Pattern(regexp = "^[Y|N]{1}$", message ="Ambil fisik diCabang  hanya Ya/Tidak")
    @Size(min = 1, max = 1, message = "Ambil fisik diCabang hanya format salah.")
//    @Pattern(regexp = "^[Y|N]{1}$", message ="Use Stock PPC, Must be Y or N")
//    @Size(min = 1, max = 1, message = "Transaction TYPE must be 1 digits")
    @Column(name = "use_stock_ppc", length = 1)
    String useStockPpc;

    // 'Y': utk RATE 'K' field kurs is inputable, 'N': no changes
    @Pattern(regexp = "^[Y|K|N]{1}$", message ="Transaksi khusus hanya  Ya/Tidak")
    @Size(min = 1, max = 1, message = "Transaksi khusus format salah.")
//    @Pattern(regexp = "^[Y|K|N]{1}$", message ="Special TXN Must be Y or K or N")
//    @Size(min = 1, max = 1, message = "Special TXN must be 1 digits")
    @Column(name = "special_txn", length = 1)
    String specialTxn;

    @Column(name = "with_provisi", length = 1)
    String withProvisi;

    @Column(name = "provisi")
    BigDecimal provisi;

    //@Column(name = "doc_code", length = 5, nullable = false)
    @Column(name = "doc_code", length = 5, nullable = true)
    String docCode;

    @Column(name = "doc_name", length = 50)
    String docName;

    @Column(name = "doc_description", length = 100)
    String docDescription;

    //@NotNull(message = "Purpose Doc. is mandatory")
    @Column(name = "purpose_doc", length = 5)
    String purposeDoc;

    @Column(name = "doc_adds", length = 60)
    String docAdds;

    @Column(name = "description", length = 60)
    String description;

    @Column(name = "record_status", length = 3)
    String recordStatus;

    @Column(name = "total_denom_prev")
    BigDecimal totalDenomPrev;

    @Column(name = "total_denom", nullable = false)
    BigDecimal totalDenom;

    @Column(name = "cancel_note")
    String cancelNote;

    @Column(name = "type_cetak")
    String typeCetak;

    @Column(name = "cetak_counter", nullable = false)
    Integer cetakCounter = 0;

    @Column(name = "bill_no_potong")
    String billNoPotong;

    @Column(name = "is_tuker_denom", length = 1)
    String isTukerDenom;

    @Column(name = "is_correction", length = 1)
    String isCorrection;

    @Column(name = "status_serah", length = 15)
    String statusSerah;

    @Column(name = "serah_origin", length = 20)
    String serahOrigin;

    @Column(name = "prepare_date", columnDefinition = "TIMESTAMP")
    LocalDateTime prepareDate;

    @Column(name = "prepare_pic", length = 30)
    String preparePic;

    @Column(name = "check_date", columnDefinition = "TIMESTAMP")
    LocalDateTime checkDate;

    @Column(name = "check_pic", length = 30)
    String checkPic;

    @Column(name = "release_date", columnDefinition = "TIMESTAMP")
    LocalDateTime releaseDate;

    @Column(name = "release_pic", length = 30)
    String releasePic;

    @Column(name = "receive_date", columnDefinition = "TIMESTAMP")
    LocalDateTime receiveDate;

    @Column(name = "receive_pic", length = 30)
    String receivePic;

    @Column(name = "opponent_acc_number")
    String opponentAccNumber;

    @Column(name = "settlement_type")
    String settlementType;

    @Column(name = "va_no", length = 23)
    String vaNo;

    @Column(name = "ids_status", length = 20)
    String idsStatus;

    @Column(name = "approval_pic", length = 30)
    String approvalPic;

    @Column(name = "batal_pic", length = 30)
    String batalPic;

    @Column(name = "batal_request_pic", length = 30)
    String batalRequestPic;

    @Column(name = "mtu_rekening", length = 50)
    String mtuRekening;

    @Column(name = "wsidno", length = 100)
    String wsidNo;

    @Column(name = "cetak_pic", length = 30)
    String cetakPic;

    @Column(name = "cetak_date", columnDefinition = "TIMESTAMP")
    LocalDateTime cetakDate;

    @Column(name = "FLAG_TOLERANSI")
    private Boolean flagToleransi;

    @Column(name = "total_denom_awal")
    BigDecimal totalDenomAwal;

    @Column(name = "total_amount_awal")
    BigDecimal totalAmountAwal;

    @Column(name = "correction_id", length = 100)
    String correctionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_penyerahan")
    private TxnPenyerahan txnPenyerahan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trxDealslip",
                cascade = CascadeType.ALL, orphanRemoval = true)
    List<TransactionDealSlipDetails> trxDealSlipDetails;

    public TransactionDealSlip clone() {
        try {
            return (TransactionDealSlip) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public TransactionDealSlip copy(String newBillNo) {
        TransactionDealSlip nt = new TransactionDealSlip();
        nt.setBillNo(newBillNo);
        nt.setTxnId(getTxnId());
        nt.setTxnType(getTxnType());
        nt.setMarketRateUsd(getMarketRateUsd());
        nt.setDocRequired(getDocRequired());
        nt.setOpponentType(getOpponentType());
        nt.setOpponentCode(getOpponentCode());
        nt.setOpponentName(getOpponentName());
        nt.setDealerId(getDealerId());
        nt.setValueDate(getValueDate());
        nt.setValueTime(getValueTime());
        nt.setIsTxnKcp(getIsTxnKcp());
        nt.setKcpCode(getKcpCode());
        nt.setBranch(getBranch());
        nt.setCcyCode(getCcyCode());
        nt.setCcyCondition(getCcyCondition());
        nt.setRateType(getRateType());
        nt.setExchangeRate(getExchangeRate());
        nt.setUseStockPpc(getUseStockPpc());
        nt.setSpecialTxn(getSpecialTxn());
        nt.setWithProvisi(getWithProvisi());
        nt.setProvisi(getProvisi());
        nt.setDocCode(getDocCode());
        nt.setPurposeDoc(getPurposeDoc());
        nt.setDocAdds(getDocAdds());
        nt.setDescription(getDescription());
        nt.setRecordStatus(getRecordStatus());
        nt.setTotalDenom(getTotalDenom());
        nt.setAmount(getAmount());
        nt.setCancelNote(getCancelNote());
        nt.setCetakCounter(getCetakCounter());
        nt.setTypeCetak(getTypeCetak());
        nt.setBillNoPotong(getBillNoPotong());
        nt.setIsTukerDenom(getIsTukerDenom());
        nt.setIsCorrection(getIsCorrection());
        nt.setStatusSerah(getStatusSerah());
        nt.setSerahOrigin(getSerahOrigin());
        nt.setPrepareDate(getPrepareDate());
        nt.setPreparePic(getPreparePic());
        nt.setCheckDate(getCheckDate());
        nt.setCheckPic(getCheckPic());
        nt.setReleaseDate(getReleaseDate());
        nt.setReleasePic(getReleasePic());
        nt.setReceiveDate(getReceiveDate());
        nt.setReceivePic(getReceivePic());
        nt.setOpponentAccNumber(getOpponentAccNumber());
        nt.setSettlementType(getSettlementType());
        nt.setTr88Rate(getTr88Rate());
        nt.setTr88RateOrg(getTr88RateOrg());
        nt.setMarginModal(getMarginModal());
        nt.setMtuRekening(getMtuRekening());
        nt.setDocDescription(getDocDescription());
        nt.setWsidNo(getWsidNo());
        nt.setBatalRequestPic(getBatalRequestPic());
        nt.setTotalAmountAwal(getTotalAmountAwal());
        nt.setTotalDenomAwal(getTotalDenomAwal());
        nt.setCorrectionId(nt.getCorrectionId());

        List<TransactionDealSlipDetails> newdetails = new ArrayList<>();
        for(TransactionDealSlipDetails dt : getTrxDealSlipDetails()) {
            TransactionDealSlipDetails nd = new TransactionDealSlipDetails();
            nd.setTrxDealslip(nt);
            nd.setBillNo(newBillNo);
            nd.setAmount(dt.getAmount());
            nd.setTotalDenom(dt.getTotalDenom());
            nd.setUseMargin(dt.getUseMargin());
            nd.setCcyCode(dt.getCcyCode());
            nd.setCcyCondition(dt.getCcyCondition());
            nd.setDenom(dt.getDenom());
            nd.setQuantity(dt.getQuantity());
            newdetails.add(nd);
        }
        nt.setTrxDealSlipDetails(newdetails);
        return nt;
    }

//    public TransactionDealSlip copyTemp(TxnDealSlipTemp txnDealSlipTemp) {
//        TransactionDealSlip nt = new TransactionDealSlip();
//        nt.setId(txnDealSlipTemp.getId());
//        nt.setBillNo(txnDealSlipTemp.getBillNo());
//        nt.setTxnId(txnDealSlipTemp.getTxnId());
//        nt.setTxnType(txnDealSlipTemp.getTxnType());
//        nt.setMarketRateUsd(txnDealSlipTemp.getMarketRateUsd());
//        nt.setDocRequired(txnDealSlipTemp.getDocRequired());
//        nt.setOpponentType(txnDealSlipTemp.getOpponentType());
//        nt.setOpponentCode(txnDealSlipTemp.getOpponentCode());
//        nt.setOpponentName(txnDealSlipTemp.getOpponentName());
//        nt.setDealerId(txnDealSlipTemp.getDealerId());
//        nt.setValueDate(txnDealSlipTemp.getValueDate());
//        //nt.setValueTime(txnDealSlipTemp.getVal);
//        nt.setIsTxnKcp(txnDealSlipTemp.getIsTxnKcp());
//        nt.setKcpCode(txnDealSlipTemp.getKcpCode());
//        nt.setBranch(txnDealSlipTemp.getBranch());
//        nt.setCcyCode(txnDealSlipTemp.getCcyCode());
//        nt.setCcyCondition(txnDealSlipTemp.getCcyCondition());
//        nt.setRateType(txnDealSlipTemp.getRateType());
//        nt.setExchangeRate(txnDealSlipTemp.getExchangeRate());
//        nt.setUseStockPpc(txnDealSlipTemp.getUseStockPpc());
//        nt.setSpecialTxn(txnDealSlipTemp.getSpecialTxn());
//        nt.setWithProvisi(txnDealSlipTemp.getWithProvisi());
//        nt.setProvisi(txnDealSlipTemp.getProvisi());
//        nt.setDocCode(txnDealSlipTemp.getDocCode());
//        nt.setPurposeDoc(txnDealSlipTemp.getPurposeDoc());
//        nt.setDocAdds(txnDealSlipTemp.getDocAdds());
//        nt.setDescription(txnDealSlipTemp.getDescription());
//        nt.setRecordStatus(txnDealSlipTemp.getRecordStatus());
//        nt.setTotalDenom(txnDealSlipTemp.getTotalDenom());
//        nt.setAmount(txnDealSlipTemp.getAmount());
//        nt.setCancelNote(txnDealSlipTemp.getCancelNote());
//        nt.setCetakCounter(txnDealSlipTemp.getCetakCounter());
//        nt.setTypeCetak(txnDealSlipTemp.getTypeCetak());
//        nt.setBillNoPotong(txnDealSlipTemp.getBillNoPotong());
//        nt.setIsTukerDenom(txnDealSlipTemp.getIsTukerDenom());
//        nt.setIsCorrection(txnDealSlipTemp.getIsCorrection());
//        nt.setStatusSerah(txnDealSlipTemp.getStatusSerah());
//        nt.setSerahOrigin(txnDealSlipTemp.getSerahOrigin());
//        nt.setPrepareDate(txnDealSlipTemp.getPrepareDate());
//        nt.setPreparePic(txnDealSlipTemp.getPreparePic());
//        nt.setCheckDate(txnDealSlipTemp.getCheckDate());
//        nt.setCheckPic(txnDealSlipTemp.getCheckPic());
//        nt.setReleaseDate(txnDealSlipTemp.getReleaseDate());
//        nt.setReleasePic(txnDealSlipTemp.getReleasePic());
//        nt.setReceiveDate(txnDealSlipTemp.getReceiveDate());
//        nt.setReceivePic(txnDealSlipTemp.getReceivePic());
//        nt.setOpponentAccNumber(txnDealSlipTemp.getOpponentAccNumber());
//        nt.setSettlementType(txnDealSlipTemp.getSettlementType());
//        nt.setTr88Rate(txnDealSlipTemp.getTr88Rate());
//        nt.setTr88RateOrg(getTr88RateOrg());
//        nt.setMarginModal(txnDealSlipTemp.getMarginModal());
//        nt.setWsidNo(getWsidNo());
//        nt.setCetakPic(getCetakPic());
//        nt.setCetakDate(getCetakDate());
//        nt.setBatalRequestPic(getBatalRequestPic());
//
//        List<TransactionDealSlipDetails> newdetails = new ArrayList<>();
//        for(TxnDealSlipTempDetails dt : txnDealSlipTemp.getTrxDealSlipDetails()) {
//            TransactionDealSlipDetails nd = new TransactionDealSlipDetails();
//            nd.setId(dt.getId());
//            nd.setTrxDealslip(nt);
//            nd.setBillNo(dt.getBillNo());
//            nd.setAmount(dt.getAmount());
//            nd.setTotalDenom(dt.getTotalDenom());
//            nd.setUseMargin(dt.getUseMargin());
//            nd.setCcyCode(dt.getCcyCode());
//            nd.setCcyCondition(dt.getCcyCondition());
//            nd.setDenom(dt.getDenom());
//            nd.setQuantity(dt.getQuantity());
//            newdetails.add(nd);
//        }
//        nt.setTrxDealSlipDetails(newdetails);
//        return nt;
//    }

    public TransactionDealSlip copyWithoutDetail(String newBillNo) {
        TransactionDealSlip t = copy(newBillNo);
        t.setTrxDealSlipDetails(new ArrayList<>());
        return t;
    }

    public BigDecimal getEq_usd() {
        return eq_usd;
    }

    public void setEq_usd(BigDecimal eq_usd) {
        this.eq_usd = eq_usd;
    }

    public String getOpponentAccNumber() {
        return opponentAccNumber;
    }

    public void setOpponentAccNumber(String opponentAccNumber) {
        this.opponentAccNumber = opponentAccNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public BigDecimal getMarketRateUsd() {
        return marketRateUsd;
    }

    public void setMarketRateUsd(BigDecimal marketRateUsd) {
        this.marketRateUsd = marketRateUsd;
    }

    public String getDocRequired() {
        return docRequired;
    }

    public void setDocRequired(String docRequired) {
        this.docRequired = docRequired;
    }

    public String getOpponentType() {
        return opponentType;
    }

    public void setOpponentType(String opponentType) {
        this.opponentType = opponentType;
    }

    public String getOpponentCode() {
        return opponentCode;
    }

    public void setOpponentCode(String opponentCode) {
        this.opponentCode = opponentCode;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public String getBongkaranDate() {
        return bongkaranDate;
    }

    public void setBongkaranDate(String bongkaranDate) {
        this.bongkaranDate = bongkaranDate;
    }

    public LocalTime getValueTime() {
        return valueTime;
    }

    public void setValueTime(LocalTime valueTime) {
        this.valueTime = valueTime;
    }

    public LocalDate getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(LocalDate trxDate) {
        this.trxDate = trxDate;
    }

    public String getIsTxnKcp() {
        return isTxnKcp;
    }

    public void setIsTxnKcp(String isTxnKcp) {
        this.isTxnKcp = isTxnKcp;
    }

    public String getKcpCode() {
        return kcpCode;
    }

    public void setKcpCode(String kcpCode) {
        this.kcpCode = kcpCode;
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

    public String getCcyCondition() {
        return ccyCondition;
    }

    public void setCcyCondition(String ccyCondition) {
        this.ccyCondition = ccyCondition;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUseStockPpc() {
        return useStockPpc;
    }

    public void setUseStockPpc(String useStockPpc) {
        this.useStockPpc = useStockPpc;
    }

    public String getSpecialTxn() {
        return specialTxn;
    }

    public void setSpecialTxn(String specialTxn) {
        this.specialTxn = specialTxn;
    }

    public String getWithProvisi() {
        return withProvisi;
    }

    public void setWithProvisi(String withProvisi) {
        this.withProvisi = withProvisi;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getPurposeDoc() {
        return purposeDoc;
    }

    public void setPurposeDoc(String purposeDoc) {
        this.purposeDoc = purposeDoc;
    }

    public String getDocAdds() {
        return docAdds;
    }

    public void setDocAdds(String docAdds) {
        this.docAdds = docAdds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public BigDecimal getTotalDenom() {
        return totalDenom;
    }

    public void setTotalDenom(BigDecimal totalDenom) {
        this.totalDenom = totalDenom;
    }

    public List<TransactionDealSlipDetails> getTrxDealSlipDetails() {
        return trxDealSlipDetails;
    }

    public void setTrxDealSlipDetails(List<TransactionDealSlipDetails> trxDealSlipDetails) {
        this.trxDealSlipDetails = trxDealSlipDetails;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    public String getTypeCetak() {
        return typeCetak;
    }

    public void setTypeCetak(String typeCetak) {
        this.typeCetak = typeCetak;
    }

    public String getBillNoPotong() {
        return billNoPotong;
    }

    public void setBillNoPotong(String billNoPotong) {
        this.billNoPotong = billNoPotong;
    }

    public Integer getCetakCounter() {
        return cetakCounter;
    }

    public void setCetakCounter(Integer cetakCounter) {
        this.cetakCounter = cetakCounter;
    }

    public BigDecimal getProvisi() {
        return provisi;
    }

    public void setProvisi(BigDecimal provisi) {
        this.provisi = provisi;
    }

    public BigDecimal getRateCounter() {
        return rateCounter;
    }

    public void setRateCounter(BigDecimal rateCounter) {
        this.rateCounter = rateCounter;
    }

    public String getIsTukerDenom() {
        return isTukerDenom;
    }

    public void setIsTukerDenom(String isTukerDenom) {
        this.isTukerDenom = isTukerDenom;
    }

    public String getIsCorrection() {
        return isCorrection;
    }

    public void setIsCorrection(String isCorrection) {
        this.isCorrection = isCorrection;
    }

    public String getStatusSerah() {
        return statusSerah;
    }

    public void setStatusSerah(String statusSerah) {
        this.statusSerah = statusSerah;
    }

    public String getSerahOrigin() {
        return serahOrigin;
    }

    public void setSerahOrigin(String serahOrigin) {
        this.serahOrigin = serahOrigin;
    }

    public LocalDateTime getPrepareDate() {
        return prepareDate;
    }

    public void setPrepareDate(LocalDateTime prepareDate) {
        this.prepareDate = prepareDate;
    }

    public String getPreparePic() {
        return preparePic;
    }

    public void setPreparePic(String preparePic) {
        this.preparePic = preparePic;
    }

    public LocalDateTime getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDateTime checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckPic() {
        return checkPic;
    }

    public void setCheckPic(String checkPic) {
        this.checkPic = checkPic;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasePic() {
        return releasePic;
    }

    public void setReleasePic(String releasePic) {
        this.releasePic = releasePic;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceivePic() {
        return receivePic;
    }

    public void setReceivePic(String receivePic) {
        this.receivePic = receivePic;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Double getTr88Rate() {
        return tr88Rate;
    }

    public void setTr88Rate(Double tr88Rate) {
        this.tr88Rate = tr88Rate;
    }

    public Double getMarginModal() {
        return marginModal;
    }

    public void setMarginModal(Double marginModal) {
        this.marginModal = marginModal;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public TxnPenyerahan getTxnPenyerahan() {
        return txnPenyerahan;
    }

    public BigDecimal getTotalDenomPrev() {
        return totalDenomPrev;
    }

    public void setTotalDenomPrev(BigDecimal totalDenomPrev) {
        this.totalDenomPrev = totalDenomPrev;
    }

    public void setTxnPenyerahan(TxnPenyerahan txnPenyerahan) {
        this.txnPenyerahan = txnPenyerahan;
    }

    public String getVaNo() {
        return vaNo;
    }

    public void setVaNo(String vaNo) {
        this.vaNo = vaNo;
    }

    public String getIdsStatus() {
        return idsStatus;
    }

    public void setIdsStatus(String idsStatus) {
        this.idsStatus = idsStatus;
    }

    public String getApprovalPic() {
        return approvalPic;
    }

    public void setApprovalPic(String approvalPic) {
        this.approvalPic = approvalPic;
    }

    public String getBatalPic() {
        return batalPic;
    }

    public void setBatalPic(String batalPic) {
        this.batalPic = batalPic;
    }

    public String getMtuRekening() {
        return mtuRekening;
    }

    public void setMtuRekening(String mtuRekening) {
        this.mtuRekening = mtuRekening;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public String getWsidNo() {
        return wsidNo;
    }

    public void setWsidNo(String wsidNo) {
        this.wsidNo = wsidNo;
    }

    public String getCetakPic() {
        return cetakPic;
    }

    public void setCetakPic(String cetakPic) {
        this.cetakPic = cetakPic;
    }

    public LocalDateTime getCetakDate() {
        return cetakDate;
    }

    public void setCetakDate(LocalDateTime cetakDate) {
        this.cetakDate = cetakDate;
    }

    public Double getTr88RateOrg() {
        return tr88RateOrg;
    }

    public void setTr88RateOrg(Double tr88RateOrg) {
        this.tr88RateOrg = tr88RateOrg;
    }

    public Boolean getFlagToleransi() {
        return flagToleransi;
    }

    public void setFlagToleransi(Boolean flagToleransi) {
        this.flagToleransi = flagToleransi;
    }

    public String getBatalRequestPic() {
        return batalRequestPic;
    }

    public void setBatalRequestPic(String batalRequestPic) {
        this.batalRequestPic = batalRequestPic;
    }

    public BigDecimal getTotalDenomAwal() {
        return totalDenomAwal;
    }

    public void setTotalDenomAwal(BigDecimal totalDenomAwal) {
        this.totalDenomAwal = totalDenomAwal;
    }

    public BigDecimal getTotalAmountAwal() {
        return totalAmountAwal;
    }

    public void setTotalAmountAwal(BigDecimal totalAmountAwal) {
        this.totalAmountAwal = totalAmountAwal;
    }

    public String getCorrectionId() {
        return correctionId;
    }

    public void setCorrectionId(String correctionId) {
        this.correctionId = correctionId;
    }
}
