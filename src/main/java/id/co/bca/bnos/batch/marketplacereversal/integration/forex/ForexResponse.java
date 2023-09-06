package id.co.bca.bnos.batch.marketplacereversal.integration.forex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForexResponse {
    @JsonProperty("error_schema")
    public ErrorSchema errorSchema;
    @JsonProperty("output_schema")
    public OutputSchema outputSchema;
}
