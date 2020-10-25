package com.sg.loan.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "loan_disbursement", indexes = {@Index(name = "loan_account_number_index",  columnList="loan_account_number", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class LoanDisbursementEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="loan_ref_number", nullable=false, length = 50)
    private String loanRefNumber;
    @Column(name="loan_account_number", nullable=false, length = 50)
    private String loanAccountNumber;
    @Column(name="vehicle_company", nullable=false, length = 100)
    private String vehicleCompany;
    @Column(name="vehicle_name", nullable=false, length = 50)
    private String vehicleName;
    @Column(name="vehicle_type", nullable=false, length = 30)
    private String vehicleType;
    @Column(name="total_price", nullable=false)
    private Long totalPrice;
    @Column(name="loan_amount", nullable=false)
    private Long loanAmount;
    @Column(name="down_payment", nullable=false)
    private Long downPayment;
    @Column(name="roi", nullable=false)
    private Double roi;
    @Column(name="financed_by", nullable=false, length = 50)
    private String financedBy;
    @Column(name="financer_address", nullable=false, length = 100)
    private String financerAddress;
    @Column(name="tenure", nullable=false)
    private Integer tenure;
    @Column(name="emi_amount", nullable=false)
    private Integer emi;
}
