package com.DAO;

import com.Model.DisplayProduct;
import com.Model.DisplayProductListReq;
import com.Model.DisplayProductListRes;
import com.Model.ProductRes;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public ProductRes getProductById(int productIdx) throws SQLException {
        ProductRes product = null;
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

    private ProductRes extractProductFromResultSet(ResultSet rs) throws SQLException {
        ProductRes product = new ProductRes();
        product.setId(String.valueOf(rs.getInt("loan_idx")));
        product.setName(rs.getString("loan_name"));
        product.setDescription(rs.getString("loan_description"));
        product.setMinCredit(rs.getInt("min_credit"));
        product.setLendLimit(rs.getBigDecimal("lend_limit"));
        product.setLoanPeriod(rs.getInt("lend_period"));
        product.setInterestRate(rs.getBigDecimal("loan_interest_rate"));
        product.setOverdueInterestRate(rs.getBigDecimal("overdue_interest_rate"));
        return product;
    }
}