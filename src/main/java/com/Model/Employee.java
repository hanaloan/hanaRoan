package com.Model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data

public class Employee {

    private  Integer empIdx;
    private String empId;
    private String empPw;
    private int empLevel;
    private String empName;
    private String empLevelName; //권한 이름



    public Employee(Integer empIdx, String empName, String empLevelName) {
        this.empIdx = empIdx;
        this.empName = empName;
        this.empLevelName = empLevelName;
    }

    public Employee(String empId, String empPw, String empName, String empLevelName) {
        this.empId = empId;
        this.empPw = empPw;
        this.empName = empName;
        this.empLevelName = empLevelName;
    }


    public Employee(int empLevel) {
        this.empLevel = empLevel;
    }

    public Employee( String empName, int empLevel) {
        this.empName = empName;
        this.empLevel = empLevel;
    }

    public Employee( String empName, String empLevelName) {
        this.empName = empName;
        this.empLevelName = empLevelName;
    }


}