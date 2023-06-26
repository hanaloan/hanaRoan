package com.Service;

import com.DAO.ChangeLoanStatusDAO;

import java.sql.SQLException;

public class ChangeLoanStatusService {
    private ChangeLoanStatusDAO changeLoanStatusDAO;

    public ChangeLoanStatusService() {
        changeLoanStatusDAO = new ChangeLoanStatusDAO();
    }

    public void updateLoanStatus(String lendId, String loanStatus, String lendPeriod) throws SQLException {
        changeLoanStatusDAO.updateLoanStatus(lendId, loanStatus, lendPeriod);
    }
}
