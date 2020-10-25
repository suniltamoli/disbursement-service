package com.sg.loan.services;

import com.sg.loan.commons.LoanException;
import com.sg.loan.model.LoanDisbursementDetail;
import com.sg.loan.model.LoanRequest;

public interface LoanDisbursementService {
    LoanDisbursementDetail getLoanDetails(String loanRefNumber) throws LoanException;
    void saveLoanDisbursementData(LoanRequest loanRequest);
}
