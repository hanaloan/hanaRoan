package com.DAO;

import com.Model.ApplyProductRes;
import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ApplyProductDao {
    public ApplyProductRes getApplyInfo(String productId) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT loan_name, lend_period, lend_limit " +
                     "FROM loan_products WHERE loan_idx = ?")) {
            stmt.setString(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ApplyProductRes applyProductRes = new ApplyProductRes();
                    applyProductRes.setProductId(Integer.valueOf(productId));
                    applyProductRes.setProductName(rs.getString("loan_name"));
                    applyProductRes.setLendLimit(rs.getBigDecimal("lend_limit"));
                    return applyProductRes;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void applyProduct(String loanIdx, String customerIdx, String lendAmount) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT loan_lend\n" +
                         "SET customer_idx = ?, loan_idx = ?, loan_amount = ?\n";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.valueOf(customerIdx));
            stmt.setInt(2, Integer.valueOf(loanIdx));
            stmt.setBigDecimal(3, BigDecimal.valueOf(Double.parseDouble(lendAmount)));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        }
    }
}
