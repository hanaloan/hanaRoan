package com.Service;

import com.DAO.LoanPaymentDao;
import com.Model.DeductDueBalanceReq;

import java.sql.SQLException;

public class DeductBalanceService {
    private final LoanPaymentDao paymentDao;

    public DeductBalanceService() { this.paymentDao = new LoanPaymentDao(); }

    public void deductBalance(DeductDueBalanceReq deductDueBalanceReq) throws SQLException {
        paymentDao.deductBalance(deductDueBalanceReq);
    }
}
