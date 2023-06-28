package com.DAO;

import com.Model.Employee;
import com.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertEmployeeDao {

    Connection conn=null;
    PreparedStatement ps = null;

    public void insertEmployee(Employee employee) throws SQLException {
        try {
            conn = DatabaseConnector.getConnection();
            String sql="INSERT INTO hanaroDB.employees (id, password, name, authority_idx) VALUES (?, ?, ?, ?)";
            ps=conn.prepareStatement(sql);

            ps.setString(1, employee.getEmpId());
            ps.setString(2, employee.getEmpPw());
            ps.setString(3, employee.getEmpName());

            Integer empAuth = null;
            String s1=employee.getEmpLevelName();
            if (s1.equals("all")){
                empAuth=1;
            }
            else if (s1.equals("managingProducts")){
                empAuth=2;
            }
            else if (s1.equals("managingCustomers")){
                empAuth=3;
            }
            else if (s1.equals("readOnly")){
                empAuth=4;
            }
            else{
                empAuth=5;
            }

            ps.setInt(4, empAuth);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }
}
