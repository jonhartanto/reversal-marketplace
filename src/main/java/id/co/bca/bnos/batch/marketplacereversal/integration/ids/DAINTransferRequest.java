package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonProperty;

// @JsonIgnoreProperties(ignoreUnknown = true)
public class DAINTransferRequest {
    private DAINInboundMessage inboundMessage;

    @JsonProperty("inbound_message")
    public DAINInboundMessage getInboundMessage() {
        return inboundMessage;
    }

    @JsonProperty("inbound_message")
    public void setInboundMessage(DAINInboundMessage inboundMessage) {
        this.inboundMessage = inboundMessage;
    }


    
}


