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

    public List<CustomerManagement> getCustomerInfo(String loanTypeName, CustomerManagementReq customerManagementReq) throws SQLException {
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
                    "LEFT JOIN hanaroDB.loan_payments AS lpm ON ll.lend_idx = lpm.loan_lend_idx\n";

            if (loanTypeName != null) {
                sql += " WHERE lt.loan_type_name = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, loanTypeName);
            } else {
                stmt = conn.prepareStatement(sql);
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
                String loanStatus = rs.getString("loan_status");
                int repaymentId = rs.getInt("repayment_idx");
                Long paymentAmount = rs.getLong("payment_amount");
                String paymentStatus = rs.getString("payment_status");
                Long overdueInterestRate = rs.getLong("overdue_interest_rate");
                loanTypeName = rs.getString("loan_type_name");
                int lendPeriod = rs.getInt("lend_period");


                CustomerManagement customerManagement1 = new CustomerManagement(cusId, name, contactInfo, customerPassword, lendId, startDate, endDate, loanAmount, loanStatus, repaymentId, paymentAmount, paymentStatus, overdueInterestRate, loanTypeName, lendPeriod);

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