package com.Model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ApplyProductReq {
    private int customerIdx;
    private int loanIdx;
    private BigDecimal loanAmount;
}


