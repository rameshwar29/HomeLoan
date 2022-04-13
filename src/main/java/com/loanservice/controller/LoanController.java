package com.loanservice.controller;

import java.net.BindException;
import java.time.DateTimeException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loanservice.model.ApplyLoan;
import com.loanservice.service.LoanService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LoanController {  // /customer endpoint

	@Autowired
	private LoanService loanService;   //customer interface


	@PostMapping("/submitLoanApplication")
	public ApplyLoan createLoan(@Valid @RequestBody ApplyLoan loan){
		ApplyLoan loanEntity = loanService.createLoan(loan);
		if (loanEntity != null)
			return loanEntity;
		else
			return null;
	}
	
	@GetMapping("/getLoanDetails/{id}")
	public ResponseEntity<?> getLoanDetails( @PathVariable String id) {
		ApplyLoan toReturnLoanDetails = loanService.getLoanDetail(id);
		if (toReturnLoanDetails == null)
			return new ResponseEntity<>("Customer Userid "+id+" DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
	
		return new ResponseEntity<>(toReturnLoanDetails, HttpStatus.OK);
	}
	
	@PostMapping("/updateLoan")
	public ApplyLoan updateLoan(@Valid @RequestBody ApplyLoan loan) {
		
		return loanService.updateLoan(loan);
	}
	
	@DeleteMapping("deleteLoan/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteLoan( @PathVariable String id) {

		ApplyLoan checkCustomerIdExists = null;
		checkCustomerIdExists = loanService.getLoanDetail(id);
		if (checkCustomerIdExists == null) {
			return new ResponseEntity<>("Customer Userid DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
		}

		System.out.println("Starting deletion of-->"+id);
		loanService.deleteLoan(id);
		System.out.println("Deleted");
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}

	
}
