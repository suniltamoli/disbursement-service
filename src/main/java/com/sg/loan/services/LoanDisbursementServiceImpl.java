package com.sg.loan.services;

import com.sg.loan.commons.LoanException;
import com.sg.loan.commons.Validator;
import com.sg.loan.entity.LoanDisbursementEntity;
import com.sg.loan.entity.LoanRequestEntity;
import com.sg.loan.middleware.MessageProducer;
import com.sg.loan.model.LoanDisbursementDetail;
import com.sg.loan.model.LoanRequest;
import com.sg.loan.repository.CustomerLoanRepository;
import com.sg.loan.repository.LoanDisbursementRepository;
import com.sg.loan.transformer.LoanRequestTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementService {
    private CustomerLoanRepository customerRepository;
    private final LoanDisbursementRepository loanDisbursementRepository;
    private LoanRequestTransformer transformer;
    private Validator validator;
    private final MessageProducer messageProducer;

    public LoanDisbursementServiceImpl(CustomerLoanRepository customerRepository, LoanDisbursementRepository loanDisbursementRepository, LoanRequestTransformer transformer, Validator validator, MessageProducer messageProducer) {
        this.customerRepository = customerRepository;
        this.loanDisbursementRepository = loanDisbursementRepository;
        this.transformer = transformer;
        this.validator = validator;
        this.messageProducer = messageProducer;
    }

    @Override
    public LoanDisbursementDetail getLoanDetails(String loanAccountNumber) throws LoanException {
        List<LoanDisbursementEntity> loanDisbursementEntities = loanDisbursementRepository.getLoanDisbursementDetail(loanAccountNumber);
        LoanDisbursementDetail loanDisbursementDetail = new LoanDisbursementDetail();
        BeanUtils.copyProperties(loanDisbursementEntities.get(0),loanDisbursementDetail);
        return loanDisbursementDetail;
    }

    @Override
    public void saveLoanDisbursementData(LoanRequest loanRequest) {
        LoanRequestEntity loanRequestEntity = transformer.transformCustomerDetails(loanRequest);
        customerRepository.save(loanRequestEntity);

        if(loanRequestEntity.getId() != null) {
            LoanDisbursementDetail loanDisbursementDetail = new LoanDisbursementDetail();
            BeanUtils.copyProperties(loanRequestEntity, loanDisbursementDetail);
            LoanDisbursementEntity loanDisbursementEntity = new LoanDisbursementEntity();
            BeanUtils.copyProperties(loanDisbursementDetail, loanDisbursementEntity);
            loanDisbursementRepository.save(loanDisbursementEntity);
            messageProducer.publishMessage(loanDisbursementDetail);
        }

    }

}
