package com.utils;

import com.config.secret.Secret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        return connection;
    }
}
