package com.DAO;

import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanPaymentDAO {
    public void createLoanPayment(int lendId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO hanaroDB.loan_payments (payment_amount, payment_status, loan_lend_idx) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setBigDecimal(1, BigDecimal.ZERO);
            stmt.setString(2, "in progress");
            stmt.setInt(3, lendId);

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
