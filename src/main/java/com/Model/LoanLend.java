package com.Model;

public class LoanLend {
    private int customerIdx;
    private int loanIdx;
    private String startDate;
    private String endDate;
    private double loanAmount;
    private String loanStatus;

    public LoanLend(int customerIdx, int loanIdx, String startDate, String endDate, double loanAmount, String loanStatus) {
        this.customerIdx = customerIdx;
        this.loanIdx = loanIdx;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
    }

    public int getCustomerIdx() {
        return customerIdx;
    }

    public void setCustomerIdx(int customerIdx) {
        this.customerIdx = customerIdx;
    }

    public int getLoanIdx() {
        return loanIdx;
    }

    public void setLoanIdx(int loanIdx) {
        this.loanIdx = loanIdx;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}
