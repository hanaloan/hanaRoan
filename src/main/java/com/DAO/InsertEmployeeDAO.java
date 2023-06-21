//package com.DAO;
//
//import com.Model.Employee;
//import com.utils.DatabaseConnector;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class InsertEmployeeDAO {
//
//    PreparedStatement ps = null;
//
//    public void insertEmployee(Employee employee){
//
//        Employee newEmployee=null;
//        try {
//            Connection conn = DatabaseConnector.getConnection();
//            String sql="INSERT INTO hanaroDB.employees (id, password, name, authority_idx) VALUES (?, ?, ?, ?) ";
//            ps=conn.prepareStatement(sql);
//
//            ps.setString(1, employee.getEmpId());
//            ps.setString(2, employee.getEmpPw());
//            ps.setString(3, employee.getEmpName());
//            ps.setInt(4, employee.getEmpLevel());
//
//            ps.executeUpdate();
//            ps.close();
//            conn.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
