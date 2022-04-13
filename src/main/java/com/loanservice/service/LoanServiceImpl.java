package com.loanservice.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanservice.model.ApplyLoan;
import com.loanservice.repository.LoanRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService {
	
	private static final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

	

	@Autowired
	LoanRepository loanRepo;


	@Override
	//@HystrixCommand(fallbackMethod="welcomeFallBack", commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="120000")})
	public ApplyLoan createLoan(ApplyLoan loan) {

		
		log.info("Consumer details saved.");
		return loanRepo.save(loan);
	}

	@Override
	//@HystrixCommand(fallbackMethod="welcomeFallBack", commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="120000")})
	public ApplyLoan getLoanDetail( String id) {
		Optional<ApplyLoan> loan = loanRepo.findById(id);
		if (!loan.isPresent())
			return null;
		log.info("Loan details fetched.");
		
		return loan.get();
	}
	
	@Override
	public boolean deleteLoan(String id) {
		ApplyLoan customer = loanRepo.findById(id).get();
		if (customer != null)
			loanRepo.deleteById(id);
		else
			return false;
		log.info("Loan details deleted.");
		return true;
	}
	
	@Override
	public ApplyLoan updateLoan( ApplyLoan loan) {
		ApplyLoan toUpdate = loanRepo.findById(loan.getUserid()).get();
		toUpdate.setLoanAmount(50*loan.getSalary());
		return loanRepo.save(toUpdate);
	}
}
