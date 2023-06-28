package com.DAO;

import com.Model.*;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashBoardPVDao {

    public DashBoardPVRes getTotalPageViews(DashBoardPVReq dashBoardPVReq) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DashBoardPVRes dashBoardPVRes = new DashBoardPVRes();
        Map<String, Integer> totalPageViews = new LinkedHashMap<>();
        int maxPVValue = 0;
        int minPVValue = Integer.MAX_VALUE;
        Map<String, Integer> oneWeekUV = new LinkedHashMap<>();
        int maxUVValue = 0;
        int minUVValue = Integer.MAX_VALUE;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConnector.getConnection();

            String query = "SELECT date, total_views FROM total_page_views WHERE date >= DATE_SUB(?, INTERVAL 6 DAY) AND date <= ? ORDER BY date DESC";
            stmt = conn.prepareStatement(query);

            stmt.setString(1, dashBoardPVReq.getDate());
            stmt.setString(2, dashBoardPVReq.getDate());

            rs = stmt.executeQuery();

            while (rs.next()) {
                int totalViews = rs.getInt("total_views");
                String date = rs.getString("date");
                totalPageViews.put(date, totalViews);
                maxPVValue = Math.max(maxPVValue, totalViews);
                minPVValue = Math.min(minPVValue, totalViews);
            }
            dashBoardPVRes.setOneWeekTotalPV(totalPageViews);
            dashBoardPVRes.setMaxPVValue(maxPVValue);
            dashBoardPVRes.setMinPVValue(minPVValue);

            // 두번째 쿼리위해서 리소스 정리
            rs.close();
            stmt.close();


            String query2 = "SELECT visit_date, COUNT(*) as count FROM unique_visitors WHERE visit_date >= DATE_SUB(?, INTERVAL 6 DAY) AND visit_date <= ? GROUP BY visit_date ORDER BY visit_date DESC";
            stmt = conn.prepareStatement(query2);

            stmt.setString(1, dashBoardPVReq.getDate());
            stmt.setString(2, dashBoardPVReq.getDate());

            rs = stmt.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");
                String date = rs.getString("visit_date");
                oneWeekUV.put(date, count);
                maxUVValue = Math.max(maxUVValue, count);
                minUVValue = Math.min(minUVValue, count);
            }

            dashBoardPVRes.setOneWeekUV(oneWeekUV);
            dashBoardPVRes.setMaxUVValue(maxUVValue);
            dashBoardPVRes.setMinUVValue(minUVValue);

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

        return dashBoardPVRes;
    }
}