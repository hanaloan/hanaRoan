package com.Model;

import lombok.ToString;

@ToString
public class CustomerManagement {
    private int cusId;
    private String name;
    private String contactInfo;
    private String password;
    private String startDate;
    private String endDate;
    private Long loanAmount;
    private String loanStatus;
    private Long paymentAmount;
    private String paymentStatus;
    private Long overdueInterestRate;

    public CustomerManagement(int cusId, String name, String contactInfo, String password, String startDate, String endDate, Long loanAmount, String loanStatus, Long paymentAmount, String paymentStatus, Long overdueInterestRate) {
        this.cusId=cusId;
        this.name=name;
        this.contactInfo=contactInfo;
        this.password=password;
        this.startDate=startDate;
        this.endDate=endDate;
        this.loanAmount=loanAmount;
        this.loanStatus=loanStatus;
        this.paymentAmount=paymentAmount;
        this.paymentStatus=paymentStatus;
        this.overdueInterestRate=overdueInterestRate;
    }

    // Getter and Setter methods
    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getOverdueInterestRate() {
        return overdueInterestRate;
    }

    public void setOverdueInterestRate(Long overdueInterestRate) {
        this.overdueInterestRate = overdueInterestRate;
    }

}