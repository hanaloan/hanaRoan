package com.Service;

import com.DAO.LoanPaymentDao;
import com.Model.PaymentReq;
import com.Model.PaymentRes;

import java.sql.SQLException;


public class LoanPaymentService {
    private final LoanPaymentDao paymentDao;

    public LoanPaymentService() {
        this.paymentDao = new LoanPaymentDao();
    }

    public PaymentRes getPayments(PaymentReq paymentReq) throws SQLException {
        return paymentDao.getPayments(paymentReq);
    }

    public void createLoanPayment(int lendId) throws SQLException {
        paymentDao.createLoanPayment(lendId);
    }
}