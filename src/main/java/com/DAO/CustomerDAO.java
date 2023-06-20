package com.DAO;

import com.Model.Customer;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {
    public void createCustomer(Customer customer) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "INSERT INTO hanaroDB.customers (name, contact_info, cus_id, password) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContactInfo());
            stmt.setString(3, customer.getCusId());
            stmt.setString(4, customer.getPassword());

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
