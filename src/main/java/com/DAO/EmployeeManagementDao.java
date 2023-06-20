package com.DAO;

import com.Model.Employee;
import com.Model.EmployeeManagementReq;
import com.Model.LoginForAdminReq;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementDao {

    PreparedStatement ps=null; // SQL문 담당

    public Employee currentEmployee(LoginForAdminReq username, String cur_name) {

        Employee cur_emp = null;
        try {

            System.out.println("currentEmployee 들어옴");
            Connection conn = DatabaseConnector.getConnection(); //변수 선언 DB와 연결

            String sql = "SELECT authority_idx FROM employees WHERE name='" + cur_name+"'";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                int level = rs.getInt("authority_idx");
                System.out.println(level);
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
            Connection conn = DatabaseConnector.getConnection();
            String sql="SELECT authority_idx, name FROM employees";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                int level = rs.getInt("authority_idx");
                String name = rs.getString("name");

                Employee emp = new Employee(level,name );
                employees.add(emp); // 주석2 리스트에 담아주고
                System.out.println("담았어?");
                System.out.println(employees);
            }
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



}


