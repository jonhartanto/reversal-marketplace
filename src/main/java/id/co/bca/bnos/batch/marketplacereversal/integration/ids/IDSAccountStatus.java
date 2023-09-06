package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSAccountStatus {
    @JsonProperty("account_no")
    String accountNo;
    @JsonProperty("account_name")
    String accountName;
    @JsonProperty("branch")
    String branch;
    @JsonProperty("primary_officer")
    Integer primaryOfficer;
    @JsonProperty("secondary_officer")
    Integer secondaryOfficer;
    @JsonProperty("tax_id")
    String taxId;
    @JsonProperty("account_type")
    String accountType;
    @JsonProperty("tax_no")
    String taxNo;
    @JsonProperty("cost_center")
    String constCenter;
    @JsonProperty("open_date")
    String openDate;
    @JsonProperty("comb_statement")
    String combStatement;
    @JsonProperty("cards")
    String cards;
    @JsonProperty("recon")
    String recon;
    @JsonProperty("sh_draft")
    String shDraft;
    @JsonProperty("closeDate")
    String closeDate;
    @JsonProperty("open_indicator")
    String openIndicator;
    @JsonProperty("post_indicator")
    String postIndicator;
    @JsonProperty("dormant_status")
    String dormantStatus;
    @JsonProperty("special_status")
    String specialStatus;
    @JsonProperty("miscellaneous_status")
    String miscellaneousStatus;

    public String getOpenIndicator() {
        return openIndicator;
    }

    public String getPostIndicator() {
        return postIndicator;
    }

    public String getDormantStatus() {
        return dormantStatus;
    }

    public String getSpecialStatus() {
        return specialStatus;
    }

    public String getMiscellaneousStatus() {
        return miscellaneousStatus;
    }
}