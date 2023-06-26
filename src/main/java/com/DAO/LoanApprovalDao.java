package com.DAO;


import com.config.secret.Secret;
import com.Model.*;
import com.Model.CustomerManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanApprovalDao {
    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;

    public List<CustomerManagement> getCustomerInfo(String loanTypeName, String loanStatus ,CustomerManagementReq customerManagementReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<CustomerManagement> customerManagementList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println(">>> db connection");
            // SQL 쿼리 작성
            String sql = "SELECT *\n" +
                    "FROM hanaroDB.customers AS c\n" +
                    "LEFT JOIN hanaroDB.loan_lend AS ll ON c.customer_idx = ll.customer_idx\n" +
                    "LEFT JOIN hanaroDB.loan_products AS lp ON ll.loan_idx = lp.loan_idx\n" +
                    "LEFT JOIN hanaroDB.loan_types AS lt ON lp.loan_type_id = lt.loan_type_idx\n" +
                    "LEFT JOIN hanaroDB.loan_payments AS lpm ON ll.lend_idx = lpm.loan_lend_idx\n" +
                    "WHERE ll.lend_idx IS NOT NULL";

            if (loanTypeName != null) {
                sql += " AND lt.loan_type_name = ?";
            }

            if (loanStatus != null) {
                sql += " AND ll.loan_status = ?";
            }

            stmt = conn.prepareStatement(sql);

            int paramIndex = 1;

            if (loanTypeName != null) {
                stmt.setString(paramIndex, loanTypeName);
                paramIndex++;
            }

            if (loanStatus != null) {
                stmt.setString(paramIndex, loanStatus);
            }

            // 쿼리 실행
            rs = stmt.executeQuery();

            while (rs.next()) {
                int cusId = rs.getInt("customer_idx");
                String name = rs.getString("name");
                String contactInfo = rs.getString("contact_info");
                String customerPassword = rs.getString("password");
                int lendId = rs.getInt("lend_idx");
                String startDate = String.valueOf(rs.getDate("start_date"));
                String endDate = String.valueOf(rs.getDate("start_date"));
                Long loanAmount = rs.getLong("loan_amount");
                String retrievedLoanStatus = rs.getString("loan_status");
                int repaymentId = rs.getInt("repayment_idx");
                Long paymentAmount = rs.getLong("payment_amount");
                String paymentStatus = rs.getString("payment_status");
                float overdueInterestRate = rs.getFloat("overdue_interest_rate");
                float loanInterestRate = rs.getFloat("loan_interest_rate");
                loanTypeName = rs.getString("loan_type_name");
                int lendPeriod = rs.getInt("lend_period");

                int creditScore = 0;
                Long income = null;
                String jobType = null;
                CustomerManagement customerManagement1 = new CustomerManagement(cusId, name, contactInfo, customerPassword, lendId, startDate, endDate, loanAmount, retrievedLoanStatus, repaymentId, paymentAmount, paymentStatus, overdueInterestRate, loanInterestRate,loanTypeName, lendPeriod, creditScore, income, jobType);

                customerManagementList.add(customerManagement1);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return customerManagementList;
    }
}