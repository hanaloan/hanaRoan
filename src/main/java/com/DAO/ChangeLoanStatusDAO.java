package com.DAO;

import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeLoanStatusDAO {
    public void updateLoanStatus(String lendId, String loanStatus) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "UPDATE hanaroDB.loan_lend SET loan_status = ? WHERE lend_idx = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, loanStatus);
            stmt.setString(2, lendId);

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
