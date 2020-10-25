package com.sg.loan.controller;

import com.sg.loan.commons.LoanError;
import com.sg.loan.commons.LoanException;
import com.sg.loan.model.LoanDisbursementDetail;
import com.sg.loan.services.LoanDisbursementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class LoanDisbursementController {

    private final LoanDisbursementService customerService;

    public LoanDisbursementController(LoanDisbursementService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/carloan/disbursement/{loan_account_number}")
    public ResponseEntity<?> approveLoanAsFrontOfficer(@PathVariable(value = "loan_account_number") String loanAccountNumber) {
        LoanDisbursementDetail loanDisbursementDetail;
        try {
            loanDisbursementDetail = customerService.getLoanDetails(loanAccountNumber);
        } catch (Exception e) {
            if(e instanceof LoanException) {
                LoanError loanError = new LoanError(((LoanException) e).getErroCode(), ((LoanException) e).getMessage());
                return new ResponseEntity<LoanError>(loanError, HttpStatus.BAD_REQUEST);
            } else {
                LoanError loanError = new LoanError(((LoanException) e).getErroCode(),  e.getMessage());
                return new ResponseEntity<LoanError>(loanError, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<LoanDisbursementDetail>(loanDisbursementDetail, HttpStatus.OK);
    }

}
