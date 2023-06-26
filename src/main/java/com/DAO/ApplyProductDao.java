package com.DAO;

import com.Model.ApplyProductRes;
import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplyProductDao {
    public ApplyProductRes getApplyInfo(String productId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ApplyProductRes applyProductRes = null;
        int loanId = Integer.parseInt(productId);
        try{
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT loan_name, lend_period, lend_limit " +
                    "FROM loan_products WHERE loan_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, loanId);
            rs = stmt.executeQuery();
            if(rs.next()){
                String loanName = rs.getString("loan_name");
                BigDecimal lendLimit = rs.getBigDecimal("lend_limit");
                applyProductRes = new ApplyProductRes(loanId, loanName, lendLimit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
            if(rs != null) rs.close();
        }
        return applyProductRes;
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
