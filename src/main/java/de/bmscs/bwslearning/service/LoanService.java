
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

        // Calculate annual repayment amount
        double annualRepaymentAmount = principal * annualRepaymentRate;
        // Calculate monthly repayment amount
        double monthlyRepaymentAmount = annualRepaymentAmount / 12;

        // Calculate monthly interest payment
        double monthlyInterestPayment = principal * monthlyInterestRate;
        // Calculate total monthly payment
        double monthlyPayment = monthlyRepaymentAmount + monthlyInterestPayment;

        List<AmortizationSchedule> schedule = new ArrayList<>();
        double totalInterest = 0;

        for (int month = 1; month <= numberOfPayments; month++) {
            double interestPayment = principal * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;

            // Adjust the last payment if the remaining balance goes below zero
            if (principal - principalPayment < 0) {
                principalPayment = principal;                
            }

            principal -= principalPayment;
            totalInterest += interestPayment;

            AmortizationSchedule entry = new AmortizationSchedule();
            entry.setMonth(month);
            entry.setPrincipalPayment(principalPayment);
            entry.setInterestPayment(interestPayment);
            entry.setRemainingBalance(principal);
            schedule.add(entry);

            // Stop calculation if remaining balance is zero
            if (principal <= 0) {
                break;
            }
        }

        LoanResponse response = new LoanResponse();
        response.setMonthlyPayment(monthlyPayment);
        response.setTotalInterest(totalInterest);
        response.setAmortizationSchedule(schedule);

        return response;
    }
}
