package com.Model;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String description;
    private int minCredit;
    private BigDecimal lendLimit;
    private int loanPeriod;
    private BigDecimal interestRate;

    private int idx;
    private String loanTypeName;
    private BigDecimal overdueInterestRate;


    public Product(Integer loanIdx, String loanName, String loanTypeName, Integer minCredit, BigDecimal lendLimit, Integer lendPeriod, String loanDescription, BigDecimal loanInterestRate, BigDecimal overdueInterestRate) {
        this.idx=loanIdx;
        this.name=loanName;
        this.loanTypeName=loanTypeName;
        this.minCredit=minCredit;
        this.lendLimit=lendLimit;
        this.loanPeriod=lendPeriod;
        this.description=loanDescription;
        this.interestRate=loanInterestRate;
        this.overdueInterestRate=overdueInterestRate;



    }

    public Product(String productName, String loanTypeName, String productInfo, BigDecimal interestRate, BigDecimal overdueInterestRate, BigDecimal limit, Integer period, Integer minCredit) {
        this.name=productName;
        this.loanTypeName=loanTypeName;
        this.minCredit=minCredit;
        this.lendLimit=limit;
        this.loanPeriod=period;
        this.description=productInfo;
        this.interestRate=interestRate;
        this.overdueInterestRate=overdueInterestRate;
    }
}
