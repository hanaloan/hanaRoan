package com.DAO;

import com.Model.Product;
import com.config.secret.Secret;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductDao {
    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;
    Connection conn = null;
    PreparedStatement stmt = null;
    public List<Product> getItems() {
        List<Product> products = new ArrayList<>();
        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM loan_products";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("loan_idx");
                String name = rs.getString("loan_name");
                String description = rs.getString("loan_description");

                Product item = new Product(id, name, description);
                products.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getItemById(int itemId) {
        Product product = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.prepareStatement("SELECT * FROM items WHERE id = ?");
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("loan_idx");
                String name = rs.getString("loan_name");
                String description = rs.getString("loan_description");

                product = new Product(id, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}