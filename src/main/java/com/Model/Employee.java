package com.Model;

public class Employee {

    private String empId;
    private String empPw;
    private int empLevel;
    private String empName;

    public Employee(int empLevel) {
        this.empLevel = empLevel;
    }

    public Employee( String empName, int empLevel) {
        this.empName = empName;
        this.empLevel = empLevel;
    }

    public int getEmpLevel() {
        return empLevel;
    }

    public void setEmpLevel(int empLevel) {
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
