package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter

public class DisplayProductDetailRes {
    private int productId;
    private String loanName;
    private String loanDescription;
    private int minCredit;
    private BigDecimal lendLimit;
    private int lendPeriod;
    private BigDecimal loanInterestRate;
    private BigDecimal overdueInterestRate;
}
