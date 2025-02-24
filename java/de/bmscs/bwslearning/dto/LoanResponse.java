package de.bmscs.bwslearning.dto;

import java.util.List;

public class LoanResponse {
   
	private double monthlyPayment;
    private double totalInterest;
    private List<AmortizationSchedule> amortizationSchedule;

    public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public double getTotalInterest() {
		return totalInterest;
	}
	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}
	public List<AmortizationSchedule> getAmortizationSchedule() {
		return amortizationSchedule;
	}
	public void setAmortizationSchedule(List<AmortizationSchedule> amortizationSchedule) {
		this.amortizationSchedule = amortizationSchedule;
	}
}
