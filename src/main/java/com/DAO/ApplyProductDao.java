package com.DAO;

import com.Model.ApplyCustomerRes;
import com.Model.ApplyProductRes;
import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplyProductDao {
    public ApplyProductRes getApplyProductInfo(String productId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ApplyProductRes applyProductRes = null;
        int loanId = Integer.parseInt(productId);
        try{
            conn = DatabaseConnector.getConnection();
            String productSql = "SELECT loan_name, loan_interest_rate, overdue_interest_rate, min_credit, lend_limit, lend_period \n" +
                    "FROM loan_products WHERE loan_idx = ?";
            stmt = conn.prepareStatement(productSql);

            stmt.setInt(1, loanId);
            rs = stmt.executeQuery();
            if(rs.next()){
                String loanName = rs.getString("loan_name");
                BigDecimal loanInterestRate = rs.getBigDecimal("loan_interest_rate");
                BigDecimal overdueInterestRate = rs.getBigDecimal("overdue_interest_rate");
                int minCredit = rs.getInt("min_credit");
                BigDecimal lendLimit = rs.getBigDecimal("lend_limit");
                int lendPeriod = rs.getInt("lend_period");
                applyProductRes = new ApplyProductRes(loanName, loanInterestRate, overdueInterestRate, minCredit,
                        lendLimit, lendPeriod);
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

    public ApplyCustomerRes getApplyCustomerInfo(String customerIdx) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ApplyCustomerRes applyCustomerRes = null;
        try{
            conn = DatabaseConnector.getConnection();
            String customerSql = "SELECT c.name, c.contact_info, cs.credit_score \n" +
                    "FROM customers as c \n" +
                    "JOIN credit_scores as cs ON c.customer_idx = cs.customer_idx \n" +
                    "WHERE c.customer_idx = ?";

            stmt = conn.prepareStatement(customerSql);
            stmt.setInt(1, Integer.parseInt(customerIdx));
            rs = stmt.executeQuery();
            if(rs.next()){
                String customerName = rs.getString("name");
                String contactInfo = rs.getString("contact_info");
                int creditScore = rs.getInt("credit_score");
                applyCustomerRes = new ApplyCustomerRes(customerName, contactInfo, creditScore);
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
        return applyCustomerRes;
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
