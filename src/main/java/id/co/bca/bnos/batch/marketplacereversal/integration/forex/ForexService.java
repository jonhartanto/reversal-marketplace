package id.co.bca.bnos.batch.marketplacereversal.integration.forex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import id.co.bca.bnos.batch.marketplacereversal.integration.oauth.OAuthResponse;
import id.co.bca.bnos.batch.marketplacereversal.integration.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ForexService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ForexService.class);

    private final RestTemplate restTemplate;
    private final OAuthService oAuthService;

    @Value("${bnos.interface.forex.service.uri}")
    private String serviceUri;

    public ForexService(RestTemplate restTemplate, OAuthService oAuthService) {
        this.restTemplate = restTemplate;
        this.oAuthService = oAuthService;
    }

    public ForexResponse dealValas(ForexRequest forex) throws InterfacingException {
        ForexResponse forexResponse = new ForexResponse();
        String forexUri = serviceUri;

        // Get OAuth token
        OAuthResponse authResponse = oAuthService.getAccessToken();
        String accessToken = authResponse.accessToken;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        // headers.add("client-id", clientId);

        HttpEntity<?> httpEntity = new HttpEntity<>(forex, headers);
        ResponseEntity<ForexResponse> response = null;
        try {
            response = restTemplate.exchange(forexUri, HttpMethod.POST, httpEntity, ForexResponse.class);
            ForexResponse body = response.getBody();
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("dealValas success = " + response.getStatusCode());
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

            // OutputSchema output = body.outputSchema;

            forexResponse = body;
            // if (output != null) {

            //     forexResponse.transactionId = output.transactionId;
            //     forexResponse.transactionDate = output.transactionDate;
            //     forexResponse.transactionTime = output.transactionTime;
            //     forexResponse.transactionType = output.transactionType;
            //     forexResponse.maturityDate = output.maturityDate;
            //     forexResponse.identityNumber = output.identityNumber;
            //     forexResponse.identityType = output.identityType;
            //     forexResponse.dealId = output.dealId;
            //     forexResponse.applicationCode = output.applicationCode;
            //     forexResponse.action = output.action;
            //     forexResponse.branchCode = output.branchCode;
            //     forexResponse.buyAmount = output.buyAmount;
            //     forexResponse.buyCurrencyCode = output.buyCurrencyCode;
            //     forexResponse.customerName = output.customerName;
            //     forexResponse.dealPurpose = output.dealPurpose;
            //     forexResponse.flagUnderlyingDocument = output.flagUnderlyingDocument;
            //     forexResponse.rate = output.rate;
            //     forexResponse.sellAmount = output.sellAmount;
            //     forexResponse.sellCurrencyCode = output.sellCurrencyCode;
            //     forexResponse.usedAmount = output.usedAmount;
            // }
        } catch (HttpStatusCodeException e) {
            LOG.debug("dealValas error = " + e.getMessage());
            LOG.debug("status code = " + e.getStatusCode());
            ForexResponse body = null;
            try {
                body = new ObjectMapper().readValue(e.getResponseBodyAsString(), ForexResponse.class);
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
            LOG.error("Call Forex service error", e);
            throw new InterfacingException(e.getMessage());
        }
        return forexResponse;
    }
}
