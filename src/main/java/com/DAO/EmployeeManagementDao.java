package com.DAO;

import com.Model.Employee;
import com.config.secret.Secret;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementDao {

    private static final String DB_URL = Secret.DB_URL;
    private static final String DB_USER = Secret.DB_USER;
    private static final String DB_PASSWORD = Secret.DB_PASSWORD;

    Connection conn= null; //변수 선언 DB와 연결
    PreparedStatement ps=null; // SQL문 담당
//    ResultSet rs=null; //select문에서 검색 결과를 담을 것

    public Employee currentEmployee(String cur_name) {

        Employee cur_emp = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 드라이버 JVM에 로딩
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT name, authority_idx FROM employees WHERE name='" + cur_name+"'";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {

                String level = rs.getString("authority_idx");
//                String name = rs.getString("name");
//                System.out.println(name);
                cur_emp = new Employee(level);
            }
//            req.setAttribute("list",alist);  // 주석3 jsp보내기위해 속성으로 저장함. 속성명 "list" 저장할 내용은 list
            conn.close();
            ps.close();
            rs.close();
            
            
            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cur_emp;
    }


    public List<Employee> selectEmployees() {
        List<Employee> employees = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 드라이버 JVM에 로딩
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //관리자 조회 화면 쿼리
            //주석1 DB에서 불러와서
//            String sql = "SELECT ";
//            sql+= " authority_idx, ";
//            sql+= " name ";
//            sql+= " FROM employees";
            String sql="SELECT authority_idx, name FROM employees";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {

//                emp.setEmpName(rs.getString(4));
//                emp.setEmpLevel(rs.getString(3));

                String level = rs.getString("authority_idx");
                String name = rs.getString("name");

                Employee emp = new Employee(level,name );
                employees.add(emp); // 주석2 리스트에 담아주고
            }
//            req.setAttribute("list",alist);  // 주석3 jsp보내기위해 속성으로 저장함. 속성명 "list" 저장할 내용은 list
            conn.close();
            ps.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employees;




    }

//    public static Connection getConnection() throws Exception{
//        Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 드라이버 JVM에 로딩
//        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//        return con; //연결하기
//    }

//    public String selectEmployee(HttpServletRequest request, HttpServletResponse response) {
//        ArrayList<Employee> alist = new ArrayList<Employee>();
//
//        try{
//            conn=getConnection();
//            //관리자 조회 화면 쿼리
//            //주석1 DB에서 불러와서
//            String sql = "SELECT ";
//            sql+= " authority_idx, ";
//            sql+= " name ";
//            sql+= " FROM employees";
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while(rs.next()) {
//                Employee emp = new Employee();
//                emp.setEmpName(rs.getString(4));
//                emp.setEmpLevel(rs.getString(3));
//
//                alist.add(emp); // 주석2 리스트에 담아주고
//            }
//            request.setAttribute("list",alist);  // 주석3 jsp보내기위해 속성으로 저장함. 속성명 "list" 저장할 내용은 list
//            conn.close();
//            ps.close();
//            rs.close();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return "EmployeeManagement.jsp";
//
//    }


}
