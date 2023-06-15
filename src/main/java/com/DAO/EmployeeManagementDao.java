package com.DAO;

import com.Model.Employee;
import com.config.secret.Secret;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeManagementDao {

    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;



    static Connection conn= null; //
    static PreparedStatement ps=null;
    static ResultSet rs=null;

    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return con;
    }


    public static String selectEmployee(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Employee> list = new ArrayList<Employee>();

        try{
            conn=getConnection();
            //관리자 조회 화면 쿼리
            //주석1 DB에서 불러와서
            String sql = "SELECT ";
            sql+= " authority_idx, ";
            sql+= " name ";
            sql+= " FROM employees";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Employee emp = new Employee();
                emp.setEmpLevel(rs.getString(1));
                emp.setEmpName(rs.getString(2));

                list.add(emp); // 주석2 리스트에 담아주고
            }
            request.setAttribute("list",list);  // 주석3 jsp보내기위해 속성으로 저장함. 속성명 "list" 저장할 내용은 list
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "EmployeeManagement.jsp";

    }
}
