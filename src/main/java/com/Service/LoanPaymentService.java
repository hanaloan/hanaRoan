package com.Service;

import com.DAO.LoanPaymentDao;
import com.Model.Payment;

import java.sql.SQLException;
import java.util.List;


public class LoanPaymentService {
    private final LoanPaymentDao paymentDao;

    public LoanPaymentService() {
        this.paymentDao = new LoanPaymentDao();
    }

    public List<Payment> getPayments(String option1, String option2) throws SQLException {
        return paymentDao.getPayments(option1, option2);
    }

    public void deductBalance(String paymentId) throws SQLException {
        paymentDao.deductBalance(paymentId);
    }

    public void handleOverdue(String paymentId) throws SQLException {
        paymentDao.handleOverdue(paymentId);
    }

    LoanPaymentDao loanPaymentDAO = new LoanPaymentDao();

    public void createLoanPayment(int lendId) throws SQLException {
        loanPaymentDAO.createLoanPayment(lendId);
    }
}