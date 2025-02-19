package de.bmscs.bwslearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public LoanResponse calculateLoan(@RequestBody LoanRequest request) {
        return loanService.calculateLoan(request);
    }
}


