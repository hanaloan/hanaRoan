package com.DAO;

import com.Model.Product;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection conn = null;
    PreparedStatement stmt = null;
    public List<Product> getProducts(String category) {
        List<Product> products = new ArrayList<>();
        try  {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products\n";

            if (!category.equals("*")) {
                sql += " WHERE loan_products.loan_type_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, category);
            } else {
                stmt = conn.prepareStatement(sql);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(String.valueOf(rs.getInt("loan_idx")));
                product.setName(rs.getString("loan_name"));
                product.setDescription(rs.getString("loan_description"));
                product.setMinCredit(rs.getInt("min_credit"));
                product.setLendLimit(rs.getBigDecimal("lend_limit"));
                product.setLoanPeriod(rs.getInt("lend_period"));
                product.setInterestRate(rs.getBigDecimal("loan_interest_rate"));
                product.setStartDate(rs.getDate("start_date"));
                product.setEndDate(rs.getDate("end_date"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getProductById(int productIdx) {
        Product product = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products WHERE loan_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productIdx);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(String.valueOf(rs.getInt("loan_idx")));
                product.setName(rs.getString("loan_name"));
                product.setDescription(rs.getString("loan_description"));
                product.setMinCredit(rs.getInt("min_credit"));
                product.setLendLimit(rs.getBigDecimal("lend_limit"));
                product.setLoanPeriod(rs.getInt("lend_period"));
                product.setInterestRate(rs.getBigDecimal("loan_interest_rate"));
                product.setStartDate(rs.getDate("start_date"));
                product.setEndDate(rs.getDate("end_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}