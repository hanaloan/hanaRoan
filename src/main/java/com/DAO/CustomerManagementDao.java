package com.DAO;


import com.config.secret.Secret;
import com.Model.*;
import com.Model.CustomerManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagementDao {
    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;

    public List<CustomerManagement> getCustomerInfo(CustomerManagementReq customerManagementReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<CustomerManagement> customerManagementList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println(">>> db connection");
            // SQL 쿼리 작성
            String sql = "SELECT * " +
                    "FROM hanaroDB.customers AS c " +
                    "LEFT JOIN hanaroDB.loan_lend AS ll ON c.customer_idx = ll.customer_idx " +
                    "LEFT JOIN hanaroDB.loan_payments AS lp ON ll.lend_idx = lp.loan_lend_idx";
            stmt = conn.prepareStatement(sql);
            // 쿼리 실행
            rs = stmt.executeQuery();

            while (rs.next()) {
                int cusId = rs.getInt("customer_idx");
                String name = rs.getString("name");
                String contactInfo = rs.getString("contact_info");
                String customerPassword = rs.getString("password");
                String startDate = String.valueOf(rs.getDate("start_date"));
                String endDate = String.valueOf(rs.getDate("start_date"));
                Long loanAmount = rs.getLong("loan_amount");
                String loanStatus = rs.getString("loan_status");
                Long paymentAmount = rs.getLong("payment_amount");
                String paymentStatus = rs.getString("payment_status");
                Long overdueInterestRate = rs.getLong("overdue_interest_rate");


                CustomerManagement customerManagement1 = new CustomerManagement(cusId, name, contactInfo, customerPassword, startDate, endDate, loanAmount, loanStatus, paymentAmount, paymentStatus, overdueInterestRate);

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