package com.sg.loan.transformer;

import com.sg.loan.entity.LoanRequestEntity;
import com.sg.loan.model.LoanRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoanRequestTransformer {
    public LoanRequestEntity transformCustomerDetails(LoanRequest loanRequest) {
        LoanRequestEntity loanRequestEntity = new LoanRequestEntity();
        BeanUtils.copyProperties(loanRequest, loanRequestEntity);
        loanRequestEntity.setLoanAccountNumber("LOAN"+UUID.randomUUID().toString().replace("-", ""));
        return  loanRequestEntity;
    }
}
