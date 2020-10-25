package com.sg.loan.repository;

import com.sg.loan.entity.LoanDisbursementEntity;
import com.sg.loan.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDisbursementRepository extends JpaRepository<LoanDisbursementEntity, Long> {

    @Query("SELECT u FROM LoanDisbursementEntity u WHERE u.loanAccountNumber = ?1")
    List<LoanDisbursementEntity> getLoanDisbursementDetail(String kycNumber);



}
