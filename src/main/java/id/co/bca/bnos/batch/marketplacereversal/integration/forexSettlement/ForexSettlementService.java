package id.co.bca.bnos.batch.marketplacereversal.integration.forexSettlement;

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
public class ForexSettlementService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ForexSettlementService.class);

    private final RestTemplate restTemplate;
    private final OAuthService oAuthService;

    @Value("${bnos.interface.forex-settlement.service.uri}")
    private String serviceUri;

    public ForexSettlementService(RestTemplate restTemplate, OAuthService oAuthService) {
        this.restTemplate = restTemplate;
        this.oAuthService = oAuthService;
    }

    public ForexSettlementResponse settlementValas(ForexSettlement forexSettlement) throws InterfacingException {
        ForexSettlementResponse forexSettlementResponse = new ForexSettlementResponse();
        String forexSettlementUri = serviceUri;

        // Get OAuth token
        OAuthResponse authResponse = oAuthService.getAccessToken();
        String accessToken = authResponse.accessToken;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        // headers.add("client-id", clientId);

        HttpEntity<?> httpEntity = new HttpEntity<>(forexSettlement, headers);
        ResponseEntity<ForexSettlementResponse> response = null;
        try {
            response = restTemplate.exchange(forexSettlementUri, HttpMethod.POST, httpEntity, ForexSettlementResponse.class);
            ForexSettlementResponse body = response.getBody();
            ErrorSchema errorSchema = body.errorSchema;
            LOG.debug("dealValas success = " + response.getStatusCode());
            LOG.debug("errorSchema.ErrorCode = " + errorSchema.errorCode);
            LOG.debug("errorSchema.ErrorMessage = " + errorSchema.errorMessage);

            forexSettlementResponse = body;

            // OutputSchema output = body.outputSchema;

            // if (output != null) {
            //     forexSettlementResponse.transactionId = output.transactionId;
            //     forexSettlementResponse.transactionType = output.transactionType;
            //     forexSettlementResponse.amount = output.amount;
            //     forexSettlementResponse.applicationCode = output.applicationCode;
            //     forexSettlementResponse.accountNumber = output.accountNumber;
            //     forexSettlementResponse.dealId = output.dealId;
            // }
        } catch (HttpStatusCodeException e) {
            LOG.debug("dealValas error = " + e.getMessage());
            LOG.debug("status code = " + e.getStatusCode());
            ForexSettlementResponse body = null;
            try {
                body = new ObjectMapper().readValue(e.getResponseBodyAsString(), ForexSettlementResponse.class);
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
            LOG.error("Call forexSettlement service error", e);
            throw new InterfacingException(e.getMessage());
        }
        return forexSettlementResponse;
    }
}
