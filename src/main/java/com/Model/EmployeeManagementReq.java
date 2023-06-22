package com.Model;

public class EmployeeManagementReq {

    private String empId;
    private String empPw;
    private String empName;
    private int empAuthIdx;

    private String empAuthName;

    public EmployeeManagementReq(String empId, String empPw, String empName, String empAuthName) {
        this.empId = empId;
        this.empPw = empPw;
        this.empName = empName;
        this.empAuthName = empAuthName;
    }

    public EmployeeManagementReq() {

    }

    public String getEmpAuthName() {
        return empAuthName;
    }

    public void setEmpAuthName(String empAuthName) {
        this.empAuthName = empAuthName;
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