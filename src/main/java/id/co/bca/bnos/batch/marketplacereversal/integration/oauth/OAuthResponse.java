package id.co.bca.bnos.batch.marketplacereversal.integration.oauth;

//import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuthResponse {
    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("expires_in")
    public Integer expiresIn;
    @JsonProperty("refresh_token")
    public String refreshToken;
    @JsonProperty("refresh_expires_in")
    public Integer refreshExpiresIn;
    @JsonProperty("token_type")
    public String tokenType;
    @JsonProperty("id_token")
    public String idToken;
    @JsonProperty("not-before-policy")
    public Integer notBeforePolicy;
    @JsonProperty("session_state")
    public String sessionState;
}

class OAuthError {
    @JsonProperty("error")
    public String error;
    @JsonProperty("error_descripton")
    public String errorDescripton;
}

