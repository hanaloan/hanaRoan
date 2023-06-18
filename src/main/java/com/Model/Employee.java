package com.Model;

public class Employee {

    private String empId;
    private String empPw;
    private String empLevel;
    private String empName;

    public Employee(String empLevel) {
        this.empLevel = empLevel;
    }

    public Employee(String empLevel, String empName) {
        this.empLevel = empLevel;
        this.empName = empName;
    }



    public String getEmpLevel() {
        return empLevel;
    }

    public void setEmpLevel(String empLevel) {
        this.empLevel = empLevel;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPw() {
        return empPw;
    }

    public void setEmpPw(String empPw) {
        this.empPw = empPw;
    }
}
