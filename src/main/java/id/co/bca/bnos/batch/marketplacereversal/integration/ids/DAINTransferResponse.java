package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DAINTransferResponse {
    private DAINOutputSchema outputSchema;
    private IDSErrorResponse errorSchema;

    @JsonProperty("output_schema")
    public DAINOutputSchema getOutputSchema() {
        return outputSchema;
    }

    @JsonProperty("output_schema")
    public void setOutputSchema(DAINOutputSchema outputSchema) {
        this.outputSchema = outputSchema;
    }

    @JsonProperty("error_schema")
    public IDSErrorResponse getErrorSchema() {
        return errorSchema;
    }

    @JsonProperty("error_schema")
    public void setErrorSchema(IDSErrorResponse errorSchema) {
        this.errorSchema = errorSchema;
    }

}





