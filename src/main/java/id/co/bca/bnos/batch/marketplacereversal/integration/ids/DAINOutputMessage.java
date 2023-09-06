package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DAINOutputMessage {
    private DAINOutputSchema outputSchema;

    @JsonProperty("output_schema")
    public DAINOutputSchema getOutputSchema() {
        return outputSchema;
    }

    @JsonProperty("output_schema")
    public void setOutputSchema(DAINOutputSchema outputSchema) {
        this.outputSchema = outputSchema;
    }

}
