package com.Service;

import com.DAO.CustomerManagementDao;
import com.DAO.LoanApprovalDao;
import com.Model.CustomerManagement;
import com.Model.CustomerManagementReq;

import java.sql.SQLException;
import java.util.List;

public class LoanApprovalService {
    private final LoanApprovalDao loanApprovalDao;

    public LoanApprovalService() {
        this.loanApprovalDao = new LoanApprovalDao();
    }

    public List<CustomerManagement> getCustomerInfo(String loanTypeName, CustomerManagementReq customerManagementReq) throws SQLException {
        return loanApprovalDao.getCustomerInfo(loanTypeName, customerManagementReq);
    }

}
