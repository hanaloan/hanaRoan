package com.Model;

public class EmployeeManagementReq {

    private String empId;
    private String empPw;
    private String empName;
    private int empAuthIdx;

    public EmployeeManagementReq(String empId, String empPw, String empName, int empAuthIdx) {
        this.empId = empId;
        this.empPw = empPw;
        this.empName = empName;
        this.empAuthIdx = empAuthIdx;
    }

    public EmployeeManagementReq() {

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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpAuthIdx() {
        return empAuthIdx;
    }

    public void setEmpAuthIdx(int empAuthIdx) {
        this.empAuthIdx = empAuthIdx;
    }



}