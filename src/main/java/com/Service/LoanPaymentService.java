package com.Service;

import com.DAO.LoanPaymentDAO;

import java.sql.SQLException;

public class LoanPaymentService {
    LoanPaymentDAO loanPaymentDAO = new LoanPaymentDAO();

    public void createLoanPayment(int lendId) throws SQLException {
        loanPaymentDAO.createLoanPayment(lendId);
    }
}
