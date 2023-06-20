package com.Service;

import com.DAO.LoanLendDAO;
import com.Model.LoanLend;

import java.sql.SQLException;

public class LoanLendService {

    private LoanLendDAO loanLendDAO;

    public LoanLendService(LoanLendDAO loanLendDAO) {
        this.loanLendDAO = loanLendDAO;
    }

    public void applyLoan(LoanLend loanLend) {
        // 필요한 로직 수행

        // DAO를 사용하여 데이터베이스에 INSERT 실행
        try {
            loanLendDAO.insertLoanLend(loanLend);
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }
    }
}
