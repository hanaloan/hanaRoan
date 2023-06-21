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
    private Date startDate;
    private Date endDate;
}
