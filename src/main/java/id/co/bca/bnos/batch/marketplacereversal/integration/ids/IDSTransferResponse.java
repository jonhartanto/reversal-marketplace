package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSTransferResponse {
    @JsonProperty("output_schema_arr_count")
    BigDecimal arrCount;
    @JsonProperty("output_schema_reference")
    BigDecimal reference;
    @JsonProperty("output_schema_log_type")
    String logType;
    @JsonProperty("output_schema_add_inf")
    String add_inf;
    @JsonProperty("output_schema_sharp_1")
    String sharp1;
    @JsonProperty("output_schema_sharp2")
    String sharp2;
    @JsonProperty("output_schema_delimeter")
    String delimeter;
}