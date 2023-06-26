package com.Model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ApplyProductRes {
    private int productId;
    private String productName;
    private BigDecimal lendLimit;
}