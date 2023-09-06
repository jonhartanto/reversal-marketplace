package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSOutputResponse {
    
    @JsonProperty("error_schema")
    public IDSErrorResponse errorSchema;
    @JsonProperty("output_schema")
    public IDSAccountSummary outputSchema;
}
