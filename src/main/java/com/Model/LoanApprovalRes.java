package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApprovalRes {
    private int cusIdx;
    private String name;
    private int lendIdx;
    private String loanTypeName;
    private String loanName;
    private int loanPeriod;
    private Date startDate;
    private Date endDate;
    private Long loanAmount;
    private String loanStatus;
    private int repaymentIdx;
    private Long paymentAmount;
    private String paymentStatus;
    private float loanInterestRate;

    public String getFormattedPaymentAmount() {
        long paymentAmount = this.loanAmount + (long) (this.loanAmount * this.loanPeriod * this.loanInterestRate / 100);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(paymentAmount);
    }

    public String getFormattedLoanAmount() {
        // Format loan amount with currency symbol and decimal places
        if (loanAmount != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            return  decimalFormat.format(loanAmount);
        }
        return null;
    }


}
