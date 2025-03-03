package de.bmscs.bwslearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.bmscs.bwslearning.dto.AmortizationSchedule;
import de.bmscs.bwslearning.dto.LoanRequest;
import de.bmscs.bwslearning.dto.LoanResponse;

/*
 * Loan Service calculates the monthly payment, total interest and amortization schedule for a loan.
 */
@Service
public class LoanService {

	public LoanResponse calculateLoan(LoanRequest request) {
        double monthlyInterestRate = request.getAnnualInterestRate() / 12 / 100;
        int numberOfPayments = request.getYears() * 12;
        double principal = request.getPrincipal();
        double annualRepaymentRate = request.getAnnualRepaymentRate() / 100;

        // Correct monthly payment calculation using annuity formula
        double monthlyPayment = (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        List<AmortizationSchedule> schedule = new ArrayList<>();
        double totalInterest = 0;

        for (int month = 1; month <= numberOfPayments; month++) {
            double interestPayment = principal * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;
            principal -= principalPayment;
            totalInterest += interestPayment;

            AmortizationSchedule entry = new AmortizationSchedule();
            entry.setMonth(month);
            entry.setPrincipalPayment(principalPayment);
            entry.setInterestPayment(interestPayment);
            entry.setRemainingBalance(principal);
            schedule.add(entry);
        }

        LoanResponse response = new LoanResponse();
        response.setMonthlyPayment(monthlyPayment);
        response.setTotalInterest(totalInterest);
        response.setAmortizationSchedule(schedule);

        return response;
    }
}