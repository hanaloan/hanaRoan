package com.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// API 응답용이 아닌 다른 모델에서 사용할 목적이므로 Res 안붙임
public class LoginLoanProduct {
    private int loanId;
    private String loanImage;
    private String loanName;
    private float loanInterestRate;

}