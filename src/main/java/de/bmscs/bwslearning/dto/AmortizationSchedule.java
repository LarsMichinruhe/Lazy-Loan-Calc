package de.bmscs.bwslearning.dto;

public class AmortizationSchedule {
    private int month;
    private double principalPayment;
    private double interestPayment;
    private double remainingBalance;
    
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getPrincipalPayment() {
		return principalPayment;
	}
	public void setPrincipalPayment(double principalPayment) {
		this.principalPayment = principalPayment;
	}
	public double getInterestPayment() {
		return interestPayment;
	}
	public void setInterestPayment(double interestPayment) {
		this.interestPayment = interestPayment;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

}
