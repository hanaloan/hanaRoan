package com.Model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data

public class EmployeeRes {

    private  Integer empIdx;
    private String empId;
    private String empPw;
    private int empLevel;
    private String empName;
    private String empLevelName; //권한 이름



    public EmployeeRes(Integer empIdx, String empName, String empLevelName) {
        this.empIdx = empIdx;
        this.empName = empName;
        this.empLevelName = empLevelName;
    }

    public EmployeeRes(String empId, String empPw, String empName, String empLevelName) {
        this.empId = empId;
        this.empPw = empPw;
        this.empName = empName;
        this.empLevelName = empLevelName;
    }


    public EmployeeRes(int empLevel) {
        this.empLevel = empLevel;
    }

    public EmployeeRes( String empName, int empLevel) {
        this.empName = empName;
        this.empLevel = empLevel;
    }

    public EmployeeRes( String empName, String empLevelName) {
        this.empName = empName;
        this.empLevelName = empLevelName;
    }


}