package de.bmscs.bwslearning.dto;

/*
 * LoanRequest represents the request for a loan.
 */
public class LoanRequest {
    private double principal;
    private double annualInterestRate;
    private int years;
    private double annualRepaymentRate;

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getAnnualRepaymentRate() {
        return annualRepaymentRate;
    }

    public void setAnnualRepaymentRate(double annualRepaymentRate) {
        this.annualRepaymentRate = annualRepaymentRate;
    }
}