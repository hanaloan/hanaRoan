package com.Service;

import com.DAO.LoanPaymentDao;
import com.Model.HandleOverdueReq;

import java.sql.SQLException;

public class HandleOverdueService {
    private final LoanPaymentDao paymentDao;

    public HandleOverdueService() { this.paymentDao = new LoanPaymentDao(); }

    public void handleOverdue(HandleOverdueReq handleOverdueReq ) throws SQLException{
        paymentDao.handleOverdue(handleOverdueReq);
    }
}
