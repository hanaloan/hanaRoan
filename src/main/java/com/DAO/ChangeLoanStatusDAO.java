package com.DAO;


import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ChangeLoanStatusDAO {
    public void updateLoanStatus(String lendId, String loanStatus, String lendPeriod) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "UPDATE hanaroDB.loan_lend SET loan_status = ?, start_date = ?, end_date = ? WHERE lend_idx = ?";
            stmt = conn.prepareStatement(sql);

            LocalDate startDate = LocalDate.now();
            int lendPeriodYears = Integer.parseInt(lendPeriod);
            LocalDate endDate = startDate.plusYears(lendPeriodYears).minusDays(1);

            stmt.setString(1, loanStatus);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));

            stmt.setString(4, lendId);

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
