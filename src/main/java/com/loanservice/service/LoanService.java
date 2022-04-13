package com.loanservice.service;



import com.loanservice.model.ApplyLoan;

public interface LoanService {

	public ApplyLoan createLoan(ApplyLoan loan);

	public ApplyLoan getLoanDetail(String id);
	
	public ApplyLoan updateLoan(ApplyLoan loan);
	
	public boolean deleteLoan(String id);

}
