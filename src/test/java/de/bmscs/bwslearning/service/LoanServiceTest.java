package de.bmscs.bwslearning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.bmscs.bwslearning.dto.AmortizationSchedule;
import de.bmscs.bwslearning.dto.LoanRequest;
import de.bmscs.bwslearning.dto.LoanResponse;

public class LoanServiceTest {

    private LoanService loanService = new LoanService();

    @Test
    public void testCalculateLoan_ValidRequest() {
        LoanRequest validRequest = new LoanRequest();
        validRequest.setPrincipal(10000);
        validRequest.setAnnualInterestRate(5);
        validRequest.setYears(10);
        validRequest.setAnnualInterestRate(2);

        LoanResponse response = loanService.calculateLoan(validRequest);

        assertEquals(92.01, response.getMonthlyPayment(), 0.01);
        assertEquals(1041.61, response.getTotalInterest(), 0.01);

        List<AmortizationSchedule> schedule = response.getAmortizationSchedule();
        assertEquals(120, schedule.size());
        assertEquals(1, schedule.get(0).getMonth());
        assertEquals(92.01, schedule.get(0).getPrincipalPayment() + schedule.get(0).getInterestPayment(), 0.01);
    }
}