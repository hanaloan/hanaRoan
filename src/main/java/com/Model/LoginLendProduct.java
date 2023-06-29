package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class LoginLendProduct {
    private String productName;
    private BigDecimal lendAmount;
    private String lendStatus;
    private Date startDate;
    private Date endDate;
}
