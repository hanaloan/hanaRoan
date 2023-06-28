package com.Model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data

public class EmployeeManagementReq {

    private String empId;
    private String empPw;
    private String empName;
    private int empAuthIdx;

    private String empAuthName;


}