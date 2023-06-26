package com.DAO;


import com.config.secret.Secret;
import com.Model.CustomerRes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagementDao {
    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;

    public List<CustomerRes> getCustomerInfo() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<CustomerRes> customerResList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println(">>> db connection");
            // SQL 쿼리 작성
            String sql = "SELECT c.customer_idx, c.name, c.contact_info, c.cus_id, c.password, cs.credit_score, cs.income, j.job_type\n" +
                    "FROM hanaroDB.customers c\n" +
                    "LEFT JOIN hanaroDB.credit_scores cs ON c.customer_idx = cs.customer_idx\n" +
                    "LEFT JOIN hanaroDB.jobs j ON cs.job_idx = j.job_idx";

            stmt = conn.prepareStatement(sql);

            // 쿼리 실행
            rs = stmt.executeQuery();

            while (rs.next()) {
                int cusIdx = rs.getInt("customer_idx");
                String name = rs.getString("name");
                String cusId = rs.getString("cus_id");
                String password = rs.getString("password");
                String contactInfo = rs.getString("contact_info");
                int creditScore = rs.getInt("credit_score");
                Long income = rs.getLong("income");
                String jobType = rs.getString("job_type");

                CustomerRes customer = new CustomerRes(cusIdx, name, cusId, password, contactInfo, creditScore, income, jobType);

                customerResList.add(customer);
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
        return customerResList;
    }
}