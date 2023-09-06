package id.co.bca.bnos.batch.marketplacereversal.integration.rate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RateService {
    private static final Logger LOG = LoggerFactory.getLogger(RateService.class);

    private final RestTemplate restTemplate;

    @Value("${bnos.interface.rate.service.uri}")
    private String serviceUri;

    @Value("${bnos.interface.rate.client-id}")
    private String clientId;

    public RateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // public List<Rate> getDummy() {
    //     List<Rate> rateList = new ArrayList<Rate>();

    //     Rate rate0 = new Rate();
    //     rate0.rateType = "TT";
    //     rate0.currencyCode = "IDR";
    //     rate0.midRate = new BigDecimal(1);
    //     rate0.buy = new BigDecimal(1);
    //     rate0.sell = new BigDecimal(1);
    //     rate0.lastUpdate = LocalDateTime.now();
        
    //     Rate rate1 = new Rate();
    //     rate1.rateType = "TT";
    //     rate1.currencyCode = "USD";
    //     rate1.midRate = new BigDecimal(14388);
    //     rate1.buy = new BigDecimal(14125);
    //     rate1.sell = new BigDecimal(14425);
    //     rate1.lastUpdate = LocalDateTime.now();

    //     Rate rate2 = new Rate();
    //     rate2.rateType = "TT";
    //     rate2.currencyCode = "EUR";
    //     rate2.midRate = new BigDecimal(16031.35);
    //     rate2.buy = new BigDecimal(15736.45);
    //     rate2.sell = new BigDecimal(16114.45);
    //     rate2.lastUpdate = LocalDateTime.now();
        
    //     rateList.add(rate0);
    //     rateList.add(rate1);
    //     rateList.add(rate2);

    //     return rateList;
    // }

    // public List<Rate> getAll() throws InterfacingException {
    //     List<Rate> rateList = new ArrayList<Rate>();

    //     BooleanExpression isRelease = QCurrency.currency.active.eq(true);
    //     BooleanExpression notDeleted = QCurrency.currency.action.in("A", "E");
    //     Predicate predicate = isRelease.and(notDeleted);

    //     Iterable<Currency> currencyList = currencyRepo.findAll(predicate);
    //     for (Currency currency : currencyList) {
    //         Rate rate = getRate(currency.getCode());
    //         if(rate != null)
    //             rateList.add(rate);
    //     }
    //     return rateList;
    // }

    // public Rate getRate(String currency) throws InterfacingException {
    //     return getRate(currency, "TT");
    // }

    // public Rate getRate(String currency, String rateType) throws InterfacingException {
    //     Rate rate = null;
    //     String rateUri = serviceUri + "/{currency}";

    //     // URL param
    //     Map<String, String> urlParams = new HashMap<String, String>();
    //     urlParams.put("currency", currency.toUpperCase());

    //     // Query param
    //     UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rateUri)
    //         .queryParam("rate-type", rateType);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     headers.add("client-id", clientId);

    //     HttpEntity<?> httpEntity = new HttpEntity<>(headers);
    //     ResponseEntity<RateResponse> response = null;
    //     try {
    //         response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, httpEntity, RateResponse.class);
    //         RateResponse body = response.getBody();
    //         ErrorSchema errorSchema = body.errorSchema;
    //         LOG.debug("getRate success = " + response.getStatusCode());
    //         LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
    //         LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

    //         OutputSchema output = body.outputSchema;
    //         List<Rate> rateResults = output.rate;
    //         if (!rateResults.isEmpty()) {
    //             rate = rateResults.get(0);
    //             rate.currency = output.currencyCode;
    //             rate.midRate = output.midRate;
    //         }
    //     } catch (HttpStatusCodeException e) {
    //         LOG.debug("getRate error = " + e.getMessage());
    //         LOG.debug("status code = " + e.getStatusCode());
    //         RateResponse body = null;
    //         try {
    //             body = new ObjectMapper().readValue(e.getResponseBodyAsString(), RateResponse.class);
    //         } catch (JsonMappingException je) {
    //             LOG.error("Mapping response body to JSON error", je);
    //             throw new InterfacingException(je.getMessage());
    //         } catch (JsonProcessingException pe) {
    //             LOG.error("Processing response body to JSON error", pe);
    //             throw new InterfacingException(pe.getMessage());
    //         }
    //         ErrorSchema errorSchema = body.errorSchema;
    //         LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
    //         LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);
    //         throw new InterfacingException(errorSchema.errorCode + " " + errorSchema.errorMessage);
    //     } catch (RestClientException e) {
    //         LOG.error("Call Rate service error", e);
    //         throw new InterfacingException(e.getMessage());
    //     }
    //     return rate;
    // }

    public Rate getSingleRate(String currency, String rateType, String condition, String category) throws InterfacingException {
        Rate rate = null;
        String rateUri = serviceUri + "/{currency}";

        // URL param
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("currency", currency.toUpperCase());

        // Query param
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rateUri)
            .queryParam("rate-type", rateType).queryParam("banknote-condition", condition.toLowerCase())
            .queryParam("category", category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("client-id", clientId);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RateResponse> response = null;
        try {
            response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, httpEntity, RateResponse.class);
            RateResponse body = response.getBody();
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("getRate success = " + response.getStatusCode());
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

            OutputSchema output = body.outputSchema;
            List<Rate> rateResults = output.rate;
            if (!rateResults.isEmpty()) {
                rate = rateResults.get(0);
                rate.currencyCode = output.currencyCode;
                rate.midRate = output.midRate;
            }
        } catch (HttpStatusCodeException e) {
            LOG.debug("getRate error = " + e.getMessage());
            LOG.debug("status code = " + e.getStatusCode());
            RateResponse body = null;
            try {
                body = new ObjectMapper().readValue(e.getResponseBodyAsString(), RateResponse.class);
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);
            throw new InterfacingException(errorSchema.errorCode + " " + errorSchema.errorMessage);
        } catch (RestClientException e) {
            LOG.error("Call Rate service error", e);
            throw new InterfacingException(e.getMessage());
        }
        return rate;
    }

    public List<CurrencyRate> getMultipleRate(List<String> currencyList, String rateType, String condition, String category) throws InterfacingException {
        List<CurrencyRate> rateList = new ArrayList<CurrencyRate>();
        String rateUri = serviceUri + "";

        String currencies = "";

        for (String currency : currencyList) {

            currencies = currencies + currency.toUpperCase() + ",";
        }

        currencies = currencies.substring(0, currencies.length() - 1);

        // URL param
        Map<String, String> urlParams = new HashMap<String, String>();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rateUri)
            .queryParam("currency-code", currencies);

        if(condition != null && (rateType == null && category == null))
        {
            // Query param
            builder.queryParam("banknote-condition", condition.toLowerCase());
        }
        else if(condition == null && (rateType != null && category == null))
        {
            builder.queryParam("rate-type", rateType);
        }
        else if(condition == null && (rateType == null && category != null))
        {
            builder.queryParam("category", category);
        }
        else if(condition != null && (rateType == null && category != null))
        {
            builder.queryParam("category", category)
            .queryParam("banknote-condition", condition.toLowerCase());
        }
        else if(condition == null && (rateType != null && category != null))
        {
            builder.queryParam("category", category)
            .queryParam("rate-type", rateType);
        }
        else if(condition != null && (rateType != null && category == null))
        {
            builder.queryParam("banknote-condition", condition.toLowerCase())
            .queryParam("rate-type", rateType);
        }
        else if(condition != null && (rateType != null && category != null))
        {
            builder.queryParam("banknote-condition", condition.toLowerCase())
            .queryParam("rate-type", rateType)
            .queryParam("category", category);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("client-id", clientId);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RateResponse> response = null;
        try {
            response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, httpEntity, RateResponse.class);
            RateResponse body = response.getBody();
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("getRate success = " + response.getStatusCode());
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

            OutputSchema output = body.outputSchema;
            rateList = output.currency;
            
        } catch (HttpStatusCodeException e) {
            LOG.debug("getRate error = " + e.getMessage());
            LOG.debug("status code = " + e.getStatusCode());
            RateResponse body = null;
            try {
                body = new ObjectMapper().readValue(e.getResponseBodyAsString(), RateResponse.class);
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);
            throw new InterfacingException(errorSchema.errorCode + " " + errorSchema.errorMessage);
        } catch (RestClientException e) {
            LOG.error("Call Rate service error", e);
            throw new InterfacingException(e.getMessage());
        }
        return rateList;
    }

    public Rate getSingleRateByCIS(String currency, String settlementType, String cis) throws InterfacingException {
        Rate rate = new Rate();
        String rateUri = serviceUri + "/{cis}/{currency}";

        // URL param
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("cis", cis);
        urlParams.put("currency", currency);

        // Query param
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rateUri)
            .queryParam("settlement-type", settlementType);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("client-id", clientId);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RateResponse> response = null;
        try {
            response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, httpEntity, RateResponse.class);
            RateResponse body = response.getBody();
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("getRate success = " + response.getStatusCode());
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

            OutputSchema output = body.outputSchema;
            // List<CurrencyRate> currencyResult = output.currency;
            if (output != null) {
                // CurrencyRate rateResults = currencyResult.get(0);

                rate.currencyCode = output.currencyCode;
                rate.buy = output.buy;
                rate.sell = output.sell;
                rate.lastUpdate = output.lastUpdate;
                
            }
        } catch (HttpStatusCodeException e) {
            LOG.debug("getRate error = " + e.getMessage());
            LOG.debug("status code = " + e.getStatusCode());
            RateResponse body = null;
            try {
                body = new ObjectMapper().readValue(e.getResponseBodyAsString(), RateResponse.class);
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);
            throw new InterfacingException(errorSchema.errorCode + " " + errorSchema.errorMessage);
        } catch (RestClientException e) {
            LOG.error("Call Rate service error", e);
            throw new InterfacingException(e.getMessage());
        }
        return rate;
    }

    public Rate updateRate(Rate rate) throws InterfacingException {
        Rate rateResult = new Rate();
        String rateUri = serviceUri + "/update-rate";

        // URL param
        // Map<String, String> urlParams = new HashMap<String, String>();

        // Query param
        // UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rateUri)
        //     .queryParam("currency-code", currency.toUpperCase())
        //     .queryParam("settlement-type", settlementType);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("client-id", clientId);

        HttpEntity<?> httpEntity = new HttpEntity<>(rate, headers);
        ResponseEntity<RateResponse> response = null;
        try {
            response = restTemplate.exchange(rateUri, HttpMethod.PUT, httpEntity, RateResponse.class);
            RateResponse body = response.getBody();
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("getRate success = " + response.getStatusCode());
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

            OutputSchema output = body.outputSchema;

            if (output != null) {
                rateResult.rateType = output.rateType;
            }
        } catch (HttpStatusCodeException e) {
            LOG.debug("getRate error = " + e.getMessage());
            LOG.debug("status code = " + e.getStatusCode());
            RateResponse body = null;
            try {
                body = new ObjectMapper().readValue(e.getResponseBodyAsString(), RateResponse.class);
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException(je.getMessage());
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException(pe.getMessage());
            }
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);
            throw new InterfacingException(errorSchema.errorCode + " " + errorSchema.errorMessage);
        } catch (RestClientException e) {
            LOG.error("Call Rate service error", e);
            throw new InterfacingException(e.getMessage());
        }

        return rateResult;
    }
}