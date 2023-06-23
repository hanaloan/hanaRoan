package com.Service;

import com.DAO.PaymentDao;
import com.Model.Payment;

import java.sql.SQLException;
import java.util.List;


public class LoanPaymentService {
    private final PaymentDao paymentDao;

    public LoanPaymentService(){this.paymentDao = new PaymentDao();}

    public List<Payment> getPayments(String option1, String option2) throws SQLException{
        return paymentDao.getPayments(option1, option2);
    }

    public void deductBalance(String paymentId) throws SQLException {
        paymentDao.deductBalance(paymentId);
    }

    public void handleOverdue(String paymentId) throws SQLException {
        paymentDao.handleOverdue(paymentId);
    }
}