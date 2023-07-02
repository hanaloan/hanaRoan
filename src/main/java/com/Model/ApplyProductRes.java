package com.Model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ApplyProductRes {
    private String loanName;
    private BigDecimal loanInterestRate;
    private BigDecimal overdueInterestRate;
    private int minCredit;
    private BigDecimal lendLimit;
    private int lendPeriod;
}