package com.DAO;

import com.utils.DatabaseConnector;
import com.Model.CustomerRes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagementDao {

    private static final String CUSTOMER_INFO_QUERY =
            "SELECT c.customer_idx, c.name, c.contact_info, c.cus_id, c.password, cs.credit_score, cs.income, j.job_type, c.personal_id " +
                    "FROM hanaroDB.customers c " +
                    "LEFT JOIN hanaroDB.credit_scores cs ON c.customer_idx = cs.customer_idx " +
                    "LEFT JOIN hanaroDB.jobs j ON cs.job_idx = j.job_idx";
    
    public List<CustomerRes> getCustomerInfo() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CUSTOMER_INFO_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            return mapResultSetToCustomerResList(rs);
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
            throw new SQLException("Failed to connect to the database.", e);
        }
    }

    private List<CustomerRes> mapResultSetToCustomerResList(ResultSet rs) throws SQLException {
        List<CustomerRes> customerResList = new ArrayList<>();

        while (rs.next()) {
            int cusIdx = rs.getInt("customer_idx");
            String name = rs.getString("name");
            String cusId = rs.getString("cus_id");
            String password = rs.getString("password");
            String contactInfo = rs.getString("contact_info");
            int creditScore = rs.getInt("credit_score");
            long income = rs.getLong("income");
            String jobType = rs.getString("job_type");
            String personalId = rs.getString("personal_id");

            CustomerRes customer = new CustomerRes(cusIdx, name, cusId, password, contactInfo, creditScore, income, jobType, personalId);
            customerResList.add(customer);
        }

        return customerResList;
    }
}