package com.DAO;
import com.Model.LoginUser;
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



    public boolean login(LoginUser loginUser) throws SQLException {
        System.out.println("dao_여기1");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        System.out.println("dao_여기2");
        boolean authenticated = false;
        System.out.println("dao_여기3");
        System.out.println(loginUser.getUsername());
        System.out.println(loginUser.getPassword());


        try {
            // 데이터베이스 연결
            System.out.println("dao_여기4");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            System.out.println("cccccc");
            // SQL 쿼리 작성
            String sql = "SELECT COUNT(*) FROM customers WHERE cus_id = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginUser.getUsername());
            stmt.setString(2, loginUser.getPassword());

            // 쿼리 실행
            rs = stmt.executeQuery();

            // 결과 처리
            if (rs.next()) {
                int count = rs.getInt(1);
                authenticated = count > 0; //행이 있으면 True 반환
            }
        } catch (ClassNotFoundException e){
            System.out.println("데이터베이스 연결 실패: " + e.getMessage());
        }

        return authenticated;
    }
}

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            System.out.println("성공?");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("여기옴?");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }