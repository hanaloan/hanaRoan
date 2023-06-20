package com.DAO;

import com.Model.LoanLend;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanLendDAO {
    public void insertLoanLend(LoanLend loanLend) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO hanaroDB.loan_lend (customer_idx, loan_idx, start_date, end_date, loan_amount, loan_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, loanLend.getCustomerIdx());
            stmt.setInt(2, loanLend.getLoanIdx());
            stmt.setString(3, loanLend.getStartDate());
            stmt.setString(4, loanLend.getEndDate());
            stmt.setDouble(5, loanLend.getLoanAmount());
            stmt.setString(6, loanLend.getLoanStatus());


            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}