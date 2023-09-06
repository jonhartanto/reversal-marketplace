package id.co.bca.bnos.batch.marketplacereversal.integration.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class OAuthService {
    private static final Logger LOG = LoggerFactory.getLogger(OAuthService.class);

    @Value("${bnos.interface.oauth.service.uri}")
    private String serviceUri;

    @Value("${bnos.interface.oauth.client-id}")
    private String clientId;

    @Value("${bnos.interface.oauth.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    public OAuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OAuthResponse getAccessToken() throws InterfacingException {
        String clientToken = clientId + ":" + clientSecret;
        String clientTokenBase64 = Base64.getEncoder().encodeToString(clientToken.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + clientTokenBase64);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "client_credentials");

        HttpEntity<?> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<OAuthResponse> response = null;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUri);
            response = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, httpEntity, OAuthResponse.class);
            OAuthResponse body = response.getBody();
            return body;
        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            OAuthError error = null;
            try {
                error = new ObjectMapper().readValue(e.getResponseBodyAsString(), OAuthError.class);
                LOG.debug("error.error = " + error.error);
                LOG.debug("error.errorDescripton = " + error.errorDescripton);

                throw new InterfacingException(error.errorDescripton);
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException("Mapping response body to JSON error");
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException("Processing response body to JSON error");
            }

        } catch (RestClientException e) {
            LOG.error("Call OAuth service error", e);
            throw new InterfacingException(e.getMessage());
        }

    }

    public OAuthResponse getAccessToken(String serviceClientId, String serviceClientSecret) throws InterfacingException {
        String clientToken = serviceClientId + ":" + serviceClientSecret;
        String clientTokenBase64 = Base64.getEncoder().encodeToString(clientToken.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + clientTokenBase64);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "client_credentials");

        HttpEntity<?> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<OAuthResponse> response = null;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUri);
            response = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, httpEntity, OAuthResponse.class);
            OAuthResponse body = response.getBody();
            return body;
        } catch (HttpStatusCodeException e) {
            LOG.debug("status code = " + e.getStatusCode());
            OAuthError error = null;
            try {
                error = new ObjectMapper().readValue(e.getResponseBodyAsString(), OAuthError.class);
                LOG.debug("error.error = " + error.error);
                LOG.debug("error.errorDescripton = " + error.errorDescripton);

                throw new InterfacingException(error.errorDescripton);
            } catch (JsonMappingException je) {
                LOG.error("Mapping response body to JSON error", je);
                throw new InterfacingException("Mapping response body to JSON error");
            } catch (JsonProcessingException pe) {
                LOG.error("Processing response body to JSON error", pe);
                throw new InterfacingException("Processing response body to JSON error");
            }

        } catch (RestClientException e) {
            LOG.error("Call OAuth service error", e);
            throw new InterfacingException(e.getMessage());
        }

    }

    private String sign(String httpMethod, String relativeUrl, String accessToken, String requestBody, String timestamp)
            throws GeneralSecurityException {
        String requestBodyHash = "";
        if (!requestBody.isEmpty()) {
            // MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // byte[] digest = messageDigest.digest(requestBody.getBytes());
            // requestBodyHash = Hex.encodeHexString(digest);
            requestBodyHash = DigestUtils.sha256Hex(requestBody);
        }
        String stringToSign = httpMethod + ":" + relativeUrl + ":" + accessToken + ":" + requestBodyHash + ":"
                + timestamp;
        try {
            return hmac(clientSecret, stringToSign);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw e;
        }
    }

    private String hmac(String key, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] hmac = mac.doFinal(message.getBytes());
            return Hex.encodeHexString(hmac);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("No such alogorithm error", e);
            throw e;
        } catch (InvalidKeyException e) {
            LOG.error("Invalid key error", e);
            throw e;
        }
    }

}