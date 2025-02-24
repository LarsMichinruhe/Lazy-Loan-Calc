package de.bmscs.bwslearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.bmscs.bwslearning.dto.ErrorResponse;
import de.bmscs.bwslearning.dto.LoanRequest;
import de.bmscs.bwslearning.dto.LoanResponse;
import de.bmscs.bwslearning.service.LoanService;

@RestController
@RequestMapping("/api/loan")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> calculateLoan(@RequestBody LoanRequest request) {
        if (! inputValid(request)) {
        	ErrorResponse errorResponse = new ErrorResponse("Invalid input: All fields must be greater than 0.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        LoanResponse response = loanService.calculateLoan(request);
        return ResponseEntity.ok(response);
    }
    
	private boolean inputValid(LoanRequest request) {
		return request.getPrincipal() > 0 && request.getAnnualInterestRate() > 0 && request.getYears() > 0 && request.getMonthlyPayment() > 0;
    }
}


