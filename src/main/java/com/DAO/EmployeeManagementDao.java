package com.DAO;

import com.Model.EmployeeRes;
import com.Model.EmployeeManagementReq;
import com.Model.LoginForAdminReq;
import com.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementDao {

    PreparedStatement ps=null; // SQL문 담당

    public EmployeeRes currentEmployee(Integer cur_idx) throws SQLException {

        EmployeeRes cur_emp = null;
        Connection conn = null;
        ResultSet rs=null;
        try {

            conn = DatabaseConnector.getConnection(); //변수 선언 DB와 연결

            String sql = "SELECT e.name, a.authority_type FROM employees e JOIN authority_types a ON a.authority_idx = e.authority_idx WHERE e.employee_idx=" + cur_idx;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                String name=rs.getString("name");
                String levelName=rs.getString("authority_type");
                cur_emp = new EmployeeRes(name, levelName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return cur_emp;
    }


    public List<EmployeeRes> selectEmployees() {
        List<EmployeeRes> employeeManagementList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnector.getConnection();
            String sql="SELECT e.employee_idx, e.name, a.authority_type FROM employees e JOIN authority_types a ON a.authority_idx = e.authority_idx";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //select문에서 검색 결과를 담을 것

            while(rs.next()) {
                Integer idx=rs.getInt("employee_idx");
                String name = rs.getString("name");
                String levelName = rs.getString("authority_type");
                EmployeeRes emp = new EmployeeRes(idx, name, levelName );
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
