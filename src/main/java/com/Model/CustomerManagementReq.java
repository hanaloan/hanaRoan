package com.Model;

public class CustomerManagementReq {
    private String name;
    private String contactInfo;
    private String cusId;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getCusId() {
        return cusId;
    }

    public String getPassword() {
        return password;
    }

    public void setLoanType(String loanType) {
    }
}
