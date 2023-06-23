package com.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DecimalFormat;

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

    public String getFormattedPaymentAmount() {
        long paymentAmount = this.loanAmount + (long) (this.loanAmount * this.lendPeriod * this.loanInterestRate);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(paymentAmount);
    }




    public String getFormattedContactInfo() {
        // Format the contact info based on the pattern
        // e.g., "01012345678" -> "010-1234-5678"
        if (contactInfo != null && !contactInfo.isEmpty()) {
            if (contactInfo.matches("\\d{3}-?\\d{3,4}-?\\d{4}")) {
                return contactInfo.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
            }
        }
        return contactInfo;
    }

    public String getFormattedLoanAmount() {
        // Format loan amount with currency symbol and decimal places
        if (loanAmount != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            return  decimalFormat.format(loanAmount);
        }
        return null;
    }

    public String getFormattedIncome() {
        // Format income with commas as thousands separators
        if (income != null) {
            return String.format("%,d", income);
        }
        return null;
    }

    public CustomerManagement(int cusId, String name, String contactInfo, String password, int lendId, String startDate, String endDate, Long loanAmount, String loanStatus, int repaymentId, Long paymentAmount, String paymentStatus, float overdueInterestRate, float loanInterestRate, String loanTypeName, int lendPeriod, int creditScore, Long income, String jobType) {
        this.cusId = cusId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
        this.lendId = lendId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
        this.repaymentId = repaymentId;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.overdueInterestRate = overdueInterestRate;
        this.loanInterestRate = loanInterestRate;
        this.loanTypeName = loanTypeName;
        this.lendPeriod = lendPeriod;
        this.creditScore = creditScore;
        this.income = income;
        this.jobType = jobType;
    }

    public CustomerManagement(int cusId, String name, String contactInfo, String password, int creditScore, Long income, String jobType) {
        this.cusId = cusId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
        this.creditScore = creditScore;
        this.income = income;
        this.jobType = jobType;
    }
}
