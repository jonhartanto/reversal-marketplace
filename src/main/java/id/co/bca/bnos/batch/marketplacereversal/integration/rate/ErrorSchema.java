package id.co.bca.bnos.batch.marketplacereversal.integration.rate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ErrorSchema {
    @JsonProperty("error_code")
    public String errorCode;
    @JsonProperty("error_message")
    public Map<String, String> errorMessage;
}
