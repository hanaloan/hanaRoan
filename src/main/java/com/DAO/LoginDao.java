package com.DAO;

import com.Model.LoginCreditScoreModelReq;
import com.Model.LoginCreditScoreModelRes;
import com.Model.LoginUserReq;
import com.Model.LoginUserRes;
import com.config.secret.Secret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;

    public LoginUserRes login(LoginUserReq user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LoginUserRes loginUserRes = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 쿼리 작성
            String sql = "SELECT customer_Idx, name FROM customers WHERE cus_id = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            // 쿼리 실행
            rs = stmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                int customer_Idx = rs.getInt("customer_Idx");
                String name = rs.getString("name");
                loginUserRes = new LoginUserRes(name, customer_Idx, false);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("데이터베이스 연결 실패: " + e.getMessage());
        } finally {
            // 사용한 자원 정리
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
        return loginUserRes;
    }


    public LoginCreditScoreModelRes getCreditScore(LoginCreditScoreModelReq modelReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LoginCreditScoreModelRes modelRes = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 쿼리 작성
            String sql = "SELECT income, credit_score FROM credit_scores WHERE customer_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, modelReq.getCustomer_Idx());

            // 쿼리 실행
            rs = stmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                // CreditScoreModel 객체에 결과를 매핑하여 처리합니다.
                int income = rs.getInt("income");
                int creditScore = rs.getInt("credit_score");
                modelRes = new LoginCreditScoreModelRes(income, creditScore);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("데이터베이스 연결 실패: " + e.getMessage());
        } finally {
            // 사용한 자원 정리
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
        return modelRes;
    }
}
