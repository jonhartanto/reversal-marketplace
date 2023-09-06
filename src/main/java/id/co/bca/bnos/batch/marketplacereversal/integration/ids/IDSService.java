package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import id.co.bca.bnos.batch.marketplacereversal.integration.oauth.OAuthResponse;
import id.co.bca.bnos.batch.marketplacereversal.integration.oauth.OAuthService;
import id.co.bca.bnos.batch.marketplacereversal.model.IDSTransfer;
import id.co.bca.bnos.batch.marketplacereversal.service.IDSTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class IDSService {
    private static final Logger LOG = LoggerFactory.getLogger(IDSService.class);

    @Value("${bnos.interface.ids.account.service.uri}")
    private String accountServiceUri;
    
    @Value("${bnos.interface.ids.transfer.service.uri}")
    private String transferServiceUri;

    @Value("${bnos.interface.ids.transfer.dain.service.uri}")
    private String transferDAINServiceUri;

    @Value("${bnos.interface.ids.transfer.dain.oauth.client-id}")
    private String clientId;

    @Value("${bnos.interface.ids.account.client-id}")
    private String clientIdAccount;

    @Value("${bnos.interface.ids.transfer.dain.oauth.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;
    private final OAuthService oAuthService;

    private IDSTransferService idsTransferService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public IDSService(RestTemplate restTemplate, OAuthService oAuthService, IDSTransferService idsTransferService) {
        this.restTemplate = restTemplate;
        this.oAuthService = oAuthService;
        this.idsTransferService = idsTransferService;
    }

    public IDSAccountSummary getAccountSummary(String accountNumber) throws InterfacingException {
        IDSAccountSummary accountSummary = null;
        String summaryUri = accountServiceUri + "/account-numbers/{account_number}/details";

        String uuidAsString = "";

        UUID uuid = UUID.randomUUID();
        uuidAsString = uuid.toString().replace("-", "");

        LocalDateTime curDateTime = LocalDateTime.now();

        DateTimeFormatter valDateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-ddHH:mm:ss");
        String strAppDate = curDateTime.format(valDateFormatter);
        strAppDate = strAppDate.replace("-", "").replace(":", "");

        uuidAsString = strAppDate + uuidAsString.substring(0, 18);
        
        // URL param
        System.out.println(accountNumber + "IDS SERVICE 1");
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("account_number", accountNumber);

        // Query param
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(summaryUri);

        // Get OAuth token
        OAuthResponse authResponse = oAuthService.getAccessToken(clientId, clientSecret);
        String accessToken = authResponse.accessToken;

        // String path = builder.build().getPath();
        // String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
        // String signature = sign("GET", path, accessToken, "", timestamp);
        System.out.println(accountNumber + "IDS SERVICE 2");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("client-id", clientIdAccount);
        headers.add("channel-transaction-id", "BANKNOTE-"+ uuidAsString);
        // headers.set("Authorization", "Bearer " + accessToken);
        // headers.add("X-BCA-Key", apiKey);
        // headers.add("X-BCA-Timestamp", timestamp);
        // headers.add("X-BCA-Signature", signature);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<IDSOutputResponse> response = null;
        try {
            System.out.println(accountNumber + "IDS SERVICE 3");
            response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, httpEntity,
            IDSOutputResponse.class);
            accountSummary = response.getBody().outputSchema;

            LOG.debug("acct.no= {}", accountSummary.getAccountNumber());
            LOG.debug("acct.ccy= {}", accountSummary.getCurrencyCode());
            LOG.debug("acct.available_balance= {}", accountSummary.getAvailableBalance());

        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            IDSOutputResponse errorResponse = null;
            try {
                errorResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), IDSOutputResponse.class);
                LOG.debug("response.errorCode = {}", errorResponse.errorSchema.getErrorCode());
                LOG.debug("response.errorMessage.indonesian = {}", errorResponse.errorSchema.getErrorMessage().get("indonesian"));
                LOG.debug("response.errorMessage.english = {}", errorResponse.errorSchema.getErrorMessage().get("english"));
                throw new InterfacingException(
                        errorResponse.errorSchema.getErrorCode() + " " + errorResponse.errorSchema.getErrorMessage().get("english"));
            } catch (JsonMappingException je) {
                LOG.error("Gagal mencari account details.", je);
                // throw new InterfacingException(je.getMessage());
                throw new InterfacingException("Gagal mencari account details.");
            } catch (JsonProcessingException pe) {
                LOG.error("Gagal mencari account details.", pe);
                // throw new InterfacingException(pe.getMessage());
                throw new InterfacingException("Gagal mencari account details.");
            }
        } catch (RestClientException e) {
            LOG.error("Call IDS service failed", e);
            throw new InterfacingException(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
        return accountSummary;
    }

    public IDSAccountStatus getAccountStatus(String accountNumber) throws InterfacingException {
        IDSAccountStatus accountStatus = null;
        String summaryUri = accountServiceUri + "/{account_no}/status";

        // URL param
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("account_no", accountNumber);

        // Query param
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(summaryUri);

        try {
            // Get OAuth token
            OAuthResponse authResponse = oAuthService.getAccessToken();
            String accessToken = authResponse.accessToken;

            String path = builder.build().getPath();
            // String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
            // String signature = sign("GET", path, accessToken, "", timestamp);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            // headers.add("X-BCA-Key", apiKey);
            // headers.add("X-BCA-Timestamp", timestamp);
            // headers.add("X-BCA-Signature", signature);

            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<IDSAccountStatus> response = null;

            response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, httpEntity,
                    IDSAccountStatus.class);
            accountStatus = response.getBody();

            LOG.debug("acct.no = {}", accountStatus.accountNo);
            LOG.debug("acct.ccy = {}", accountStatus.accountName);

        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            IDSErrorResponse errorResponse = null;
            try {
                errorResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), IDSErrorResponse.class);
                LOG.debug("response.errorCode = {}", errorResponse.getErrorCode());
                LOG.debug("response.errorMessage.indonesian = {}", errorResponse.getErrorMessage().get("indonesian"));
                LOG.debug("response.errorMessage.english = {}", errorResponse.getErrorMessage().get("english"));
                throw new InterfacingException(
                        errorResponse.getErrorCode() + " " + errorResponse.getErrorMessage().get("english"));
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
        } catch (RestClientException e) {
            LOG.error("Call IDS service error", e);
            throw new InterfacingException(e.getMessage());
        } catch (InterfacingException e) {
            // TODO Auto-generated catch block
            throw e;
        }
        return accountStatus;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public IDSTransferResponse transfer(IDSTransferRequest request) throws InterfacingException {
        IDSTransferResponse transferResponse = null;
        IDSTransfer idsTransfer = null;
        String path = transferServiceUri + "/one-account";

        try {
            // get ids transfer from log
            idsTransfer = idsTransferService.getIDSTransfer(request);
            if (idsTransfer == null) {
                idsTransfer = logTransfer(request);
            } else if (idsTransfer.getActive()) {
                transferResponse = new IDSTransferResponse();
                transferResponse.reference = idsTransfer.getResRef();
                transferResponse.logType = idsTransfer.getResLogType();
                transferResponse.delimeter = idsTransfer.getResDelimeter();
                transferResponse.add_inf = idsTransfer.getResAddInf();
                transferResponse.sharp1 = idsTransfer.getResSharp1();
                transferResponse.sharp2 = idsTransfer.getResSharp2();

            	LOG.debug("IDS Transfer Bypass, ID = {}", idsTransfer.getId()) ;
                return transferResponse;
            }

            try {
                LOG.debug("JSON to be sent: " + new ObjectMapper().writeValueAsString(request));
            } catch (Exception e) {
            }

            // Get OAuth token
            OAuthResponse authResponse = oAuthService.getAccessToken();
            String accessToken = authResponse.accessToken;

            // String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
            // String requestBody = new ObjectMapper().writeValueAsString(request);
            // String signature = sign("POST", path, accessToken, requestBody, timestamp);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            // headers.add("X-BCA-Key", apiKey);
            // headers.add("X-BCA-Timestamp", timestamp);
            // headers.add("X-BCA-Signature", signature);

            HttpEntity<?> httpEntity = new HttpEntity<IDSTransferRequest>(request, headers);
            ResponseEntity<IDSTransferResponse> response = null;
            response = restTemplate.exchange(path, HttpMethod.POST, httpEntity, IDSTransferResponse.class);
            transferResponse = response.getBody();

            LOG.debug("transferResponse.reference = {}", transferResponse.reference);
            LOG.debug("transferResponse.add_inf = {}", transferResponse.add_inf);

            transferResponse.reference = idsTransfer.getResRef();
            transferResponse.logType = idsTransfer.getResLogType();

            String busnChar = request.getBusnDate();
            LocalDate busnDate = parseDate(busnChar);
            idsTransfer.setBusnDate(busnDate);
            idsTransfer.setBusnChar(busnChar);
            idsTransfer.setResRef(transferResponse.reference);
            idsTransfer.setResLogType(transferResponse.logType);
            idsTransfer.setResDelimeter(transferResponse.delimeter);
            idsTransfer.setResAddInf(transferResponse.add_inf);
            idsTransfer.setResSharp1(transferResponse.sharp1);
            idsTransfer.setResSharp2(transferResponse.sharp2);
            idsTransfer.setReleaseFlag(true);

        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            IDSErrorResponse errorResponse = null;
            try {
                errorResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), IDSErrorResponse.class);
                String errorCode = errorResponse.getErrorCode();
                String errorMsgId = (String) errorResponse.getErrorMessage().get("indonesian");
                String errorMsgEn = (String) errorResponse.getErrorMessage().get("english");

                LOG.debug("response.errorCode = {}", errorCode);
                LOG.debug("response.errorMessage.indonesian = {}", errorMsgId);
                LOG.debug("response.errorMessage.english = {}", errorMsgEn);

                idsTransfer.setErrorCode(errorCode);
                idsTransfer.setErrorMsgId(errorMsgId);
                idsTransfer.setErrorMsgEn(errorMsgEn);
                idsTransfer.setReleaseFlag(false);

                throw new InterfacingException(
                        errorResponse.getErrorCode() + " " + errorResponse.getErrorMessage().get("english"));
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
        } catch (RestClientException e) {
            LOG.error("Call IDS service error", e);
            throw new InterfacingException(e.getMessage());
        } finally {
            idsTransferService.update(idsTransfer.getId(), idsTransfer);
            LOG.debug("Update IDS tranfer log ID = {}", idsTransfer.getId());
        }
        return transferResponse;
    }

    // @Transactional
    public IDSTransferResponse debit(IDSTrfDebitRequest debitRequest) throws InterfacingException {
        IDSTransferRequest trfRequest = new IDSTransferRequest();

        String acctNo = debitRequest.getAcctNo();
        String currCode = debitRequest.getCurrency();

        LocalDate valueDate = debitRequest.getValueDate();
        String irNumber = debitRequest.getIrNumber();
        String refNo = formatRefNo(valueDate, irNumber);

        LocalDateTime appDate = debitRequest.getAppDate();
        String strAppDate = formatDate(appDate);

        BigDecimal amount = debitRequest.getAmount();
        String strAmount = formatAmount(amount);

        String irType = debitRequest.getIrType();
        String trailer1 = irType + irNumber + "AUTODB-IR";

        String irRefNo = debitRequest.getRefNo();
        String irRefNo1 = (irRefNo.length() > 0) ? irRefNo.substring(0, Math.min(10, irRefNo.length())) : "";
        String irRefNo2 = (irRefNo.length() > 10) ? irRefNo.substring(10, 10 + Math.min(6, irRefNo.length() - 10)) : "";

        String benCust = debitRequest.getBenCust();
        String benCust2 = (benCust.length() > 0) ? benCust.substring(0, Math.min(17, benCust.length())) : "";

        String trailer2 = "S103" + "@" + irRefNo1;
        String trailer3 = irRefNo2 + "@" + irType + irNumber;
        String trailer4 = "@" + benCust2;
        String trailer5 = "";
        String trailer6 = "";

        trfRequest.setTxnId("SMEK");
        trfRequest.setTxnCode("828");
        trfRequest.setAcctNo(acctNo);
        trfRequest.setCurrCode(currCode);
        trfRequest.setBenefBrch("0998");
        trfRequest.setRefNo(refNo);
        trfRequest.setTermId("95032");
        trfRequest.setLogType("L");
        trfRequest.setBusnDate(strAppDate);
        trfRequest.setAmtChar(strAmount);
        trfRequest.setKursChar("000000100");
        trfRequest.setTrailer1(trailer1);
        trfRequest.setTrailer2(trailer2);
        trfRequest.setTrailer3(trailer3);
        trfRequest.setTrailer4(trailer4);
        trfRequest.setTrailer5(trailer5);
        trfRequest.setTrailer6(trailer6);
        trfRequest.setUserId("SME90002");

        trfRequest.setAcctName(debitRequest.getAcctName());
        trfRequest.setSrcApp(debitRequest.getSrcApp());

        IDSTransferResponse response = transfer(trfRequest);

        return response;
    }

    // @Transactional
    public IDSTransferResponse creditVostro(IDSTrfCreditVostroRequest creditVostroRequest) throws InterfacingException {
        IDSTransferRequest trfRequest = new IDSTransferRequest();

        String acctNo = creditVostroRequest.getAcctNo();
        String currCode = creditVostroRequest.getCurrency();

        String devisaBranch = creditVostroRequest.getDevisaBranch();

        LocalDate valueDate = creditVostroRequest.getValueDate();
        String irNumber = creditVostroRequest.getIrNumber();
        String refNo = formatRefNo(valueDate, irNumber);

        LocalDateTime appDate = creditVostroRequest.getAppDate();
        String strAppDate = formatDate(appDate);

        BigDecimal amount = creditVostroRequest.getAmount();
        String strAmount = formatAmount(amount);

        String irType = creditVostroRequest.getIrType();
        String trailer1 = irType + irNumber + "AUTOCR-IR";

        String irRefNo = creditVostroRequest.getRefNo();
        String irRefNo1 = (irRefNo.length() > 0) ? irRefNo.substring(0, Math.min(13, irRefNo.length())) : "";
        String irRefNo2 = (irRefNo.length() > 13) ? irRefNo.substring(13, 13 + Math.min(3, irRefNo.length() - 13)) : "";

        String bicSender = creditVostroRequest.getBicSender();

        String trailer2 = "NTRF" + "@" + irRefNo1;
        String trailer3 = irRefNo2 + "@" + irType + irNumber;
        String trailer4 = "@YR CV" + bicSender;
        String trailer5 = "";
        String trailer6 = "";

        trfRequest.setTxnId("SMEK");
        trfRequest.setTxnCode("827");
        trfRequest.setAcctNo(acctNo);
        trfRequest.setCurrCode(currCode);
        trfRequest.setBenefBrch(devisaBranch);
        trfRequest.setRefNo(refNo);
        trfRequest.setTermId("95032");
        trfRequest.setLogType("L");
        trfRequest.setBusnDate(strAppDate);
        trfRequest.setAmtChar(strAmount);
        trfRequest.setKursChar("000000100");
        trfRequest.setTrailer1(trailer1);
        trfRequest.setTrailer2(trailer2);
        trfRequest.setTrailer3(trailer3);
        trfRequest.setTrailer4(trailer4);
        trfRequest.setTrailer5(trailer5);
        trfRequest.setTrailer6(trailer6);
        trfRequest.setUserId("SME90002");

        trfRequest.setAcctName(creditVostroRequest.getAcctName());
        trfRequest.setSrcApp(creditVostroRequest.getSrcApp());

        IDSTransferResponse response = transfer(trfRequest);

        return response;
    }

    // @Transactional
    public IDSTransferResponse credit(IDSTrfCreditRequest creditRequest) throws InterfacingException {
        IDSTransferRequest trfRequest = new IDSTransferRequest();

        String acctNo = creditRequest.getAcctNo();
        String currCode = creditRequest.getCurrency();

        String devisaBranch = creditRequest.getDevisaBranch();

        LocalDate valueDate = creditRequest.getValueDate();
        String irNumber = creditRequest.getIrNumber();
        String refNo = formatRefNo(valueDate, irNumber);

        LocalDateTime appDate = creditRequest.getAppDate();
        String strAppDate = formatDate(appDate);

        BigDecimal amount = creditRequest.getAmount();
        String strAmount = formatAmount(amount);

        String irType = creditRequest.getIrType();
        String trailer1 = irType + irNumber + "AUTOCR-IR";

        String ordCust2 = creditRequest.getOrdCust2();
        String ordCust1 = creditRequest.getOrdCust1();
        String ordCust = (ordCust2 != null) ? ordCust2 : ordCust1;

        String remitInfo = creditRequest.getRemitInfo();
        String remitInfo1 = (remitInfo.length() > 0) ? remitInfo.substring(0, Math.min(18, remitInfo.length())) : "";
        String remitInfo2 = (remitInfo.length() > 18)
                ? remitInfo.substring(18, 18 + Math.min(18, remitInfo.length() - 18))
                : "";

        BigDecimal amountScale = amount.setScale(2, RoundingMode.DOWN);

        String trailer2 = (ordCust.length() > 18) ? ordCust.substring(0, 18) : ordCust;
        String trailer3 = currCode + amountScale.toPlainString();
        String trailer4 = remitInfo1;
        String trailer5 = remitInfo2;
        String trailer6 = "";

        trfRequest.setTxnId("SMEK");
        trfRequest.setTxnCode("827");
        trfRequest.setAcctNo(acctNo);
        trfRequest.setCurrCode(currCode);
        trfRequest.setBenefBrch(devisaBranch);
        trfRequest.setRefNo(refNo);
        trfRequest.setTermId("95032");
        trfRequest.setLogType("L");
        trfRequest.setBusnDate(strAppDate);
        trfRequest.setAmtChar(strAmount);
        trfRequest.setKursChar("000000100");
        trfRequest.setTrailer1(trailer1);
        trfRequest.setTrailer2(trailer2);
        trfRequest.setTrailer3(trailer3);
        trfRequest.setTrailer4(trailer4);
        trfRequest.setTrailer5(trailer5);
        trfRequest.setTrailer6(trailer6);
        trfRequest.setUserId("SME90002");

        trfRequest.setAcctName(creditRequest.getAcctName());
        trfRequest.setSrcApp(creditRequest.getSrcApp());

        IDSTransferResponse response = transfer(trfRequest);

        return response;
    }

    @Transactional
    private IDSTransfer logTransfer(IDSTransferRequest request) {

        IDSTransfer idsTransfer = new IDSTransfer();

        idsTransfer.setAcctNo(request.getAcctNo());
        idsTransfer.setTxnId(request.getTxnId());
        idsTransfer.setTxnCode(request.getTxnCode());
        idsTransfer.setAcctNo(request.getAcctNo());
        idsTransfer.setCurrCode(request.getCurrCode());
        idsTransfer.setBenefBrch(request.getBenefBrch());
        idsTransfer.setRefNo(request.getRefNo());
        idsTransfer.setTermId(request.getTermId());
        idsTransfer.setLogType(request.getLogType());

        String busnChar = request.getBusnDate();
        LocalDate busnDate = parseDate(busnChar);
        idsTransfer.setBusnDate(busnDate);
        idsTransfer.setBusnChar(busnChar);

        String amtChar = request.getAmtChar();
        idsTransfer.setAmtChar(amtChar);
        BigDecimal amtNum = parseAmount(amtChar);
        idsTransfer.setAmtNum(amtNum);

        String kursChar = request.getKursChar();
        idsTransfer.setKursChar(kursChar);
        idsTransfer.setKursNum(new BigDecimal(1));

        idsTransfer.setTrailer1(request.getTrailer1());
        idsTransfer.setTrailer2(request.getTrailer2());
        idsTransfer.setTrailer3(request.getTrailer3());
        idsTransfer.setTrailer4(request.getTrailer4());
        idsTransfer.setTrailer5(request.getTrailer5());
        idsTransfer.setTrailer6(request.getTrailer6());
        idsTransfer.setUserId(request.getUserId());
        idsTransfer.setAcctName(request.getAcctName());
        idsTransfer.setSrcApp(request.getSrcApp());

        return idsTransferService.create(idsTransfer);
    }

    @Transactional
    private IDSTransfer logTransferDAIN(DAINInboundMessage inbound) {

        IDSTransfer idsTransfer = new IDSTransfer();

        // DAINInboundMessage inbound = request.getInboundMessage(); 
        idsTransfer.setAcctNo(inbound.getAcctNo());
        idsTransfer.setTxnId(inbound.getTxnId());
        idsTransfer.setTxnCode(inbound.getTxnCode());
        idsTransfer.setAcctNo(inbound.getAcctNo());
        idsTransfer.setCurrCode(inbound.getCurrCode());
        idsTransfer.setBenefBrch(inbound.getBenefBrch());
        idsTransfer.setRefNo(inbound.getRefNo());
        idsTransfer.setTermId(inbound.getTermId());
        idsTransfer.setLogType(inbound.getLogType());

        String busnChar = inbound.getBusnDate();
        LocalDate busnDate = parseDate(busnChar);
        idsTransfer.setBusnDate(busnDate);
        idsTransfer.setBusnChar(busnChar);

        String amtChar = inbound.getAmtChar();
        idsTransfer.setAmtChar(amtChar);
        BigDecimal amtNum = parseAmount(amtChar);
        idsTransfer.setAmtNum(amtNum);

        String kursChar = inbound.getKursChar();
        idsTransfer.setKursChar(kursChar);
        BigDecimal kursNum = parseKurs(kursChar);
        idsTransfer.setKursNum(kursNum);

        List<String> trailers = inbound.getTrailers();
        idsTransfer.setTrailer1(trailers != null && trailers.size() > 0 ? trailers.get(0) : "");
        idsTransfer.setTrailer2(trailers != null && trailers.size() > 1 ? trailers.get(1) : "");
        idsTransfer.setTrailer3(trailers != null && trailers.size() > 2 ? trailers.get(2) : "");
        idsTransfer.setTrailer4(trailers != null && trailers.size() > 3 ? trailers.get(3) : "");
        idsTransfer.setTrailer5(trailers != null && trailers.size() > 4 ? trailers.get(4) : "");
        idsTransfer.setTrailer6(trailers != null && trailers.size() > 5 ? trailers.get(5) : "");

        idsTransfer.setUserId(inbound.getUserId());
        // idsTransfer.setAcctName(inbound.getAcctName());
        // idsTransfer.setSrcApp(inbound.getSrcApp());

        return idsTransferService.create(idsTransfer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public IDSTransferResponse transferbySmek(IDSTransferRequest request) throws InterfacingException {
        IDSTransferResponse transferResponse = null;
        IDSTransfer idsTransfer = null;
        String path = transferServiceUri + "/smek";

        try {
            // get ids transfer from log
            idsTransfer = idsTransferService.getIDSTransfer(request);
            if (idsTransfer == null) {
                idsTransfer = logTransfer(request);
            } else if (idsTransfer.getActive()) {
                transferResponse = new IDSTransferResponse();
                transferResponse.reference = idsTransfer.getResRef();
                transferResponse.logType = idsTransfer.getResLogType();
                transferResponse.delimeter = idsTransfer.getResDelimeter();
                transferResponse.add_inf = idsTransfer.getResAddInf();
                transferResponse.sharp1 = idsTransfer.getResSharp1();
                transferResponse.sharp2 = idsTransfer.getResSharp2();

            	LOG.debug("IDS Transfer Bypass, ID = {}", idsTransfer.getId()) ;
                return transferResponse;
            }

            try {
                LOG.debug("JSON to be sent: " + new ObjectMapper().writeValueAsString(request));
            } catch (Exception e) {
            }

            // Get OAuth token
            OAuthResponse authResponse = oAuthService.getAccessToken();
            String accessToken = authResponse.accessToken;

            // String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
            // String requestBody = new ObjectMapper().writeValueAsString(request);
            // String signature = sign("POST", path, accessToken, requestBody, timestamp);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            // headers.add("X-BCA-Key", apiKey);
            // headers.add("X-BCA-Timestamp", timestamp);
            // headers.add("X-BCA-Signature", signature);

            HttpEntity<?> httpEntity = new HttpEntity<IDSTransferRequest>(request, headers);
            ResponseEntity<IDSTransferResponse> response = null;
            response = restTemplate.exchange(path, HttpMethod.POST, httpEntity, IDSTransferResponse.class);
            transferResponse = response.getBody();

            LOG.debug("transferResponse.reference = {}", transferResponse.reference);
            LOG.debug("transferResponse.add_inf = {}", transferResponse.add_inf);

            transferResponse.reference = idsTransfer.getResRef();
            transferResponse.logType = idsTransfer.getResLogType();

            String busnChar = request.getBusnDate();
            LocalDate busnDate = parseDate(busnChar);
            idsTransfer.setBusnDate(busnDate);
            idsTransfer.setBusnChar(busnChar);
            idsTransfer.setResRef(transferResponse.reference);
            idsTransfer.setResLogType(transferResponse.logType);
            idsTransfer.setResDelimeter(transferResponse.delimeter);
            idsTransfer.setResAddInf(transferResponse.add_inf);
            idsTransfer.setResSharp1(transferResponse.sharp1);
            idsTransfer.setResSharp2(transferResponse.sharp2);
            idsTransfer.setReleaseFlag(true);

        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            IDSErrorResponse errorResponse = null;
            try {
                errorResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), IDSErrorResponse.class);
                String errorCode = errorResponse.getErrorCode();
                String errorMsgId = (String) errorResponse.getErrorMessage().get("indonesian");
                String errorMsgEn = (String) errorResponse.getErrorMessage().get("english");

                LOG.debug("response.errorCode = {}", errorCode);
                LOG.debug("response.errorMessage.indonesian = {}", errorMsgId);
                LOG.debug("response.errorMessage.english = {}", errorMsgEn);

                idsTransfer.setErrorCode(errorCode);
                idsTransfer.setErrorMsgId(errorMsgId);
                idsTransfer.setErrorMsgEn(errorMsgEn);
                idsTransfer.setReleaseFlag(false);

                throw new InterfacingException(
                        errorResponse.getErrorCode() + " " + errorResponse.getErrorMessage().get("english"));
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
        } catch (RestClientException e) {
            LOG.error("Call IDS service error", e);
            throw new InterfacingException(e.getMessage());
        } finally {
            idsTransferService.update(idsTransfer.getId(), idsTransfer);
            LOG.debug("Update IDS tranfer log ID = {}", idsTransfer.getId());
        }
        return transferResponse;
    }    

    public DAINTransferResponse transferbyDAIN(DAINInboundMessage request) throws InterfacingException {
        DAINTransferResponse transferResponse = null;
        IDSTransfer idsTransfer = null;
        String path = transferDAINServiceUri;

        try {
			//String acctNo = request.getAcctNo();
			//String amount = request.getAmtChar();
			//String refNo = request.getRefNo();
			//String currency = request.getCurrCode();
			//String busnDate = request.getBusnDate();
            // get ids transfer from log
            LOG.debug("get ids transfer from log" );
            // DAINInboundMessage inbound = request.getInboundMessage();
            idsTransfer = idsTransferService.getIDSTransferDAIN(request);
			//LOG.debug("log id = " + idsTransfer.getId());
            try {
                LOG.debug("JSON to be sent: " + new ObjectMapper().writeValueAsString(request));
            } catch (Exception e) {
            }
            if (idsTransfer == null) {
                idsTransfer = logTransferDAIN(request);
            } else {
                LOG.debug("IDS Transfer Bypass, ID = {}", idsTransfer.getId()) ;

                DAINOutputSchema outputSchema = new DAINOutputSchema();
                outputSchema.setReference(idsTransfer.getResRef());
                outputSchema.setLogType(idsTransfer.getResLogType());
                outputSchema.setDelimeter1(idsTransfer.getResDelimeter());
                outputSchema.setDelimeter2(idsTransfer.getResDelimeter());
                outputSchema.setAdd_inf(idsTransfer.getResAddInf());
                outputSchema.setSharp1(idsTransfer.getResSharp1());
                outputSchema.setSharp2(idsTransfer.getResSharp2());
                
                // DAINOutputMessage outputMessage = new DAINOutputMessage();
                // outputMessage.setOutputSchema(outputSchema);

                transferResponse = new DAINTransferResponse();
                transferResponse.setOutputSchema(outputSchema);
                
                return transferResponse;
            }


            // Get OAuth token
            OAuthResponse authResponse = oAuthService.getAccessToken();
            String accessToken = authResponse.accessToken;

            // String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
            // String requestBody = new ObjectMapper().writeValueAsString(request);
            // String signature = sign("POST", path, accessToken, requestBody, timestamp);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            // headers.add("X-BCA-Key", apiKey);
            // headers.add("X-BCA-Timestamp", timestamp);
            // headers.add("X-BCA-Signature", signature);

            LOG.info("Call DAIN..");
            HttpEntity<?> httpEntity = new HttpEntity<DAINInboundMessage>(request, headers);
            ResponseEntity<DAINTransferResponse> response = null;
            response = restTemplate.exchange(path, HttpMethod.POST, httpEntity, DAINTransferResponse.class);
            transferResponse = response.getBody();

            if (transferResponse != null && transferResponse.getOutputSchema() != null) {
                // DAINOutputMessage outputMessage = transferResponse.getOutputMessage();
                DAINOutputSchema outputSchema = transferResponse.getOutputSchema();

                // LOG.debug("outputSchema.reference = {}", outputSchema.getReference());
                // LOG.debug("outputSchema.add_inf = {}", outputSchema.getAdd_inf());

                outputSchema.setReference(idsTransfer.getResRef());
                outputSchema.setLogType(idsTransfer.getResLogType());

                String busnChar = request.getBusnDate();
                LocalDate busnDate = parseDate(busnChar);
                idsTransfer.setBusnDate(busnDate);
                idsTransfer.setBusnChar(busnChar);
                idsTransfer.setResRef(outputSchema.getReference());
                idsTransfer.setResLogType(outputSchema.getLogType());
                idsTransfer.setResDelimeter(outputSchema.getDelimeter1());
                idsTransfer.setResAddInf(outputSchema.getAdd_inf());
                idsTransfer.setResSharp1(outputSchema.getSharp1());
                idsTransfer.setResSharp2(outputSchema.getSharp2());
                idsTransfer.setReleaseFlag(true);
                idsTransfer.setErrorCode(transferResponse.getErrorSchema().getErrorCode());
                idsTransfer.setErrorMsgEn(transferResponse.getErrorSchema().getErrorMessage().get("english"));
                idsTransfer.setErrorMsgId(transferResponse.getErrorSchema().getErrorMessage().get("indonesian"));
                idsTransfer.setActive(true);

                idsTransferService.update(idsTransfer.getId(), idsTransfer);
            } else {
                idsTransfer.setErrorCode("-");
                idsTransfer.setErrorMsgId("-");
                idsTransfer.setErrorMsgEn("No response from IDS");
                idsTransfer.setReleaseFlag(false);
                idsTransfer.setActive(false);

                throw new InterfacingException("No response from IDS");
            }

        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            DAINTransferResponse dainTransferResponse = null;
            try {
                dainTransferResponse = new ObjectMapper().readValue(e.getResponseBodyAsString(), DAINTransferResponse.class);
                if (dainTransferResponse != null && dainTransferResponse.getErrorSchema() != null){
                    IDSErrorResponse errorResponse = dainTransferResponse.getErrorSchema();
                    String errorCode = errorResponse.getErrorCode();
                    String errorMsgId = (String) errorResponse.getErrorMessage().get("indonesian");
                    String errorMsgEn = (String) errorResponse.getErrorMessage().get("english");

                    LOG.debug("response.errorCode = {}", errorCode);
                    LOG.debug("response.errorMessage.indonesian = {}", errorMsgId);
                    LOG.debug("response.errorMessage.english = {}", errorMsgEn);

                    idsTransfer.setErrorCode(errorCode);
                    idsTransfer.setErrorMsgId(errorMsgId);
                    idsTransfer.setErrorMsgEn(errorMsgEn);
                    idsTransfer.setReleaseFlag(false);
                    idsTransfer.setActive(false);

                    throw new InterfacingException(
                            errorResponse.getErrorCode() + " " + errorResponse.getErrorMessage().get("english"));
                } else {
                    throw new InterfacingException("No reponse from IDS DAIN.");
                }
                
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
        } catch (RestClientException e) {
            LOG.error("Call IDS service error", e);
            throw new InterfacingException(e.getMessage());
        } catch (Exception e) {
            LOG.error("System error", e);
            throw new InterfacingException(e.getMessage());
        }
        finally {
            LOG.debug("Update IDS tranfer log ID = {}", idsTransfer.getId());
            idsTransferService.update(idsTransfer.getId(), idsTransfer);
        }
        return transferResponse;
    }

    public String formatKurs(BigDecimal kurs) {
        kurs = kurs.setScale(2, RoundingMode.DOWN);
        String strKurs = kurs.toPlainString();
        int radixIdx = strKurs.indexOf(".");
        // String strIntKurs = strKurs.substring(0, radixIdx);
        long longKurs = kurs.longValue();
        String strDeciKurs = strKurs.substring(radixIdx + 1, strKurs.length());
        String fmtKurs = String.format("%07d", longKurs) + strDeciKurs;
        return fmtKurs;
    }

    private BigDecimal parseKurs(String kursChar) {
        String intKurs = kursChar.substring(0, kursChar.length() - 2);
        String decKurs = kursChar.substring(kursChar.length() - 2, kursChar.length());
        String strKurs = intKurs + "." + decKurs;
        return new BigDecimal(strKurs);
    }
    
    public String formatAmount(BigDecimal amount) {
        amount = amount.setScale(2, RoundingMode.DOWN);
        String strAmount = amount.toPlainString();
        int radixIdx = strAmount.indexOf(".");
        // String strIntAmount = strAmount.substring(0, radixIdx);
        long longAmount = amount.longValue();
        String strDeciAmount = strAmount.substring(radixIdx + 1, strAmount.length());
        String fmtAmount = String.format("%015d", longAmount) + strDeciAmount;
        return fmtAmount;
    }

    public BigDecimal parseAmount(String amtChar) {
        String intAmount = amtChar.substring(0, amtChar.length() - 2);
        String decAmount = amtChar.substring(amtChar.length() - 2, amtChar.length());
        String strAmount = intAmount + "." + decAmount;
        return new BigDecimal(strAmount);
    }

    private String formatMonthYear(LocalDate appDate) {
        DateTimeFormatter valDateFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String strAppDate = appDate.format(valDateFormatter);
        
        return strAppDate;
    }

    private String formatRefNo(LocalDate valueDate, String irNumber) {
        DateTimeFormatter valDateFormatter = DateTimeFormatter.ofPattern("1yyMMdd");
        String strValueDate = valueDate.format(valDateFormatter);
        String subIrNumber = irNumber.substring(2, 6);
        return strValueDate + subIrNumber;
    }

    public String formatDainRefNo(String noNota){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("00");
        strBuilder.append(noNota.substring(1, 5));
        strBuilder.append(noNota.substring(noNota.length() - 5, noNota.length()));
        return strBuilder.toString();
    }

    public String formatDate(LocalDateTime date) {
        return date.format(formatter);
    }

    private LocalDate parseDate(String dateChar) {
        LOG.info("parse date : " + dateChar);
        LocalDate result = LocalDate.parse(dateChar, formatter);

        return result;
    }


}