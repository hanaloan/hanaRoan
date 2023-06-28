package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReq {
    private int cusIdx;
    private String name;
    private String cusId;
    private String password;
    private String contactInfo;
    private int creditScore;
    private Long income;
    private String jobType;
}
