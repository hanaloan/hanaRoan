package com.DAO;

import com.Model.LoginUserRes;
import com.Model.RedisViewsReq;
import com.Model.RedisViewsRes;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.Map;

public class UpdateRedisViewsDao {

    public void updatePageViews(RedisViewsReq redisViewsReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnector.getConnection();

            // SQL 쿼리 작성
            String query = "UPDATE page_views SET page_views = page_views + ?, date = ? WHERE customer_idx = ?";
            stmt = conn.prepareStatement(query);

            // RedisPageViewsReq 객체에서 페이지 뷰 정보를 가져와서 쿼리문에 바인딩
            Map<Integer, Integer> pageViewsMap = redisViewsReq.getPageViewsMap();
            for (Map.Entry<Integer, Integer> entry : pageViewsMap.entrySet()) {
                Integer customerIdx = entry.getKey();
                Integer currentPageViews = entry.getValue();
                stmt.setInt(1, currentPageViews);
                stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                stmt.setInt(3, customerIdx);
                // 쿼리 실행
                stmt.executeUpdate();
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
    }

    public void insertUniqueVisitors(RedisViewsReq redisViewsReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnector.getConnection();

            // SQL 쿼리 작성
            String query = "INSERT IGNORE INTO unique_visitors (customer_idx, visit_date) VALUES (?, ?)";
            stmt = conn.prepareStatement(query);

            // RedisViewsReq 객체에서 페이지 뷰 정보를 가져와서 쿼리문에 바인딩
            Map<Integer, Integer> pageViewsMap = redisViewsReq.getPageViewsMap();
            for (Map.Entry<Integer, Integer> entry : pageViewsMap.entrySet()) {
                Integer customerIdx = entry.getKey();
                stmt.setInt(1, customerIdx);
                stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                // 쿼리 실행
                stmt.executeUpdate();
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
    }


    public RedisViewsRes checkDuplicationTotalViewsDate(RedisViewsReq redisViewsReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RedisViewsRes redisViewsRes = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnector.getConnection();

            // SQL 쿼리 작성
            String query = "SELECT CASE WHEN EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM total_page_views\n" +
                    "    WHERE date = ?\n" +
                    ") THEN TRUE ELSE FALSE END AS date_exists;";
            stmt = conn.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));

            // 쿼리 실행
            rs = stmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                int date_exists = rs.getInt("date_exists");
                if (date_exists == 1) {
                    redisViewsRes = new RedisViewsRes(true);
                }else{
                    redisViewsRes = new RedisViewsRes(false);
                }
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
    return redisViewsRes;
    }

    public void insertTotalPageViews(RedisViewsReq redisViewsReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnector.getConnection();

            // SQL 쿼리 작성
            String query = "insert into total_page_views (date, total_views) values (?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            stmt.setInt(2, redisViewsReq.getTotalPageViews());

            // 쿼리 실행
            stmt.executeUpdate();


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

    }

    public void updateTotalPageViews(RedisViewsReq redisViewsReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnector.getConnection();

            // SQL 쿼리 작성
            String query = "UPDATE total_page_views SET total_views = total_views + ? WHERE date = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, redisViewsReq.getTotalPageViews());
            stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));

            // 쿼리 실행
            stmt.executeUpdate();

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
    }
}
