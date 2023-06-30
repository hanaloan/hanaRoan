package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ApplyCustomerRes {
    private String customerName;
    private String contactInfo;
    private int creditScore;
}
