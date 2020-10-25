package com.sg.loan.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LoanDisbursementDetail implements Serializable {
    @JsonProperty("loan_ref_number")
    private String loanRefNumber;
    @JsonProperty("loan_account_number")
    private String loanAccountNumber;
    @JsonProperty("vehicle_company")
    private String vehicleCompany;
    @JsonProperty("vehicle_name")
    private String vehicleName;
    @JsonProperty("vehicle_type")
    private String vehicleType;
    @JsonProperty("total_price")
    private Long totalPrice;
    @JsonProperty("loan_amount")
    private Long loanAmount;
    @JsonProperty("down_payment")
    private Long downPayment;
    @JsonProperty("roi")
    private Double roi;
    @JsonProperty("financed_by")
    private String financedBy;
    @JsonProperty("financer_address")
    private String financerAddress;
    @JsonProperty("tenure")
    private Integer tenure;
    @JsonProperty("emi")
    private Integer emi;

    private final static long serialVersionUID = 3343543324008830673L;
}
