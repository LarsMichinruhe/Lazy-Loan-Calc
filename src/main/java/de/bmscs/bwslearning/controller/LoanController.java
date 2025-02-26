package de.bmscs.bwslearning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.bmscs.bwslearning.dto.ErrorResponse;
import de.bmscs.bwslearning.dto.LoanRequest;
import de.bmscs.bwslearning.dto.LoanResponse;
import de.bmscs.bwslearning.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * Loan Controller handles the loan calculation request.
 */
@RestController
@RequestMapping("/api/loan")
@CrossOrigin(origins = "*")
public class LoanController {

	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private LoanService loanService;

    @Operation(summary = "Calculate loan details", description = "Calculate loan details based on the input values: the monthly payment, total interest and amortization schedule for a loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = LoanResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/calculate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> calculateLoan(@RequestBody LoanRequest request) {
    	logger.info("Received loan calculation request: {}", toJson(request));
        if (! inputValid(request)) {
        	ErrorResponse errorResponse = new ErrorResponse("Invalid input: All fields must be greater than 0.");
            logger.error("Invalid input: {}", errorResponse.getErrorMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        LoanResponse response = loanService.calculateLoan(request);
        logger.info("Loan calculation response: {}", toJson(response));
        return ResponseEntity.ok(response);
    }
    
	private boolean inputValid(LoanRequest request) {
		return request.getPrincipal() > 0 && request.getAnnualInterestRate() > 0 && request.getYears() > 0 && request.getAnnualInterestRate() > 0;
    }
	
	private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON", e);
            return "{}";
        }
    }
}


