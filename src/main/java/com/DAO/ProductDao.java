package com.DAO;

import com.Model.Product;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> getProducts(String category) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products";

            if (!category.equals("*")) {
                sql += " WHERE loan_products.loan_type_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, category);
            } else {
                stmt = conn.prepareStatement(sql);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return products;
    }

    public Product getProductById(int productIdx) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products WHERE loan_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productIdx);
            rs = stmt.executeQuery();

            if (rs.next()) {
                product = extractProductFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return product;
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
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
        return product;
    }
}