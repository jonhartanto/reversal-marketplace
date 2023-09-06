package id.co.bca.bnos.batch.marketplacereversal.service;


import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import id.co.bca.bnos.batch.marketplacereversal.exception.ValidationException;
import id.co.bca.bnos.batch.marketplacereversal.integration.forex.ForexRequest;
import id.co.bca.bnos.batch.marketplacereversal.integration.forex.ForexResponse;
import id.co.bca.bnos.batch.marketplacereversal.integration.forex.ForexService;
import id.co.bca.bnos.batch.marketplacereversal.integration.forexSettlement.ForexSettlement;
import id.co.bca.bnos.batch.marketplacereversal.integration.forexSettlement.ForexSettlementResponse;
import id.co.bca.bnos.batch.marketplacereversal.integration.forexSettlement.ForexSettlementService;
import id.co.bca.bnos.batch.marketplacereversal.integration.ids.DAINInboundMessage;
import id.co.bca.bnos.batch.marketplacereversal.integration.ids.DAINTransferResponse;
import id.co.bca.bnos.batch.marketplacereversal.integration.ids.IDSAccountSummary;
import id.co.bca.bnos.batch.marketplacereversal.integration.ids.IDSService;
import id.co.bca.bnos.batch.marketplacereversal.integration.rate.Rate;
import id.co.bca.bnos.batch.marketplacereversal.integration.rate.RateService;
import id.co.bca.bnos.batch.marketplacereversal.model.*;
import id.co.bca.bnos.batch.marketplacereversal.model.Currency;
import id.co.bca.bnos.batch.marketplacereversal.repository.*;
import id.co.bca.bnos.batch.marketplacereversal.util.Constants;
import id.co.bca.bnos.batch.marketplacereversal.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TxnMarketplaceReversalService {

    private final Logger log = LoggerFactory.getLogger(TxnMarketplaceReversalService.class);
    private final String errMsgInvalidVa = "Data dengan VA tsb tidak diketemukan.";

    Branch branch;
    @Autowired
    TxnMarketplaceRepository txnMarketplaceRepository;

    @Autowired
    TransactionDealSlipRepository trxDealSlipRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    MarginRepository marginRepository;

    @Autowired
    GeneralParameterRepository generalParameterRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RateService rateService;

    @Autowired
    StockMarketplaceService stockMarketplaceService;

    @Autowired
    ForexSettlementService forexSettlementService;

    @Autowired
    ForexService forexService;

    @Autowired
    IDSService idsService;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    public boolean getFlagForBypassIds() {
        boolean isSendIDS = true;
        try {
            GeneralParameter paramByPassIDS = generalParameterRepository.findByTableAndItem("IDS_BYPASS", "IDS_BYPASS");
            for(GeneralParameterDetail d : paramByPassIDS.getDetails()) {
                if ( d.getKeyName().equalsIgnoreCase("STATUS") ) {
                    int value = Integer.parseInt(d.getValue());
                    if ( value == 1) {
                        isSendIDS = false;
                    } else {
                        isSendIDS = true;
                    }
                    break;
                }
            }
        } catch (Exception e) {
        }
        return isSendIDS;
    }

    private ForexResponse dbValas(String action, TransactionDealSlip paramTrx, String flagUnderlying, Customer nasabah)
            throws InterfacingException {

        LocalDateTime now = LocalDateTime.now();
        String tempDate = "" + paramTrx.getValueDate().getYear() +
                String.format("%02d", paramTrx.getValueDate().getMonthValue()) +
                String.format("%02d", paramTrx.getValueDate().getDayOfMonth());
        String tempTime = "" + now.getHour() + now.getMinute() + now.getSecond();

        ForexRequest forexReq = new ForexRequest();
        ForexResponse forexResponse = new ForexResponse();

        forexReq.transactionId = "DLV4";
        if(paramTrx.getSettlementType().equalsIgnoreCase("OTOMATIS"))
        {
            forexReq.identityNumber = paramTrx.getOpponentAccNumber();

            forexReq.identityType = "1";
        }
        else
        {
            forexReq.identityNumber = nasabah.getIdNumber();

            if(nasabah.getIdType().equalsIgnoreCase("KTP"))
            {
                forexReq.identityType = "3";
            }
            else if(nasabah.getIdType().equalsIgnoreCase("NPWP"))
            {
                forexReq.identityType = "5";
            }
            else if(nasabah.getIdType().equalsIgnoreCase("PASSPORT"))
            {
                forexReq.identityType = "6";
            }
        }

        if(action.equalsIgnoreCase("inquiry"))
        {
            forexReq.dealId = "";
            forexReq.dealPurpose = "";
            forexReq.applicationCode = "";
            forexReq.transactionDate = 0;
            forexReq.transactionTime = 0;
            forexReq.maturityDate = 0;
            forexReq.transactionType = "B";
            forexReq.sellCurrencyCode = "IDR";
            forexReq.buyCurrencyCode = paramTrx.getCcyCode();
            forexReq.sellAmount = Long.valueOf(0);
            forexReq.buyAmount = Long.valueOf(0);
            forexReq.branchCode = 0;
            forexReq.customerName = "";
            forexReq.rate = new BigDecimal(0).setScale(4);
            forexReq.action = "I";
            forexReq.flagUnderlyingDocument = "";
            forexReq.specialTransactionCode = "";
        }
        else if(action.equalsIgnoreCase("validate"))
        {
            forexReq.dealId = "";
            forexReq.dealPurpose = "";
            forexReq.applicationCode = "";
            forexReq.transactionDate = 0;
            forexReq.transactionTime = 0;
            forexReq.maturityDate = 0;
            forexReq.transactionType = "B";
            forexReq.sellCurrencyCode = "IDR";
            forexReq.buyCurrencyCode = paramTrx.getCcyCode();
            forexReq.sellAmount = paramTrx.getAmount().longValue();
            forexReq.buyAmount = paramTrx.getTotalDenom().longValue();
            forexReq.branchCode = 0;
            forexReq.customerName = "";
            forexReq.rate = new BigDecimal(0).setScale(4);
            forexReq.action = "V";
            forexReq.flagUnderlyingDocument = "";
            forexReq.specialTransactionCode = "";
        }
        else if(action.equalsIgnoreCase("add"))
        {
            forexReq.dealId = paramTrx.getBillNo().concat("    ");
            forexReq.dealPurpose = paramTrx.getPurposeDoc();
            forexReq.applicationCode = "B/N";
            forexReq.transactionDate = Integer.parseInt(tempDate);
            forexReq.transactionTime = Integer.parseInt(tempTime);
            forexReq.maturityDate = Integer.parseInt(tempDate);
            forexReq.transactionType = "B";
            forexReq.sellCurrencyCode = "IDR";
            forexReq.buyCurrencyCode = paramTrx.getCcyCode();
            forexReq.sellAmount = paramTrx.getAmount().longValue();
            forexReq.buyAmount = paramTrx.getTotalDenom().longValue();

            forexReq.branchCode = Integer.parseInt(branch.getCode());
            forexReq.customerName = paramTrx.getOpponentName();
            forexReq.rate = paramTrx.getExchangeRate().setScale(4, RoundingMode.DOWN);
            forexReq.action = "A";
            if(flagUnderlying.equalsIgnoreCase("Y"))
            {
                forexReq.flagUnderlyingDocument = "Y";
            }
            else{
                forexReq.flagUnderlyingDocument = "N";
            }

            forexReq.specialTransactionCode = "";
        }
        else if(action.equalsIgnoreCase("cancel"))
        {
            forexReq.dealId = paramTrx.getBillNo().concat("    ");
            forexReq.dealPurpose = paramTrx.getPurposeDoc();
            forexReq.applicationCode = "B/N";
            forexReq.transactionDate = Integer.parseInt(tempDate);
            forexReq.transactionTime = Integer.parseInt(tempTime);
            forexReq.maturityDate = Integer.parseInt(tempDate);
            forexReq.transactionType = "B";
            forexReq.sellCurrencyCode = "IDR";
            forexReq.buyCurrencyCode = paramTrx.getCcyCode();
            forexReq.sellAmount = paramTrx.getAmount().longValue();
            forexReq.buyAmount = paramTrx.getTotalDenom().longValue();

            forexReq.branchCode = Integer.parseInt(branch.getCode());
            forexReq.customerName = paramTrx.getOpponentName();
            forexReq.rate = paramTrx.getExchangeRate().setScale(4, RoundingMode.DOWN);
            forexReq.action = "D";
            if(flagUnderlying.equalsIgnoreCase("Y"))
            {
                forexReq.flagUnderlyingDocument = "Y";
            }
            else{
                forexReq.flagUnderlyingDocument = "N";
            }

            forexReq.specialTransactionCode = "";
        }

        forexResponse = forexService.dealValas(forexReq);

        return forexResponse;
    }

    public void reversal() throws Exception {

//        init(currentUserId);

//        String noVa,
//        String reverseIdr,
//        String reverseRate

        List<TxnMarketplace> dataMarketplace = txnMarketplaceRepository.getTxnMarketplace();


        for(int i=0;i<dataMarketplace.size();i++){

            TxnMarketplace txnMarketplace = dataMarketplace.get(i);

            CustomerAccount customerAccount = customerAccountRepository.findByAcctNumber(txnMarketplace.getCustomerAccountNo());

            Customer customer = customerRepository.findByCode(txnMarketplace.getCustomerAccountNo());

//            IDSAccountSummary accountSummary = requestCheckAccount(txnMarketplace, customerAccount);

            try{

                if ( dataMarketplace == null) {
                    throw new ValidationException(errMsgInvalidVa);
                }

                if ( txnMarketplace.getRecordStatus().equalsIgnoreCase("1x")) {
                    throw new ValidationException("Nota sudah direverse.");
                }

//        AppUser appUser = appUserRepository.findByUserIdIgnoreCase(currentUserId);
//        Branch branchLogin = branchRepository.findByCode(appUser.getBranch());
//        if ( branchLogin == null) {
//            throw new ValidationException("Branch tidak diketemukan.");
//        }


                IDSAccountSummary accountSummary = requestCheckAccount(txnMarketplace, customerAccount);

//                trx.setRecordStatus(Constants.TrxStatusCode.CETAK_NOTA);
//                trx.setTypeCetak(typeCetak);

//                if(trxAutoCoverKP!=null) {
//                    trxDealSlipRepository.save(trxAutoCoverKP);
//                }
//                trxDealSlipRepository.save(trx);

                // #start request DAIN
                log.info("[trx-release] start request to DAIN, account summary, acc_name:"+accountSummary.getAccountName()
                        + " acc_status:" + accountSummary.getAccountStatus()
                        + " acc_balance:" + accountSummary.getAvailableBalance());
                try {
                    log.info("call IDS service..");
                    DAINTransferResponse dainResponse = requestIDS(customer, accountSummary, txnMarketplace);
                    if (dainResponse != null
                            && dainResponse.getErrorSchema() != null
                            && dainResponse.getErrorSchema().getErrorCode() != null) {
//                        txnMarketplace.setIdsStatus(dainResponse.getErrorSchema().getErrorCode().equals("MC") ? "AUTO" : "MANUAL");
                    } else {
//                        txnMarketplace.setIdsStatus("MANUAL");
                    }
                } catch (Exception e) {
//                    txnMarketplace.setIdsStatus("MANUAL");
                }

                Margin margin = marginRepository.findByCcyCodeAndConditionAndMarginType(
                        txnMarketplace.getCcyCode(),
                        txnMarketplace.getCcyCond(),
                        Constants.MarginType.operational);

                if ( margin == null) {
                    throw new ValidationException("Margin tidak diketemukan.");
                }

                Rate rate = null;
                boolean isSendIds = getFlagForBypassIds();
                if ( isSendIds) {
                    try {
                        rate = rateService.getSingleRate(txnMarketplace.getCcyCode(),
                                "TR88",
                                txnMarketplace.getCcyCond(),
                                txnMarketplace.getBranch());
                    } catch (InterfacingException e) {
                        throw new ValidationException("Transaksi tidak dapat dilanjutkan, rate tr88 tidak dapat diambil.");
                    }
                } else {
                    rate = new Rate();
                    rate.buy = BigDecimal.valueOf(10000);
                    rate.sell = BigDecimal.valueOf(11000);
                }

                Branch branch = branchRepository.findByCode(txnMarketplace.getBranch());
                if ( branch == null) {
                    throw new ValidationException("Branch tidak diketemukan.");
                }
                long counterLastBranch = branch.getLastBillNumber();

                Currency currency = currencyRepository.findByCode(txnMarketplace.getCcyCode());
                if ( currency == null ) {
                    throw new ValidationException("Mata uang tidak diketemukan.");
                }

                LocalDateTime localDateTime = getDate();
                LocalDate localDate = localDateTime.toLocalDate();

                txnMarketplace.setReverseDate(localDateTime);
                txnMarketplace.setReverseAmountIdr(txnMarketplace.getReverseAmountIdr());
                txnMarketplace.setReverseRate(txnMarketplace.getReverseRate());
                txnMarketplace.setRecordStatus("1X");

//                Customer customer = customerRepository.findByCode(txnMarketplace.getCustomerIdNo());
                // start create nota deal slip
                TransactionDealSlip notaDealSlip = new TransactionDealSlip();
                notaDealSlip.setTxnId(UUID.randomUUID().toString());
                notaDealSlip.setBillNo(txnMarketplace.getBillNo());
                counterLastBranch++;
                notaDealSlip.setVaNo(txnMarketplace.getVaNo());
                notaDealSlip.setTxnType(txnMarketplace.getTxnType());
                notaDealSlip.setBranch(branch.getCode());
                notaDealSlip.setOpponentCode(txnMarketplace.getCustomerAccountNo());
                notaDealSlip.setOpponentType(Constants.OpponentType.NASABAH);
                notaDealSlip.setOpponentName(txnMarketplace.getCustomerName());
                notaDealSlip.setOpponentAccNumber(txnMarketplace.getCustomerAccountNo());
                notaDealSlip.setPurposeDoc(txnMarketplace.getTxnPurpose());
                notaDealSlip.setTrxDealSlipDetails(new ArrayList<>());
                notaDealSlip.setDealerId(null);
                notaDealSlip.setDocRequired(Constants.DocType.WITHOUT_DOC);
                notaDealSlip.setIsTxnKcp(Constants.Flag.NO);
                notaDealSlip.setValueDate(txnMarketplace.getExpiredDate());
                notaDealSlip.setSettlementType(Constants.SettlementType.OTOMATIS);

                notaDealSlip.setCcyCode(txnMarketplace.getCcyCode());
                notaDealSlip.setCcyCondition(txnMarketplace.getCcyCond());
                notaDealSlip.setAmount(txnMarketplace.getAmount());
                notaDealSlip.setTotalDenom(txnMarketplace.getTotalDenom());
                //nota.setRateType(Constants.RateType.RATE_COUNTER);
                notaDealSlip.setRateType(Constants.RateType.RATE_KHUSUS);
                notaDealSlip.setRateCounter(txnMarketplace.getRateTxn());
                notaDealSlip.setMarginModal(margin.getMarginSell().doubleValue());
                notaDealSlip.setExchangeRate(txnMarketplace.getRateTxn());
                notaDealSlip.setTr88Rate(txnMarketplace.getRateModal().doubleValue());
                notaDealSlip.setMarketRateUsd(txnMarketplace.getReverseRate());

//        nota.setTxnType(txnMarketplace.getTxnType());
//        nota.setBranch(branch.getCode());
//
//        nota.setId(UUID.randomUUID().toString());
//        nota.setAdminFeeAfter(txnMarketplace.getAdminFeeAfter());
//        nota.setAdminFeeBefore(txnMarketplace.getAdminFeeBefore());
//        nota.setAmount(txnMarketplace.getAmount());
//        nota.setAuthorizationCode(txnMarketplace.getAuthorizationCode());
//        nota.setBillNo(txnMarketplace.getBillNo());
//        counterLastBranch++;
//        nota.setBranch(txnMarketplace.getBranch());
//        nota.setCcyCode(txnMarketplace.getCcyCode());
//        nota.setCcyCond(txnMarketplace.getCcyCond());
//        nota.setChannel(txnMarketplace.getChannel());
//        nota.setCustomerAccountCis(txnMarketplace.getCustomerAccountCis());
//        nota.setCustomerAccountNo(txnMarketplace.getCustomerAccountNo());
//        nota.setCustomerAddress1(txnMarketplace.getCustomerAddress1());
//        nota.setCustomerAddress2(txnMarketplace.getCustomerAddress2());
//        nota.setCustomerBirthdate(txnMarketplace.getCustomerBirthdate());
//        nota.setCustomerIdNo(txnMarketplace.getCustomerIdNo());
//        nota.setCustomerIdType(txnMarketplace.getCustomerIdType());
//        nota.setCustomerName(txnMarketplace.getCustomerName());
//        nota.setCustomerNationality(txnMarketplace.getCustomerNationality());
//        nota.setCustomerZipCode(txnMarketplace.getCustomerZipCode());
//        nota.setDenom(txnMarketplace.getDenom());
//        nota.setDiscount(txnMarketplace.getDiscount());
//        nota.setDtPengambilan(txnMarketplace.getDtPengambilan());
//        nota.setEformReffNumber(txnMarketplace.getEformReffNumber());
//        nota.setEmail(txnMarketplace.getEmail());
//        nota.setExpiredDate(txnMarketplace.getExpiredDate());
//        nota.setFlagPerwakilan(txnMarketplace.getFlagPerwakilan());
//        nota.setFlagReverse(txnMarketplace.getFlagReverse());
//        nota.setFlagReverseReport(txnMarketplace.getFlagReverseReport());
//        nota.setComment(txnMarketplace.getComment());
//        nota.setKomisiBca(txnMarketplace.getKomisiBca());
//        nota.setKomisiMp(txnMarketplace.getKomisiMp());
//        nota.setMerchantId(txnMarketplace.getMerchantId());
//        nota.setPaymentDate(txnMarketplace.getPaymentDate());
//        nota.setPaymentLimitTime(txnMarketplace.getPaymentLimitTime());
//        nota.setPerwakilan(txnMarketplace.getPerwakilan());
//        nota.setPerwakilanId(txnMarketplace.getPerwakilanId());
//        nota.setPerwakilanIdType(txnMarketplace.getPerwakilanIdType());
//        nota.setPerwakilanName(txnMarketplace.getPerwakilanName());
//        nota.setPerwakilanUpdated(txnMarketplace.getPerwakilanUpdated());
//        nota.setPhoneNumber(txnMarketplace.getPhoneNumber());
//        nota.setPurposeDetail(txnMarketplace.getPurposeDetail());
//        nota.setRateModal(txnMarketplace.getRateModal());
//        nota.setRateTxn(txnMarketplace.getRateTxn());
//        nota.setRating(txnMarketplace.getRating());
//        nota.setRatingDate(txnMarketplace.getRatingDate());
//        nota.setRecordStatus(txnMarketplace.getRecordStatus());
//        nota.setReverseAmountIdr(txnMarketplace.getReverseAmountIdr());
//        nota.setReverseDate(txnMarketplace.getReverseDate());
//        nota.setReverseRate(txnMarketplace.getReverseRate());
//        nota.setSelisihRate(txnMarketplace.getSelisihRate());
//        nota.setTotalAmount(txnMarketplace.getTotalAmount());
//        nota.setTotalDenom(txnMarketplace.getTotalDenom());
//        nota.setTransactionDate(txnMarketplace.getTransactionDate());
//        nota.setTxnPurpose(txnMarketplace.getTxnPurpose());
//        nota.setTxnType(txnMarketplace.getTxnType());
//        nota.setUniqueIdentifier(txnMarketplace.getUniqueIdentifier());
//        nota.setUserId(txnMarketplace.getUserId());
//        nota.setValueDate(txnMarketplace.getValueDate());
//        nota.setVaNo(txnMarketplace.getVaNo());

                // start create new nota marketplace
                TxnMarketplace newNotaMarketplace = txnMarketplace.copy(Tools.generateNotaNumberC(txnMarketplace.getBranch(),
                        counterLastBranch, localDate));
                counterLastBranch++;
                newNotaMarketplace.setVaNo(newNotaMarketplace.getVaNo() + "_" + counterLastBranch);
                newNotaMarketplace.setTxnType(Constants.DealSlipTrxType.BUY);
                newNotaMarketplace.setRecordStatus(Constants.TrxStatusCode.CETAK_NOTA);
                newNotaMarketplace.setRateTxn( currency.getRateBC06Buy() );
                newNotaMarketplace.setMargin(0d);
                newNotaMarketplace.setValueDate(localDateTime);

                // calculate new nota marketplace
                double totalAmount = 0;
                for(TxnMarketplaceDetails d : newNotaMarketplace.getTxnMarketplaceDetailsList()) {
                    totalAmount += (newNotaMarketplace.getRateTxn().doubleValue()
                            + newNotaMarketplace.getMargin().doubleValue()) * d.getTotalDenom().doubleValue();
                    d.setUseMargin( new BigDecimal(newNotaMarketplace.getMargin()) );
                    d.setAmount( new BigDecimal(totalAmount) );
                }
                newNotaMarketplace.setAmount( new BigDecimal(totalAmount) );

                // start create nota autocover
                TransactionDealSlip notaAutocover = new TransactionDealSlip();
                notaAutocover.setTxnId(UUID.randomUUID().toString());
                notaAutocover.setBillNo(Tools.generateNotaNumberC(branch.getCode(), counterLastBranch, localDate));
                counterLastBranch++;
                notaAutocover.setVaNo(txnMarketplace.getVaNo());
                //notaAutocover.setTxnType(Constants.DealSlipTrxType.BUY);
                notaAutocover.setTxnType(Constants.DealSlipTrxType.SELL);
                notaAutocover.setCcyCode(txnMarketplace.getCcyCode());
                notaAutocover.setCcyCondition(txnMarketplace.getCcyCond());

                //notaAutocover.setMarginModal( txnMarketplace.getMargin() );
                //notaAutocover.setExchangeRate( txnMarketplace.getRateTxn() );
                notaAutocover.setAmount(txnMarketplace.getAmount());
                notaAutocover.setTotalDenom(txnMarketplace.getTotalDenom());
                notaAutocover.setTr88Rate( rate.buy.doubleValue() );
                notaAutocover.setTr88RateOrg(rate.buy.doubleValue() );
                notaAutocover.setMarginModal( margin.getMarginBuy().doubleValue() );
                notaAutocover.setExchangeRate( rate.buy.add(margin.getMarginBuy()) );
                notaAutocover.setRateType(Constants.RateType.RATE_KHUSUS);

                notaAutocover.setMarketRateUsd( txnMarketplace.getReverseRate() );
                notaAutocover.setRateCounter( txnMarketplace.getRateModal() );
                notaAutocover.setDealerId(null);
                notaAutocover.setDocRequired(Constants.DocType.WITHOUT_DOC);
                notaAutocover.setIsTxnKcp(Constants.Flag.NO);
                notaAutocover.setValueDate(localDateTime.toLocalDate());
                notaAutocover.setValueTime(localDateTime.toLocalTime());
                notaAutocover.setTrxDealSlipDetails(new ArrayList<>());
                notaAutocover.setRecordStatus(Constants.TrxStatusCode.CETAK_NOTA);
                notaAutocover.setSettlementType(Constants.SettlementType.REVERSAL_EBRANCH);

                // assign detail
                totalAmount = 0;
                notaAutocover.setTrxDealSlipDetails(new ArrayList<>());
                for(TxnMarketplaceDetails d : newNotaMarketplace.getTxnMarketplaceDetailsList() ) {
                    TransactionDealSlipDetails tsd = new TransactionDealSlipDetails();
                    tsd.setTotalDenom(d.getTotalDenom());
                    tsd.setDenom(d.getDenom());
                    tsd.setQuantity(d.getQuantity());
                    tsd.setUseMargin( BigDecimal.valueOf(notaAutocover.getMarginModal()) );
                    tsd.setCcyCondition(d.getCcyCond());
                    tsd.setCcyCode(d.getCcyCode());
                    tsd.setTrxDealslip(notaAutocover);
                    tsd.setBillNo(notaAutocover.getBillNo());

                    BigDecimal amount = notaAutocover.getExchangeRate().multiply( d.getTotalDenom() );
                    tsd.setAmount(amount);
                    totalAmount += amount.doubleValue();

                    notaAutocover.getTrxDealSlipDetails().add(tsd);
                }
                notaAutocover.setAmount(BigDecimal.valueOf(totalAmount));

                // // save nota autocover
                // trxDealSlipRepository.save(notaAutocover);

                // // save nota ebranch
                // txnEBranchRepository.save(newNotaBranch);

                // save txnMarketplace
                txnMarketplaceRepository.save(txnMarketplace);

                stockMarketplaceService.checkStock(txnMarketplace);
                stockMarketplaceService.updateStock(txnMarketplace);

                // save nota marketplace
                txnMarketplaceRepository.save(newNotaMarketplace);

                stockMarketplaceService.checkStock(newNotaMarketplace);
                stockMarketplaceService.updateStock(newNotaMarketplace);

                // save nota autocover
                notaAutocover.setTotalDenomAwal(notaAutocover.getTotalDenom());
                notaAutocover.setTotalAmountAwal(notaAutocover.getAmount());
                trxDealSlipRepository.save(notaAutocover);

//        stockService.checkStock(notaAutocover);
//        stockService.updateStock(notaAutocover);

                //boolean isSendIds = getFlagForBypassIds();

                ForexSettlementResponse response = new ForexSettlementResponse();
                try {
                    ForexSettlement forexReq2 = new ForexSettlement();
                    forexReq2.transactionId = "DLW4";
                    forexReq2.dealId = notaDealSlip.getBillNo().concat("    ");
                    forexReq2.applicationCode = "B/N";
                    forexReq2.transactionType = "R";
                    forexReq2.accountNumber = notaDealSlip.getOpponentAccNumber();
                    forexReq2.amount = notaDealSlip.getAmount();
                    log.info("[trx-release] start request DLW4(Kredit)");
                    response = forexSettlementService.settlementValas(forexReq2);
                    if(!response.errorSchema.errorCode.equals("00"))
                    {
                        throw new RuntimeException(response.errorSchema.errorCode + ": " +
                                response.errorSchema.errorMessage.get("indonesian"));
                    }
                } catch (InterfacingException e) {
                    log.error("[trx-release] failed response DLW4(Debit), msg:" + e.getMessage());
                }

                if ( isSendIds) {
                    try{
                        ForexResponse forexResponse = dbValas("cancel", notaDealSlip, "", customer);
                        if(!forexResponse.errorSchema.errorCode.equals("C$"))
                        {
                            throw new RuntimeException(forexResponse.errorSchema.errorCode + ": "
                                    + forexResponse.errorSchema.errorMessage.get("indonesian"));
                        }
                    } catch (Exception e){
                        throw new RuntimeException(e.getMessage());
                    }
                }

                branch.setLastBillNumber(counterLastBranch);
                branchRepository.save(branch);

            }catch (ValidationException e){            {
                log.info("Failed to process data");
                e.printStackTrace();
            }
        }}

//        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String txnDate = dataMarketplace.getTrxDate().format(formatDate);
//        String valueDate = localDate.format(formatDate);
//        formatDate = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String txnTime = dataMarketplace.getTrxDate().format(formatDate);
//        trxEbranchInfoServices.eaiService.pushNotif(noVa,
//                dataMarketplace.getBranch(), branch.getName(),
//                txnDate, txnTime, "EXPIRED",
//                valueDate,
//                reverseRate);

//        return ResponseEntity.ok(dataMarketplace);
    }

    private DAINTransferResponse requestIDS(Customer customer,
                                            IDSAccountSummary accountSummary,
                                            TxnMarketplace trx) throws Exception {

        if ( customer.getCustAcct() == null || customer.getCustAcct().size() <= 0) {
            throw new Exception("Invalid account");
        }

        if ( trx.getCustomerAccountNo() == null || trx.getCustomerAccountNo().length() <= 0) {
            throw new Exception("Invalid account number.");
        }

        CustomerAccount customerAccount = null;
        for(Iterator<CustomerAccount> it=customer.getCustAcct().iterator(); it.hasNext();) {
            CustomerAccount ca = it.next();
            if ( ca.getAcctNumber()!=null && ca.getAcctNumber().equalsIgnoreCase(trx.getCustomerAccountNo())) {
                customerAccount = ca;
            }
        }

        if ( customerAccount == null) {
            throw new Exception("Invalid account number.");
        }

        DAINInboundMessage data = new DAINInboundMessage();
        data.setTxnId("DAIN");
        data.setTermId("95150");
        data.setTxnCode("806");

        List<String> trailers = new ArrayList<String>();
        if ( trx.getTxnType().equalsIgnoreCase(Constants.DealSlipTrxType.SELL)) {
            trailers.add("OTOCR " + trx.getBranch() + "JUAL    ");
        } else {
            trailers.add("OTODB " + trx.getBranch() + "BELI    ");
        }

        String amountStr = idsService.formatAmount(trx.getAmount());
        data.setLogType("L");
        data.setAcctNo(trx.getCustomerAccountNo());
        data.setAmtChar(amountStr);
        data.setRefNo(trx.getBillNo());
        data.setCurrCode(accountSummary.getCurrencyCode());
        data.setBusnDate(idsService.formatDate(trx.getTransactionDate()));
        data.setBenefBrch(trx.getBranch());
        data.setKursChar(idsService.formatKurs(trx.getReverseRate()));

        String refNo = idsService.formatDainRefNo(trx.getBillNo());
        data.setRefNo(refNo);

        trailers.add("@"+ trx.getBillNo());
        trailers.add("@PPCBCA-BANKNOTES");
        trailers.add("@" + customerAccount.getAcctCcy() + amountStr.substring(3));
        trailers.add("");

        data.setTrailers(trailers);

        data.setUserId("SME"+customer.getCabang());//cabang
        Map<String, Long> infoMap = new HashMap<String, Long>();
        infoMap.put("control_number", 0L);
        data.setInfo(infoMap);

        // DAINTransferRequest transfer = new DAINTransferRequest();
        // transfer.setInboundMessage(data);

        log.info("Call Transfer DAIN..");
        DAINTransferResponse transferResponse = idsService.transferbyDAIN(data);
        if(transferResponse.getErrorSchema().getErrorCode().equals("MTV") &&
                transferResponse.getErrorSchema().getErrorMessage().get("indonesian").equals("TRANSAKSI TIDAK AD"))
        {
            data.setLogType("L");

            transferResponse = idsService.transferbyDAIN(data);

        }
        else{
            transferResponse = null;
        }

        return transferResponse;
    }

    private IDSAccountSummary requestCheckAccount(TxnMarketplace trx, CustomerAccount customerAccount) throws Exception {
        String accountNumber = trx.getCustomerAccountNo();
        try{
            IDSAccountSummary accountSummary = idsService.getAccountSummary(accountNumber);

            if ( accountSummary.getAccountStatus().getOpenIndicator().trim().equals("")
                    && accountSummary.getAccountStatus().getDormantStatus().trim().equals("")
                    && !accountSummary.getAccountStatus().getPostIndicator().trim().equalsIgnoreCase("T")) {

                if ( trx.getTxnType().equalsIgnoreCase(Constants.DealSlipTrxType.SELL)) {
                    // validation customer balance
                    try {
                        log.info("availbale balance : " + accountSummary.getAvailableBalance());
                        log.info("total amount : " + trx.getAmount().toString());
                        log.info("total valas : " + trx.getTotalDenom().toString());

                        BigDecimal availabeBalance = idsService.parseAmount(accountSummary.getAvailableBalance());

                        log.info("availbale balance : " + availabeBalance.toString());

                        if(customerAccount.getAcctCcy() != null && !customerAccount.getAcctCcy().equals(""))
                        {
                            if(customerAccount.getAcctCcy().equalsIgnoreCase("IDR"))
                            {
                                if(availabeBalance.compareTo(trx.getAmount()) < 0)
                                {
                                    throw new Exception();
                                }
                            }
                            else
                            {
                                if(availabeBalance.compareTo(trx.getTotalDenom()) < 0)
                                {
                                    throw new Exception();
                                }
                            }
                        }

                    } catch (Exception e) {
                        throw new Exception("Insufficent balance.");
                    }
                }

                return accountSummary;
            } else {
                throw new Exception("Account is not open status.");
            }
        }
        catch (Exception e){
            // throw new Exception("Rekening tidak dapat digunakan untuk transaksi.");
            throw new Exception(e.getMessage());
        }
    }

    public LocalDateTime getDate() throws ValidationException {
        boolean found = false;
        LocalDateTime datetimeNow = LocalDateTime.now();
        GeneralParameter generalParamDate = generalParameterRepository.findByTableAndItem("APPDATE", "TGL_APLIKASI");
        // set value date
        for (Iterator<GeneralParameterDetail> it = generalParamDate.getDetails().iterator(); it.hasNext(); ) {
            GeneralParameterDetail d = it.next();
            if (d.getKeyName().equalsIgnoreCase("TODAY_DATE")
                    && d.getValue() != null && d.getValue().trim().length() > 0 ) {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String tempTime = sdf.format(new Date());

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                datetimeNow = LocalDateTime.parse(d.getValue() + " " + tempTime, df);
                found = true;
            }
        }
        if ( !found ) {
            throw new ValidationException("PERIKSA TGL. JATUH TEMPO");
        }
        return datetimeNow;
    }


}
