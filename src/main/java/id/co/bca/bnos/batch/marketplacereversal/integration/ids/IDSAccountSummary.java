package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDSAccountSummary {
    @JsonProperty("account_number")
    String accountNumber;
    @JsonProperty("account_name")
    String accountName;
    @JsonProperty("account_type")
    String accountType;
    @JsonProperty("account_status")
    IDSAccountStatus accountStatus;
    @JsonProperty("amount_transaction")
    String amountTransaction;
    @JsonProperty("available_balance")
    String availableBalance;
    @JsonProperty("passbook_indicator")
    String passbookIndicator;
    @JsonProperty("passbook_balance")
    String passbookBalance;
    @JsonProperty("last_print_date")
    String lastPrintDate;
    @JsonProperty("ledger_balance")
    String ledgerBalance;
    @JsonProperty("hold_amount")
    String holdAmount;
    @JsonProperty("open_date")
    String openDate;
    @JsonProperty("tax_number")
    String taxNumber;
    @JsonProperty("branch_code")
    String branchCode;
    @JsonProperty("deposit_category")
    String depositCategory;
    @JsonProperty("user_code")
    String userCode;
    @JsonProperty("currency_code")
    String currencyCode;
    @JsonProperty("overdraft_limit")
    String overdraftLimit;
    @JsonProperty("overdraft_plan")
    String overdraftPlan;
    @JsonProperty("interest_plan")
    String interestPlan;
    @JsonProperty("account_memo")
    String accountMemo;
    @JsonProperty("account_next_day")
    String accountNextDay;
    @JsonProperty("account_last_day")
    String accountLastDay;
    @JsonProperty("float_amount")
    String floatAmount;
    @JsonProperty("float_today")
    String floatToday;
    @JsonProperty("float_next_day")
    String floatNextDay;
    @JsonProperty("float_last_day")
    String floatLastDay;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public IDSAccountStatus getAccountStatus() {
        return accountStatus;
    }

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public String getPassbookIndicator() {
        return passbookIndicator;
    }

    public String getPassbookBalance() {
        return passbookBalance;
    }

    public String getLastPrintDate() {
        return lastPrintDate;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public String getHoldAmount() {
        return holdAmount;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getDepositCategory() {
        return depositCategory;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getOverdraftLimit() {
        return overdraftLimit;
    }

    public String getOverdraftPlan() {
        return overdraftPlan;
    }

    public String getInterestPlan() {
        return interestPlan;
    }

    public String getAccountMemo() {
        return accountMemo;
    }

    public String getAccountNextDay() {
        return accountNextDay;
    }

    public String getAccountLastDay() {
        return accountLastDay;
    }

    public String getFloatAmount() {
        return floatAmount;
    }

    public String getFloatToday() {
        return floatToday;
    }

    public String getFloatNextDay() {
        return floatNextDay;
    }

    public String getFloatLastDay() {
        return floatLastDay;
    }
}