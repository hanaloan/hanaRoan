package com.Service;

import com.DAO.LoanApprovalDao;
import com.Model.LoanApprovalRes;

import java.sql.SQLException;
import java.util.List;

public class LoanApprovalService {
    private final LoanApprovalDao loanApprovalDao;

    public LoanApprovalService() {
        this.loanApprovalDao = new LoanApprovalDao();
    }

    public List<LoanApprovalRes> loanApprovalRes() throws SQLException {
        return loanApprovalDao.loanApprovalResList();
    }

    public List<LoanApprovalRes> getPendingLoanApprovalRes()  throws SQLException {
        return loanApprovalDao.getPendingLoanApprovalResList();
    }
}
