package com.DAO;

import com.Model.*;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class ProductDao {
    public DisplayProductListRes getProducts(DisplayProductListReq displayProductListReq) throws SQLException {
        DisplayProductListRes productListRes = null;
        ArrayList<DisplayProduct> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products \n" +
                        "WHERE loan_products.flag = 1";
            String category = displayProductListReq.getCategory();
            if (!category.equals("*")) {
                sql += " AND loan_products.loan_type_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, category);
            } else {
                stmt = conn.prepareStatement(sql);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                DisplayProduct product = new DisplayProduct(
                        rs.getInt("loan_idx"),
                        rs.getString("loan_name"),
                        rs.getString("loan_description")
                );
                products.add(product);
            }
            productListRes = new DisplayProductListRes(products);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return productListRes;
    }

    public DisplayProductDetailRes getProductDetail(DisplayProductDetailReq productDetailReq) throws SQLException {
        DisplayProductDetailRes productDetailRes = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            int loanIdx = productDetailReq.getProductId();
            conn = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM loan_products WHERE loan_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, loanIdx);
            rs = stmt.executeQuery();

            if (rs.next()) {
                productDetailRes = new DisplayProductDetailRes(
                        rs.getInt("loan_idx"),
                        rs.getString("loan_name"),
                        rs.getString("loan_description"),
                        rs.getInt("min_credit"),
                        rs.getBigDecimal("lend_limit"),
                        rs.getInt("lend_period"),
                        rs.getBigDecimal("loan_interest_rate"),
                        rs.getBigDecimal("overdue_interest_rate")
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return productDetailRes;
    }
}