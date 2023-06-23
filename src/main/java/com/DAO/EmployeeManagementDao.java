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

    public Employee currentEmployee(Integer cur_idx) {

        Employee cur_emp = null;
        try {

//            System.out.println("currentEmployee 들어옴");
            Connection conn = DatabaseConnector.getConnection(); //변수 선언 DB와 연결

            String sql = "SELECT e.name, a.authority_type FROM employees e JOIN authority_types a ON a.authority_idx = e.authority_idx WHERE e.employee_idx=" + cur_idx;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                String name=rs.getString("name");
//                int level = rs.getInt("authority_idx");
                String levelName=rs.getString("authority_type");
                cur_emp = new Employee(name, levelName);
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
        List<Employee> employeeManagementList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnector.getConnection();
//            String sql="SELECT name, authority_idx FROM employees";
            String sql="SELECT e.employee_idx, e.name, a.authority_type FROM employees e JOIN authority_types a ON a.authority_idx = e.authority_idx";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                Integer idx=rs.getInt("employee_idx");
                String name = rs.getString("name");
//                int level = rs.getInt("authority_idx");
                String levelName = rs.getString("authority_type");
//                System.out.println(levelName);
                Employee emp = new Employee(idx, name, levelName );
                employeeManagementList.add(emp); // 주석2 리스트에 담아주고
            }
            conn.close();
            ps.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employeeManagementList;
    }




}
