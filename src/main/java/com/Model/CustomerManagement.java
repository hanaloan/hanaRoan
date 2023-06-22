package com.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CustomerManagement {
    private int cusId;
    private String name;
    private String contactInfo;
    private String password;
    private int lendId;
    private String startDate;
    private String endDate;
    private Long loanAmount;
    private String loanStatus;
    private int repaymentId;
    private Long paymentAmount;
    private String paymentStatus;
    private float overdueInterestRate;
    private float loanInterestRate;
    private String loanTypeName;
    private int lendPeriod;
    private int creditScore;
    private Long income;
    private String jobType;

    public Long calculatePaymentAmount() {
        return this.loanAmount + (long)(this.loanAmount * this.lendPeriod * this.loanInterestRate);
    }


    public CustomerManagement(int cusId, String name, String contactInfo, String password, int lendId, String startDate, String endDate, Long loanAmount, String loanStatus, int repaymentId, Long paymentAmount, String paymentStatus, float overdueInterestRate, float loanInterestRate, String loanTypeName, int lendPeriod, int creditScore, Long income, String jobType) {
        this.cusId=cusId;
        this.name=name;
        this.contactInfo=contactInfo;
        this.password=password;
        this.lendId=lendId;
        this.startDate=startDate;
        this.endDate=endDate;
        this.loanAmount=loanAmount;
        this.loanStatus=loanStatus;
        this.repaymentId=repaymentId;
        this.paymentAmount=paymentAmount;
        this.paymentStatus=paymentStatus;
        this.overdueInterestRate=overdueInterestRate;
        this.loanInterestRate=loanInterestRate;
        this.loanTypeName=loanTypeName;
        this.lendPeriod=lendPeriod;
    }

    public CustomerManagement(int cusId, String name, String contactInfo, String password, int creditScore, Long income, String jobType) {
        this.cusId=cusId;
        this.name=name;
        this.contactInfo=contactInfo;
        this.password=password;
        this.creditScore=creditScore;
        this.income=income;
        this.jobType=jobType;
    }
}