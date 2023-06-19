package com.DAO;

import com.Model.Employee;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementDao {
    static Connection conn=null;
    static PreparedStatement ps=null; // SQL문 담당

    public static void insertEmployee(String empId, String empPw, String empAuthority, String empName) {

        Employee addEmp = null;
        try {
            conn = DatabaseConnector.getConnection(); //변수 선언 DB와 연결
            String empAuthorityIdx = null;

            String sql = "INSERT INTO employees (employee_idx, password, authority_idx, name) VALUES (?, ?, ?, ?)";
            System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.setString(1, empId);
            ps.setString(2, empPw);
            if(empAuthority=="all"){
                empAuthorityIdx="1";
            }
            System.out.println(empAuthorityIdx);
            ps.setString(3, empAuthorityIdx);
            ps.setString(4, empName);
//            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것
            ps.executeUpdate();

            conn.close();
            ps.close();
//            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the statement and connection
            DatabaseConnector.closeStatement(ps);
            DatabaseConnector.closeConnection(conn);
        }



    }

    public Employee currentEmployee(String cur_name) {

        Employee cur_emp = null;
        try {

//            Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 드라이버 JVM에 로딩
            conn = DatabaseConnector.getConnection(); //변수 선언 DB와 연결

            String sql = "SELECT name, authority_idx FROM employees WHERE name='" + cur_name+"'";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                String level = rs.getString("authority_idx");
                cur_emp = new Employee(level);
            }
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
//            Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 드라이버 JVM에 로딩
            conn = DatabaseConnector.getConnection();
            String sql="select e.name, a.authority_type from employees e join authority_types a on a.authority_idx = e.authority_idx";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                String level = rs.getString("a.authority_type");
                String name = rs.getString("e.name");

                Employee emp = new Employee(level,name );
                employees.add(emp); // 주석2 리스트에 담아주고
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }





}


