package com.loanservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loanservice.model.ApplyLoan;


@Repository
public interface LoanRepository extends CrudRepository<ApplyLoan, String> {

}
