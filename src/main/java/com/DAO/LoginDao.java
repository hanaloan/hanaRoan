package com.DAO;

import com.Model.*;
import com.config.secret.Secret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


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

    public LoginRecommendationRes getRecoProduct(LoginRecommendationReq recoReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LoginRecommendationRes recoRes = null;
        List<LoginLoanProduct> recommendedProducts = new ArrayList<>();

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 쿼리1) 평균 pv 도출 쿼리 (pv 테이블에서 전체 pv 대비 해당 idx pv 비율 구하기 위함)
            // SQL 쿼리 작성
            String sql = "SELECT\n" +
                    "  (SELECT ROUND(AVG(page_views)) FROM page_views) AS avg_pv,\n" +
                    "  page_views\n" +
                    "  FROM\n" +
                    "  page_views\n" +
                    "  WHERE\n" +
                    "  customer_idx = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recoReq.getCustomer_Idx());

            rs = stmt.executeQuery();

            int avgPv = 0; //72
            int customerPv = 0; //100
            if (rs.next()) {
                avgPv = rs.getInt("avg_pv");
                customerPv = rs.getInt("page_views");
            }

            // 쿼리2) Recommendation 테이블에서 pv기준 top3 상품 도출쿼리 (신용도, 소득 감안)
            // 첫번째 PreparedStatement와 ResultSet 객체를 닫고, (PreparedStatement 객체는 한 번에 하나의 쿼리만 수행하기 때문에)
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }

            // customerPv >= avgPv 조건이 만족되면 새로운 쿼리를 실행
            System.out.println(recoReq.getCustomer_Idx() + "손님의 신용도 : " + recoReq.getCredit());
            float minRate = 0;
            float maxRate = 100;
            //4가지 경우의 수에 따른 max, min추천 이자율 구하기
            if (customerPv >= avgPv && recoReq.getIncome() < 24000000){
                minRate = 5.0f;
            }
            else if (customerPv >= avgPv && recoReq.getIncome() >= 24000000){
                minRate = 4.0f;
                maxRate = 5.0f;
            }
            else if (customerPv < avgPv && recoReq.getIncome() < 24000000){
                minRate = 3.0f;
                maxRate = 4.0f;
            }
            else if (customerPv < avgPv && recoReq.getIncome() >= 24000000){
                maxRate = 3.0f;
            }

            System.out.println(recoReq.getCustomer_Idx() + "손님의 이자율은 max :" + maxRate + "  min :" + minRate);

            sql = "SELECT\n" +
                    "    r.loan_id, l.loan_image, l.loan_name, l.loan_interest_rate\n" +
                    "FROM\n" +
                    "    recommendations r\n" +
                    "JOIN\n" +
                    "    loan_products l ON r.loan_id = l.loan_idx\n" +
                    "WHERE\n" +
                    "    r.customer_idx = ? AND l.min_credit <= ? AND l.loan_interest_rate >= ? AND l.loan_interest_rate < ?\n" +
                    "ORDER BY\n" +
                    "    r.loan_pv DESC\n" +
                    "LIMIT 3";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recoReq.getCustomer_Idx());
            stmt.setInt(2, recoReq.getCredit());
            stmt.setFloat(3, minRate);
            stmt.setFloat(4, maxRate);

            rs = stmt.executeQuery();
            ArrayList<Integer> loanIdList = new ArrayList<>();

            // LoginLoanProduct 모델을 recommendedProducts라는 리스트에 넣어서 축적
            while (rs.next()) {
                loanIdList.add(rs.getInt("loan_id"));

                LoginLoanProduct product = new LoginLoanProduct(
                        rs.getString("loan_image"),
                        rs.getString("loan_name"),
                        rs.getFloat("loan_interest_rate")
                );
                recommendedProducts.add(product);
            }
            System.out.println(recoReq.getCustomer_Idx() + "손님의 상위3개 추천상품의 idx는 :" + recommendedProducts);

            // 쿼리3) 최소 신용도를 충족시키는 상품 가져오기
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }

            // ★ NOT IN안에 그냥 loanIdList를 넣기에는 인젝션주입 공격에 취약하다 고로 아래와 같이 진행
            // 리스트가 비어있으면 구문이 NOT IN ()이니까 에러나므로 두가지 케이스로 진행
            if (loanIdList.isEmpty()) {
                sql = "SELECT loan_image, loan_name, loan_interest_rate " +
                        "FROM loan_products " +
                        "WHERE min_credit < ?";
            } else {
                String questionmarks = String.join(",", Collections.nCopies(loanIdList.size(), "?"));
                sql = "SELECT loan_image, loan_name, loan_interest_rate " +
                        "FROM loan_products " +
                        "WHERE min_credit < ? AND loan_idx NOT IN (" + questionmarks + ")";
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, recoReq.getCredit());

            // NOT IN안에 들어갈 ?를 파라메터인덱스 2번부터 넣어주기
            if (!loanIdList.isEmpty()) {
                for (int i = 0; i < loanIdList.size(); i++) {
                    stmt.setInt(i + 2, loanIdList.get(i));
                }
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                LoginLoanProduct product = new LoginLoanProduct(
                        rs.getString("loan_image"),
                        rs.getString("loan_name"),
                        rs.getFloat("loan_interest_rate")
                );
                recommendedProducts.add(product);
            }

            System.out.println(recoReq.getCustomer_Idx() + "손님에게 가능한 모든 추천상품의 idx는 :" + recommendedProducts);
            // recommendedProducts에 쌓아뒀던 정보들을 LoginRecommendationRes에 넣기
            recoRes = new LoginRecommendationRes(recommendedProducts);

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
        return recoRes;
    }

    public LoginForAdminRes loginAdmin(LoginForAdminReq loginForAdminReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LoginForAdminRes loginForAdminRes = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 쿼리 작성
            String sql = "SELECT e.name, e.employee_idx, a.authority_type\n" +
                    "FROM employees e\n" +
                    "JOIN authority_types a ON e.authority_idx = a.authority_idx\n" +
                    "WHERE e.id = ? AND e.password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginForAdminReq.getUsername());
            stmt.setString(2, loginForAdminReq.getPassword());

            // 쿼리 실행
            rs = stmt.executeQuery();
            // 결과 처리
            if (rs.next()) {
                String name = rs.getString("name");
                int idx = rs.getInt("employee_idx");
                String authType = rs.getString("authority_type");
                loginForAdminRes = new LoginForAdminRes(name, idx, authType, false);
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
        return loginForAdminRes;
    }
}
