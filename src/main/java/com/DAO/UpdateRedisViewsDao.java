package com.DAO;

import com.Model.RedisViewsReq;
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
            String query = "UPDATE page_views SET page_views = ? WHERE customer_idx = ?";
            stmt = conn.prepareStatement(query);

            // RedisPageViewsReq 객체에서 페이지 뷰 정보를 가져와서 쿼리문에 바인딩
            Map<Integer, Integer> pageViewsMap = redisViewsReq.getPageViewsMap();
            for (Map.Entry<Integer, Integer> entry : pageViewsMap.entrySet()) {
                Integer customerIdx = entry.getKey();
                Integer currentPageViews = entry.getValue();
                stmt.setInt(1, currentPageViews);
                stmt.setInt(2, customerIdx);
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
            String query = "INSERT IGNORE INTO unique_visitors (customer_id, visit_date) VALUES (?, ?)";
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

}
