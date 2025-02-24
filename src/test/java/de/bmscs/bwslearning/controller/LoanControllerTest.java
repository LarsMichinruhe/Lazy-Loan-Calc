package de.bmscs.bwslearning.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.bmscs.bwslearning.BwslearningApplication;
import de.bmscs.bwslearning.dto.ErrorResponse;
import de.bmscs.bwslearning.dto.LoanRequest;
import de.bmscs.bwslearning.dto.LoanResponse;
import de.bmscs.bwslearning.service.LoanService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = LoanController.class)
@ContextConfiguration(classes = LoanControllerTest.TestConfig.class)
public class LoanControllerTest {

    @Configuration
    @ComponentScan(basePackages = "de.bmscs.bwslearning", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BwslearningApplication.class))
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCalculateLoan_ValidRequest() throws Exception {
        LoanRequest validRequest = new LoanRequest();
        validRequest.setPrincipal(10000);
        validRequest.setAnnualInterestRate(5);
        validRequest.setYears(10);
        validRequest.setMonthlyPayment(200);

        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setMonthlyPayment(200);
        loanResponse.setTotalInterest(5000);

        mockMvc.perform(post("/api/loan/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCalculateLoan_InvalidRequest() throws Exception {
        LoanRequest invalidRequest = new LoanRequest();
        invalidRequest.setPrincipal(0);
        invalidRequest.setAnnualInterestRate(5);
        invalidRequest.setYears(10);
        invalidRequest.setMonthlyPayment(200);

        ErrorResponse errorResponse = new ErrorResponse("Invalid input: All fields must be greater than 0.");

        mockMvc.perform(post("/api/loan/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }

    @Test
    public void testCalculateLoan_AnotherInvalidRequest() throws Exception {
        LoanRequest anotherInvalidRequest = new LoanRequest();
        anotherInvalidRequest.setPrincipal(10000);
        anotherInvalidRequest.setAnnualInterestRate(0);
        anotherInvalidRequest.setYears(10);
        anotherInvalidRequest.setMonthlyPayment(200);

        ErrorResponse errorResponse = new ErrorResponse("Invalid input: All fields must be greater than 0.");

        mockMvc.perform(post("/api/loan/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(anotherInvalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(errorResponse)));
    }
}