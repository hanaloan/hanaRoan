package com.DAO;

import com.Model.Product;
import com.utils.DatabaseConnector;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection conn = null;
    PreparedStatement stmt = null;
    public List<Product> getItems() {
        List<Product> products = new ArrayList<>();
        try  {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("loan_idx");
                String name = rs.getString("loan_name");
                String description = rs.getString("loan_description");
                int minCredit = rs.getInt("min_credit");
                BigDecimal lendLimit = rs.getBigDecimal("lend_limit");
                int loanPeriod = rs.getInt("lend_period");
                BigDecimal interestRate = rs.getBigDecimal("loan_interest_rate");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                Product product = new Product(id, name, description, minCredit, lendLimit, loanPeriod, interestRate, startDate, endDate);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getItemById(int productIdx) {
        Product product = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products WHERE loan_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productIdx);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("loan_idx");
                String name = rs.getString("loan_name");
                String description = rs.getString("loan_description");
                int minCredit = rs.getInt("min_credit");
                BigDecimal lendLimit = rs.getBigDecimal("lend_limit");
                int loanPeriod = rs.getInt("lend_period");
                BigDecimal interestRate = rs.getBigDecimal("loan_interest_rate");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                product = new Product(id, name, description, minCredit, lendLimit, loanPeriod, interestRate, startDate, endDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}